package com.example.learnplaylive.HomeBottom.Fitness.FitnessActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.learnplaylive.HomeBottom.Fitness.FitnessFragments.ExerciseLogPreviousFragment;
import com.example.learnplaylive.HomeBottom.Fitness.FitnessFragments.ExerciseTrackingFragment;
import com.example.learnplaylive.HomeBottom.Fitness.FitnessAdapter.ExerciseTrackingPagerAdapter;
import com.example.learnplaylive.R;
import com.google.android.material.tabs.TabLayout;

public class ExerciseTrackingLogPreviousActivity extends AppCompatActivity implements View.OnClickListener
{

    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_tracking_log_previous);

        findIds();
        SetUps();

    }

    private void findIds() {



        TextView textView=findViewById(R.id.tv_header);
        textView.setText("");
        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);




        tabLayout = findViewById(R.id.trackingTAbLayout);
        viewPager = findViewById(R.id.trackingViewPager);


    }

    private void SetUps() {
        ExerciseTrackingFragment exerciseTrackingFragment=new ExerciseTrackingFragment();
        ExerciseLogPreviousFragment exerciseLogPreviousFragment=new ExerciseLogPreviousFragment();

        ExerciseTrackingPagerAdapter exerciseTrackingPagerAdapter = new ExerciseTrackingPagerAdapter(getSupportFragmentManager());
        exerciseTrackingPagerAdapter.addFrag(exerciseTrackingFragment,"Tracking");
        exerciseTrackingPagerAdapter.addFrag(exerciseLogPreviousFragment,"Log Previous");

        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

        viewPager.setAdapter(exerciseTrackingPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar:
                onBackPressed();
                break;


        }
    }




}
