package com.example.mareu.di;

import com.example.mareu.generator.MeetingListGenerator;
import com.example.mareu.model.Meeting;

import java.util.List;

public class DI {

    private static final List<Meeting> meetingList = MeetingListGenerator.generateMeetings();

    public static List<Meeting> getMeetingList() {
        return meetingList;
    }

    public static List<Meeting> getNewMeetingList() {
        return MeetingListGenerator.generateMeetings();
    }

}
