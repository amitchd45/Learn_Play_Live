package com.example.learnplaylive.HomeBottom.Fitness.FitnessActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnplaylive.HomeBottom.Fitness.FitnessAdapter.PointAdapter;
import com.example.learnplaylive.Model.SubCategoryDetailModel;
import com.example.learnplaylive.Model.SubCategoryModel;
import com.example.learnplaylive.Model.SubCategoryStatusModel;
import com.example.learnplaylive.R;
import com.example.learnplaylive.SharedPreference.App;
import com.example.learnplaylive.SharedPreference.AppConstants;
import com.example.learnplaylive.SharedPreference.CommonUtils;
import com.example.learnplaylive.ViewModel.FitnessViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FitnessExerciseActivity extends AppCompatActivity implements View.OnClickListener {
    private Button openExerciseBtn, addExerciseBtn;
    private TextView tt_title, tv_title, Tv_note, point1, point2, point3, health_exercise, addedText;
    private String exercise="";
    private ImageView exercise_bg_img;
    private FitnessViewModel viewModel;
    private Activity activity;
    private ImageView color_img;
    private RecyclerView pointRecyclerView;
    private List<String> points = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_exercise);
        viewModel = ViewModelProviders.of(this).get(FitnessViewModel.class);

        findIds();

    }

    private void findIds() {
        activity = FitnessExerciseActivity.this;

        TextView textView=findViewById(R.id.tv_header2);
        textView.setText("");
        ImageView back= findViewById(R.id.back_topbar2);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        openExerciseBtn = findViewById(R.id.openExerciseBtn);
        openExerciseBtn.setOnClickListener(this);
        addExerciseBtn = findViewById(R.id.addExerciseBtn);
        addExerciseBtn.setOnClickListener(this);
        color_img = findViewById(R.id.color_img);
        addedText = findViewById(R.id.addedText);

        tt_title = findViewById(R.id.tt_title);
        exercise_bg_img = findViewById(R.id.exercise_bg_img);
        tv_title = findViewById(R.id.tv_title);
        Tv_note = findViewById(R.id.Tv_note);
//        point1 = findViewById(R.id.point1);
//        point2 = findViewById(R.id.point2);
        point3 = findViewById(R.id.point3);
        health_exercise = findViewById(R.id.health_exercise);

        pointRecyclerView = findViewById(R.id.pointRecyclerView);


        getData();
    }

    private void getData() {

        final String subCategoryId = getIntent().getExtras().getString("subCategoryId");
        String userId = App.getAppPreference().getLoginDetail().getDetails().getId();

        viewModel.subcategoryDetailList(activity,subCategoryId,userId).observe(FitnessExerciseActivity.this, new Observer<SubCategoryDetailModel>() {
            @Override
            public void onChanged(SubCategoryDetailModel subCategoryDetailModel) {
                if (CommonUtils.isNetworkConnected(activity)) {
                if (subCategoryDetailModel.getSuccess().equalsIgnoreCase("1")){


                        Picasso.get().load("http://apptech.mobi/learnPlayLiveApplication/" + subCategoryDetailModel.getDetails().getBannerImage()).into(exercise_bg_img);
                        Tv_note.setText(subCategoryDetailModel.getDetails().getNote());
                        tv_title.setText(subCategoryDetailModel.getDetails().getTitle());
                        tt_title.setText(subCategoryDetailModel.getDetails().getStitle());
                        if (subCategoryDetailModel.getDetails().getStatus().equalsIgnoreCase("0")){
                            openExerciseBtn.setVisibility(View.GONE);
                            addExerciseBtn.setVisibility(View.VISIBLE);
                            color_img.setVisibility(View.GONE);
                            addedText.setVisibility(View.GONE);


                        }else {
                            openExerciseBtn.setVisibility(View.VISIBLE);
                            addExerciseBtn.setVisibility(View.GONE);
                            color_img.setVisibility(View.VISIBLE);
                            addedText.setVisibility(View.VISIBLE);
                        }


                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false);
                    pointRecyclerView.setLayoutManager(linearLayoutManager);
                    PointAdapter pointAdapter = new PointAdapter(subCategoryDetailModel.getDetails().getPoints(), activity);
                    pointRecyclerView.setAdapter(pointAdapter);

//                        for (int i = 0; i < subCategoryDetailModel.getDetails().getPoints().size(); i++) {
//                            point1.setText(subCategoryDetailModel.getDetails().getPoints().get(i).getPoints());
//                            point2.setText(subCategoryDetailModel.getDetails().getPoints().get(i).getPoints());
//                        }

//                    if (points!=null){
//                        points.clear();
//                    }
//                    for (int i = 0; i < list.size(); i++) {
//                        points.add(list.get(i).getPoints());
//                    }
//                    pointData = new StringBuilder();
//                    for (String s : points) {
//                        pointData.append(s + " ");
//                    }
////                    int size = pointData.length();
////                    pointData.deleteCharAt(size-1);
//
//                    Point = pointData.toString();
//
//                    point1.setText(pointData);

                    }

                }else {
                    Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    private  void  changeStatus(){
        final String subCategoryId = getIntent().getExtras().getString("subCategoryId");
        String userId = App.getAppPreference().getLoginDetail().getDetails().getId();

        viewModel.subcategoryStatusList(activity,subCategoryId,userId).observe(FitnessExerciseActivity.this, new Observer<SubCategoryStatusModel>() {
            @Override
            public void onChanged(SubCategoryStatusModel subcategoryStatusModel) {
                if (subcategoryStatusModel.getSuccess().equalsIgnoreCase("1")){
                    Toast.makeText(activity, subcategoryStatusModel.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar2:
                onBackPressed();
                break;

            case R.id.openExerciseBtn:
                startActivity(new Intent(FitnessExerciseActivity.this, ExerciseDetailActivity.class));
                break;

            case R.id.addExerciseBtn:
                changeStatus();
                startActivity(new Intent(this, ExerciseDetailActivity.class));
                break;


        }
    }
}
