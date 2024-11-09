package com.abhinash.pollingApp.springBootPollingApplication.web.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.RESTAURANTS;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.RESTAURANT_1;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.RESTAURANT_2;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.RESTAURANT_3;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.RESTAURANT_4;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.RESTAURANT_5;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.RESTAURANT_6;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.RESTAURANT_7;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.RES_ID;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.assertMatch;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.getCreated;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.getUpdated;
import static com.abhinash.pollingApp.springBootPollingApplication.TestUtil.contentJson;
import static com.abhinash.pollingApp.springBootPollingApplication.TestUtil.mockAuthorize;
import static com.abhinash.pollingApp.springBootPollingApplication.TestUtil.readFromJson;
import static com.abhinash.pollingApp.springBootPollingApplication.TestUtil.userAuth;
import static com.abhinash.pollingApp.springBootPollingApplication.UserTestData.ADMIN;
import static com.abhinash.pollingApp.springBootPollingApplication.UserTestData.USER_2;

import java.util.Comparator;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.abhinash.pollingApp.springBootPollingApplication.model.Restaurant;
import com.abhinash.pollingApp.springBootPollingApplication.web.AbstractControllerTest;
import com.abhinash.pollingApp.springBootPollingApplication.web.json.JsonUtil;

/**
 * Created by Abhinash Singh - 2024
 */

class RestaurantAdminControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantAdminController.REST_URL + "/";

    @Test
    void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL + (RES_ID + 4))
                .with(userAuth(USER_2)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userAuth(ADMIN))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        mockAuthorize(ADMIN);
        RESTAURANTS.sort(Comparator.comparing(Restaurant::getTitle));
        assertMatch(restaurantService.getAll(), RESTAURANTS);
    }

    @Test
    void testRetrieve() throws Exception {
        mockMvc.perform(get(REST_URL + (RES_ID + 3))
                .with(userAuth(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(RESTAURANT_4));

        mockAuthorize(ADMIN);
        assertMatch(restaurantService.retrieve(RES_ID + 3), RESTAURANT_4);
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RES_ID)
                .with(userAuth(ADMIN))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());
    }

    @Test
     void testCreate() throws Exception {
        Restaurant created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userAuth(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated());

        Restaurant returned = readFromJson(action, Restaurant.class);
        created.setId(returned.getId());

        assertMatch(returned, created);
        mockAuthorize(ADMIN);
        assertMatch(restaurantService.getAll(), created, RESTAURANT_7, RESTAURANT_5, RESTAURANT_3, RESTAURANT_1, RESTAURANT_2,
                RESTAURANT_6, RESTAURANT_4);
    }

    @Test
    void testUpdate() throws Exception {
        Restaurant updated = getUpdated();
        mockMvc.perform(put(REST_URL + RES_ID + 6)
                .with(userAuth(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        mockAuthorize(ADMIN);
        assertMatch(restaurantService.retrieve(RES_ID + 6), updated);
    }
}
