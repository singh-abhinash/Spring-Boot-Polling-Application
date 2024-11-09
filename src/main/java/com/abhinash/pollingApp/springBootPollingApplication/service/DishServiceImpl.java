package com.abhinash.pollingApp.springBootPollingApplication.service;

import static com.abhinash.pollingApp.springBootPollingApplication.util.ValidationUtil.checkNotFoundWithId;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhinash.pollingApp.springBootPollingApplication.model.Dish;
import com.abhinash.pollingApp.springBootPollingApplication.repository.datajpa.DishRepository;
import com.abhinash.pollingApp.springBootPollingApplication.util.exceptions.NotFoundException;

/**
 * Created by Abhinash Singh - 2024
 */

@Service
public class DishServiceImpl implements DishService {

    private static Logger log = LoggerFactory.getLogger(RestaurantServiceImp.class);

    @Autowired
    private DishRepository repository;

    @Override
    public List<Dish> getAll() {
        log.info("get all dishes {}");
        return repository.findAll();
    }

    @Override
    public Dish retrieve(int id) throws NotFoundException {
        log.info("get dish with id {}", id);
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        log.info("delete dish with id {}", id);
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Dish create(Dish newDish) {
        log.info("create dish {}", newDish);
        return repository.save(newDish);
    }

    @Override
    public void update(Dish newDish, int id) throws NotFoundException {
        log.info("update dish {} with id {}", newDish, id);
        checkNotFoundWithId(repository.save(newDish), id);
    }
}
