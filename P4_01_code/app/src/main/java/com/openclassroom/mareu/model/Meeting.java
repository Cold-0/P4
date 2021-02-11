package com.openclassroom.mareu.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Meeting implements Parcelable {
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

    protected Meeting(Parcel in) {
        mDate = in.readString();
        mTime = in.readString();
        mRoom = in.readString();
        mSubject = in.readString();
        mParticipants = in.createStringArrayList();
    }

    public static final Creator<Meeting> CREATOR = new Creator<Meeting>() {
        @Override
        public Meeting createFromParcel(Parcel in) {
            return new Meeting(in);
        }

        @Override
        public Meeting[] newArray(int size) {
            return new Meeting[size];
        }
    };

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getTime() {
        return mTime;
    }

    public void setTime(String time) {
        mTime = time;
    }

    public String getRoom() {
        return mRoom;
    }

    public void setRoom(String room) {
        mRoom = room;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mDate);
        dest.writeString(mTime);
        dest.writeString(mRoom);
        dest.writeString(mSubject);
        dest.writeStringList(mParticipants);
    }
}
