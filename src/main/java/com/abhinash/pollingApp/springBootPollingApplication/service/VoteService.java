package com.abhinash.pollingApp.springBootPollingApplication.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.security.access.annotation.Secured;

import com.abhinash.pollingApp.springBootPollingApplication.model.Vote;
import com.abhinash.pollingApp.springBootPollingApplication.to.VoteTo;

/**
 * Created by Abhinash Singh - 2024
 */

public interface VoteService {

    Optional<Vote> getTodayUserVote(int userId, LocalDate date);

    @Secured({"ROLE_USER"})
    VoteTo create(int userId, int restaurantId);

    @Secured({"ROLE_USER"})
    VoteTo createOrUpdate(int userId, int restaurantId);

    @Secured({"ROLE_USER"})
    List<Vote> getVotesBetween(int userId, LocalDate startDate, LocalDate endDate);
}
