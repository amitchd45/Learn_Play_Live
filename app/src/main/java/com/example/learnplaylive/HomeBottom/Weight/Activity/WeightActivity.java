package com.example.learnplaylive.HomeBottom.Weight.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnplaylive.R;

public class WeightActivity extends AppCompatActivity  implements View.OnClickListener {
    private Button openWeightBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight);
        findIds();
    }

    private void findIds() {
        TextView textView=findViewById(R.id.tv_header2);
        textView.setText("");
        ImageView back= findViewById(R.id.back_topbar2);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        openWeightBtn = findViewById(R.id.openWeightBtn);
        openWeightBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar2:
                onBackPressed();
                break;

            case R.id.openWeightBtn:
                startActivity(new Intent(WeightActivity.this, WeightDetailActivity.class));
                break;
        }
    }
}
