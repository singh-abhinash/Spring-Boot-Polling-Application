package com.abhinash.pollingApp.springBootPollingApplication.util.exceptions;

import org.springframework.lang.NonNull;

/**
 * Created by Abhinash Singh - 2024
 */

public class IllegalRequestDataException extends RuntimeException {
    public IllegalRequestDataException(@NonNull String msg) {
        super(msg);
    }
}