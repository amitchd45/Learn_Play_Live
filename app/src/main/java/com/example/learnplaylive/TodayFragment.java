package com.example.learnplaylive;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.learnplaylive.Actiivties.LogWeightActivity;
import com.example.learnplaylive.HomeBottom.Fitness.FitnessActivity.ExerciseTrackingLogPreviousActivity;


public class TodayFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout logWeightRl, trackRl, mlRl;

    public TodayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_today, container, false);

        intIds(view);
        performActions();

        return view;
    }

    private void intIds(View view) {

        TextView textView=view.findViewById(R.id.tv_header);
        textView.setText("Today");

        ImageView back= view.findViewById(R.id.back_topbar);
        back.setVisibility(View.GONE);
        TextView edit = view.findViewById(R.id.editText_topbar);
        edit.setVisibility(View.VISIBLE);



        logWeightRl = view.findViewById(R.id.logWeightRl);
        trackRl = view.findViewById(R.id.trackRl);
        mlRl = view.findViewById(R.id.mlRl);
        trackRl = view.findViewById(R.id.trackRl);
    }

    private void performActions() {
        logWeightRl.setOnClickListener(this);
        trackRl.setOnClickListener(this);
        mlRl.setOnClickListener(this);
        trackRl.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.logWeightRl:
                startActivity(new Intent(getActivity(), LogWeightActivity.class));
                break;

            case R.id.trackRl:
                startActivity(new Intent(getActivity(), ExerciseTrackingLogPreviousActivity.class));
                break;

            case R.id.mlRl:

                break;
        }
    }
}
