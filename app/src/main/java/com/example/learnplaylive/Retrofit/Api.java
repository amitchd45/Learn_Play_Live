package com.example.learnplaylive.Retrofit;


import com.example.learnplaylive.Model.AddLoggedDetailModel;
import com.example.learnplaylive.Model.ChangeEmailModel;
import com.example.learnplaylive.Model.ChangePasswordModel;
import com.example.learnplaylive.Model.CheckEmailModel;
import com.example.learnplaylive.Model.ForgotPasswordModel;
import com.example.learnplaylive.Model.LoginRegisterModel;
import com.example.learnplaylive.Model.SubCategoryDetailModel;
import com.example.learnplaylive.Model.SubCategoryModel;
import com.example.learnplaylive.Model.SubCategoryStatusModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface Api {


    @FormUrlEncoded
    @POST("checkEmail")
    Call<CheckEmailModel> checkEmail(@Field("email") String userEmail);



    @Multipart
    @POST("userRegister")
    Call<LoginRegisterModel> userRegister(@Part("name") RequestBody name,
                                          @Part("email") RequestBody email,
                                          @Part("password") RequestBody password,
                                          @Part MultipartBody.Part qrCode,
                                          @Part("gender") RequestBody gender,
                                          @Part("phone") RequestBody phone,
                                          @Part("dob") RequestBody dob,
                                          @Part("address") RequestBody address,
                                          @Part("reg_id") RequestBody reg_id,
                                          @Part("device_type") RequestBody device_type,
                                          @Part("login_type") RequestBody login_type);

    @FormUrlEncoded
    @POST("userLogin")
    Call<LoginRegisterModel> userLogin(@Field("email") String userEmail,
                                        @Field("password") String password,
                                        @Field("device_type") String device_type,
                                        @Field("reg_id") String reg_id);


    @Multipart
    @POST("userUpdateProfile")
    Call<LoginRegisterModel> editProfile(@Part("name") RequestBody name,
                                          @Part MultipartBody.Part qrCode,
                                          @Part("gender") RequestBody gender,
                                          @Part("phone") RequestBody phone,
                                          @Part("dob") RequestBody dob,
                                          @Part("address") RequestBody address,
                                          @Part("aboutMe") RequestBody aboutMe,
                                          @Part("height") RequestBody height,
                                          @Part("userId") RequestBody userId);


    @FormUrlEncoded
    @POST("changePassword")
    Call<ChangePasswordModel> changePassword(@Field("userId") String userId,
                                             @Field("oldPassword") String currentPassword,
                                             @Field("newPassword") String newPassword);

    @FormUrlEncoded
    @POST("changeEmail")
    Call<ChangeEmailModel> changeEmail(@Field("userId") String userId,
                                       @Field("email") String email,
                                       @Field("newEmail") String newemail);

    @FormUrlEncoded
    @POST("forgotPassword")
    Call<ForgotPasswordModel> forgotPassword(@Field("email") String email);

    @FormUrlEncoded
    @POST("UserSocialLogin")
    Call<LoginRegisterModel> userSocialLogin(@Field("username") String username,
                                             @Field("email") String email,
                                             @Field("phone") String phone,
                                             @Field("social_id") String social_id,
                                             @Field("device_type") String device_type,
                                             @Field("reg_id") String reg_id,
                                             @Field("image") String image,
                                             @Field("loginType") String loginType);


    @FormUrlEncoded
    @POST("userCheckSocialId")
    Call<LoginRegisterModel> checkSocialId(@Field("social_id") String social_id);

    @FormUrlEncoded
    @POST("subCategoryList")
    Call<SubCategoryModel> subCategoryList(@Field("categoryId") String categoryId,
                                           @Field("userId") String userId);

    @FormUrlEncoded
    @POST("subCategoryList12")
    Call<SubCategoryDetailModel> subCategoryDetailList(@Field("subcategoryId") String categoryId,
                                                       @Field("userId") String userId);

    @FormUrlEncoded
    @POST("subCategoryStatus")
    Call<SubCategoryStatusModel> subCategoryStatus(@Field("subCategoryId") String categoryId,
                                                   @Field("userId") String userId);

    @FormUrlEncoded
    @POST("addLogdetails")
    Call<AddLoggedDetailModel> addLoggedDetail(@Field("categoryId") String categoryId,
                                               @Field("subCategoryId") String subCategoryId,
                                               @Field("userId") String userId,
                                               @Field("exerciseType") String exerciseType,
                                               @Field("startDate") String startDate,
                                               @Field("startTime") String startTime,
                                               @Field("duration") String duration,
                                               @Field("calories") String calories);


//    @FormUrlEncoded
//    @POST("UserSocialLogin")
//    Call<UserLoginRegisterModel> userSocialLogin(@Field("username") String username,
//                                                 @Field("phoneNumber") String phoneNumber,
//                                                 @Field("gender") String gender,
//                                                 @Field("date_of_birth") String date_of_birth,
//                                                 @Field("phoneCode") String country,
//                                                 @Field("reg_id") String reg_id,
//                                                 @Field("latitude") String latitude,
//                                                 @Field("longitude") String longitude,
//                                                 @Field("device_type") String device_type,
//                                                 @Field("social_id") String socialId);
//
//
//    @FormUrlEncoded
//    @POST("userCheckSocialId")
//    Call<UserLoginRegisterModel> checkSocialId(@Field("social_id") String socialid);



}
