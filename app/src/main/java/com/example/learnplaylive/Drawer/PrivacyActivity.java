package com.example.learnplaylive.Drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnplaylive.R;

public class PrivacyActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView picturePrivacyTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy);
        findIds();
    }
    private void findIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("Privacy Settings");

        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        picturePrivacyTv = findViewById(R.id.picturePrivacyTv);
        picturePrivacyTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar:
                onBackPressed();
                break;

            case R.id.picturePrivacyTv:
                startActivity(new Intent(PrivacyActivity.this, PicturePrivacyActivity.class));
                break;


        }
    }
}
