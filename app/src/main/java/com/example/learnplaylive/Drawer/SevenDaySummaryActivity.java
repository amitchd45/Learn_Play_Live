package com.example.learnplaylive.Drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.learnplaylive.R;

public class SevenDaySummaryActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout trackSEvenRl, logWeightSevenRl, logFoodSevenRl, mlSevenRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seven_day_summary);

        findIds();
    }

    private void findIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("7-Day Summary");

        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        trackSEvenRl = findViewById(R.id.trackSEvenRl);
        trackSEvenRl.setOnClickListener(this);

        logWeightSevenRl =findViewById(R.id.logWeightSevenRl);
        logWeightSevenRl.setOnClickListener(this);

        logFoodSevenRl = findViewById(R.id.logFoodSevenRl);
        logFoodSevenRl.setOnClickListener(this);

        mlSevenRl = findViewById(R.id.mlSevenRl);
        mlSevenRl.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar:
                onBackPressed();
                break;

            case R.id.trackSEvenRl:
                startActivity(new Intent(SevenDaySummaryActivity.this, StepsSevenDayActivity.class));
                break;

            case R.id.logWeightSevenRl:
                startActivity(new Intent(SevenDaySummaryActivity.this, WeightSevenDayActivity.class));
                break;

            case R.id.logFoodSevenRl:
                startActivity(new Intent(SevenDaySummaryActivity.this, FoodLoggedSevenDayActivity.class));
                break;

            case R.id.mlSevenRl:
                startActivity(new Intent(SevenDaySummaryActivity.this, WaterSevenDayActivity.class));
                break;
        }
    }
}
