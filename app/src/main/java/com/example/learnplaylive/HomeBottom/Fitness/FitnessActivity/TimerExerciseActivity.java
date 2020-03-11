package com.example.learnplaylive.HomeBottom.Fitness.FitnessActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnplaylive.HomeBottom.Fitness.FitnessAdapter.TimerPagerAdapter;
import com.example.learnplaylive.HomeBottom.Fitness.FitnessFragments.MapFragment;
import com.example.learnplaylive.HomeBottom.Fitness.FitnessFragments.TimerFragment;
import com.example.learnplaylive.R;

import me.relex.circleindicator.CircleIndicator;

public class TimerExerciseActivity extends AppCompatActivity implements View.OnClickListener {
    private CircleIndicator timeCcircleIndicator;
    private ViewPager timerViewPager;
    int[] layouts = {R.layout.fragment_timer, R.layout.fragment_map};
    private RelativeLayout timer_btn, timer_finish_btn;
    private TextView playPauseTv;
    private ImageView palyPauseIv;
    private int i=0;
    private CountDownTimer countDownTimer;
    private LinearLayout transitionsContainer;
//    private ElasticButton timer_finish_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer_exercise);
        findIds();
        setUps();



    }

    private void setUps() {

        TimerPagerAdapter timerPagerAdapter ;
        timerPagerAdapter = new TimerPagerAdapter(getSupportFragmentManager());
        timerPagerAdapter.addFrag(new TimerFragment());
        timerPagerAdapter.addFrag(new MapFragment());
        timerViewPager.setAdapter(timerPagerAdapter);
        timeCcircleIndicator.setViewPager(timerViewPager);


    }
    private void findIds() {
        TextView textView=findViewById(R.id.tv_header2);
        textView.setText("Learn Play Live");
        ImageView back= findViewById(R.id.back_topbar2);
        back.setVisibility(View.GONE);

        timeCcircleIndicator = findViewById(R.id.timeCcircleIndicator);
        timerViewPager = findViewById(R.id.timerViewPager);

        timer_btn = findViewById(R.id.timer_btn);
        timer_btn.setOnClickListener(this);

        playPauseTv = findViewById(R.id.playPauseTv);
        palyPauseIv = findViewById(R.id.palyPauseIv);

        transitionsContainer = findViewById(R.id.transitionsContainer);

        timer_finish_btn = findViewById(R.id.timer_finish_btn);
        timer_finish_btn.setOnClickListener(this);
        timer_finish_btn.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                startActivity(new Intent(TimerExerciseActivity.this, ExerciseDetailActivity.class));
                finish();
                return  true;
            }
        });



    }

    @Override
    public void onClick(View view) {
        boolean visible = false;
        switch (view.getId()){

            case R.id.timer_btn:

                TransitionManager.beginDelayedTransition(transitionsContainer);

                if (i==0) {
                    timer_finish_btn.setVisibility(View.VISIBLE);
                    playPauseTv.setText("PLAY");
                    palyPauseIv.setImageResource(R.drawable.ic_play_button);
                    Intent intent = new Intent("stopwatch");
                    intent.putExtra("count1","1");
                    LocalBroadcastManager.getInstance(TimerExerciseActivity.this).sendBroadcast(intent);
                    i = 1;
                }else if (i==1){
                   // timer_finish_btn.animate().alpha(1.0f);
                    timer_finish_btn.setVisibility(View.GONE);
                    Intent intent = new Intent("stopwatch");
                    intent.putExtra("count1","0");
                    LocalBroadcastManager.getInstance(TimerExerciseActivity.this).sendBroadcast(intent);
                    playPauseTv.setText("PAUSE");
                    palyPauseIv.setImageResource(R.drawable.ic_pause);
                    i=0;
                }

                break;

            case R.id.timer_finish_btn:
                Toast.makeText(this, "Long Press on Button", Toast.LENGTH_SHORT).show();
                break;

        }

    }


}
