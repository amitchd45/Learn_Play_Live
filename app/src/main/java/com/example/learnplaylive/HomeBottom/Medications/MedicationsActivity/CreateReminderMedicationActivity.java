package com.example.learnplaylive.HomeBottom.Medications.MedicationsActivity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.learnplaylive.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CreateReminderMedicationActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView timesAndDaysOfWeek_Tv, AdditionOption_Tv, additionalDate, showDate2;
    private AlertDialog dialog;
    private List<String> list = new ArrayList<>();
    private List<String> list1 = new ArrayList<>();
    private TextView  cancelTv, okTv;
    private int mYear, mMonth, mDay;
    private ImageView openCalendar, openCalendar2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_reminder_medication);


        findIds();
        spinners();

    }



    private void findIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("Create a Reminder");

        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.GONE);
        ImageView close= findViewById(R.id.close_topbar);
        close.setVisibility(View.VISIBLE);
        close.setOnClickListener(this);
        ImageView done= findViewById(R.id.done_topbar);
        done.setVisibility(View.VISIBLE);
        done.setOnClickListener(this);

        timesAndDaysOfWeek_Tv = findViewById(R.id.timesAndDaysOfWeek_Tv);
        timesAndDaysOfWeek_Tv.setOnClickListener(this);

        AdditionOption_Tv = findViewById(R.id.AdditionOption_Tv);
        AdditionOption_Tv.setOnClickListener(this);


    }

    private void showTimeAndDayDialog()
    {


        View customView = LayoutInflater.from(CreateReminderMedicationActivity.this).inflate(R.layout.time_and_days_of_the_week_popup, null);
        dialog = new AlertDialog.Builder(CreateReminderMedicationActivity.this).create();
        dialog.setView(customView);

        dialog.setCanceledOnTouchOutside(true);

        cancelTv = customView.findViewById(R.id.timeDayCancelTv);
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        okTv = customView.findViewById(R.id.timeDayOkTv);
        okTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();
    }

    private void showAdditionalOptionDialog()
    {


        View customView = LayoutInflater.from(CreateReminderMedicationActivity.this).inflate(R.layout.additional_option_popup, null);
        dialog = new AlertDialog.Builder(CreateReminderMedicationActivity.this).create();
        dialog.setView(customView);

        dialog.setCanceledOnTouchOutside(true);
        additionalDate = customView.findViewById(R.id.additionalDate);
        showDate2 = customView.findViewById(R.id.showDate2);

        openCalendar= customView.findViewById(R.id.openCalendar);
        openCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDate();
            }
        });

        openCalendar2= customView.findViewById(R.id.openCalendar2);
        openCalendar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDate();
            }
        });

        Window window = dialog.getWindow();
        window.setGravity(Gravity.CENTER);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        dialog.show();
    }

    private void getDate() {
        Calendar mcurrentDate = Calendar.getInstance();
        mYear = mcurrentDate.get(Calendar.YEAR);

        mMonth = mcurrentDate.get(Calendar.MONTH);

        mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        final Calendar[] myCalendar = new Calendar[1];
        myCalendar[0] = Calendar.getInstance();

        final DatePickerDialog mDatePicker = new DatePickerDialog(CreateReminderMedicationActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                myCalendar[0].set(Calendar.YEAR, selectedyear);
                myCalendar[0].set(Calendar.MONTH, selectedmonth);
                myCalendar[0].set(Calendar.DAY_OF_MONTH, selectedday);

                String myFormat = "MMM dd, yyyy"; //Change as you need
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                //finalDate = sdf.format(myCalendar.getTime());

                additionalDate.setText(sdf.format(myCalendar[0].getTime()));
                showDate2.setText(sdf.format(myCalendar[0].getTime()));

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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.close_topbar:
            onBackPressed();
            break;

            case R.id.done_topbar:
                startActivity(new Intent(CreateReminderMedicationActivity.this, ReminderActivity.class));
                break;

            case R.id.timesAndDaysOfWeek_Tv:
                showTimeAndDayDialog();
                break;
            case R.id.AdditionOption_Tv:
                showAdditionalOptionDialog();
                break;
        }

    }

    private void spinners() {
        Spinner medicationInformationSpinner = findViewById(R.id.medicationInformationSpinner);
        ArrayAdapter<String> medicationInformationAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        medicationInformationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        medicationInformationSpinner.setAdapter(medicationInformationAdapter);
        medicationInformationAdapter.add("Abanatuss PED");
        medicationInformationAdapter.add("Abanatuss PED 1");
        medicationInformationAdapter.add("Abanatuss PED 2");
        medicationInformationAdapter.notifyDataSetChanged();
        medicationInformationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.grey));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Spinner selectDosageSpinner = findViewById(R.id.selectDosageSpinner);
        ArrayAdapter<String> selectDosageAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        selectDosageAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectDosageSpinner.setAdapter(selectDosageAdapter);
        selectDosageAdapter.add("0.5-15-6.25mg/mL");
        selectDosageAdapter.add("0.5-15-6.25mg/mL 1");
        selectDosageAdapter.add("0.5-15-6.25mg/mL 2");
        selectDosageAdapter.notifyDataSetChanged();
        selectDosageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.grey));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

//        Spinner additionalOptionSpinner = findViewById(R.id.additionalOptionSpinner);
//        ArrayAdapter<String> additionalOptionAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
//        additionalOptionAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        additionalOptionSpinner.setAdapter(additionalOptionAdapter);
//        additionalOptionAdapter.add("Additional Option");
//        additionalOptionAdapter.add("Additional Option 1");
//        additionalOptionAdapter.add("Additional Option 2");
//        additionalOptionAdapter.notifyDataSetChanged();
//
//        additionalOptionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.orange));
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

    }


}
