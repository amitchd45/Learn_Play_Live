package com.example.learnplaylive.HomeBottom.Fitness.FitnessFragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.learnplaylive.R;

public class TimerFragment extends Fragment {
    private TextView timerTv;
    Button start, pause, reset;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L ;
    Handler handler;
    int Seconds, Minutes, MilliSeconds ;

    public TimerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timer, container, false);

        findIds(view);


        LocalBroadcastManager.getInstance(getContext()).registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                if (intent.getStringExtra("count1") != null){
                    if (intent.getStringExtra("count1").equalsIgnoreCase("1")) {
                        pause();
                    } else if (intent.getStringExtra("count1").equalsIgnoreCase("0")) {
                        start();
                    }
                }
            }
        },new IntentFilter("stopwatch"));

        return view;
    }

    private void findIds(View view) {
        timerTv = view.findViewById(R.id.timerTv);
        handler = new Handler() ;
    start();

    }

//    public  interface StartPauseStopwatch{
//        void start( );
//    }
//
//    public void timer(StartPauseStopwatch startPauseStopwatch){
//
//    }

    public Runnable runnable = new Runnable() {

        public void run() {

            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;

            MilliSeconds = (int) (UpdateTime % 1000);

            timerTv.setText("" + Minutes + ":"
                    + String.format("%02d", Seconds) + ":"
                    + String.format("%02d", MilliSeconds));

            handler.postDelayed(this, 0);
        }

    };
    private void pause(){
        TimeBuff += MillisecondTime;

        handler.removeCallbacks(runnable);
    }
    private void start(){
        StartTime = SystemClock.uptimeMillis();
        handler.postDelayed(runnable, 0);
    }

}
