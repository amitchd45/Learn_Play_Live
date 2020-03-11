package com.example.learnplaylive.Actiivties;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.learnplaylive.R;

public class LogWeightActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_weight);

        intIds();
        performActions();
    }

    private void intIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("Log Weight");

        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        TextView save = findViewById(R.id.saveText_topbar);
        save.setVisibility(View.VISIBLE);
        save.setOnClickListener(this);

//        back = findViewById(R.id.back);
    }

    private void performActions() {
//        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.back_topbar:
                onBackPressed();
                break;

            case R.id.saveText_topbar:
//                startActivity(new Intent(LogWeightActivity.this, TodayFragment.class));
                Intent intent = new Intent(LogWeightActivity.this, HomeActivity.class);
                intent.putExtra("saveLbs",1);
                startActivity(intent);

                break;
        }
    }
}
