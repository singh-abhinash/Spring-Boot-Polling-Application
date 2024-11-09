package com.abhinash.pollingApp.springBootPollingApplication.util.exceptions;

/**
 * Created by Abhinash Singh - 2024
 */

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
