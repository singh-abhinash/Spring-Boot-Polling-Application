package com.abhinash.pollingApp.springBootPollingApplication.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.abhinash.pollingApp.springBootPollingApplication.AuthorizedUser;

import static java.util.Objects.requireNonNull;

/**
 * Created by Abhinash Singh - 2024
 */

public class SecurityUtil {

    private SecurityUtil() {  }

    public static AuthorizedUser safeGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        Object principal = auth.getPrincipal();
        return (principal instanceof AuthorizedUser) ? (AuthorizedUser) principal : null;
    }

    public static AuthorizedUser get() {
        AuthorizedUser user = safeGet();
        requireNonNull(user, "No authorized user found");
        return user;
    }

    public static int authUserId() {
        return get().getUser().getId();
    }

}
