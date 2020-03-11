package com.example.learnplaylive.SharedPreference;

public class SingltonPojo {

    String exerciseName;
    String weight1;
    String weightStatus;
    String weightAndPercentage;
    String goalWeight;
    String UserName, UserEmail, UserPassword, UserOTP;
    String socialLoginStatus;
    String forgotPasswordStatus;

    public String getForgotPasswordStatus() {
        return forgotPasswordStatus;
    }

    public void setForgotPasswordStatus(String forgotPasswordStatus) {
        this.forgotPasswordStatus = forgotPasswordStatus;
    }

    public String getSocialLoginStatus() {
        return socialLoginStatus;
    }

    public void setSocialLoginStatus(String socialLoginStatus) {
        this.socialLoginStatus = socialLoginStatus;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserEmail() {
        return UserEmail;
    }

    public void setUserEmail(String userEmail) {
        UserEmail = userEmail;
    }

    public String getUserPassword() {
        return UserPassword;
    }

    public void setUserPassword(String userPassword) {
        UserPassword = userPassword;
    }

    public String getUserOTP() {
        return UserOTP;
    }

    public void setUserOTP(String userOTP) {
        UserOTP = userOTP;
    }

    public String getGoalWeight() {
        return goalWeight;
    }

    public void setGoalWeight(String goalWeight) {
        this.goalWeight = goalWeight;
    }

    public String getWeightAndPercentage() {
        return weightAndPercentage;
    }

    public void setWeightAndPercentage(String weightAndPercentage) {
        this.weightAndPercentage = weightAndPercentage;
    }

    public String getWeightStatus() {
        return weightStatus;
    }

    public void setWeightStatus(String weightStatus) {
        this.weightStatus = weightStatus;
    }

    public String getWeight1() {
        return weight1;
    }

    public void setWeight1(String weight1) {
        this.weight1 = weight1;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }
}
