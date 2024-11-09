package com.abhinash.pollingApp.springBootPollingApplication.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.abhinash.pollingApp.springBootPollingApplication.AuthorizedUser;
import com.abhinash.pollingApp.springBootPollingApplication.model.User;
import com.abhinash.pollingApp.springBootPollingApplication.repository.datajpa.UserRepository;

/**
 * Created by Abhinash Singh - 2024
 */

@Service
public class UserServiceSecurity implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserServiceSecurity.class);

    @Autowired
    private UserRepository repository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.findByEmail(email.trim().toLowerCase());
        log.debug("Authenticating {}", email);
        if (user == null) {
            throw new UsernameNotFoundException("User with email" + email + " not found");
        }
        return new AuthorizedUser(user);
    }
}

