package com.example.learnplaylive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnplaylive.Model.CheckEmailModel;
import com.example.learnplaylive.Model.LoginRegisterModel;
import com.example.learnplaylive.SharedPreference.App;
import com.example.learnplaylive.ViewModel.LoginRegisterViewModel;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_login,tvHeader;
    private Button btnRegister;
    private ImageView backimg;
    private EditText userName, userEmail, userPassword, userConfirmPassword ;
    private String S_userName, S_userEmail, S_userPassword, S_userConfirmPassword;
    private LoginRegisterViewModel viewModel;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        viewModel = ViewModelProviders.of(this).get(LoginRegisterViewModel.class);


        initIds();

    }

    private void initIds() {

        activity = RegisterActivity.this;

        tv_login=findViewById(R.id.tv_login_register);
        tv_login.setOnClickListener(this);


        btnRegister=findViewById(R.id.btn_register_register);
        btnRegister.setOnClickListener(this);


        userName = findViewById(R.id.userName);
        userEmail = findViewById(R.id.userEmail);
        userPassword = findViewById(R.id.userPassword);
        userConfirmPassword = findViewById(R.id.userConfirmPassword);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.tv_login_register:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                break;

            case R.id.btn_register_register:
                validate();
//                startActivity(new Intent(RegisterActivity.this,OTPVerificationActivity.class));
                break;



        }
    }
    private void validate() {
        S_userName = userName.getText().toString().trim();
        S_userEmail = userEmail.getText().toString().trim();
        S_userPassword = userPassword.getText().toString().trim();
        S_userConfirmPassword = userConfirmPassword.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]{2,4}";

        if (S_userName.isEmpty()) {
            userName.setError("Enter User Name");
        } else if (S_userEmail.isEmpty()) {
            userEmail.setError("Enter email");
        } else if (S_userEmail.isEmpty() || !S_userEmail.matches(emailPattern)) {
            userEmail.setError("Enter valid email");
        } else if (S_userPassword.isEmpty() || S_userPassword.length() < 5) {
            userPassword.setError("enter minimum 5 character password");
        } else if (S_userConfirmPassword.isEmpty()) {
            userConfirmPassword.setError("Confirm password");
        } else if (!S_userConfirmPassword.equals(S_userPassword)) {
            userConfirmPassword.setError("password mismatch");
        }
        else {
            CheckEmail();

        }
    }

    private void CheckEmail() {
         viewModel.checkEmail(activity,S_userEmail).observe(RegisterActivity.this, new Observer<CheckEmailModel>() {
           @Override
           public void onChanged(CheckEmailModel checkEmailModel) {
               if (checkEmailModel.getSuccess().equalsIgnoreCase("1")){

                   App.getSinltonPojo().setUserName(S_userName);
                   App.getSinltonPojo().setUserEmail(S_userEmail);
                   App.getSinltonPojo().setUserPassword(S_userPassword);
                   App.getSinltonPojo().setUserOTP(String.valueOf(checkEmailModel.getOtp()));
                   Toast.makeText(activity, String.valueOf(checkEmailModel.getOtp()), Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(activity, OTPVerificationActivity.class));

               } else {
                   Toast.makeText(activity, checkEmailModel.getMessage(), Toast.LENGTH_SHORT).show();
               }

           }
       });

    }
}
