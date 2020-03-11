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

public class BodyFatPercentageGoalActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_save, btn_skip;
    private RulerValuePicker bodyFatruler_picker;
    private TextView showWeight1;
    private String s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_fat_percentage_goal);
        findIds();
    }

    private void findIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("");
        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        btn_save = findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
        btn_skip = findViewById(R.id.btn_skip);
        btn_skip.setOnClickListener(this);

        showWeight1 = findViewById(R.id.showWeight1);
        bodyFatruler_picker = findViewById(R.id.bodyFatruler_picker);
        bodyFatruler_picker.setValuePickerListener(new RulerValuePickerListener() {
            @Override
            public void onValueChange(int selectedValue) {
                showWeight1.setText(selectedValue+"");
            }

            @Override
            public void onIntermediateValueChange(int selectedValue) {
                showWeight1.setText(selectedValue+"");
                s = String.valueOf(selectedValue);


            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar:
                onBackPressed();
                break;

            case R.id.btn_save:
//                App.getSinltonPojo().setWeightStatus("1");
                App.getSinltonPojo().setWeight1(s);
                startActivity(new Intent(BodyFatPercentageGoalActivity.this, WeightDetailActivity.class));

                break;

            case R.id.btn_skip:
                startActivity(new Intent(BodyFatPercentageGoalActivity.this, WeightDetailActivity.class));
                break;
        }
    }
}
