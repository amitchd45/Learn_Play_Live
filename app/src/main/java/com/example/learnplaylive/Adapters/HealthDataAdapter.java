package com.example.learnplaylive.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.learnplaylive.R;

public class HealthDataAdapter extends RecyclerView.Adapter<HealthDataAdapter.MyViewHolder> {
    private Context context;
    private int[] imgL={R.drawable.blood_pressure_png,R.drawable.heart_rate_png, R.drawable.temperature_png, R.drawable.blood_sugar_png, R.drawable.weight_png};
    private String[] large={"130/85","105", "36.6", "186","167.0"};
    private String[] small={"mmHg","BPM", "c", "mg/dl", "lb"};
    private String[] type={"Blood Pressure","Heart Rate", "Temperature", "Blood Sugar", "Weight"};
    private String[] normal={"120/80","80/100", "36.6c", "100 mg/dl","110 lb"};


    public HealthDataAdapter(Context context, int[] imgL, String[] large, String[] small, String[] type, String[] normal) {
        this.context = context;
        this.imgL = imgL;
        this.large = large;
        this.small = small;
        this.type = type;
        this.normal = normal;
    }

    @NonNull
    @Override
    public HealthDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.health_data_items, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthDataAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.img.setImageResource(imgL[i]);
        myViewHolder.large_text.setText(large[i]);
        myViewHolder.smallTv.setText(small[i]);
        myViewHolder.typeTv.setText(type[i]);
        myViewHolder.normalTv.setText(normal[i]);

    }

    @Override
    public int getItemCount() {
        return imgL.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private CardView healthDataItem;
        private ImageView img;
        private TextView large_text, smallTv, typeTv, normalTv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            healthDataItem = itemView.findViewById(R.id.healthDataItem);
            img = itemView.findViewById(R.id.img);
            large_text = itemView.findViewById(R.id.large_text);
            smallTv = itemView.findViewById(R.id.smallTv);
            typeTv = itemView.findViewById(R.id.typeTv);
            normalTv = itemView.findViewById(R.id.normalTv);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){

            }
        }
    }
}
