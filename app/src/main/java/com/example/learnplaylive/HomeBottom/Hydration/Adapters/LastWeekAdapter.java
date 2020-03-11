package com.example.learnplaylive.HomeBottom.Hydration.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnplaylive.R;

public class LastWeekAdapter extends RecyclerView.Adapter<LastWeekAdapter.MyViewHolder> {
    private Context context;
    Select select;

    public interface Select{
        void Choose(int position);
    }

    public LastWeekAdapter(Context context, Select select) {
        this.context = context;
        this.select = select;
    }

    @NonNull
    @Override
    public LastWeekAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.last_week_items, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LastWeekAdapter.MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private RelativeLayout lastWeekItems;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            lastWeekItems = itemView.findViewById(R.id.lastWeekItems);
            lastWeekItems.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.lastWeekItems:
                    select.Choose(getLayoutPosition());
                    break;
            }

        }
    }
}
