package com.abhinash.pollingApp.springBootPollingApplication.web.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.abhinash.pollingApp.springBootPollingApplication.DishTestData.DISHES;
import static com.abhinash.pollingApp.springBootPollingApplication.DishTestData.DISH_6;
import static com.abhinash.pollingApp.springBootPollingApplication.DishTestData.DISH_ID;
import static com.abhinash.pollingApp.springBootPollingApplication.DishTestData.assertMatch;
import static com.abhinash.pollingApp.springBootPollingApplication.DishTestData.getCreated;
import static com.abhinash.pollingApp.springBootPollingApplication.DishTestData.getUpdated;
import static com.abhinash.pollingApp.springBootPollingApplication.TestUtil.contentJson;
import static com.abhinash.pollingApp.springBootPollingApplication.TestUtil.mockAuthorize;
import static com.abhinash.pollingApp.springBootPollingApplication.TestUtil.readFromJson;
import static com.abhinash.pollingApp.springBootPollingApplication.TestUtil.userAuth;
import static com.abhinash.pollingApp.springBootPollingApplication.UserTestData.ADMIN;
import static com.abhinash.pollingApp.springBootPollingApplication.UserTestData.USER_1;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;

import com.abhinash.pollingApp.springBootPollingApplication.model.Dish;
import com.abhinash.pollingApp.springBootPollingApplication.web.AbstractControllerTest;
import com.abhinash.pollingApp.springBootPollingApplication.web.json.JsonUtil;

/**
 * Created by Abhinash Singh - 2024
 */

class DishAdminControllerTest extends AbstractControllerTest {

    private static final String REST_URL = DishAdminController.REST_URL + "/";

    @Test
    public void testGetForbidden() throws Exception {
        mockMvc.perform(get(REST_URL + DISH_ID + 4)
                .with(userAuth(USER_1)))
                .andDo(print())
                .andExpect(status().isForbidden());
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userAuth(ADMIN))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        mockAuthorize(ADMIN);
        assertMatch(dishService.getAll(), DISHES);
    }

    @Test
    public void testRetrieve() throws Exception {
        mockMvc.perform(get(REST_URL + (DISH_ID + 5))
                .with(userAuth(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                // https://jira.spring.io/browse/SPR-14472
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(DISH_6));

        mockAuthorize(ADMIN);
        assertMatch(dishService.retrieve(DISH_ID + 5), DISH_6);
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH_ID)
                .with(userAuth(ADMIN))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andDo(print());

        assertMatch(dishService.getAll());
    }

    @Test
    public void testCreate() throws Exception {
        Dish created = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userAuth(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(created)))
                .andExpect(status().isCreated());

       Dish returned = readFromJson(action, Dish.class);
       created.setId(returned.getId());

        assertMatch(returned, created);
    }

    @Test
    public void testUpdate() throws Exception {
        Dish updated = getUpdated();
        mockMvc.perform(put(REST_URL + DISH_ID + 4)
                .with(userAuth(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        mockAuthorize(ADMIN);
        assertMatch(dishService.retrieve(DISH_ID+4), updated);
    }
}
