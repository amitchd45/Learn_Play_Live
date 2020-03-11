package com.example.learnplaylive.HomeBottom.Fitness.FitnessActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnplaylive.HomeBottom.Fitness.FitnessAdapter.FitnessHomeAdapter;
import com.example.learnplaylive.Model.SubCategoryModel;
import com.example.learnplaylive.R;
import com.example.learnplaylive.SharedPreference.App;
import com.example.learnplaylive.SharedPreference.CommonUtils;
import com.example.learnplaylive.ViewModel.FitnessViewModel;

import java.util.ArrayList;
import java.util.List;

public class FitnessHomePageActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView fitness_item_recycleview;
    private CardView Exercise_card;
    private String exercise,exercise1;
    private List<SubCategoryModel.Detail> list = new ArrayList<>();
    private FitnessViewModel viewModel;
    private Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_home_page);
        viewModel = ViewModelProviders.of(this).get(FitnessViewModel.class);


        findIds();
        setUps();
        getSubCategory();

    }

    private void getSubCategory(){
        String userId = App.getAppPreference().getLoginDetail().getDetails().getId();
        viewModel.subcategoryList(activity,"4",userId).observe(FitnessHomePageActivity.this, new Observer<SubCategoryModel>() {
            @Override
            public void onChanged(final SubCategoryModel subCategoryModel) {
                if (subCategoryModel.getSuccess().equalsIgnoreCase("1")){

                    FitnessHomeAdapter fitnessHomeAdapter=new FitnessHomeAdapter(activity,subCategoryModel.getDetails(), new FitnessHomeAdapter.Select() {
                        @Override
                        public void Choose(int position) {
                            if (CommonUtils.isNetworkConnected(FitnessHomePageActivity.this)) {
//                            startActivity(new Intent(FitnessHomePageActivity.this, FitnessExerciseActivity.class));
//                            Toast.makeText(activity,String.valueOf(position), Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(FitnessHomePageActivity.this, FitnessExerciseActivity.class);
                                intent.putExtra("subCategoryId", subCategoryModel.getDetails().get(position).getId());
                                startActivity(intent);

                            }
                            else {
                                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                    fitness_item_recycleview.setAdapter(fitnessHomeAdapter);

                }
            }
        });



    }

    private void setUps() {
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        fitness_item_recycleview.setLayoutManager(gridLayoutManager);
    }

    private void findIds() {

        activity = FitnessHomePageActivity.this;
        TextView textView=findViewById(R.id.tv_header2);
        textView.setText("Health & Fitness");
        ImageView back= findViewById(R.id.back_topbar2);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);


//        Exercise_card = findViewById(R.id.Exercise_card);
//        Exercise_card.setOnClickListener(this);
        fitness_item_recycleview = findViewById(R.id.fitness_item_recycleview);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar2:
                onBackPressed();
                break;
//            case R.id.Exercise_card:
//                startActivity(new Intent(FitnessHomePageActivity.this, FitnessExerciseActivity.class));
//                break;

        }
    }
}
