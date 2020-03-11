package com.example.learnplaylive.ViewModel;

import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.learnplaylive.Model.AddLoggedDetailModel;
import com.example.learnplaylive.Model.SubCategoryDetailModel;
import com.example.learnplaylive.Model.SubCategoryModel;
import com.example.learnplaylive.Model.SubCategoryStatusModel;
import com.example.learnplaylive.Retrofit.Api;
import com.example.learnplaylive.Retrofit.ApiClient;
import com.example.learnplaylive.SharedPreference.CommonUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FitnessViewModel extends ViewModel {

    private MutableLiveData<SubCategoryModel> subCategoryData;
    public LiveData<SubCategoryModel> subcategoryList(final Activity activity, String categoryId, String userId){
        subCategoryData = new MutableLiveData<>();
        CommonUtils.showProgress(activity,"");
        Api api = ApiClient.getClient().create(Api.class);
        api.subCategoryList(categoryId,userId).enqueue(new Callback<SubCategoryModel>() {
            @Override
            public void onResponse(Call<SubCategoryModel> call, Response<SubCategoryModel> response) {
                if (response.body()!=null){
                    CommonUtils.dismissProgress();
                    subCategoryData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SubCategoryModel> call, Throwable t) {
                CommonUtils.dismissProgress();
                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        return subCategoryData;
    }


    // SubCategory Detail
    private MutableLiveData<SubCategoryDetailModel> subCategoryDetailData;
    public LiveData<SubCategoryDetailModel> subcategoryDetailList(final Activity activity, String subCaregoryId, String userId){
        subCategoryDetailData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "");
        Api api = ApiClient.getClient().create(Api.class);
        api.subCategoryDetailList(subCaregoryId, userId).enqueue(new Callback<SubCategoryDetailModel>() {
            @Override
            public void onResponse(Call<SubCategoryDetailModel> call, Response<SubCategoryDetailModel> response) {
                if (response.body()!=null){
                    CommonUtils.dismissProgress();
                    subCategoryDetailData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SubCategoryDetailModel> call, Throwable t) {
                CommonUtils.dismissProgress();
                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
        return subCategoryDetailData;
    }


    // SubCategory Status
    private MutableLiveData<SubCategoryStatusModel> subCategoryStatusData;
    public LiveData<SubCategoryStatusModel> subcategoryStatusList(final Activity activity, String subCaregoryId, String userId){
        subCategoryStatusData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "");
        Api api = ApiClient.getClient().create(Api.class);
        api.subCategoryStatus(subCaregoryId,userId).enqueue(new Callback<SubCategoryStatusModel>() {
            @Override
            public void onResponse(Call<SubCategoryStatusModel> call, Response<SubCategoryStatusModel> response) {
                if (response.body()!=null){
                    CommonUtils.dismissProgress();
                    subCategoryStatusData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SubCategoryStatusModel> call, Throwable t) {
                CommonUtils.dismissProgress();
                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
        return subCategoryStatusData;
    }


    // Logged Detail

    private MutableLiveData<AddLoggedDetailModel> loggedDetailData;
    public LiveData<AddLoggedDetailModel> loggedDetail (final  Activity activity, String categoryId, String subCategoryId, String userId, String exerciseType, String startDate, String startTime, String duration, String calories){
        loggedDetailData = new MutableLiveData<>();
        CommonUtils.showProgress(activity,"");
        Api api = ApiClient.getClient().create(Api.class);
        api.addLoggedDetail(categoryId,subCategoryId,userId,exerciseType,startDate,startTime,duration,calories).enqueue(new Callback<AddLoggedDetailModel>() {
            @Override
            public void onResponse(Call<AddLoggedDetailModel> call, Response<AddLoggedDetailModel> response) {
                if (response.body()!=null){
                    CommonUtils.dismissProgress();
                    loggedDetailData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AddLoggedDetailModel> call, Throwable t) {
                CommonUtils.dismissProgress();
                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

        return loggedDetailData;
    }
}
