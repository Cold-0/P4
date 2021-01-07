package com.example.mareu.events;

import com.example.mareu.model.Meeting;

public class RemoveMeetingEvent {
    public Meeting mMeeting;
    public RemoveMeetingEvent(Meeting meeting) {
        mMeeting = meeting;
    }
}