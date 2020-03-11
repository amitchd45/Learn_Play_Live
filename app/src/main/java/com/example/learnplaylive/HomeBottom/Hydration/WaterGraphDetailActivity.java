package com.example.learnplaylive.HomeBottom.Hydration;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.learnplaylive.HomeBottom.Hydration.Adapters.WaterGraphPagerAdapter;
import com.example.learnplaylive.HomeBottom.Hydration.Fragments.OneMonthWaterGraphFragment;
import com.example.learnplaylive.HomeBottom.Hydration.Fragments.OneWeekWaterGraphFragment;
import com.example.learnplaylive.HomeBottom.Hydration.Fragments.OneYearWaterGraphFragment;
import com.example.learnplaylive.HomeBottom.Hydration.Fragments.ThreeMonthWaterGraphFragment;
import com.example.learnplaylive.R;
import com.google.android.material.tabs.TabLayout;

public class WaterGraphDetailActivity extends AppCompatActivity implements View.OnClickListener {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView minimizeGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_graph_detail);
        findIds();
        SetUps();
    }

    private void findIds() {
        tabLayout = findViewById(R.id.waterGraphTAbLayout);
        viewPager = findViewById(R.id.waterGraphViewPager);
        minimizeGraph = findViewById(R.id.minimizeGraph);
        minimizeGraph.setOnClickListener(this);
    }

    private void SetUps() {
        OneWeekWaterGraphFragment oneWeekWaterGraphFragment=new OneWeekWaterGraphFragment();
        OneMonthWaterGraphFragment oneMonthWaterGraphFragment=new OneMonthWaterGraphFragment();
        ThreeMonthWaterGraphFragment threeMonthWaterGraphFragment=new ThreeMonthWaterGraphFragment();
        OneYearWaterGraphFragment oneYearWaterGraphFragment = new OneYearWaterGraphFragment();

        WaterGraphPagerAdapter waterGraphPagerAdapter = new WaterGraphPagerAdapter(getSupportFragmentManager());
        waterGraphPagerAdapter.addFrag(oneWeekWaterGraphFragment,"1 WK");
        waterGraphPagerAdapter.addFrag(oneMonthWaterGraphFragment,"1 MO");
        waterGraphPagerAdapter.addFrag(threeMonthWaterGraphFragment,"3 MO");
        waterGraphPagerAdapter.addFrag(oneYearWaterGraphFragment,"1 YR");

        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

        viewPager.setAdapter(waterGraphPagerAdapter);
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
