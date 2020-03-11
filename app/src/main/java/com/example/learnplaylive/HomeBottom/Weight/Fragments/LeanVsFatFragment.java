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

public class LeanVsFatFragment extends Fragment implements View.OnClickListener {
    private ImageView lean_fat_Iv;


    public LeanVsFatFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lean_vs_fat, container, false);


        findIds(view);
        return view;
    }

    private void findIds(View view) {
        lean_fat_Iv = view.findViewById(R.id.lean_fat_Iv);
        lean_fat_Iv.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lean_fat_Iv:
                startActivity(new Intent(getActivity(), WeightGraphDetailActivity.class));
                break;
        }
    }
}
