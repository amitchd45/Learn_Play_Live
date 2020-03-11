package com.example.learnplaylive.Drawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnplaylive.LoginActivity;
import com.example.learnplaylive.Model.ChangeEmailModel;
import com.example.learnplaylive.Model.ChangePasswordModel;
import com.example.learnplaylive.R;
import com.example.learnplaylive.SharedPreference.App;
import com.example.learnplaylive.ViewModel.LoginRegisterViewModel;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText currentPassword_Et, newPassword_Et, confirmNewPassword_et;
    private Button changePasswordlBtn;
    private String S_currentPassword, S_newPassword, S_confirmNewPassword;
    private LoginRegisterViewModel viewModel;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        viewModel = ViewModelProviders.of(this).get(LoginRegisterViewModel.class);
        findIds();
    }

    private void findIds() {
        activity = ChangePasswordActivity.this;
        TextView textView = findViewById(R.id.tv_header);
        textView.setText("Change Password");

        ImageView back = findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        currentPassword_Et = findViewById(R.id.currentPassword_Et);
        newPassword_Et = findViewById(R.id.newPassword_Et);
        confirmNewPassword_et = findViewById(R.id.confirmNewPassword_et);
        changePasswordlBtn = findViewById(R.id.changePasswordlBtn);
        changePasswordlBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_topbar:
                onBackPressed();
                break;
            case R.id.changePasswordlBtn:
                if (App.getSinltonPojo().getForgotPasswordStatus() != null) {
                    if (App.getSinltonPojo().getForgotPasswordStatus().equalsIgnoreCase("1")) {
                        validate();
                    } else {
                        validate();
                    }
                } else {
                    validate();
                }

                break;


        }
    }

    private void validate() {
        S_currentPassword = currentPassword_Et.getText().toString().trim();
        S_newPassword = newPassword_Et.getText().toString().trim();
        S_confirmNewPassword = confirmNewPassword_et.getText().toString().trim();
        String CuurPass = String.valueOf(App.getAppPreference().getLoginDetail().getDetails().getPassword());

        if (S_currentPassword.equals(CuurPass)) {
            currentPassword_Et.setError("Please Enter Correct Password");
        } else if (S_newPassword.isEmpty() || S_newPassword.length() < 5) {
            newPassword_Et.setError("enter minimum 5 character password");
        } else if (S_confirmNewPassword.isEmpty()) {
            confirmNewPassword_et.setError("Confirm password");
        } else if (!S_confirmNewPassword.equals(S_newPassword)) {
            confirmNewPassword_et.setError("password mismatch");
        } else {
            changePassword();

        }

    }

    private void changePassword() {
        String userId = App.getAppPreference().getLoginDetail().getDetails().getId();
        viewModel.changePassword(activity, userId, S_currentPassword, S_newPassword).observe(ChangePasswordActivity.this, new Observer<ChangePasswordModel>() {
            @Override
            public void onChanged(ChangePasswordModel changePasswordModel) {
                if (changePasswordModel.getSuccess().equalsIgnoreCase("1")) {

                    if (App.getSinltonPojo().getForgotPasswordStatus()!=null){
                        if (App.getSinltonPojo().getForgotPasswordStatus().equalsIgnoreCase("1")){
                            Toast.makeText(activity, "Login with New Password", Toast.LENGTH_SHORT).show();
                            App.getAppPreference().clearAppPreferences();
                            App.getSinltonPojo().setForgotPasswordStatus("0");
                            startActivity(new Intent(activity, LoginActivity.class));
                            finish();
                        }
                    }else {
                        Toast.makeText(activity, changePasswordModel.getMessage(), Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                } else {
                    Toast.makeText(activity, changePasswordModel.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
