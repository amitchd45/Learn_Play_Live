package com.example.learnplaylive.HomeBottom.Medications.MedicationsFragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.learnplaylive.R;


public class ReminderLifetimeFragment extends Fragment {

    public ReminderLifetimeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reminder_lifetime, container, false);
    }

}