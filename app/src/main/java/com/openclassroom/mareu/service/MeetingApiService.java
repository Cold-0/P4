package com.openclassroom.mareu.service;

import com.openclassroom.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {
    List<Meeting> getMeetings();

    void createMeeting(Meeting meeting);

    void removeMeeting(Meeting meeting);

    int indexOfMeeting(Meeting meeting);

    List<Meeting> getFilteredList(String room, String date);
}

