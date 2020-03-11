package com.example.learnplaylive.HomeBottom.Fitness.FitnessFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.learnplaylive.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;


public class MapFragment extends Fragment implements OnMapReadyCallback, View.OnClickListener {
    private ImageView lock_btn, unlock_btn;
    private RelativeLayout layout;
    private int i=0;
    private GoogleMap googleMap;

    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.ma_frag);
        supportMapFragment.getMapAsync(MapFragment.this);


        findIds(view);
        return view;
    }

    private void findIds(View view) {
        lock_btn =view.findViewById(R.id.lock_btn);
        lock_btn.setVisibility(View.VISIBLE);
        lock_btn.setOnClickListener(this);

        unlock_btn = view.findViewById(R.id.unlock_btn);
        unlock_btn.setOnClickListener(this);

//        layout = view.findViewById(R.id.layout);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.lock_btn:
                unlock_btn.setVisibility(View.VISIBLE);
                lock_btn.setVisibility(View.GONE);
//                googleMap.getUiSettings().setScrollGesturesEnabled(false);

                break;
            case R.id.unlock_btn:
                lock_btn.setVisibility(View.VISIBLE);
                unlock_btn.setVisibility(View.GONE);
                break;
        }
    }

    private void lock(){
//        layout.setVisibility(View.VISIBLE);
//        unlock_btn.setVisibility(View.VISIBLE);
//        lock_btn.setVisibility(View.GONE);


//        if (i==0){
//            layout.setVisibility(View.VISIBLE);
//            unlock_btn.setVisibility(View.VISIBLE);
//            lock_btn.setVisibility(View.GONE);
//            i=1;
//        }else if (i==1){
//            layout.setVisibility(View.GONE);
//            unlock_btn.setVisibility(View.GONE);
//            lock_btn.setVisibility(View.VISIBLE);
//            i=0;
//
//        }
    }
}
