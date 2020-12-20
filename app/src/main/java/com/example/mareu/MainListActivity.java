package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.example.mareu.databinding.ActivityMainListBinding;
import com.example.mareu.dummylist.DummyMeetingList;
import com.example.mareu.model.Meeting;

import java.util.List;

public class MainListActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<Meeting> list;
    ActivityMainListBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainListBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        mRecyclerView = binding.meetingList;
        initRecyclerView();
    }

    private void initRecyclerView()
    {
        Context context = mRecyclerView.getContext();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        list = DummyMeetingList.generateMeetings();
        mRecyclerView.setAdapter(new MyReunionItemRecyclerViewAdapter(list));
    }
}