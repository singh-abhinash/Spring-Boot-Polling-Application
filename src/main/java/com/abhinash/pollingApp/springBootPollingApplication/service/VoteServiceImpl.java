package com.abhinash.pollingApp.springBootPollingApplication.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.abhinash.pollingApp.springBootPollingApplication.model.Vote;
import com.abhinash.pollingApp.springBootPollingApplication.repository.datajpa.RestaurantRepository;
import com.abhinash.pollingApp.springBootPollingApplication.repository.datajpa.UserRepository;
import com.abhinash.pollingApp.springBootPollingApplication.repository.datajpa.VoteRepository;
import com.abhinash.pollingApp.springBootPollingApplication.to.VoteTo;

/**
 * Created by Abhinash Singh - 2024
 */

@Service
public class VoteServiceImpl implements VoteService {
    private static final Logger log = LoggerFactory.getLogger(VoteServiceImpl.class);

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RestaurantRepository restaurantRepo;

    @Autowired
    private VoteRepository voteRepo;

    @Override
    public Optional<Vote> getTodayUserVote(int userId, LocalDate date) {
        log.info("get today={} vote, user with id ={} ", date, userId);
        return voteRepo.getTodayUserVote(userId, date);
    }

    @Transactional
    @Override
    public VoteTo create (int userId, int restaurantId) {
        Vote todayVote = getTodayUserVote(userId, LocalDate.now()).orElse(null);
        if (todayVote!=null){
            throw  new DataIntegrityViolationException("");
        }
       Vote newVote = new Vote(userRepo.getOne(userId),
               restaurantRepo.getOne(restaurantId),
               LocalDate.now());
        VoteTo voteTo = new VoteTo(newVote, true);
        voteRepo.save(voteTo.getVote());
        log.info("create vote{} ", voteTo);
        return voteTo;
    }

    @Transactional
    @Override
    public VoteTo createOrUpdate(int userId, int restaurantId) {
        Vote newVote = new Vote(userRepo.getOne(userId),
                restaurantRepo.getOne(restaurantId),
                LocalDate.now());
        VoteTo todayVote = voteRepo.getTodayUserVote(userId, LocalDate.now())
                .map(v->  {
                    v.setRestaurant(restaurantRepo.getOne(restaurantId));
                    return new VoteTo(v, false);
                })
                .orElseGet(() -> new VoteTo(newVote, true));
        voteRepo.save(todayVote.getVote());
        log.info("update vote{}", todayVote);
        return todayVote;
    }

	@Override
	public List<Vote> getVotesBetween(int userId, LocalDate startDate, LocalDate endDate) {
		log.info("get voteHistory for user with id ={}", userId);
		return voteRepo.getVotesBetween(userId, startDate!=null ? startDate : LocalDate.of(1, 1, 1),
				endDate!=null? endDate : LocalDate.of(3000, 1, 1));
	}
}