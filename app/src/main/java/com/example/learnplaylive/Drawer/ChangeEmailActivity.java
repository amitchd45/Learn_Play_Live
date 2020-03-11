package com.example.learnplaylive.Drawer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnplaylive.Model.ChangeEmailModel;
import com.example.learnplaylive.R;
import com.example.learnplaylive.SharedPreference.App;
import com.example.learnplaylive.ViewModel.LoginRegisterViewModel;

public class ChangeEmailActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText currentEmail_Et, newEmail_Et, confirmNewEmail_et, passwordCahngeEmail_Et;
    private Button changeEmailBtn;
    private String S_currentEmail, S_newEmail, S_confirmNewEmail, S_passwordCahngeEmail;
    private LoginRegisterViewModel viewModel;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_email);
        viewModel = ViewModelProviders.of(this).get(LoginRegisterViewModel.class);
        findIds();
    }

    private void findIds() {
        activity = ChangeEmailActivity.this;
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("Change Email");

        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        currentEmail_Et = findViewById(R.id.currentEmail_Et);
        newEmail_Et = findViewById(R.id.newEmail_Et);
        confirmNewEmail_et = findViewById(R.id.confirmNewEmail_et);
//        passwordCahngeEmail_Et = findViewById(R.id.passwordCahngeEmail_Et);
        changeEmailBtn = findViewById(R.id.changeEmailBtn);
        changeEmailBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar:
                onBackPressed();
                break;

            case R.id.changeEmailBtn:
                validate();



        }
    }

    private void validate() {
        S_currentEmail = currentEmail_Et.getText().toString().trim();
        S_newEmail = newEmail_Et.getText().toString().trim();
        S_confirmNewEmail = confirmNewEmail_et.getText().toString().trim();
//        S_passwordCahngeEmail = passwordCahngeEmail_Et.getText().toString().trim();
        String pass = App.getAppPreference().getLoginDetail().getDetails().getPassword();
        String CuurEmail = App.getAppPreference().getLoginDetail().getDetails().getEmail();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]{2,4}";

        if (S_currentEmail.equalsIgnoreCase(CuurEmail)) {

            if (S_newEmail.isEmpty()) {
                newEmail_Et.setError("Enter email");
            } else if (S_newEmail.isEmpty() || !S_newEmail.matches(emailPattern)) {
                newEmail_Et.setError("Enter valid email");
            } else if (S_confirmNewEmail.isEmpty()) {
                confirmNewEmail_et.setError("Confirm Email");
            } else if (S_confirmNewEmail.isEmpty() || !S_confirmNewEmail.matches(emailPattern)) {
                confirmNewEmail_et.setError("Enter valid Email");
            } else if (!S_confirmNewEmail.equals(S_newEmail)) {
                confirmNewEmail_et.setError("Email mismatch");
            }
            else {
                changeEmail();
            }
        }
        else {
            currentEmail_Et.setError("Please Enter Correct Email");

        }
    }

    private void changeEmail() {
        String userId = App.getAppPreference().getLoginDetail().getDetails().getId();
        viewModel.changeEmail(activity,userId,S_currentEmail, S_newEmail).observe(ChangeEmailActivity.this, new Observer<ChangeEmailModel>() {
            @Override
            public void onChanged(ChangeEmailModel changeEmailModel) {
                if (changeEmailModel.getSuccess().equalsIgnoreCase("1")){
                    Toast.makeText(activity, changeEmailModel.getMessage(), Toast.LENGTH_SHORT).show();
                    onBackPressed();
                }else {
                    Toast.makeText(activity, changeEmailModel.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}
