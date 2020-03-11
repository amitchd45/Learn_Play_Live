package com.example.learnplaylive.HomeBottom.Weight.Fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.learnplaylive.R;
import com.example.learnplaylive.HomeBottom.Weight.Activity.WeightGraphDetailActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeightTrendsFragment extends Fragment  implements View.OnClickListener {
    private ImageView weight_trend_Iv;


    public WeightTrendsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weight_trends, container, false);


        findIds(view);
        return view;
    }

    private void findIds(View view) {
        weight_trend_Iv = view.findViewById(R.id.weight_trend_Iv);
        weight_trend_Iv.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.weight_trend_Iv:
                startActivity(new Intent(getActivity(), WeightGraphDetailActivity.class));
                break;
        }
    }
}
