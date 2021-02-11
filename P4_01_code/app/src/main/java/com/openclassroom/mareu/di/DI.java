package com.openclassroom.mareu.di;

import com.openclassroom.mareu.service.MeetingApiService;
import com.openclassroom.mareu.service.MeetingApiServiceDummy;

public class DI {

    private static final MeetingApiService meetingApiService = new MeetingApiServiceDummy();

    public static MeetingApiService getMeetingApiService() {
        return meetingApiService;
    }

    public static MeetingApiService getNewMeetingApiService() {
        return new MeetingApiServiceDummy();
    }

}
