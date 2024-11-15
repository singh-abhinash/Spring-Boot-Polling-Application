package com.abhinash.pollingApp.springBootPollingApplication;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static com.abhinash.pollingApp.springBootPollingApplication.web.json.JsonUtil.writeValue;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import com.abhinash.pollingApp.springBootPollingApplication.model.User;
import com.abhinash.pollingApp.springBootPollingApplication.web.json.JsonUtil;

/**
 * Created by Abhinash Singh - 2024
 */

public class TestUtil {

    public static final LocalDate TODAY = LocalDate.now();

    public static String getContent(ResultActions action) throws UnsupportedEncodingException {
        return action.andReturn().getResponse().getContentAsString();
    }

    public static <T> T readFromJson(ResultActions action, Class<T> clazz) throws UnsupportedEncodingException {
        return JsonUtil.readValue(getContent(action), clazz);
    }

    public static <T> ResultMatcher contentJson(T expected) {
        return content().json(writeValue(expected));
    }

    public static <T> ResultMatcher contentJsonArray(T... expected) {
        return contentJson(expected);
    }

    public static void mockAuthorize(User user) {
        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(new AuthorizedUser(user), null, user.getRoles()));
    }

    public static RequestPostProcessor userAuth(User user) {
        return SecurityMockMvcRequestPostProcessors.authentication(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
    }
}