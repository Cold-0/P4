package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.mareu.databinding.ActivityMeetingAddBinding;
import com.example.mareu.databinding.ActivityMeetingListBinding;

public class MeetingAddActivity extends AppCompatActivity {

    ActivityMeetingAddBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMeetingAddBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(R.layout.activity_meeting_add);
    }
}