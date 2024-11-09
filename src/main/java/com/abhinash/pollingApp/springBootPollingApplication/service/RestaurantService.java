package com.abhinash.pollingApp.springBootPollingApplication.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;

import com.abhinash.pollingApp.springBootPollingApplication.model.Restaurant;
import com.abhinash.pollingApp.springBootPollingApplication.util.exceptions.NotFoundException;

/**
 * Created by Abhinash Singh - 2024
 */

public interface RestaurantService {

    @Secured({"ROLE_ADMIN"})
    Restaurant retrieve(int id) throws NotFoundException;

    @Secured({"ROLE_ADMIN"})
    void delete(int id) throws NotFoundException;

    @Secured({"ROLE_ADMIN"})
    Restaurant create(Restaurant restaurant);

    @Secured({"ROLE_ADMIN"})
    void update(Restaurant restaurant, int id) throws NotFoundException;

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    List<Restaurant> findByTitle(String title);

    @Secured({"ROLE_ADMIN", "ROLE_USER"})
    List<Restaurant> getRestaurantsWithDishes();

    @Secured({"ROLE_ADMIN"})
    List<Restaurant> getAll();
}
