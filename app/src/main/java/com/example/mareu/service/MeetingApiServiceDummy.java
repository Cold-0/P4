package com.example.mareu.service;

import com.example.mareu.model.Meeting;

import java.util.List;

public class MeetingApiServiceDummy implements MeetingApiService {
    private List<Meeting> mMeetings = MeetingListGeneratorDummy.getNewMeetingList();

    @Override
    public List<Meeting> getMeetings() {
        return mMeetings;
    }

    @Override
    public void createMeeting(Meeting meeting) {
        mMeetings.add(meeting);
    }

    @Override
    public void removeMeeting(Meeting meeting) {
        mMeetings.remove(meeting);
    }

    @Override
    public int indexOfMeeting(Meeting meeting) {
        return mMeetings.indexOf(meeting);
    }
}
