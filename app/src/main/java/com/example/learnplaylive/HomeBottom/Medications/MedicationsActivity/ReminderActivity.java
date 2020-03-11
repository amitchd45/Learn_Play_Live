package com.example.learnplaylive.HomeBottom.Medications.MedicationsActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.learnplaylive.HomeBottom.Medications.MedicationAdapters.ReminderPagerAdapter;
import com.example.learnplaylive.HomeBottom.Medications.MedicationsFragments.ReminderLifetimeFragment;
import com.example.learnplaylive.HomeBottom.Medications.MedicationsFragments.ReminderMonthFragment;
import com.example.learnplaylive.HomeBottom.Medications.MedicationsFragments.ReminderWeekFragment;
import com.example.learnplaylive.R;
import com.google.android.material.tabs.TabLayout;

public class ReminderActivity extends AppCompatActivity implements View.OnClickListener {

    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        findIds();
        SetUps();
    }

    private void findIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("Reminder");
        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        ImageView close= findViewById(R.id.close_topbar);
        close.setVisibility(View.GONE);
        ImageView done= findViewById(R.id.done_topbar);
        done.setVisibility(View.GONE);
        ImageView edit= findViewById(R.id.edit_topbar);
        edit.setVisibility(View.VISIBLE);
        ImageView delete= findViewById(R.id.delete_topbar);
        delete.setVisibility(View.VISIBLE);
        edit.setOnClickListener(this);
        delete.setOnClickListener(this);

        tabLayout = findViewById(R.id.reminderTAbLayout);
        viewPager = findViewById(R.id.reminderViewPager);

    }

    private void SetUps() {
        ReminderWeekFragment reminderWeekFragment=new ReminderWeekFragment();
        ReminderMonthFragment reminderMonthFragment=new ReminderMonthFragment();
        ReminderLifetimeFragment reminderLifetimeFragment=new ReminderLifetimeFragment();


        ReminderPagerAdapter reminderPagerAdapter = new ReminderPagerAdapter(getSupportFragmentManager());
        reminderPagerAdapter.addFrag(reminderWeekFragment,"Week");
        reminderPagerAdapter.addFrag(reminderMonthFragment,"Month");
        reminderPagerAdapter.addFrag(reminderLifetimeFragment,"Lifetime");

        LinearLayout linearLayout = (LinearLayout) tabLayout.getChildAt(0);
        linearLayout.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);

        viewPager.setAdapter(reminderPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar:
                onBackPressed();
                break;
        }

    }
}
