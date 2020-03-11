package com.example.learnplaylive.HomeBottom.Weight.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.learnplaylive.R;
import com.example.learnplaylive.SharedPreference.App;
import com.example.learnplaylive.HomeBottom.Weight.Adapters.WeightPagerAdapter;
import com.example.learnplaylive.HomeBottom.Weight.Adapters.WeightWeekAdapter;
import com.example.learnplaylive.HomeBottom.Weight.Fragments.BmiPastFragment;
import com.example.learnplaylive.HomeBottom.Weight.Fragments.BodyFatFragment;
import com.example.learnplaylive.HomeBottom.Weight.Fragments.LeanVsFatFragment;
import com.example.learnplaylive.HomeBottom.Weight.Fragments.WeightTrendsFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import me.relex.circleindicator.CircleIndicator;

public class WeightDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private ViewPager weightfragmentViewPager;
    private CircleIndicator weightcircleindicator;
    int[] layouts = {R.layout.fragment_weight_trends, R.layout.fragment_lean_vs_fat, R.layout.fragment_bmi_past, R.layout.fragment_body_fat};
    private AlertDialog dialog;
    private RecyclerView weightDetailRecyclerView;
    private TextView save_Tv, logWeightRedTv, setGoalTv, selectedPercentage, goalWeightTv;
    private EditText logWeightCalander;
    private int mYear, mMonth, mDay;
    private LinearLayout weightLl, bodyFatLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_detail);

        if (App.getSinltonPojo().getWeightStatus()!=null) {
            if (App.getSinltonPojo().getWeightStatus().equalsIgnoreCase("1")) {
                showBodyFatDialog();
                selectedPercentage.setText(App.getSinltonPojo().getWeight1());

            } else if (App.getSinltonPojo().getWeightStatus().equalsIgnoreCase("2")){
                showBodyFatDialog();
                selectedPercentage.setText(App.getSinltonPojo().getWeight1());
                goalWeightTv.setText(App.getSinltonPojo().getGoalWeight());
            }
            else {

            }
        }
        findIds();
        SetUps();

        weightfragmentViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WeightDetailActivity.this, WeightGraphDetailActivity.class));
            }
        });


    }

    private void findIds() {
        TextView textView=findViewById(R.id.tv_header2);
        textView.setText("Weight");
        ImageView back= findViewById(R.id.back_topbar2);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        ImageView setting= findViewById(R.id.setting_topbar2);
        setting.setVisibility(View.VISIBLE);
        setting.setOnClickListener(this);
        ImageView timer= findViewById(R.id.add_topbar2);
        timer.setVisibility(View.VISIBLE);
        timer.setOnClickListener(this);
        logWeightRedTv = findViewById(R.id.logWeightRedTv);
        logWeightRedTv.setOnClickListener(this);
        setGoalTv = findViewById(R.id.setGoalTv);
        setGoalTv.setOnClickListener(this);


        weightfragmentViewPager = findViewById(R.id.weightfragmentViewPager);
        weightfragmentViewPager.setOnClickListener(this);

        weightcircleindicator = findViewById(R.id.weightcircleindicator);

        weightDetailRecyclerView = findViewById(R.id.weightDetailRecyclerView);

    }

    private void SetUps() {

        WeightPagerAdapter weightPagerAdapter = new WeightPagerAdapter(getSupportFragmentManager());
//
        weightPagerAdapter.addFrag(new WeightTrendsFragment());
        weightPagerAdapter.addFrag(new LeanVsFatFragment());
        weightPagerAdapter.addFrag(new BmiPastFragment());
        weightPagerAdapter.addFrag(new BodyFatFragment());
        weightfragmentViewPager.setAdapter(weightPagerAdapter);
        weightcircleindicator.setViewPager(weightfragmentViewPager);

        WeightWeekAdapter weightWeekAdapter = new WeightWeekAdapter(WeightDetailActivity.this);
        weightDetailRecyclerView.setAdapter(weightWeekAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(WeightDetailActivity.this, LinearLayoutManager.VERTICAL, false);
        weightDetailRecyclerView.setLayoutManager(linearLayoutManager);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar2:
                onBackPressed();
                break;

            case R.id.setting_topbar2:
                showBodyFatDialog();
                break;

            case R.id.add_topbar2:
                showLogWeightDialog();
                break;
            case R.id.logWeightRedTv:
                showLogWeightDialog();
                break;
            case R.id.setGoalTv:
                App.getSinltonPojo().setWeightStatus("3");
                startActivity(new Intent(WeightDetailActivity.this, SetWeightGoalActivity.class));
                break;

//            case R.id.weightfragmentViewPager:
//                startActivity(new Intent(WeightDetailActivity.this, WeightGraphDetailActivity.class));
//                break;
        }
    }
    private void showLogWeightDialog() {


        View customView = LayoutInflater.from(WeightDetailActivity.this).inflate(R.layout.log_weight_popup, null);
        dialog = new AlertDialog.Builder(WeightDetailActivity.this).create();
        dialog.setView(customView);

        dialog.setCanceledOnTouchOutside(true);


        save_Tv = customView.findViewById(R.id.save_Tv);

        logWeightCalander = customView.findViewById(R.id.logWeightCalander);

        logWeightCalander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDate();
            }
        });

        save_Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Window window = dialog.getWindow();
        window.setGravity(Gravity.TOP);

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);



        dialog.show();
    }

    private void showBodyFatDialog() {


        View customView = LayoutInflater.from(WeightDetailActivity.this).inflate(R.layout.weight_goal_popup, null);
        dialog = new AlertDialog.Builder(WeightDetailActivity.this).create();
        dialog.setView(customView);

        dialog.setCanceledOnTouchOutside(true);

        selectedPercentage = customView.findViewById(R.id.selectedPercentage);
        goalWeightTv = customView.findViewById(R.id.goalWeightTv);

        weightLl = customView.findViewById(R.id.weightLl);
        weightLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WeightDetailActivity.this, SetWeightGoalActivity.class));
                App.getSinltonPojo().setWeightStatus("2");
            }
        });
        bodyFatLl = customView.findViewById(R.id.bodyFatLl);
        bodyFatLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                selectedPercentage.setText(App.getSinltonPojo().getWeight1());
                startActivity(new Intent(WeightDetailActivity.this, BodyFatPercentageGoalActivity.class));
                App.getSinltonPojo().setWeightStatus("1");
            }
        });

        Window window = dialog.getWindow();
        window.setGravity(Gravity.TOP);

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

        final DatePickerDialog mDatePicker = new DatePickerDialog(WeightDetailActivity.this, new DatePickerDialog.OnDateSetListener() {
            public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                myCalendar[0].set(Calendar.YEAR, selectedyear);
                myCalendar[0].set(Calendar.MONTH, selectedmonth);
                myCalendar[0].set(Calendar.DAY_OF_MONTH, selectedday);

                String myFormat = "MMM dd, yyyy"; //Change as you need
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.FRANCE);
                //finalDate = sdf.format(myCalendar.getTime());

                logWeightCalander.setText(sdf.format(myCalendar[0].getTime()));

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
}
