package com.example.learnplaylive.ViewModel;

import android.app.Activity;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.learnplaylive.Model.ChangeEmailModel;
import com.example.learnplaylive.Model.ChangePasswordModel;
import com.example.learnplaylive.Model.CheckEmailModel;
import com.example.learnplaylive.Model.ForgotPasswordModel;
import com.example.learnplaylive.Model.LoginRegisterModel;
import com.example.learnplaylive.Retrofit.Api;
import com.example.learnplaylive.Retrofit.ApiClient;
import com.example.learnplaylive.SharedPreference.CommonUtils;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginRegisterViewModel  extends ViewModel {

    //Check Email
    private MutableLiveData<CheckEmailModel> checkEmailData;
    public LiveData<CheckEmailModel> checkEmail(final Activity activity, String email){
        checkEmailData = new MutableLiveData<>();
        CommonUtils.showProgress(activity,"");
        Api api = ApiClient.getClient().create(Api.class);
        api.checkEmail(email).enqueue(new Callback<CheckEmailModel>() {
           @Override
           public void onResponse(Call<CheckEmailModel> call, Response<CheckEmailModel> response) {
                CommonUtils.dismissProgress();
                if (response.body()!=null){
                    checkEmailData.setValue(response.body());
                }
           }

           @Override
           public void onFailure(Call<CheckEmailModel> call, Throwable t) {
               CommonUtils.dismissProgress();
               Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();
           }
       });

        return checkEmailData;
    }

    // Register
    private MutableLiveData<LoginRegisterModel> userRegisterData;
    public LiveData<LoginRegisterModel> register(final Activity activity, RequestBody name, RequestBody email, RequestBody password, final MultipartBody.Part image, RequestBody gender, RequestBody phone, RequestBody dob, RequestBody address,  RequestBody reg_id,  RequestBody device_type, RequestBody login_type){
        userRegisterData = new MutableLiveData<>();
        CommonUtils.showProgress(activity,"");
        Api api = ApiClient.getClient().create(Api.class);
        api.userRegister(name, email, password, image, gender, phone, dob, address, reg_id, device_type, login_type).enqueue(new Callback<LoginRegisterModel>() {
            @Override
            public void onResponse(Call<LoginRegisterModel> call, Response<LoginRegisterModel> response) {
                CommonUtils.dismissProgress();
                if (response.body()!=null){
                    userRegisterData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterModel> call, Throwable t) {
                CommonUtils.dismissProgress();
                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

        return userRegisterData;
    }


    // Login
    private MutableLiveData<LoginRegisterModel> userLoginData;
    public LiveData<LoginRegisterModel> login(final Activity activity, String email, String password, String device_type, String reg_id){
        userLoginData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "");
        Api api = ApiClient.getClient().create(Api.class);
        api.userLogin(email, password, device_type, reg_id).enqueue(new Callback<LoginRegisterModel>() {
            @Override
            public void onResponse(Call<LoginRegisterModel> call, Response<LoginRegisterModel> response) {
                CommonUtils.dismissProgress();
                if (response.body()!=null){
                    userLoginData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterModel> call, Throwable t) {
                CommonUtils.dismissProgress();
            }
        });

        return userLoginData;
    }

    // Edit Profile
    private MutableLiveData<LoginRegisterModel> updateProfileData;
    public LiveData<LoginRegisterModel> updateProfile(final Activity activity, RequestBody name, final MultipartBody.Part image, RequestBody gender, RequestBody phone, RequestBody dob, RequestBody address, RequestBody aboutMe, RequestBody height, final RequestBody userId){
        updateProfileData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "");
        Api api = ApiClient.getClient().create(Api.class);
        api.editProfile(name,image,gender,phone,dob,address,aboutMe,height,userId).enqueue(new Callback<LoginRegisterModel>() {
            @Override
            public void onResponse(Call<LoginRegisterModel> call, Response<LoginRegisterModel> response) {
                CommonUtils.dismissProgress();
                if (response.body()!=null){
                    updateProfileData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterModel> call, Throwable t) {
                CommonUtils.dismissProgress();
                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
        return updateProfileData;
    }

    // Change Password
    private MutableLiveData<ChangePasswordModel> changePasswordData;
    public LiveData<ChangePasswordModel> changePassword(final Activity activity, String userId, String currentPassword, String newPassword){
        changePasswordData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "");
        Api api = ApiClient.getClient().create(Api.class);
        api.changePassword(userId,currentPassword,newPassword).enqueue(new Callback<ChangePasswordModel>() {
            @Override
            public void onResponse(Call<ChangePasswordModel> call, Response<ChangePasswordModel> response) {
                if (response.body()!=null){
                    changePasswordData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordModel> call, Throwable t) {
                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
        return changePasswordData;
    }


    // Change Email
    private MutableLiveData<ChangeEmailModel> changeEmailData;
    public LiveData<ChangeEmailModel> changeEmail(final Activity activity, String userId, String currentEmail, String newEmail){
        changeEmailData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "");
        Api api = ApiClient.getClient().create(Api.class);
        api.changeEmail(userId,currentEmail,newEmail).enqueue(new Callback<ChangeEmailModel>() {
            @Override
            public void onResponse(Call<ChangeEmailModel> call, Response<ChangeEmailModel> response) {
                CommonUtils.dismissProgress();
                if (response.body()!=null){
                    changeEmailData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ChangeEmailModel> call, Throwable t) {
                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
        return changeEmailData;
    }


    // Forgot Password
    private MutableLiveData<ForgotPasswordModel> forgotPasswodData;
    public LiveData<ForgotPasswordModel> forgotPassword(final  Activity activity, String email){
        forgotPasswodData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "");
        Api api = ApiClient.getClient().create(Api.class);
        api.forgotPassword(email).enqueue(new Callback<ForgotPasswordModel>() {
            @Override
            public void onResponse(Call<ForgotPasswordModel> call, Response<ForgotPasswordModel> response) {
                CommonUtils.dismissProgress();
                if (response.body()!=null){
                    forgotPasswodData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ForgotPasswordModel> call, Throwable t) {
                CommonUtils.dismissProgress();
                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });

        return forgotPasswodData;
    }

    // Social Login
    private MutableLiveData<LoginRegisterModel> socialLoginData;
    public LiveData<LoginRegisterModel> socialLogin(final Activity activity, String username, String email, String phone, String social_id, String device_type, String reg_id, String image, String loginType){
        socialLoginData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "");
        Api api = ApiClient.getClient().create(Api.class);
        api.userSocialLogin(username,email,phone,social_id,device_type,reg_id,image,loginType).enqueue(new Callback<LoginRegisterModel>() {
            @Override
            public void onResponse(Call<LoginRegisterModel> call, Response<LoginRegisterModel> response) {
                CommonUtils.dismissProgress();
                if (response.body()!=null){
                    socialLoginData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterModel> call, Throwable t) {
                CommonUtils.dismissProgress();
                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();

            }
        });
        return  socialLoginData;

    }

    // Check Social Id
    private MutableLiveData<LoginRegisterModel> checkSocialIdData;
    public LiveData<LoginRegisterModel>  checkSocialId (final  Activity activity, String socialId){
        checkSocialIdData = new MutableLiveData<>();
        CommonUtils.showProgress(activity, "");
        Api api = ApiClient.getClient().create(Api.class);
        api.checkSocialId(socialId).enqueue(new Callback<LoginRegisterModel>() {
            @Override
            public void onResponse(Call<LoginRegisterModel> call, Response<LoginRegisterModel> response) {
                CommonUtils.dismissProgress();
                if (response.body()!=null){
                    checkSocialIdData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginRegisterModel> call, Throwable t) {
                CommonUtils.dismissProgress();
                Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
       return  checkSocialIdData;
    }
}
