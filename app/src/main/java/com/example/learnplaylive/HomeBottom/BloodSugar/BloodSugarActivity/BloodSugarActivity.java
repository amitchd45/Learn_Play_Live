package com.example.learnplaylive.HomeBottom.BloodSugar.BloodSugarActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnplaylive.R;

public class BloodSugarActivity extends AppCompatActivity implements View.OnClickListener {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar);

        findIds();


    }

    private void findIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("Blood Sugar");
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
                startActivity(new Intent(BloodSugarActivity.this, BloodSugarStatsActivity.class));
                break;
        }
    }


}
