package com.example.mareu.di;

import com.example.mareu.service.MeetingApiService;
import com.example.mareu.service.MeetingApiServiceDummy;

public class DI {

    private static MeetingApiService meetinApiService = new MeetingApiServiceDummy();

    public static MeetingApiService getMeetingApiService() {
        return meetinApiService;
    }

    public static MeetingApiService getNewMeetingApiService() {
        return new MeetingApiServiceDummy();
    }

}
