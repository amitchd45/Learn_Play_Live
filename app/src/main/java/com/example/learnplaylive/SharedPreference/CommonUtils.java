package com.example.learnplaylive.SharedPreference;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.view.Gravity;
import android.view.Window;

import com.example.learnplaylive.R;

public class CommonUtils {

    private static Dialog progressDialog;

    public static void showProgress(Activity activity, String message) {
        progressDialog = new Dialog(activity);
        progressDialog.setTitle("Please wait...");
        progressDialog.setContentView(R.layout.spinkitanimation);
        progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Window dialogWindow = progressDialog.getWindow();
        dialogWindow.setGravity(Gravity.CENTER);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    public static void dismissProgress() {
        progressDialog.dismiss();
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }


}
