package com.example.learnplaylive.HomeBottom.Fitness.FitnessAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnplaylive.Model.SubCategoryDetailModel;
import com.example.learnplaylive.R;

import java.util.ArrayList;
import java.util.List;

public class PointAdapter extends RecyclerView.Adapter<PointAdapter.MyViewHolder> {
    private List<SubCategoryDetailModel.Details.Point> list = new ArrayList<>();
    private Context context;

    public PointAdapter(List<SubCategoryDetailModel.Details.Point> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.points_items,parent,false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.point3.setText(list.get(position).getPoints());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView point3;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            point3 = itemView.findViewById(R.id.point3);
        }
    }
}
