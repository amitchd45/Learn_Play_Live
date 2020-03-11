package com.example.learnplaylive.HomeBottom.Weight.Adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeightGraphPagerAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentList = new ArrayList<>();
    private  List<String> titleList = new ArrayList<>();

    public WeightGraphPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFrag(Fragment fragment, String title){
        titleList.add(title);
        fragmentList.add(fragment);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return titleList.get(position);
    }
}
