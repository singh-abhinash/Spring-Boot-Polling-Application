package com.abhinash.pollingApp.springBootPollingApplication.web.user;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhinash.pollingApp.springBootPollingApplication.model.Restaurant;
import com.abhinash.pollingApp.springBootPollingApplication.model.Vote;
import com.abhinash.pollingApp.springBootPollingApplication.service.VoteService;
import com.abhinash.pollingApp.springBootPollingApplication.to.VoteTo;
import com.abhinash.pollingApp.springBootPollingApplication.web.SecurityUtil;

/**
 * Created by Abhinash Singh - 2024
 */

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
	private static final Logger log = LoggerFactory.getLogger(VoteController.class);
	public static final String REST_URL = "/rest/vote";

	private static final LocalTime TIME_EXPIRED = LocalTime.of(11, 0);

	@Autowired
	private VoteService service;

	/*
	 * User vote code: 201 Created, 200 Updated, 409 Conflict
	 */
	@PostMapping(value = "/{id}")
	public ResponseEntity<Restaurant> vote(@PathVariable("id") int restaurantId) {
		int userId = SecurityUtil.authUserId();
		boolean acceptVote = LocalTime.now().isBefore(TIME_EXPIRED);
		// user can update his vote until 11 a.m
		VoteTo newVote = acceptVote ? service.createOrUpdate(userId, restaurantId)
				: service.create(userId, restaurantId);
		log.info("user with {} id voted for restaurant {}", userId, restaurantId);
		return new ResponseEntity<>(newVote.getVote().getRestaurant(),
				newVote.isCreated() ? HttpStatus.CREATED : (acceptVote ? HttpStatus.OK : HttpStatus.CONFLICT));
	}

	@GetMapping
	public Vote getUserVoteForDate(
			@RequestParam(value = "date", required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
		int userId = SecurityUtil.authUserId();
		log.info("get vote for user with id ={}, day ={}", userId, date);
		return service.getTodayUserVote(userId, date).get();
	}

	@GetMapping("/history")
	public List<Vote> getVotesBetween(
			@RequestParam(value = "startDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
			@RequestParam(value = "endDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
		int userId = SecurityUtil.authUserId();
		log.info("get voteHistory for user with id ={}", userId);
		return service.getVotesBetween(userId, startDate, endDate);
	}
}
