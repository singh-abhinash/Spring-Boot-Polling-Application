package com.abhinash.pollingApp.springBootPollingApplication.model;

import org.springframework.security.core.GrantedAuthority;

/**
 * Created by Abhinash Singh - 2024
 */

public enum Role implements GrantedAuthority {
    ROLE_USER,
    ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
