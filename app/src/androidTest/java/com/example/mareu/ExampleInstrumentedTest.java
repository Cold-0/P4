package com.example.mareu;

import android.content.Context;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.mareu.generator.GenerateMeetingList;
import com.example.mareu.model.Meeting;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;



import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    private static int ITEMS_COUNT = 12;

    private MeetingListActivity mActivity;
    private List<Meeting> mMeetingList;

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        mMeetingList = GenerateMeetingList.generateMeetings();
        assertThat(mActivity, notNullValue());
        assertEquals(mMeetingList.size(), ITEMS_COUNT);
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.mareu", appContext.getPackageName());
    }
}