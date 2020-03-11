package com.example.learnplaylive.Actiivties;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.bumptech.glide.Glide;
import com.example.learnplaylive.Drawer.ActivityGoalActivity;
import com.example.learnplaylive.Drawer.BlockedUserActivity;
import com.example.learnplaylive.Drawer.ManageDataActivity;
import com.example.learnplaylive.Drawer.NotificationActivity;
import com.example.learnplaylive.Drawer.NutritionAndBodyGoalActivity;
import com.example.learnplaylive.Drawer.PrivacyActivity;
import com.example.learnplaylive.Drawer.SecurityAndLoginActivity;
import com.example.learnplaylive.HomeBottom.Fitness.FitnessActivity.WeeklyExercisesGoalActivity;
import com.example.learnplaylive.LoginActivity;
import com.example.learnplaylive.SharedPreference.App;
import com.example.learnplaylive.TodayFragment;
import com.example.learnplaylive.HealthDataFragment;
import com.example.learnplaylive.HomePageFragment;
import com.example.learnplaylive.Drawer.ProfileActivity;
import com.example.learnplaylive.R;
import com.example.learnplaylive.Drawer.SetUpDeviceActivity;
import com.example.learnplaylive.Drawer.SevenDaySummaryActivity;
import com.infideap.drawerbehavior.AdvanceDrawerLayout;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView bottomFirst, bottomSecond, bottomThird, bottomFourth, bottomFifth, topFirst, topSecond, topThird, topFourth, topFifth;
    private FragmentManager fm;
    private Activity activity;
    private AdvanceDrawerLayout advanceDrawerLayout;
    private Button Hydration;
    private ImageView navigation;
    private TextView ProfileTv, setUpDeviceTv, summaryTv, exerciseTv, activityTv, nutritionTv, privacyTv, securityTv, notificationTv, manageDataTv, blockedTv, logoutTv, NameTv, DateTv;
    private int a;
    private CircleImageView profilePhoto;
    private String joinedDate="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        activity = HomeActivity.this;

        initView();
        SetUps();
        performActions();
    }

    private void initView() {
       // Hydration = findViewById(R.id.Hydration);
        navigation = findViewById(R.id.navigation);
        bottomFirst = findViewById(R.id.bottomFirst);
        bottomSecond = findViewById(R.id.bottomSecond);
        bottomThird = findViewById(R.id.bottomThird);
        bottomFourth = findViewById(R.id.bottomFourth);
        bottomFifth = findViewById(R.id.bottomFifth);

        topFirst = findViewById(R.id.topFirst);
        topSecond = findViewById(R.id.topSecond);
        topThird = findViewById(R.id.topThird);
        topFourth = findViewById(R.id.topFourth);
        topFifth = findViewById(R.id.topFifth);
        advanceDrawerLayout = findViewById(R.id.advanceDrawerLayout);


        ProfileTv = findViewById(R.id.ProfileTv);
        setUpDeviceTv = findViewById(R.id.setUpDeviceTv);
        summaryTv = findViewById(R.id.summaryTv);
        exerciseTv = findViewById(R.id.exerciseTv);
        activityTv = findViewById(R.id.activityTv);
        nutritionTv = findViewById(R.id.nutritionTv);
        privacyTv = findViewById(R.id.privacyTv);
        securityTv = findViewById(R.id.securityTv);
        notificationTv = findViewById(R.id.notificationTv);
        manageDataTv = findViewById(R.id.manageDataTv);
        blockedTv = findViewById(R.id.blockedTv);
        logoutTv = findViewById(R.id.logoutTv);
        profilePhoto = findViewById(R.id.profilePhoto);
        NameTv = findViewById(R.id.NameTv);
        DateTv = findViewById(R.id.DateTv);

//        if (App.getAppPreference().getLoginDetail().getDetails().getLoginType()!=null){
//            if (App.getAppPreference().getLoginDetail().getDetails().getLoginType().equalsIgnoreCase("normal")){
//                Glide.with(HomeActivity.this).load("http://apptech.mobi/learnPlayLiveApplication/"+App.getAppPreference().getLoginDetail().getDetails().getImage()).into(profilePhoto);
//            }
//            else {
//                Glide.with(HomeActivity.this).load(App.getAppPreference().getLoginDetail().getDetails().getImage()).into(profilePhoto);
//            }
//        }

//        Glide.with(HomeActivity.this).load(App.getAppPreference().getLoginDetail().getDetails().getImage()).into(profilePhoto);
//        NameTv.setText(App.getAppPreference().getLoginDetail().getDetails().getName());
//        joinedDate=App.getAppPreference().getLoginDetail().getDetails().getCreated();
//
//        String[] date = joinedDate.split(" ");
//
//        DateTv.setText("Joined "+date[0]);


    }

    private void SetUps() {
        bottomFirst.setOnClickListener(this);
        bottomSecond.setOnClickListener(this);
        bottomThird.setOnClickListener(this);
        bottomFourth.setOnClickListener(this);
        bottomFifth.setOnClickListener(this);
        navigation.setOnClickListener(this);

        ProfileTv.setOnClickListener(this);
        setUpDeviceTv.setOnClickListener(this);
        summaryTv.setOnClickListener(this);
        exerciseTv.setOnClickListener(this);
        activityTv.setOnClickListener(this);
        nutritionTv.setOnClickListener(this);
        privacyTv.setOnClickListener(this);
        securityTv.setOnClickListener(this);
        notificationTv.setOnClickListener(this);
        manageDataTv.setOnClickListener(this);
        blockedTv.setOnClickListener(this);
        logoutTv.setOnClickListener(this);

        fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.container, new HomePageFragment()).commit();

