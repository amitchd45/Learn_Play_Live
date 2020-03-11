package com.example.learnplaylive.HomeBottom.Learn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnplaylive.R;

public class LearnActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView LearnRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        findIds();
        setUps();
    }


    private void findIds() {
        TextView textView = findViewById(R.id.tv_header);
        textView.setText("Learn");

        ImageView back = findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        LearnRecyclerView = findViewById(R.id.LearnRecyclerView);

    }

    private void setUps() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        LearnRecyclerView.setLayoutManager(linearLayoutManager);
        LearnAdapter learnAdapter = new LearnAdapter(this);
        LearnRecyclerView.setAdapter(learnAdapter);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case
                R.id.back_topbar:
                onBackPressed();
            break;


        }
    }
}
