package com.example.learnplaylive.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnplaylive.R;

public class LogHistoryAdapter extends RecyclerView.Adapter<LogHistoryAdapter.MyViewHolder> {
    private Context context;

    public LogHistoryAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public LogHistoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.log_history_items, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LogHistoryAdapter.MyViewHolder myViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView mlTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mlTv = itemView.findViewById(R.id.mlTv);
        }
    }
}
