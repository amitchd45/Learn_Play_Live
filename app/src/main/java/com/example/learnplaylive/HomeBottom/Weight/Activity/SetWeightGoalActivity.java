package com.example.learnplaylive.HomeBottom.Weight.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnplaylive.R;

public class SetWeightGoalActivity extends AppCompatActivity implements View.OnClickListener {
    private Button weightGoalBtn, gain_weight, maintain_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_weight_goal);
        findIds();
    }

    private void findIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("");
        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        weightGoalBtn = findViewById(R.id.weightGoalBtn);
        weightGoalBtn.setOnClickListener(this);
        gain_weight = findViewById(R.id.gain_weight);
        gain_weight.setOnClickListener(this);

        maintain_weight = findViewById(R.id.maintain_weight);
        maintain_weight.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar:
                onBackPressed();
                break;

            case R.id.weightGoalBtn:
                startActivity(new Intent(SetWeightGoalActivity.this, WhatIsYourGoalActivity.class));
                break;
            case R.id.gain_weight:
                startActivity(new Intent(SetWeightGoalActivity.this, WhatIsYourGoalActivity.class));
                break;
            case R.id.maintain_weight:
                startActivity(new Intent(SetWeightGoalActivity.this, WhatIsYourGoalActivity.class));
                break;
        }
    }
}
