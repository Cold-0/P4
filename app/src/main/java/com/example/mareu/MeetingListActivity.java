package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.dummylist.DummyMeetingList;
import com.example.mareu.model.Meeting;

import java.util.List;

public class MeetingListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<Meeting> mList;
    ActivityMeetingListBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        mRecyclerView = mBinding.meetingList;
        initRecyclerView();
    }

    private void initRecyclerView()
    {
        Context context = mRecyclerView.getContext();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mList = DummyMeetingList.generateMeetings();
        mRecyclerView.setAdapter(new MeetingListRecyclerViewAdapter(mList));
    }
}