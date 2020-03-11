package com.example.learnplaylive.HomeBottom.Medications.MedicationsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.learnplaylive.R;

public class MedicationsActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private AdapterMedicationsActivity adapterMedicationsActivity;
    private String[] list= {};
    private TextView tvHeader;
    private ImageView backimg;
    private RelativeLayout BtnAddReminder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medications);

        initIds();
        RecyclerMethod();
        tvHeader.setText("Medications");
    }

    private void initIds() {

        recyclerView=findViewById(R.id.recycler_medicationspageactivity);
        tvHeader=findViewById(R.id.tv_header);
        backimg=findViewById(R.id.back_topbar);
        backimg.setOnClickListener(this);
        BtnAddReminder=findViewById(R.id.BtnAddReminder);
        BtnAddReminder.setOnClickListener(this);

    }

    private void RecyclerMethod(){
        recyclerView.setLayoutManager(new LinearLayoutManager(MedicationsActivity.this));
        adapterMedicationsActivity=new AdapterMedicationsActivity(list);
        recyclerView.setAdapter(adapterMedicationsActivity);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar:
                onBackPressed();
                break;
            case R.id.BtnAddReminder:
                startActivity(new Intent(MedicationsActivity.this, CreateReminderMedicationActivity.class));
                break;
        }
    }
}
