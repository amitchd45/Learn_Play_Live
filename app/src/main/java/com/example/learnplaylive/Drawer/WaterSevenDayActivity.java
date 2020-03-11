package com.example.learnplaylive.Drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnplaylive.R;

public class WaterSevenDayActivity extends AppCompatActivity  implements View.OnClickListener {
    private Button editWaterGoalBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_seven_day);

        findIds();
    }

    private void findIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("");

        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        editWaterGoalBtn = findViewById(R.id.editWaterGoalBtn);
        editWaterGoalBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar:
                onBackPressed();
                break;

            case R.id.editWaterGoalBtn:
                startActivity(new Intent(WaterSevenDayActivity.this, EditWaterGoalActivity.class));
                break;
        }
    }
}
