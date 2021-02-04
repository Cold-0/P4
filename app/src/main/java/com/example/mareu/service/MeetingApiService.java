package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

public interface MeetingApiService {
    List<Meeting> getMeetings();

    void createMeeting(Meeting meeting);
    void removeMeeting(Meeting meeting);
    int indexOfMeeting(Meeting meeting);
    List<Meeting> getFilteredList(String room, String date);
}

