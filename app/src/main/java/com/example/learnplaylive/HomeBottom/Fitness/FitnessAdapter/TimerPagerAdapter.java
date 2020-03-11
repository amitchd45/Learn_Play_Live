package com.example.learnplaylive.HomeBottom.Fitness.FitnessAdapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TimerPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> list = new ArrayList<>();

    public TimerPagerAdapter(FragmentManager fm) {
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
//    private int[] layouts;
//    private LayoutInflater layoutInflater;
//    private Context context;
//
//    public TimerPagerAdapter(int[] layouts, Context context) {
//        this.layouts = layouts;
//        this.context = context;
//        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//    }
//
//    @Override
//    public int getCount() {
//        return layouts.length;
//    }
//
//    @Override
//    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//        return view == object;
//    }
//
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        View v = layoutInflater.inflate(layouts[position], container, false);
//        container.addView(v);
//        return v;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        container.removeView((View) object);
//
//    }
}
