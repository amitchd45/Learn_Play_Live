package com.example.learnplaylive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;

import com.example.learnplaylive.Actiivties.HomeActivity;
import com.example.learnplaylive.SharedPreference.App;
import com.example.learnplaylive.SharedPreference.AppConstants;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        PackageInfo info;
        try {
            info = getPackageManager().getPackageInfo("com.example.learnplaylive", PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md;
                md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String something = new String(Base64.encode(md.digest(), 0));
                //String something = new String(Base64.encodeBytes(md.digest()));
                Log.e("hash key", something);
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e("name not found", e1.toString());
        } catch (NoSuchAlgorithmException e) {
            Log.e("no such an algorithm", e.toString());
        } catch (Exception e) {
            Log.e("exception", e.toString());
        }


//        splasTimer();

        init();

    }

//    private void splasTimer(){
//        new Handler().postDelayed(new Runnable() {
//            public void run() {
//                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
//                finish();
//            }
//        },  2000);
//    }

    private void init() {

        Thread thread=new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1500);
                    if (App.getAppPreference().getString(AppConstants.TOKEN).equalsIgnoreCase("1")){
                        startActivity(new Intent(SplashScreen.this, HomeActivity.class));
                        finishAffinity();
                    }
//
                    else {
                        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                        finishAffinity();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();
    }
}
