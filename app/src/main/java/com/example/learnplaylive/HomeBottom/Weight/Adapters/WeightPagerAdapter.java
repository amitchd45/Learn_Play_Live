package com.example.learnplaylive.HomeBottom.Weight.Adapters;

import android.content.Context;
import android.view.LayoutInflater;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeightPagerAdapter extends FragmentPagerAdapter {
    private int[] layouts;
    private List<Fragment> list = new ArrayList<>();
    private LayoutInflater layoutInflater;
    private Context context;
//    private Select select;

    public WeightPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return list.size();
    }
    public void addFrag(Fragment fragment){
        list.add(fragment);
    }


}
