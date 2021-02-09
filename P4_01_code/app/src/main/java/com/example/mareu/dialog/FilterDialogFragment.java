package com.example.mareu.dialog;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;

import com.example.mareu.R;
import com.example.mareu.callback.IFilterCallback;
import com.example.mareu.databinding.FragmentDialogFilterBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class FilterDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private FragmentDialogFilterBinding mBinding;
    private DatePickerDialog mDatePickerDialog;
    private IFilterCallback mIFilterCallback;

    public static FilterDialogFragment newInstance(String title, IFilterCallback callback) {
        FilterDialogFragment returned = new FilterDialogFragment();
        returned.mIFilterCallback = callback;
        return returned;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mBinding = FragmentDialogFilterBinding.inflate(LayoutInflater.from(getContext()));
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Choisir un filtre");
        builder.setCancelable(false);
        builder.setView(mBinding.getRoot())
                .setPositiveButton("Ok", (dialog, id) -> {
                    DatePicker picker = mDatePickerDialog.getDatePicker();
                    mIFilterCallback.onValidateFilter(
                            String.format("%d", mBinding.filterRoom.getSelectedItemPosition()+1),
                            String.format("%02d/%02d/%04d", picker.getDayOfMonth(), picker.getMonth()+1, picker.getYear())
                );

                })
                .setNegativeButton("Reset", (dialog, id) ->
                {
                    mIFilterCallback.onResetFilter();
                });
        setSpinner();
        setDatePickerDialog();
        setCancelable(false);
        return builder.create();
    }

    private void setSpinner() {
        Spinner spinner = mBinding.filterRoom;
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.meeting_room_names_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void setDatePickerDialog() {
        Calendar c = Calendar.getInstance();
        mDatePickerDialog = new DatePickerDialog(getContext(), FilterDialogFragment.this,
                c.get(Calendar.YEAR),
                c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)
        );
        mDatePickerDialog.setCancelable(false);
        setDate(mDatePickerDialog.getDatePicker());
        mBinding.filterDate.setOnClickListener(v -> {
            mDatePickerDialog.show();
        });
    }

    private void setDate(DatePicker date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(date.getYear(), date.getMonth(), date.getDayOfMonth());
        SimpleDateFormat formatter = new SimpleDateFormat("EEEE d MMMM yyyy", Locale.FRANCE);
        String output = formatter.format(calendar.getTime());
        mBinding.filterDate.setText(output);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        setDate(view);
    }
}