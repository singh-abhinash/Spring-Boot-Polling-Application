package com.abhinash.pollingApp.springBootPollingApplication.web.json;

import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.RESTAURANTS;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.RESTAURANT_2;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.assertMatch;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.abhinash.pollingApp.springBootPollingApplication.model.Restaurant;

/**
 * Created by Abhinash Singh - 2024
 */

public class JsonUtilTest {

    @Test
    public void testReadWriteValue() throws Exception {
        String json = JsonUtil.writeValue(RESTAURANT_2);
        System.out.println(json);
        Restaurant restaurant = JsonUtil.readValue(json, Restaurant.class);
        assertMatch(restaurant, RESTAURANT_2);
    }

    @Test
    public void testReadWriteValues() throws Exception {
        String json = JsonUtil.writeValue(RESTAURANTS);
        System.out.println(json);
        List<Restaurant> restaurants = JsonUtil.readValues(json, Restaurant.class);
        assertMatch(restaurants, RESTAURANTS);
    }
}
