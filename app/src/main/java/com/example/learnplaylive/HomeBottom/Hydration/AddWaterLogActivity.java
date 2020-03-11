package com.example.learnplaylive.HomeBottom.Hydration;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import com.example.learnplaylive.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;



public class AddWaterLogActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner mlSpinner;
    private TextView glassTv, bottleTv, bottleTv1, datePickerTv;
    private Activity activity;
    private Button SaveBtn;
    private ImageView back;
    private int mYear, mMonth, mDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_water_log);

        activity = AddWaterLogActivity.this;

        intIds();
        performActions();
    }

    private void intIds() {

        TextView textView=findViewById(R.id.tv_header);
        textView.setText("Add water Log");
        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        mlSpinner = findViewById(R.id.mlSpinner);
        glassTv = findViewById(R.id.glassTv);
        bottleTv = findViewById(R.id.bottleTv);
        bottleTv1 = findViewById(R.id.bottleTv1);
        SaveBtn = findViewById(R.id.SaveBtn);
//        back = findViewById(R.id.back);
        datePickerTv = findViewById(R.id.datePickerTv);
    }

    private void performActions() {
        SaveBtn.setOnClickListener(this);
//        back.setOnClickListener(this);
        datePickerTv.setOnClickListener(this);

        String text = "<font color=#000000>1 glass</font><font color=#747474> (250 ml) </font>";
        glassTv.setText(Html.fromHtml(text));

        String text1 = "<font color=#000000>1 bottle</font><font color=#747474> (500 ml) </font>";
        bottleTv.setText(Html.fromHtml(text1));

        String text2 = "<font color=#000000>1 lg bottle</font><font color=#747474> (750 ml) </font>";
        bottleTv1.setText(Html.fromHtml(text2));

        mlSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ((TextView) view).setTextColor(getResources().getColor(R.color.black));
                ((TextView) view).setTextSize(12);
                Typeface typeface = ResourcesCompat.getFont(activity, R.font.poppins);
                ((TextView) view).setTypeface(typeface);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void getDate() {
        Calendar mcurrentDate = Calendar.getInstance();
        mYear = mcurrentDate.get(Calendar.YEAR);

        mMonth = mcurrentDate.get(Calendar.MONTH);

        mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

        final Calendar[] myCalendar = new Calendar[1];
        myCalendar[0] = Calendar.getInstance();

        final DatePickerDialog mDatePicker = new DatePickerDialog(activity, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                myCalendar[0].set(Calendar.YEAR, selectedyear);
                myCalendar[0].set(Calendar.MONTH, selectedmonth);
                myCalendar[0].set(Calendar.DAY_OF_MONTH, selectedday);

                String myFormat = "MMM dd, yyyy"; //Change as you need
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                //finalDate = sdf.format(myCalendar.getTime());

                datePickerTv.setText(sdf.format(myCalendar[0].getTime()));

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
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.SaveBtn:
                startActivity(new Intent(activity, WaterActivity.class));
                break;

            case R.id.back_topbar:
                onBackPressed();
                break;

            case R.id.datePickerTv:
                getDate();
                break;
        }
    }
}
