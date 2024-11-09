package com.abhinash.pollingApp.springBootPollingApplication.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.abhinash.pollingApp.springBootPollingApplication.model.User;

/**
 * Created by Abhinash Singh - 2024
 */

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT u FROM User u LEFT JOIN u.roles WHERE u.email=:email")
    User findByEmail(@Param("email") String email);
}
