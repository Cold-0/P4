package com.example.mareu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.Toast;

import com.example.mareu.callback.IFilterCallback;
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.generator.GenerateMeetingList;
import com.example.mareu.events.RemoveMeetingEvent;
import com.example.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class MeetingListActivity extends AppCompatActivity implements IFilterCallback {

    private static final String TAG = "MeetingListActivity";
    RecyclerView mMeetingsRecyclerView;
    List<Meeting> mMeetingsList;
    ActivityMeetingListBinding mBinding;

    static public final int REQUEST_CREATE_NEW_MEETING = 1;
    static public final int RETURN_CODE_MEETING_CREATED = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        mMeetingsRecyclerView = mBinding.meetingList;
        getSupportActionBar().setTitle(getString(R.string.actionbar_title_meeting_list));
        initRecyclerView();
        initButtons();
    }

    private void initButtons() {
        mBinding.addMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MeetingListActivity.this, MeetingAddActivity.class);
                MeetingListActivity.this.startActivityForResult(intent, REQUEST_CREATE_NEW_MEETING);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMeetingsRecyclerView.getAdapter().notifyDataSetChanged();
    }

    private void initRecyclerView() {
        Context context = mMeetingsRecyclerView.getContext();
        mMeetingsRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mMeetingsList = GenerateMeetingList.generateMeetings();
        mMeetingsRecyclerView.setAdapter(new MeetingListRecyclerViewAdapter(getApplicationContext(), mMeetingsList));
    }

    private void setRecyclerViewList(List<Meeting> list) {
        MeetingListRecyclerViewAdapter adapter = (MeetingListRecyclerViewAdapter) mMeetingsRecyclerView.getAdapter();
        adapter.setList(list);
    }

    @Subscribe
    public void onRemoveMeeting(RemoveMeetingEvent event) {
        AlertDialog.Builder adb = new AlertDialog.Builder(this);
        adb.setCancelable(false);
        adb.setTitle(getString(R.string.delete_meeting_confirmation_title));
        adb.setPositiveButton(R.string.delete_meeting_confirm, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                int index = mMeetingsList.indexOf(event.mMeeting);
                mMeetingsList.remove(event.mMeeting);
                Toast.makeText(getApplicationContext(), getString(R.string.delete_meeting_toast), Toast.LENGTH_SHORT).show();
                mMeetingsRecyclerView.getAdapter().notifyItemRemoved(index);
            }
        });
        adb.setNegativeButton(R.string.delete_meeting_cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), getString(R.string.cancel_delete_meeting_toast), Toast.LENGTH_SHORT).show();
            }
        });
        adb.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CREATE_NEW_MEETING && resultCode == RETURN_CODE_MEETING_CREATED && data != null) {
            Meeting meeting = data.getExtras().getParcelable("meeting");
            mMeetingsList.add(meeting);
            mMeetingsRecyclerView.getAdapter().notifyItemInserted(mMeetingsList.indexOf(meeting));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.filter_menu) {
            FragmentManager fm = getSupportFragmentManager();
            FilterDialogFragment filterDialogFragment = FilterDialogFragment.newInstance("Some Title", this);
            filterDialogFragment.show(fm, "fragment_edit_name");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onValidate(String room, String date) {
        Toast.makeText(getApplicationContext(), "Validate", Toast.LENGTH_SHORT).show();
        List<Meeting> new_list = new ArrayList<>();
        Log.i(TAG, String.format("onValidate: %s, %s", room, date));
        for(Meeting meeting : mMeetingsList)
        {
            Log.i(TAG, String.format("List all : %s, %s", meeting.getRoom(), meeting.getDate()));
            if(meeting.getRoom().equals(room) && meeting.getDate().equals(date))
                new_list.add(meeting);
        }
        setRecyclerViewList(new_list);
    }

    @Override
    public void onReset() {
        setRecyclerViewList(mMeetingsList);
        Toast.makeText(getApplicationContext(), "Reset", Toast.LENGTH_SHORT).show();
        Log.i(TAG, "onReset");
    }
}

