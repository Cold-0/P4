package com.openclassroom.mareu.utils;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import android.view.View;
import android.widget.TextView;

import com.openclassroom.mareu.R;

import org.hamcrest.Matcher;

import static org.junit.Assert.assertTrue;

public class CheckMeetingSubject implements ViewAction {
    final String subject;

    public CheckMeetingSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        TextView editText = (TextView) view.findViewById(R.id.subject_meeting);
        assertTrue(editText.getText().toString().startsWith(subject));
    }
}