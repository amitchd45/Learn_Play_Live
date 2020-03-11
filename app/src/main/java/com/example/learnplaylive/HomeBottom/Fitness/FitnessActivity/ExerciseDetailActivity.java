package com.example.learnplaylive.HomeBottom.Fitness.FitnessActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnplaylive.HomeBottom.Fitness.FitnessAdapter.ExercisePagerAdapter;
import com.example.learnplaylive.R;

import me.relex.circleindicator.CircleIndicator;

public class ExerciseDetailActivity extends AppCompatActivity implements View.OnClickListener {


    private ViewPager fragmentViewPager;
    private CircleIndicator circleindicator;
    int[] layouts = {R.layout.fragment_exercise_starting, R.layout.fragment_exercise_weekdays, R.layout.fragment_exercise_duration, R.layout.fragment_exercise_distance, R.layout.fragment_exercise_calories};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_detail);

        findIds();
        SetUps();
    }

    private void findIds() {
        TextView textView=findViewById(R.id.tv_header2);
        textView.setText("");
        ImageView back= findViewById(R.id.back_topbar2);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        ImageView setting= findViewById(R.id.setting_topbar2);
        setting.setVisibility(View.VISIBLE);
        setting.setOnClickListener(this);
        ImageView timer= findViewById(R.id.timer_topbar2);
        timer.setVisibility(View.VISIBLE);
        timer.setOnClickListener(this);

        fragmentViewPager = findViewById(R.id.fragmentViewPager);
        circleindicator = findViewById(R.id.circleindicator);
    }

     private void SetUps() {

         ExercisePagerAdapter exercisePagerAdapter ;
         exercisePagerAdapter = new ExercisePagerAdapter(layouts, ExerciseDetailActivity.this);
         fragmentViewPager.setAdapter(exercisePagerAdapter);
         circleindicator.setViewPager(fragmentViewPager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar2:
                onBackPressed();
                break;

            case R.id.setting_topbar2:

                startActivity(new Intent(ExerciseDetailActivity.this, WeeklyExercisesGoalActivity.class));
                break;

            case R.id.timer_topbar2:

                startActivity(new Intent(ExerciseDetailActivity.this,ExerciseTrackingLogPreviousActivity.class));
                break;
        }
    }
}