//        a=getIntent().getExtras().getInt("saveLbs");
//        if (a==1){
//            fm.beginTransaction().replace(R.id.container, new TodayFragment()).commit();
//        } else {
//            fm.beginTransaction().replace(R.id.container, new HomePageFragment()).commit();
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.bottomFirst:
                fm.beginTransaction().replace(R.id.container, new HomePageFragment()).commit();
                bottomFirst.setVisibility(View.INVISIBLE);
                bottomSecond.setVisibility(View.VISIBLE);
                bottomThird.setVisibility(View.VISIBLE);
                bottomFourth.setVisibility(View.VISIBLE);
                bottomFifth.setVisibility(View.VISIBLE);

                topFirst.setVisibility(View.VISIBLE);
                topSecond.setVisibility(View.INVISIBLE);
                topThird.setVisibility(View.INVISIBLE);
                topFourth.setVisibility(View.INVISIBLE);
                topFifth.setVisibility(View.INVISIBLE);
                break;

            case R.id.bottomSecond:
                fm.beginTransaction().replace(R.id.container, new TodayFragment()).commit();
                bottomFirst.setVisibility(View.VISIBLE);
                bottomSecond.setVisibility(View.INVISIBLE);
                bottomThird.setVisibility(View.VISIBLE);
                bottomFourth.setVisibility(View.VISIBLE);
                bottomFifth.setVisibility(View.VISIBLE);

                topFirst.setVisibility(View.INVISIBLE);
                topSecond.setVisibility(View.VISIBLE);
                topThird.setVisibility(View.INVISIBLE);
                topFourth.setVisibility(View.INVISIBLE);
                topFifth.setVisibility(View.INVISIBLE);
                break;

            case R.id.bottomThird:
                fm.beginTransaction().replace(R.id.container, new HealthDataFragment()).commit();
                bottomFirst.setVisibility(View.VISIBLE);
                bottomSecond.setVisibility(View.VISIBLE);
                bottomThird.setVisibility(View.INVISIBLE);
                bottomFourth.setVisibility(View.VISIBLE);
                bottomFifth.setVisibility(View.VISIBLE);

                topFirst.setVisibility(View.INVISIBLE);
                topSecond.setVisibility(View.INVISIBLE);
                topThird.setVisibility(View.VISIBLE);
                topFourth.setVisibility(View.INVISIBLE);
                topFifth.setVisibility(View.INVISIBLE);
                break;

            case R.id.bottomFourth:
                fm.beginTransaction().replace(R.id.container, new TodayFragment()).commit();
                bottomFirst.setVisibility(View.VISIBLE);
                bottomSecond.setVisibility(View.VISIBLE);
                bottomThird.setVisibility(View.VISIBLE);
                bottomFourth.setVisibility(View.INVISIBLE);
                bottomFifth.setVisibility(View.VISIBLE);

                topFirst.setVisibility(View.INVISIBLE);
                topSecond.setVisibility(View.INVISIBLE);
                topThird.setVisibility(View.INVISIBLE);
                topFourth.setVisibility(View.VISIBLE);
                topFifth.setVisibility(View.INVISIBLE);
                break;

            case R.id.bottomFifth:
                fm.beginTransaction().replace(R.id.container, new TodayFragment()).commit();
                bottomFirst.setVisibility(View.VISIBLE);
                bottomSecond.setVisibility(View.VISIBLE);
                bottomThird.setVisibility(View.VISIBLE);
                bottomFourth.setVisibility(View.VISIBLE);
                bottomFifth.setVisibility(View.INVISIBLE);

                topFirst.setVisibility(View.INVISIBLE);
                topSecond.setVisibility(View.INVISIBLE);
                topThird.setVisibility(View.INVISIBLE);
                topFourth.setVisibility(View.INVISIBLE);
                topFifth.setVisibility(View.VISIBLE);
                break;

            case R.id.navigation:
                advanceDrawerLayout.openDrawer(Gravity.LEFT);
                break;

            case R.id.ProfileTv:
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
                break;

            case R.id.setUpDeviceTv:
                startActivity(new Intent(HomeActivity.this, SetUpDeviceActivity.class));
                break;
            case R.id.summaryTv:
                startActivity(new Intent(HomeActivity.this, SevenDaySummaryActivity.class));
                break;
            case R.id.exerciseTv:
                startActivity(new Intent(HomeActivity.this, WeeklyExercisesGoalActivity.class));
                break;

            case R.id.activityTv:
                startActivity(new Intent(HomeActivity.this, ActivityGoalActivity.class));
                break;
            case R.id.nutritionTv:
                startActivity(new Intent(HomeActivity.this, NutritionAndBodyGoalActivity.class));
                break;

            case R.id.privacyTv:
                startActivity(new Intent(HomeActivity.this, PrivacyActivity.class));
                break;

            case R.id.securityTv:
                startActivity(new Intent(HomeActivity.this, SecurityAndLoginActivity.class));
                break;

            case R.id.notificationTv:
                startActivity(new Intent(HomeActivity.this, NotificationActivity.class));
                break;
            case R.id.manageDataTv:
                startActivity(new Intent(HomeActivity.this, ManageDataActivity.class));
                break;

            case R.id.blockedTv:
                startActivity(new Intent(HomeActivity.this, BlockedUserActivity.class));
                break;
            case R.id.logoutTv:
                App.getAppPreference().clearAppPreferences();
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                Toast.makeText(activity, "Successfully Logged Out", Toast.LENGTH_SHORT).show();
                finishAffinity();
                break;

        }
    }



    private void performActions() {
//        Hydration.setOnClickListener(this);
//

        advanceDrawerLayout.useCustomBehavior(Gravity.START);
        advanceDrawerLayout.setRadius(Gravity.START, 35);//set end container's corner radius (dimension)
        advanceDrawerLayout.setViewScale(Gravity.START, 0.9f);
        advanceDrawerLayout.setViewElevation(Gravity.START, 20);
    }

}
