package com.example.learnplaylive.HomeBottom.Fitness.FitnessFragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import com.example.learnplaylive.HomeBottom.Fitness.FitnessActivity.ExerciseDetailActivity;
import com.example.learnplaylive.R;
import com.example.learnplaylive.SharedPreference.App;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class ExerciseLogPreviousFragment extends Fragment implements View.OnClickListener {
    private Button logItBtn;
    private EditText startTimeEt, durationEt, pickDateEt, ed_calories;
    private int mYear, mMonth, mDay;
    private String format = "";
    private EditText exerciseType_et;


    public ExerciseLogPreviousFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercise_log_previous, container, false);
        findIds(view);
//        pickDateEt.requestFocus();
//        startTimeEt.requestFocus();
//        durationEt.requestFocus();

        return view;
    }

    private void findIds(View view) {
        logItBtn = view.findViewById(R.id.logItBtn);
        logItBtn.setOnClickListener(this);

        exerciseType_et = view.findViewById(R.id.exerciseType_et);
        ed_calories = view.findViewById(R.id.ed_calories);

//        startTimeLl = view.findViewById(R.id.startTimeLl);
//        startTimeLl.setOnClickListener(this);
//        duartionLl = view.findViewById(R.id.duartionLl);
//        duartionLl.setOnClickListener(this);
//        pickDateLl = view.findViewById(R.id.pickDateLl);
//        pickDateLl.setOnClickListener(this);
        pickDateEt= view.findViewById(R.id.pickDateEt);
        pickDateEt.setOnClickListener(this);
        startTimeEt = view.findViewById(R.id.startTimeEt);
        startTimeEt.setOnClickListener(this);
        durationEt = view.findViewById(R.id.durationEt);
        durationEt.setOnClickListener(this);

    }

    private void addLoggedDetail(){
        String userId = App.getAppPreference().getLoginDetail().getDetails().getId();
//        String subCategoryId = getIntent().getExtras().getString("subCategoryId");
        String exerciseType = exerciseType_et.getText().toString();
        String startDate = pickDateEt.getText().toString();
        String startTime = startTimeEt.getText().toString();
        String duration = durationEt.getText().toString();
        String calories = ed_calories.getText().toString();


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logItBtn:
                startActivity(new Intent(getContext(), ExerciseDetailActivity.class));
                getActivity().finish();
                break;
            case R.id.pickDateLl:
                getDate();
                break;
            case R.id.pickDateEt:
                getDate();
                break;
            case R.id.startTimeEt:
                PickTime();
                break;
            case R.id.durationEt:
                PickTime2();
                break;
        }
    }

    private void getDate() {
        Calendar mcurrentDate = Calendar.getInstance();
        mYear = mcurrentDate.get(Calendar.YEAR);

        mMonth = mcurrentDate.get(Calendar.MONTH);

        mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        final Calendar[] myCalendar = new Calendar[1];
        myCalendar[0] = Calendar.getInstance();

        final DatePickerDialog mDatePicker = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                myCalendar[0].set(Calendar.YEAR, selectedyear);
                myCalendar[0].set(Calendar.MONTH, selectedmonth);
                myCalendar[0].set(Calendar.DAY_OF_MONTH, selectedday);

                String myFormat = "MMM dd, yyyy"; //Change as you need
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                //finalDate = sdf.format(myCalendar.getTime());

                pickDateEt.setText(sdf.format(myCalendar[0].getTime()));

                mDay = selectedday;
                mMonth = selectedmonth;
                mYear = selectedyear;
            }
        }
                , mYear, mMonth, mDay);
        mDatePicker.getDatePicker().setMinDate(myCalendar[0].getTimeInMillis());
        //mDatePicker.setTitle("Select date");
        mDatePicker.show();
    }

    private void PickTime() {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);


        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        if (hourOfDay == 0) {
                            hourOfDay += 12;
                            format = "AM";
                        } else if (hourOfDay == 12) {
                            format = "PM";
                        } else if (hourOfDay > 12) {
                            hourOfDay -= 12;
                            format = "PM";
                        } else {
                            format = "AM";
                        }
                        startTimeEt.setText(hourOfDay + ":" + minute+" "+format);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    private void PickTime2() {
        final Calendar c = Calendar.getInstance();
        int mHour = c.get(Calendar.HOUR_OF_DAY);
        int mMinute = c.get(Calendar.MINUTE);

        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        if (hourOfDay == 0) {
                            hourOfDay += 12;
                            format = "AM";
                        } else if (hourOfDay == 12) {
                            format = "PM";
                        } else if (hourOfDay > 12) {
                            hourOfDay -= 12;
                            format = "PM";
                        } else {
                            format = "AM";
                        }
                        durationEt.setText(hourOfDay + ":" + minute);
                    }
                }, mHour, mMinute, true);
        timePickerDialog.show();
    }
}
