package com.example.learnplaylive.HomeBottom.Fitness.FitnessAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnplaylive.Model.SubCategoryModel;
import com.example.learnplaylive.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class FitnessHomeAdapter extends RecyclerView.Adapter<FitnessHomeAdapter.MyViewHolder> {

//    private int[] bg={R.drawable.fitness_sleep_bg,R.drawable.fitness_exercise_bg, R.drawable.fitness_heart_rate_bg, R.drawable.fitness_weight_bg};
//    private int[] img={R.drawable.fitness_sleep,R.drawable.fitness_exercise, R.drawable.fitness_heart_rate, R.drawable.fitness_weight};
//    private String[] heading={"Sleep","Exercise", "Heart Rate", "Weight"};
//    private String[] description={"Plus sleep stages","Plus sleep stages", "Plus sleep stages", "Plus sleep stages"};

    Context context;
    Select select;
    List<SubCategoryModel.Detail> list = new ArrayList<>();



    public interface Select {
        void Choose(int position);
//        void Exercise(int position);
//        void HeartRate(int position);
//        void Weight(int position);
    }

    public FitnessHomeAdapter(Context context, List<SubCategoryModel.Detail> list, Select select) {
        this.context = context;
        this.select = select;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fitness_items, parent, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);


        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        holder.fitness_bg_img.setImageResource(bg[position]);
//        holder.fitness_img.setImageResource(img[position]);
//        holder.fitness_heading.setText(heading[position]);
//        holder.fitness_description.setText(description[position]);
        Picasso.get().load("http://apptech.mobi/learnPlayLiveApplication/"+list.get(position).getBackgroundImage()).into(holder.fitness_bg_img);
        Picasso.get().load("http://apptech.mobi/learnPlayLiveApplication/"+list.get(position).getImage()).into(holder.fitness_img);
        holder.fitness_heading.setText(list.get(position).getTitle());
        holder.fitness_description.setText(list.get(position).getSubtitle());
        if (list.get(position).getStatus().equalsIgnoreCase("1")){
            holder.fitness_description.setVisibility(View.GONE);
            holder.subAddedText.setVisibility(View.VISIBLE);
        }else {
            holder.fitness_description.setVisibility(View.VISIBLE);
            holder.subAddedText.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView fitness_bg_img, fitness_img;
        private TextView fitness_heading, fitness_description;
        private RelativeLayout  fitness_item_lay;
        private LinearLayout subAddedText;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fitness_bg_img = itemView.findViewById(R.id.fitness_bg_img);
            fitness_img = itemView.findViewById(R.id.fitness_img);
            fitness_item_lay = itemView.findViewById(R.id.fitness_item_lay);
            fitness_item_lay.setOnClickListener(this);
            fitness_heading = itemView.findViewById(R.id.fitness_heading);
            fitness_description = itemView.findViewById(R.id.fitness_description);
            subAddedText = itemView.findViewById(R.id.subAddedText);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.fitness_item_lay:
//                    select.Exercise(getLayoutPosition());
//                    select.Sleep(getLayoutPosition());
//                    select.HeartRate(getLayoutPosition());
//                    select.Weight(getLayoutPosition());
                    select.Choose(getLayoutPosition());
                    break;
            }


        }
    }
}
