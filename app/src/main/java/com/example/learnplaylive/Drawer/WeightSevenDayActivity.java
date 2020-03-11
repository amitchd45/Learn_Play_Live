package com.example.learnplaylive.Drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnplaylive.R;

public class WeightSevenDayActivity extends AppCompatActivity implements View.OnClickListener {
    private Button editWeightGoalBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_seven_day);
        findIds();

    }

    private void findIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("");

        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        editWeightGoalBtn = findViewById(R.id.editWeightGoalBtn);
        editWeightGoalBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar:
                onBackPressed();
                break;

            case R.id.editWeightGoalBtn:
                startActivity(new Intent(WeightSevenDayActivity.this, EditWeightGoalActivity.class));
                break;
        }
    }
}
