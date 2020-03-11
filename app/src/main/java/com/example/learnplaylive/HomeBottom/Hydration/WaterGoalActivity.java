package com.example.learnplaylive.HomeBottom.Hydration;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.learnplaylive.R;

public class WaterGoalActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView waterGoalTv, waterUnitsTv, cancelTv, okTv;
    private Activity activity;
    private AlertDialog dialog;
    private ImageView back;
    private LinearLayout waterGoalLl, waterUnitLl;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_goal);

        activity = WaterGoalActivity.this;

        intIds();
        performActions();
    }

    private void intIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("Water");
        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

//        waterGoalTv = findViewById(R.id.waterGoalTv);
//        waterUnitsTv = findViewById(R.id.waterUnitsTv);

        waterGoalLl = findViewById(R.id.waterGoalLl);
        waterUnitLl = findViewById(R.id.waterUnitLl);

    }

    private void performActions() {
//        waterGoalTv.setOnClickListener(this);
//        waterUnitsTv.setOnClickListener(this);
        waterGoalLl.setOnClickListener(this);
        waterUnitLl.setOnClickListener(this);

    }

    private void showWaterGoalDialog() {

        View customView = LayoutInflater.from(activity).inflate(R.layout.water_goal_popup, null);
        dialog = new AlertDialog.Builder(activity).create();
        dialog.setView(customView);
        dialog.setCanceledOnTouchOutside(false);

        cancelTv = customView.findViewById(R.id.cancelTv);
        cancelTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });

        okTv = customView.findViewById(R.id.okTv);
        okTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    private void showWaterUnitDialog() {

        View customView = LayoutInflater.from(activity).inflate(R.layout.water_unit_popup, null);
        dialog = new AlertDialog.Builder(activity).create();
        dialog.setView(customView);
        dialog.setCanceledOnTouchOutside(true);



        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.waterGoalLl:
                showWaterGoalDialog();
                break;

            case R.id.waterUnitLl:
                showWaterUnitDialog();
                break;

            case R.id.back_topbar:
                onBackPressed();
                break;
        }
    }
}
