package com.example.mareu;


import com.example.mareu.generator.GenerateMeetingList;
import com.example.mareu.model.Meeting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class UnitTestList {

    private List<Meeting> mMeetingList;

    @Before
    public void setup() {
        mMeetingList = GenerateMeetingList.generateMeetings();
    }

    @Test
    public void getDefaultList() {
        assertEquals(12, mMeetingList.size());
    }

    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = mMeetingList.get(0);
        mMeetingList.remove(meetingToDelete);
        assertFalse(mMeetingList.contains(meetingToDelete));
    }

    @Test
    public void addMeetingWithSuccess() {
        int size = mMeetingList.size();
        Meeting meetingToAdd = new Meeting("19/07/2020", "08h40", "4", "Test",
                Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com"));
        mMeetingList.add(meetingToAdd);
        assertEquals(mMeetingList.size(), size + 1);
    }
}
