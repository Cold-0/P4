package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mareu.databinding.ActivityMeetingAddBinding;
import com.example.mareu.databinding.ActivityMeetingListBinding;
import com.example.mareu.generator.GenerateMeetingList;
import com.example.mareu.model.Meeting;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;

public class MeetingAddActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private ActivityMeetingAddBinding mBinding;
    private DatePickerDialog mDatePickerDialog;
    private TimePickerDialog mTimePickerDialog;
    private TimePicker mTimePicker;

    private static final int DEFAULT_HOUR = 8;
    private static final int DEFAULT_MINUTE = 0;
    private static final int DEFAULT_DAY = 1;
    private static final int DEFAULT_MONTH = 1;
    private static final int DEFAULT_YEAR = 2021;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityMeetingAddBinding.inflate(getLayoutInflater());
        View view = mBinding.getRoot();
        setContentView(view);
        getSupportActionBar().setTitle(getString(R.string.actionbar_title_meeting_add));

        setSpinner();
        setDatePickerDialog();
        setTimePickerDialog();

        mBinding.accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker datePicker = mDatePickerDialog.getDatePicker();
                Meeting meeting = new Meeting(
                        String.format("%02d/%02d/%04d", datePicker.getDayOfMonth(), datePicker.getMonth(), datePicker.getYear()),
                        String.format("%02dh%02d", mTimePicker != null ? mTimePicker.getCurrentHour() : DEFAULT_HOUR, mTimePicker != null ? mTimePicker.getCurrentMinute() : DEFAULT_MINUTE),
                        String.format("%d", mBinding.meetingRoom.getSelectedItemPosition()),
                        mBinding.meetingSubject.getText().toString(),
                        GenerateMeetingList.getRandomColor(),
                        Arrays.asList(mBinding.editTextTextEmailAddress.getText().toString())
                );
                Toast.makeText(MeetingAddActivity.this.getApplicationContext(), getString(R.string.add_meeting_toast_create), Toast.LENGTH_SHORT).show();

                Intent data = new Intent();
                data.putExtra("meeting", meeting);
                setResult(MeetingListActivity.RETURN_CODE_MEETING_CREATED, data);
                finish();
            }
        });
        mBinding.cancel.setOnClickListener(v -> {
            finish();
            Toast.makeText(MeetingAddActivity.this.getApplicationContext(), getString(R.string.add_meeting_toast_cancel), Toast.LENGTH_SHORT).show();
        });
    }

    private void setDatePickerDialog() {
        mDatePickerDialog = new DatePickerDialog(this, MeetingAddActivity.this, DEFAULT_YEAR, DEFAULT_MONTH, DEFAULT_DAY);
        mDatePickerDialog.setCancelable(false);
        setDate(mDatePickerDialog.getDatePicker());
        mBinding.meetingDate.setOnClickListener(v -> {
            mDatePickerDialog.show();
        });
    }

    private void setTimePickerDialog() {
        mTimePickerDialog = new TimePickerDialog(this, MeetingAddActivity.this, DEFAULT_HOUR, DEFAULT_MINUTE, true);
        mTimePickerDialog.setCancelable(false);
        setTime(DEFAULT_HOUR, DEFAULT_MINUTE);
        mBinding.meetingTime.setOnClickListener(v -> {
            mTimePickerDialog.show();
        });
    }

    private void setDate(DatePicker date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonth(), date.getDayOfMonth());
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE d MMMM yyyy", Locale.FRANCE);
        String output = formatter.format(calendar.getTime());
        mBinding.meetingDate.setText(output);
    }

    private void setTime(int hour, int minute) {
        mBinding.meetingTime.setText(String.format("%02d h %02d", hour, minute));
    }

    private void setSpinner() {
        Spinner spinner = mBinding.meetingRoom;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.meeting_room_names_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        setDate(view);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(mTimePicker==null)
            mTimePicker=view;

        setTime(view.getCurrentHour(), view.getCurrentMinute());
    }
}