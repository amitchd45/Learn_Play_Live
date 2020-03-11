package com.example.learnplaylive.HomeBottom.Weight.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.learnplaylive.R;
import com.example.learnplaylive.HomeBottom.Weight.Adapters.WeightGraphPagerAdapter;
import com.example.learnplaylive.HomeBottom.Weight.Fragments.OneMonthWeightGraphFragment;
import com.example.learnplaylive.HomeBottom.Weight.Fragments.OneWeekWeightGraphFragment;
import com.example.learnplaylive.HomeBottom.Weight.Fragments.OneYearWeightGraphFragment;
import com.example.learnplaylive.HomeBottom.Weight.Fragments.ThreeMonthWeightGraphFragment;
import com.google.android.material.tabs.TabLayout;

public class WeightGraphDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView minimizeGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weight_graph_detail);
        findIds();
        SetUps();
    }
    private void findIds() {
        tabLayout = findViewById(R.id.weightGraphTAbLayout);
        viewPager = findViewById(R.id.weightGraphViewPager);
        minimizeGraph = findViewById(R.id.minimizeGraph);
        minimizeGraph.setOnClickListener(this);
    }

    private void SetUps() {
        OneWeekWeightGraphFragment oneWeekWeightGraphFragment=new OneWeekWeightGraphFragment();
        OneMonthWeightGraphFragment oneMonthWeightGraphFragment=new OneMonthWeightGraphFragment();
        ThreeMonthWeightGraphFragment threeMonthWeightGraphFragment=new ThreeMonthWeightGraphFragment();
        OneYearWeightGraphFragment oneYearWeightGraphFragment = new OneYearWeightGraphFragment();

        WeightGraphPagerAdapter weightGraphPagerAdapter = new WeightGraphPagerAdapter(getSupportFragmentManager());
        weightGraphPagerAdapter.addFrag(oneWeekWeightGraphFragment,"1 WK");
        weightGraphPagerAdapter.addFrag(oneMonthWeightGraphFragment,"1 MO");
        weightGraphPagerAdapter.addFrag(threeMonthWeightGraphFragment,"3 MO");
        weightGraphPagerAdapter.addFrag(oneYearWeightGraphFragment,"1 YR");

        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

        viewPager.setAdapter(weightGraphPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.minimizeGraph:
                onBackPressed();
                break;
        }
    }
}
