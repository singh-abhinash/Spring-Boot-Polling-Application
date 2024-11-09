package com.abhinash.pollingApp.springBootPollingApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created by Abhinash Singh - 2024
 */

@SpringBootApplication
@EnableCaching
public class SpringBootPollingApplication {

    public static void main(String[] args) {
      SpringApplication.run(SpringBootPollingApplication.class, args);
    }
}
