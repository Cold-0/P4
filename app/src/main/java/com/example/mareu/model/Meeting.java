package com.example.mareu.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Meeting {
    private String mDate;
    private String mTime;
    private String mRoom;
    private String mSubject;
    private List<String> mParticipants;

    public Meeting(String date, String time, String room, String subject, List<String> participants) {
        mDate = date;
        mTime = time;
        mRoom = room;
        mSubject = subject;
        mParticipants = participants;
    }

    public String getLocation() {
        return mRoom;
    }

    public void setLocation(String location) {
        mRoom = location;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

    public List<String> getParticipants() {
        return mParticipants;
    }

    public void setParticipants(List<String> participants) {
        mParticipants = participants;
    }
}
