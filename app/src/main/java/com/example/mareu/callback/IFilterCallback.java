package com.example.mareu.callback;

public interface IFilterCallback {
    void onValidate(String room, String date);
    void onReset();
}
