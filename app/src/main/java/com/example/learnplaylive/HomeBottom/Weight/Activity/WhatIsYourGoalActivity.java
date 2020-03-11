package com.example.learnplaylive.HomeBottom.Weight.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnplaylive.R;
import com.example.learnplaylive.SharedPreference.App;
import com.kevalpatel2106.rulerpicker.RulerValuePicker;
import com.kevalpatel2106.rulerpicker.RulerValuePickerListener;

public class WhatIsYourGoalActivity extends AppCompatActivity implements View.OnClickListener {
    private RulerValuePicker ruler_picker;
    private TextView showWeight;
    private Button btn_next;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_what_is_your_goal);
        findIds();
    }

    private void findIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("");
        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        btn_next =findViewById(R.id.btn_next);
        btn_next.setOnClickListener(this);

        showWeight = findViewById(R.id.showWeight);
        ruler_picker = findViewById(R.id.ruler_picker);
        ruler_picker.setValuePickerListener(new RulerValuePickerListener() {
            @Override
            public void onValueChange(int selectedValue) {
                showWeight.setText(selectedValue+"");
            }

            @Override
            public void onIntermediateValueChange(int selectedValue) {
                showWeight.setText(selectedValue+"");
                s= String.valueOf(selectedValue);

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar:
                onBackPressed();
                break;

            case R.id.weightGoalBtn:
                startActivity(new Intent(WhatIsYourGoalActivity.this, WhatIsYourGoalActivity.class));
                break;

            case R.id.btn_next:
                App.getSinltonPojo().setGoalWeight(s);
                startActivity(new Intent(WhatIsYourGoalActivity.this, BodyFatPercentageGoalActivity.class));
                break;
        }
    }
}
