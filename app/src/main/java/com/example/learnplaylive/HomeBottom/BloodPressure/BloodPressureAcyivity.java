package com.example.learnplaylive.HomeBottom.BloodPressure;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnplaylive.R;

public class BloodPressureAcyivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_pressure_acyivity);

        findIds();
    }

    private void findIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("Blood Pressure");
        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        ImageView stats= findViewById(R.id.stat_topbar);
        stats.setVisibility(View.VISIBLE);
        stats.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.back_topbar:
                onBackPressed();
                break;
            case R.id.stat_topbar:
                startActivity(new Intent(BloodPressureAcyivity.this, BloodPressureStatsActivity.class));
                break;
        }

    }
}
