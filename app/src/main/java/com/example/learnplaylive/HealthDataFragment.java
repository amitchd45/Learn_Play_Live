package com.example.learnplaylive;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnplaylive.Adapters.HealthDataAdapter;

public class HealthDataFragment extends Fragment implements View.OnClickListener {
    private RecyclerView healthDataRecyclerView;
    private int[] imgL={R.drawable.blood_pressure_png,R.drawable.heart_rate_png, R.drawable.temperature_png, R.drawable.blood_sugar_png, R.drawable.weight_png};
    private String[] large={"130/85","105", "36.6", "186","167.0"};
    private String[] small={"mmHg","BPM", "c", "mg/dl", "lb"};
    private String[] type={"Blood Pressure","Heart Rate", "Temperature", "Blood Sugar", "Weight"};
    private String[] normal={"120/80","80/100", "36.6c", "100 mg/dl","110 lb"};



    public HealthDataFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health_data, container, false);

        findIds(view);
        return view;
    }

    private void findIds(View view) {
        TextView textView=view.findViewById(R.id.tv_header2);
        textView.setText("Health Data");

        ImageView back= view.findViewById(R.id.back_topbar2);
        back.setVisibility(View.GONE);

        healthDataRecyclerView = view.findViewById(R.id.healthDataRecyclerView);
        healthDataRecyclerView.setOnClickListener(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        healthDataRecyclerView.setLayoutManager(linearLayoutManager);
        HealthDataAdapter healthDataAdapter = new HealthDataAdapter(getContext(),imgL,large,small,type,normal);
        healthDataRecyclerView.setAdapter(healthDataAdapter);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {


        }
    }

}
