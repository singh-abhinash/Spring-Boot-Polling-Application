package com.abhinash.pollingApp.springBootPollingApplication.service;

import static com.abhinash.pollingApp.springBootPollingApplication.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;

import javax.cache.annotation.CacheResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhinash.pollingApp.springBootPollingApplication.model.Restaurant;
import com.abhinash.pollingApp.springBootPollingApplication.repository.datajpa.RestaurantRepository;
import com.abhinash.pollingApp.springBootPollingApplication.util.exceptions.NotFoundException;

/**
 * Created by Abhinash Singh - 2024
 */

@Service
public class RestaurantServiceImp implements RestaurantService {

    private static Logger log = LoggerFactory.getLogger(RestaurantServiceImp.class);

    @Autowired
    private RestaurantRepository repository;

    @Override
    public Restaurant retrieve(int id) throws NotFoundException {
        log.info("restaurant with id {}", id);
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        log.info("delete restaurant with id {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    @CacheResult(cacheName = "restaurants")
    public Restaurant create(Restaurant newRestaurant) {
        log.info("create restaurant {}", newRestaurant);
        return repository.save(newRestaurant);
    }

    @Override
    @CacheResult(cacheName = "restaurants")
    public void update(Restaurant newRestaurant, int id) throws IllegalArgumentException, NotFoundException {
        log.info("update restaurant {} with id {}", newRestaurant, id);
        checkNotFoundWithId(repository.save(newRestaurant), id);
    }

    @Override
    public List<Restaurant> findByTitle(String title) {
        log.info("find restaurants by title ={}", title);
        return repository.findByTitle(title);
    }

    @Override
    @CacheResult(cacheName = "restaurants")
    public List<Restaurant> getRestaurantsWithDishes() {
        log.info("get all restaurants with dishes {}");
        return repository.getRestaurantsWithDishes();
    }

    @Override
    public List<Restaurant> getAll() {
        log.info("get all restaurants {}");
        return repository.getAll();
    }
}
