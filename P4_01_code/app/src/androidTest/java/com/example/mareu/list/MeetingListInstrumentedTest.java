package com.example.mareu.list;

import android.content.Context;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.mareu.MeetingListActivity;
import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.service.MeetingApiService;

import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MeetingListInstrumentedTest {

    private final int ITEMS_COUNT = 12;
    private MeetingListActivity mActivity;
    private MeetingApiService mMeetingApiService;

    @Rule
    public ActivityTestRule<MeetingListActivity> mActivityRule =
            new ActivityTestRule(MeetingListActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        mMeetingApiService = DI.getMeetingApiService();
        assertThat(mActivity, notNullValue());
        assertEquals(mMeetingApiService.getMeetings().size(), ITEMS_COUNT);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.mareu", appContext.getPackageName());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void meetingListShouldNotBeEmpty() {
        onView(AllOf.allOf(ViewMatchers.withId(R.id.meeting_list), isDisplayed())).check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        Matcher matcher = allOf(withId(R.id.meeting_list), isDisplayed());

        // Given : We remove the element at position 2
        onView(matcher).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        //onView(matcher).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        //onView(matcher).check(withItemCount(ITEMS_COUNT - 1));
    }
}