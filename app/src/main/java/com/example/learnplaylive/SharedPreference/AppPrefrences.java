package com.example.learnplaylive.SharedPreference;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.learnplaylive.Model.LoginRegisterModel;
import com.google.gson.Gson;
//
//import com.google.gson.Gson;
//import com.omninos.smartclub.modelClass.UserLoginRegisterModel;
//import com.omninos.smartclub.utils.AppConstants;

public class AppPrefrences {

    private static AppPrefrences appPreference;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;


    public AppPrefrences(Context context) {
        sharedPreferences = context.getSharedPreferences("LearnPlayLive", Context.MODE_PRIVATE);

    }

    public static AppPrefrences init(Context context) {
        if (appPreference == null) {
            appPreference = new AppPrefrences(context);
        }
        return appPreference;
    }

    public void clearAppPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }

    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getString(String key) {

        return sharedPreferences.getString(key, "");
    }


    public void saveLoginDetail(LoginRegisterModel loginRegisterModel) {
        Gson gson = new Gson();
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstants.LOGIN_DETAIL, gson.toJson(loginRegisterModel));
        editor.apply();
    }

    public LoginRegisterModel getLoginDetail() {
        Gson gson = new Gson();
        return gson.fromJson(sharedPreferences.getString(AppConstants.LOGIN_DETAIL, ""), LoginRegisterModel.class);
    }

}
