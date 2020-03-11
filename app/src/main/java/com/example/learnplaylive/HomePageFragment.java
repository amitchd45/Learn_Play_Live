package com.example.learnplaylive;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.learnplaylive.HomeBottom.Hydration.HydrationActivity;
import com.example.learnplaylive.HomeBottom.BloodPressure.BloodPressureAcyivity;
import com.example.learnplaylive.HomeBottom.BloodSugar.BloodSugarActivity.BloodSugarActivity;
import com.example.learnplaylive.HomeBottom.Fitness.FitnessActivity.FitnessHomePageActivity;
import com.example.learnplaylive.HomeBottom.Learn.LearnActivity;
import com.example.learnplaylive.HomeBottom.Medications.MedicationsActivity.MedicationsActivity;
import com.example.learnplaylive.HomeBottom.Weight.Activity.WeightActivity;


public class HomePageFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout bloodPressure, bloodsugar, medications, fitness, hydration, learn, weightRl;


    public HomePageFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home_page, container, false);

        initIds(view);

        return view;
    }

    private void initIds(View view) {

        medications=view.findViewById(R.id.medications);
        medications.setOnClickListener(this);

        bloodsugar= view.findViewById(R.id.bloodsugar);
        bloodsugar.setOnClickListener(this);

        bloodPressure = view.findViewById(R.id.bloodPressure);
        bloodPressure.setOnClickListener(this);

        fitness = view.findViewById(R.id.fitness);
        fitness.setOnClickListener(this);

        hydration = view.findViewById(R.id.hydration);
        hydration.setOnClickListener(this);

        learn = view.findViewById(R.id.learn);
        learn.setOnClickListener(this);

        weightRl = view.findViewById(R.id.weightRl);
        weightRl.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.medications:
                startActivity(new Intent(getActivity(), MedicationsActivity.class));
                break;
            case R.id.bloodsugar:
                startActivity(new Intent(getActivity(), BloodSugarActivity.class));
                break;
            case R.id.bloodPressure:
                startActivity(new Intent(getActivity(), BloodPressureAcyivity.class));
                break;
            case R.id.fitness:
                startActivity(new Intent(getActivity(), FitnessHomePageActivity.class));
                break;
            case R.id.hydration:
                startActivity(new Intent(getActivity(), HydrationActivity.class));
                break;
            case R.id.learn:
                startActivity(new Intent(getActivity(), LearnActivity.class));
                break;
            case R.id.weightRl:
                startActivity(new Intent(getActivity(), WeightActivity.class));
                break;

        }
    }

}
