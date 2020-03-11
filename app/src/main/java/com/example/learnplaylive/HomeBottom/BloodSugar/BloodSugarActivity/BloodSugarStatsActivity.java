package com.example.learnplaylive.HomeBottom.BloodSugar.BloodSugarActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.learnplaylive.R;

public class BloodSugarStatsActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout before, after, gen;
    private RelativeLayout toolbar;
    private ImageView iv_before, iv_after, iv_beforeCircle, iv_afterCircle, iv_notagCircle;
    private TextView tv_before, tv_after, tv_tag,tv_gen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_sugar_stats);
        findIds();
        beforeMeal();
    }

    private void findIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("Blood Sugar Stats");
        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        before = findViewById(R.id.meal1);
        before.setOnClickListener(this);
        after = findViewById(R.id.cent_ll);
        after.setOnClickListener(this);
        gen = findViewById(R.id.meal3);
        gen.setOnClickListener(this);
        tv_after=findViewById(R.id.tv_after_meal);
        tv_before=findViewById(R.id.tv_before_meal);
        tv_tag=findViewById(R.id.tv_no_tag);
        tv_gen=findViewById(R.id.tv_gen1);

        iv_after=findViewById(R.id.iv_after_mral);
        iv_before=findViewById(R.id.iv_before_meals);

        iv_notagCircle=findViewById(R.id.iv_gen_circle);
        iv_afterCircle=findViewById(R.id.iv_after_circle);
        iv_beforeCircle=findViewById(R.id.iv_circle_before);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar:
                onBackPressed();
                break;
            case R.id.meal1:
                beforeMeal();

                break;

            case R.id.cent_ll:
                afterMeal();

                break;

            case R.id.meal3:
                genMeal();

                break;

        }
    }
    private void beforeMeal() {
        tv_gen.setTextColor(getResources().getColor(R.color.black));
        before.setBackgroundResource(R.drawable.analysis_lay_bg_onclick);
        after.setBackgroundResource(R.drawable.curved_green_outline);
        gen.setBackgroundResource(R.drawable.curved_green_outline);
        tv_before.setTextColor(getResources().getColor(R.color.white));
        tv_after.setTextColor(getResources().getColor(R.color.black));
        tv_tag.setTextColor(getResources().getColor(R.color.black));
        iv_before.setBackgroundResource(R.drawable.meal);
        iv_after.setBackgroundResource(R.drawable.meal_2);
        iv_beforeCircle.setBackgroundResource(R.drawable.ic_white_circle_small);
        iv_afterCircle.setBackgroundResource(R.drawable.circle_green);
        iv_notagCircle.setBackgroundResource(R.drawable.circle_green);

    }
    private void afterMeal() {

        tv_gen.setTextColor(getResources().getColor(R.color.black));
        before.setBackgroundResource(R.drawable.curved_green_outline);
        after.setBackgroundResource(R.drawable.analysis_lay_bg_onclick);
        gen.setBackgroundResource(R.drawable.curved_green_outline);
        tv_tag.setTextColor(getResources().getColor(R.color.black));
        tv_before.setTextColor(getResources().getColor(R.color.black));
        tv_after.setTextColor(getResources().getColor(R.color.white));
        iv_after.setBackgroundResource(R.drawable.meal);
        iv_before.setBackgroundResource(R.drawable.meal_2);
        iv_notagCircle.setBackgroundResource(R.drawable.circle_green);
        iv_beforeCircle.setBackgroundResource(R.drawable.circle_green);
        iv_afterCircle.setBackgroundResource(R.drawable.ic_white_circle_small);
    }
    private void genMeal() {
        before.setBackgroundResource(R.drawable.curved_green_outline);
        after.setBackgroundResource(R.drawable.curved_green_outline);
        gen.setBackgroundResource(R.drawable.analysis_lay_bg_onclick);
        tv_after.setTextColor(getResources().getColor(R.color.black));
        tv_before.setTextColor(getResources().getColor(R.color.black));
        tv_tag.setTextColor(getResources().getColor(R.color.white));
        tv_gen.setTextColor(getResources().getColor(R.color.white));
        iv_after.setBackgroundResource(R.drawable.meal_2);
        iv_before.setBackgroundResource(R.drawable.meal_2);
        iv_afterCircle.setBackgroundResource(R.drawable.circle_green);
        iv_beforeCircle.setBackgroundResource(R.drawable.circle_green);
        iv_notagCircle.setBackgroundResource(R.drawable.ic_white_circle_small);


    }
}
