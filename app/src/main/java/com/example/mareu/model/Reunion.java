package com.example.mareu.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Reunion {
    private SimpleDateFormat mDayTimeStart;
    private String mLocation;
    private String mSubject;
    private ArrayList<String> mParticipants;

    public Reunion(SimpleDateFormat dayTimeStart, String location, String subject, ArrayList<String> participants) {
        mDayTimeStart = dayTimeStart;
        mLocation = location;
        mSubject = subject;
        mParticipants = participants;
    }

    public SimpleDateFormat  getDayTimeStart() {
        return mDayTimeStart;
    }

    public void setDayTimeStart(SimpleDateFormat dayTimeStart) {
        mDayTimeStart = dayTimeStart;
    }

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getSubject() {
        return mSubject;
    }

    public void setSubject(String subject) {
        mSubject = subject;
    }

    public ArrayList<String> getParticipants() {
        return mParticipants;
    }

    public void setParticipants(ArrayList<String> participants) {
        mParticipants = participants;
    }
}
