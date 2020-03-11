package com.example.learnplaylive.HomeBottom.Fitness.FitnessFragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.learnplaylive.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseDurationFragment extends Fragment {


    public ExerciseDurationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_exercise_duration, container, false);
    }

}
