package com.abhinash.pollingApp.springBootPollingApplication.web;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import javax.annotation.PostConstruct;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import com.abhinash.pollingApp.springBootPollingApplication.service.DishService;
import com.abhinash.pollingApp.springBootPollingApplication.service.RestaurantService;
import com.abhinash.pollingApp.springBootPollingApplication.service.UserServiceSecurity;
import com.abhinash.pollingApp.springBootPollingApplication.service.VoteService;

/**
 * Created by Abhinash Singh - 2024
 */

@Transactional
@ExtendWith(SpringExtension.class)
@SpringBootTest()
@ActiveProfiles("test")
public abstract class AbstractControllerTest {

    private static final CharacterEncodingFilter CHARACTER_ENCODING_FILTER = new CharacterEncodingFilter();

    static {
        CHARACTER_ENCODING_FILTER.setEncoding("UTF-8");
        CHARACTER_ENCODING_FILTER.setForceEncoding(true);
    }

    @Autowired
    private WebApplicationContext context;

    protected MockMvc mockMvc;

    @Autowired
    protected UserServiceSecurity serviceSecurity;

    @Autowired
    protected RestaurantService restaurantService;

    @Autowired
    protected DishService dishService;

    @Autowired
    protected VoteService voteService;

    @PostConstruct
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .addFilter(CHARACTER_ENCODING_FILTER).apply((springSecurity()))
                .build();
    }
}