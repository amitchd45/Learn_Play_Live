package com.example.learnplaylive.HomeBottom.Weight.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.learnplaylive.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class OneWeekWeightGraphFragment extends Fragment {


    public OneWeekWeightGraphFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one_week_weight_graph, container, false);
    }

}
