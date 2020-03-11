package com.example.learnplaylive.HomeBottom.Hydration.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnplaylive.R;

public class WaterAdapter extends RecyclerView.Adapter<WaterAdapter.MyViewHolder> {
    Context context;
    Select select;

    public interface Select{
        void Choose(int position);
    }

    public WaterAdapter(Context context, Select select) {
        this.context = context;
        this.select = select;
    }

    @NonNull
    @Override
    public WaterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.water_ml_items, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WaterAdapter.MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RelativeLayout waterMlItem;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            waterMlItem = itemView.findViewById(R.id.waterMlItem);
            waterMlItem.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.waterMlItem:
                    select.Choose(getLayoutPosition());
                    break;

            }

        }
    }
}
