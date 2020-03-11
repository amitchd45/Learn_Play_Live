package com.example.learnplaylive.HomeBottom.BloodPressure;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnplaylive.R;

public class BloodPressureStatsActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure_stats);
        findIds();
    }

    private void findIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("Blood Pressure Stats");
        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
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
