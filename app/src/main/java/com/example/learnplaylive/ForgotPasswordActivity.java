package com.example.learnplaylive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.learnplaylive.Model.ForgotPasswordModel;
import com.example.learnplaylive.SharedPreference.App;
import com.example.learnplaylive.ViewModel.LoginRegisterViewModel;

public class ForgotPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSentOtpForgotPass;
    private EditText email_forgotPass;
    private String S_userEmail = "";
    private LoginRegisterViewModel viewModel;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        viewModel = ViewModelProviders.of(this).get(LoginRegisterViewModel.class);
        findIds();
    }

    private void findIds() {

        activity = ForgotPasswordActivity.this;
        btnSentOtpForgotPass = findViewById(R.id.btnSentOtpForgotPass);
        btnSentOtpForgotPass.setOnClickListener(this);
        email_forgotPass = findViewById(R.id.email_forgotPass);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnSentOtpForgotPass:
                App.getSinltonPojo().setForgotPasswordStatus("1");
//                startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                checkForgetEmail();
                break;
        }
    }

    private void checkForgetEmail() {

        S_userEmail = email_forgotPass.getText().toString();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]{2,4}";
        if (S_userEmail.isEmpty()) {
            email_forgotPass.setError("Enter email");
        } else if (S_userEmail.isEmpty() || !S_userEmail.matches(emailPattern)) {
            email_forgotPass.setError("Enter valid email");
        }else {
            forgotEmail();
        }
    }

    private void forgotEmail() {

        viewModel.forgotPassword(activity, S_userEmail).observe(ForgotPasswordActivity.this,  new Observer<ForgotPasswordModel>() {
            @Override
            public void onChanged(ForgotPasswordModel forgotPasswordModel) {
                if (forgotPasswordModel.getSuccess().equalsIgnoreCase("1")){
                    Toast.makeText(activity, "Password has been sent to your Email", Toast.LENGTH_SHORT).show();
                    App.getAppPreference().clearAppPreferences();
                    startActivity(new Intent(ForgotPasswordActivity.this, LoginActivity.class));
                }

            }
        });


    }
}
