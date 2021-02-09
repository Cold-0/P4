package com.example.mareu.callback;

public interface IFilterCallback {
    void onValidateFilter(String room, String date);

    void onResetFilter();
}
