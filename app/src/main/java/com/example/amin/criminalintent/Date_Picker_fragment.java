package com.example.amin.criminalintent;


import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


/**
 * A simple {@link Fragment} subclass.
 */
public class Date_Picker_fragment extends DialogFragment {

    public static final String DIALOG_DATE = "dialog_date";
    private static final String ARG_DATE = "arg_data" ;
    private static Date mDate;
    private DatePicker mDatePicker;
    public static final String EXTRA_DATE="";
    public static final int RESULT_OK = 1;
    public static final int REQUEST_DATE = 0;

    public Date_Picker_fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       mDate = (Date) getArguments().getSerializable(ARG_DATE);
    }



    public static Date_Picker_fragment newInstance(Date date) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);
        Date_Picker_fragment fragment = new Date_Picker_fragment();
        fragment.setArguments(args);
        return fragment;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View view = layoutInflater.inflate(R.layout.fragment_date__picker_fragment,null);


        Calendar calender = Calendar.getInstance();
        calender.setTime(mDate);
        int year = calender.get(calender.YEAR);
        int month = calender.get(calender.MONTH);
        int dayOfMonth = calender.get(calender.DAY_OF_MONTH);

        mDatePicker = (DatePicker) view.findViewById(R.id.dialog_view);
        mDatePicker.init(year,month,dayOfMonth,null);

        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .setTitle(R.string.date_picker)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sendResult();
                    }})
                .create();
        return alertDialog;
    }
    private void sendResult() {
        int year = mDatePicker.getYear();
        int month = mDatePicker.getMonth();
        int dayOfMonth = mDatePicker.getDayOfMonth();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(year,month,dayOfMonth);
        Date date = gregorianCalendar.getTime();

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);
        getTargetFragment().onActivityResult(REQUEST_DATE,RESULT_OK,intent);
    }
}
