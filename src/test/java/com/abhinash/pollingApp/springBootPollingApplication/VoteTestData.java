package com.abhinash.pollingApp.springBootPollingApplication;

import static org.assertj.core.api.Assertions.assertThat;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.RESTAURANT_1;
import static com.abhinash.pollingApp.springBootPollingApplication.RestaurantTestData.RESTAURANT_4;
import static com.abhinash.pollingApp.springBootPollingApplication.TestUtil.TODAY;
import static com.abhinash.pollingApp.springBootPollingApplication.UserTestData.USER_1;
import static com.abhinash.pollingApp.springBootPollingApplication.UserTestData.USER_2;
import static com.abhinash.pollingApp.springBootPollingApplication.model.AbstractBaseEntity.START_SEQ;

import java.util.Arrays;
import java.util.List;

import com.abhinash.pollingApp.springBootPollingApplication.model.Vote;

/**
 * Created by Abhinash Singh - 2024
 */

public class VoteTestData {

    public static final int VOTE_ID = START_SEQ+19;
    public static final Vote VOTE_1 = new Vote(VOTE_ID, USER_1, RESTAURANT_1, TODAY);
    public static final Vote VOTE_2 = new Vote(VOTE_ID+1, USER_2, RESTAURANT_4, TODAY);
    public static final List<Vote> VOTE_HISTORY_USER_1 = Arrays.asList(VOTE_1);

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}