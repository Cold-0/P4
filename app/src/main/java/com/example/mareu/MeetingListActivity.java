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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Filter;
import android.widget.Toast;

import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.generator.GenerateMeetingList;
import com.example.mareu.events.RemoveMeetingEvent;
import com.example.mareu.model.Meeting;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

public class MeetingListActivity extends AppCompatActivity {

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
        mBinding.addMeetingButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, MeetingAddActivity.class);
            startActivityForResult(intent, REQUEST_CREATE_NEW_MEETING   );
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
        if(requestCode == REQUEST_CREATE_NEW_MEETING && resultCode == RETURN_CODE_MEETING_CREATED && data != null) {
            Meeting meeting = data.getExtras().getParcelable("meeting");
            mMeetingsList.add(meeting);
            mMeetingsRecyclerView.getAdapter().notifyItemInserted(mMeetingsList.indexOf(meeting));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        if (item.getItemId() == R.id.filter_menu) {
            //Toast.makeText(getApplicationContext(), "Clicked Filter", Toast.LENGTH_SHORT).show();
            FragmentManager fm = getSupportFragmentManager();
            FilterDialogFragment filterDialogFragment = FilterDialogFragment.newInstance("Some Title");
            filterDialogFragment.show(fm, "fragment_edit_name");
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

