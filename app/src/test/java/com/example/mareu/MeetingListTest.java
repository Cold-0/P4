package com.example.mareu;

import com.example.mareu.generator.GenerateMeetingList;
import com.example.mareu.model.Meeting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class MeetingListTest {
    List<Meeting> mMeetingList;

    @Before
    public void setup() {
        mMeetingList = GenerateMeetingList.generateMeetings();
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
        Meeting meetingToAdd = new Meeting("19/07/2020", "08h40", "4", "Test", GenerateMeetingList.getRandomColor(),
                Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com"));
        mMeetingList.add(meetingToAdd);
        assertFalse(mMeetingList.size() == size+1);
    }
}