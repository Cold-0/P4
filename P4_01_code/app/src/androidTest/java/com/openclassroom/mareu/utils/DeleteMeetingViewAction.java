package com.openclassroom.mareu.utils;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import android.view.View;

import com.openclassroom.mareu.R;

import org.hamcrest.Matcher;

import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

public class DeleteMeetingViewAction implements ViewAction {
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
        View button = view.findViewById(R.id.remove_meeting);
        button.performClick();
    }
}