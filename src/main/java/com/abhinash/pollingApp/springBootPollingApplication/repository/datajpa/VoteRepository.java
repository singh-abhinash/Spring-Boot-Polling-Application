package com.abhinash.pollingApp.springBootPollingApplication.repository.datajpa;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.abhinash.pollingApp.springBootPollingApplication.model.Vote;

/**
 * Created by Abhinash Singh - 2024
 */

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    @Override
    @Transactional
    Vote save(Vote vote);

    @Transactional(readOnly = true)
    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId AND v.date=:date")
    Optional<Vote> getTodayUserVote(@Param("userId") int userId, @Param("date") LocalDate date);

    @Query("SELECT v from Vote v WHERE v.user.id=:userId AND v.date BETWEEN :startDate AND :endDate ORDER BY v.date DESC")
    List<Vote> getVotesBetween(@Param("userId") int userId, @Param("startDate") LocalDate startDate, @Param("endDate") LocalDate endDate);
}
