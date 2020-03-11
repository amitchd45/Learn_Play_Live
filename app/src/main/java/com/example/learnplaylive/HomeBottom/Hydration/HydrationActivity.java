package com.example.learnplaylive.HomeBottom.Hydration;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learnplaylive.R;

public class HydrationActivity extends AppCompatActivity implements View.OnClickListener {
    private Button OpenBtn;
    private ImageView back;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hydration);

        activity = HydrationActivity.this;

        intIds();
        performActions();
    }

    private void intIds() {

        TextView textView=findViewById(R.id.tv_header2);
        textView.setText("");
        ImageView back= findViewById(R.id.back_topbar2);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
//        back = findViewById(R.id.back);
        OpenBtn = findViewById(R.id.OpenBtn);
    }

    private void performActions() {
//        back.setOnClickListener(this);
        OpenBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.back_topbar2:
                onBackPressed();
                break;

            case R.id.OpenBtn:
                startActivity(new Intent(activity, WaterActivity.class));
                break;
        }
    }
}
