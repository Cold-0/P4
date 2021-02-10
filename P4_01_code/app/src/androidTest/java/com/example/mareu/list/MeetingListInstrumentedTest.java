package com.example.mareu.list;

import android.content.Context;
import android.widget.DatePicker;

import androidx.test.InstrumentationRegistry;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import com.example.mareu.MeetingListActivity;
import com.example.mareu.R;
import com.example.mareu.di.DI;
import com.example.mareu.model.Meeting;
import com.example.mareu.service.MeetingApiService;
import com.example.mareu.utils.CheckMeetingSubject;
import com.example.mareu.utils.DeleteMeetingViewAction;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.core.AllOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
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

    private MeetingListActivity mActivity;
    private MeetingApiService mMeetingApiService;

    @Rule
    public ActivityTestRule<MeetingListActivity> mActivityRule =
            new ActivityTestRule<>(MeetingListActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        mMeetingApiService = DI.getMeetingApiService();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void meetingList_CheckAppContext() {
        // Context of the app under test.
        Context appContext = getInstrumentation().getTargetContext();
        assertEquals("com.example.mareu", appContext.getPackageName());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void meetingList_ShouldNotBeEmpty() {
        onView(AllOf.allOf(ViewMatchers.withId(R.id.meeting_list), isDisplayed())).check(matches(hasMinimumChildCount(1)));
    }

    /**
     * We ensure that our recyclerview contain the good itemcount
     */
    @Test
    public void meetingList_GoodCount() {
        Matcher<android.view.View> matcher = allOf(withId(R.id.meeting_list), isDisplayed());
        int size = mMeetingApiService.getMeetings().size();
        onView(matcher).check(withItemCount(size));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void meetingList_DeleteAction_ShouldRemoveItem() {
        Matcher<android.view.View> matcher = allOf(withId(R.id.meeting_list), isDisplayed());
        int size = mMeetingApiService.getMeetings().size();
        // Given : We remove the element at position 2
        onView(matcher).check(withItemCount(size));
        // When perform a click on a delete icon
        onView(matcher).perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteMeetingViewAction()));
        //onView(withText(R.string.ok)).check(matches(isDisplayed()));
        onData(withText(R.string.ok)).perform(click());
        onView(matcher).check(withItemCount(size - 1));
    }

    /**
     * Add meeting and check if it's in the recyclerview (and in the meeting list at same time)
     */
    @Test
    public void meetingList_AddMeeting() {
        onView(allOf(withId(R.id.add_meeting_button), isDisplayed())).perform(click());
        final String test = "TestTest";
        onView(allOf(withId(R.id.meetingSubject))).perform(replaceText(test));
        onView(allOf(withId(R.id.accept))).perform(click());
        List<Meeting> meetings = mMeetingApiService.getMeetings();

        boolean found = false;
        for (Meeting meeting : meetings) {
            if (meeting.getSubject().equals(test)) {
                found = true;
                break;
            }
        }
        assertTrue(found);

        onView(allOf(withId(R.id.meeting_list))).perform(
                RecyclerViewActions.actionOnItemAtPosition(
                        meetings.size() - 1, new CheckMeetingSubject(test)
                )
        );
    }

    /**
     * Check filter work
     */
    @Test
    public void meetingList_FilterMeetingList() {
        // Click on the filter icon
        onView(allOf(withId(R.id.filter_menu))).perform(click());
        onView(allOf(withId(R.id.filterDate))).perform(click());
        onView(allOf(withClassName(Matchers.equalTo(DatePicker.class.getName())))).perform(PickerActions.setDate(2021, 2, 1));
        onView(allOf(withText("OK"))).perform(click());
        onView(allOf(withText(R.string.ok))).perform(click());

        onView(allOf(withId(R.id.meeting_list), isDisplayed())).check(withItemCount(1));
    }
}