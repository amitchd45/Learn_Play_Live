package com.example.learnplaylive.HomeBottom.Fitness.FitnessFragments;


import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.example.learnplaylive.R;
import com.example.learnplaylive.HomeBottom.Fitness.FitnessActivity.TimerExerciseActivity;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ExerciseTrackingFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    private ImageView iv_tracking;
    private LayoutInflater layoutInflater;
    private RelativeLayout horn_text3, on_off, rl_dialog;
    private Switch tracking_switch;


    public ExerciseTrackingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_exercise_tracking, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map_tracking_frag);
        supportMapFragment.getMapAsync(ExerciseTrackingFragment.this);

        findIds(view);
        spinners(view);
        return view;
    }

    private void findIds(View view) {
        iv_tracking = view.findViewById(R.id.iv_tracking);
        iv_tracking.setOnClickListener(this);

        horn_text3 = view.findViewById(R.id.horn_text3);

        rl_dialog = view.findViewById(R.id.rl_dialog);
        rl_dialog.setOnClickListener(this);

        tracking_switch = view.findViewById(R.id.tracking_switch);
        tracking_switch.setOnCheckedChangeListener(this);

//        rl_dialog = view.findViewById(R.id.rl_dialog);

//        if (tracking_switch.isActivated()){
//            horn_text3.setVisibility(View.VISIBLE);
//        }else {
//            horn_text3.setVisibility(View.GONE);
//        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    private void spinners(View view) {
        Spinner tracking_spinner = view.findViewById(R.id.tracking_spinner);
        ArrayAdapter<String> tracking_spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, android.R.id.text1);
        tracking_spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tracking_spinner.setAdapter(tracking_spinnerAdapter);
        tracking_spinnerAdapter.add("Hike");
        tracking_spinnerAdapter.add("Running");
        tracking_spinnerAdapter.add("Jumping");
        tracking_spinnerAdapter.notifyDataSetChanged();
        tracking_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.grey));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        Spinner tracking_minute_spinner = view.findViewById(R.id.tracking_minute_spinner);
        ArrayAdapter<String> tracking_minute_spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, android.R.id.text1);
        tracking_minute_spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tracking_minute_spinner.setAdapter(tracking_minute_spinnerAdapter);
        tracking_minute_spinnerAdapter.add("1");
        tracking_minute_spinnerAdapter.add("2");
        tracking_minute_spinnerAdapter.add("3");
        tracking_minute_spinnerAdapter.notifyDataSetChanged();
        tracking_minute_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.grey));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        Spinner tracking_minute_miles_spinner = view.findViewById(R.id.tracking_minute_miles_spinner);
        ArrayAdapter<String> tracking_minute_miles_spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, android.R.id.text1);
        tracking_minute_miles_spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tracking_minute_miles_spinner.setAdapter(tracking_minute_miles_spinnerAdapter);
        tracking_minute_miles_spinnerAdapter.add("Minute");
        tracking_minute_miles_spinnerAdapter.add("Miles");
        tracking_minute_miles_spinnerAdapter.notifyDataSetChanged();

        tracking_minute_miles_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.grey));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void dialog(View view)
    {
        layoutInflater = LayoutInflater.from(getContext());
        final View trackingDialog = layoutInflater.inflate(R.layout.exercise_tracking_dialog, null);
        final AlertDialog dailogbox = new AlertDialog.Builder(getContext()).create();
        dailogbox.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dailogbox.setCancelable(true);
        dailogbox.setView(trackingDialog);

//        trackingDialog.findViewById(R.id.btn_done_dialog).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent=new Intent(ExerciseTrackingLogPreviousActivity.this,SelectRide.class);
//                intent.putExtra("type",0);
//                startActivity(intent);
////                       startActivity(new Intent(OTPVerificationActivity.this, SelectRide.class));
//                finishAffinity();

//            }
//        });
        dailogbox.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_dialog:
                dialog(view);
                break;

            case R.id.iv_tracking:
                startActivity(new Intent(getActivity(), TimerExerciseActivity.class));
                getActivity().finish();
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        if (b){
            horn_text3.setVisibility(View.VISIBLE);
        }else {
            horn_text3.setVisibility(View.GONE);
        }
    }
}
