package com.example.mareu.di;

import com.example.mareu.service.MeetingApiService;
import com.example.mareu.service.MeetingApiServiceDummy;

public class DI {

    private static final MeetingApiService meetingApiService = new MeetingApiServiceDummy();

    public static MeetingApiService getMeetingApiService() {
        return meetingApiService;
    }

    public static MeetingApiService getNewMeetingApiService() {
        return new MeetingApiServiceDummy();
    }

}
