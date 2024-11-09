package com.abhinash.pollingApp.springBootPollingApplication.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import com.abhinash.pollingApp.springBootPollingApplication.model.Dish;

/**
 * Created by Abhinash Singh - 2024
 */

@Transactional(readOnly = true)
public interface DishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Override
    Dish save(Dish dish);

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);
}
