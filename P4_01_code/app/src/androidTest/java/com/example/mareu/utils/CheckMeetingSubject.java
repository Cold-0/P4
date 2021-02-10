package com.example.mareu.utils;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mareu.R;
import org.hamcrest.Matcher;

import java.util.ArrayList;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertEquals;
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