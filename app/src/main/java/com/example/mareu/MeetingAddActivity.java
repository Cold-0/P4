package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.mareu.databinding.ActivityMeetingAddBinding;
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.generator.GenerateMeetingList;
import com.example.mareu.model.Meeting;

import java.util.Arrays;

public class MeetingAddActivity extends AppCompatActivity {

    ActivityMeetingAddBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMeetingAddBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);

        mBinding.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Meeting meeting = new Meeting("19/07/2020", "08h40", "4", "BOB", GenerateMeetingList.getRandomColor(),
                        Arrays.asList("jean@email.com", "lea@email.com", "nina@email.com"));
                finish();
                Toast.makeText(MeetingAddActivity.this.getApplicationContext(), getString(R.string.add_meeting_toast_create), Toast.LENGTH_SHORT).show();
            }
        });
        mBinding.cancel.setOnClickListener(v -> {
            finish();
            Toast.makeText(MeetingAddActivity.this.getApplicationContext(), getString(R.string.add_meeting_toast_cancel), Toast.LENGTH_SHORT).show();
        });
    }
}