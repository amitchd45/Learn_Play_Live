package com.example.learnplaylive.HomeBottom.Hydration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnplaylive.HomeBottom.Hydration.Adapters.LastWeekAdapter;
import com.example.learnplaylive.HomeBottom.Hydration.Adapters.WaterAdapter;
import com.example.learnplaylive.R;


public class WaterActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView DayRecycler1, DayRecycler2;
    private ImageView addWaterTag, back, maximizeGraph;
    private Activity activity;
    private TextView glassTv, bottleTv, bottleTv1, todayTodayGoalTv;
    private RelativeLayout graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water);

        activity = WaterActivity.this;

        intIds();
        performActions();
        setAdapter();
    }

    private void intIds() {

        TextView textView=findViewById(R.id.tv_header2);
        textView.setText("Water");
        ImageView back= findViewById(R.id.back_topbar2);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        ImageView setting= findViewById(R.id.setting_topbar2);
        setting.setVisibility(View.VISIBLE);
        setting.setOnClickListener(this);

        ImageView add= findViewById(R.id.add_topbar2);
        add.setVisibility(View.VISIBLE);
        add.setOnClickListener(this);

        DayRecycler1 = findViewById(R.id.DayRecycler1);
        DayRecycler2 = findViewById(R.id.DayRecycler2);
//        addWaterTag = findViewById(R.id.addWaterTag);
        glassTv = findViewById(R.id.glassTv);
        bottleTv = findViewById(R.id.bottleTv);
        bottleTv1 = findViewById(R.id.bottleTv1);
        todayTodayGoalTv = findViewById(R.id.todayTodayGoalTv);
        todayTodayGoalTv.setOnClickListener(this);
        graph = findViewById(R.id.graph);
        graph.setOnClickListener(this);
//        maximizeGraph = findViewById(R.id.maximizeGraph);
//        maximizeGraph.setOnClickListener(this);
//        back = findViewById(R.id.back);
    }

    private void performActions() {
//        addWaterTag.setOnClickListener(this);
//        back.setOnClickListener(this);

        String text = "<font color=#000000>1 glass</font><font color=#747474> (250 ml) </font>";
        glassTv.setText(Html.fromHtml(text));

        String text1 = "<font color=#000000>1 bottle</font><font color=#747474> (500 ml) </font>";
        bottleTv.setText(Html.fromHtml(text1));

        String text2 = "<font color=#000000>1 lg bottle</font><font color=#747474> (750 ml) </font>";
        bottleTv1.setText(Html.fromHtml(text2));
    }

    private void setAdapter() {
        WaterAdapter waterAdapter = new WaterAdapter(activity, new WaterAdapter.Select() {
            @Override
            public void Choose(int position) {
                startActivity(new Intent(activity,TodayWaterActivity.class));
            }
        });
        DayRecycler1.setAdapter(waterAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        DayRecycler1.setLayoutManager(linearLayoutManager);

        LastWeekAdapter lastWeekAdapter = new LastWeekAdapter(activity, new LastWeekAdapter.Select() {
            @Override
            public void Choose(int position) {
                startActivity(new Intent(activity,TodayWaterActivity.class));
            }
        });
        DayRecycler2.setAdapter(lastWeekAdapter);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        DayRecycler2.setLayoutManager(linearLayoutManager1);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.add_topbar2:
                startActivity(new Intent(activity, AddWaterLogActivity.class));
                break;

            case R.id.setting_topbar2:
                startActivity(new Intent(activity, WaterGoalActivity.class));
                break;

            case R.id.todayTodayGoalTv:
                startActivity(new Intent(activity,TodayWaterActivity.class));
                break;

            case R.id.back_topbar2:
                onBackPressed();
                break;

            case R.id.graph:
                startActivity(new Intent(activity, WaterGraphDetailActivity.class));
                break;
//            case R.id.maximizeGraph:
//                startActivity(new Intent(activity, WaterGraphDetailActivity.class));
//                break;
        }
    }
}
