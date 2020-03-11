package com.example.learnplaylive.HomeBottom.Hydration;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnplaylive.Adapters.LogHistoryAdapter;
import com.example.learnplaylive.R;

public class TodayWaterActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView logHistoryRecycler;
    private Activity activity;
    private TextView goalTv;
    private ImageView back;
    private RelativeLayout waterRl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        activity = TodayWaterActivity.this;

        intIds();
        performActions();
        setAdapter();
    }

    private void intIds() {

        TextView textView=findViewById(R.id.tv_header);
        textView.setText("Today");
        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        logHistoryRecycler = findViewById(R.id.logHistoryRecycler);
//        goalTv = findViewById(R.id.goalTv);
        waterRl = findViewById(R.id.waterRl);
//        back = findViewById(R.id.back);
    }

    private void performActions() {
//        goalTv.setOnClickListener(this);
        waterRl.setOnClickListener(this);
//        back.setOnClickListener(this);
    }

    private void setAdapter() {
        LogHistoryAdapter logHistoryAdapter = new LogHistoryAdapter(activity);
        logHistoryRecycler.setAdapter(logHistoryAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        logHistoryRecycler.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

//            case R.id.waterRl:
//                startActivity(new Intent(activity, WaterGoalActivity.class));
//                break;

            case R.id.back_topbar:
                onBackPressed();
                break;
        }
    }
}
