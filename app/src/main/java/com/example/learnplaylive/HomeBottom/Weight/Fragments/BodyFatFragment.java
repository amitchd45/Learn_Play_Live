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
public class BodyFatFragment extends Fragment implements View.OnClickListener {
    private ImageView body_fat_Iv;


    public BodyFatFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_body_fat, container, false);


        findIds(view);
        return view;
    }

    private void findIds(View view) {
        body_fat_Iv = view.findViewById(R.id.body_fat_Iv);
        body_fat_Iv.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.body_fat_Iv:
                startActivity(new Intent(getActivity(), WeightGraphDetailActivity.class));
                break;
        }
    }

}
