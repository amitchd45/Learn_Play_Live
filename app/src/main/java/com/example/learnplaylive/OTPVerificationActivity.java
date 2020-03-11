package com.example.learnplaylive;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnplaylive.Model.CheckEmailModel;
import com.example.learnplaylive.SharedPreference.App;
import com.example.learnplaylive.ViewModel.LoginRegisterViewModel;

import org.w3c.dom.Text;

public class OTPVerificationActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnSubmit;
    private LayoutInflater layoutInflater;
    private EditText otp1, otp2, otp3, otp4;
    private LinearLayout lay_resend_code;
    private LoginRegisterViewModel viewModel;
    private Activity activity;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverification);
        viewModel = ViewModelProviders.of(this).get(LoginRegisterViewModel.class);


        initIds();

//        OTPFocus1();
//        OTPFocus2();
//        OTPFocus3();
//        OTPFocus4();
        OTPCode();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initIds() {
        activity = OTPVerificationActivity.this;

        btnSubmit=findViewById(R.id.btn_submit_verify);
        btnSubmit.setOnClickListener(this);

        layoutInflater = LayoutInflater.from(OTPVerificationActivity.this);


        otp1 = findViewById(R.id.otp1);
        otp1.requestFocus();
        otp1.setShowSoftInputOnFocus(true);
        otp2 = findViewById(R.id.otp2);
        otp3 = findViewById(R.id.otp3);
        otp4 = findViewById(R.id.otp4);

        lay_resend_code = findViewById(R.id.lay_resend_code);
        lay_resend_code.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_submit_verify:
                VerifyOTP();
                break;
            case R.id.lay_resend_code:
                resendOTP();
                break;



        }
    }

    private void resendOTP() {
        String S_userEmail = App.getSinltonPojo().getUserEmail();
        viewModel.checkEmail(activity,S_userEmail).observe(OTPVerificationActivity.this, new Observer<CheckEmailModel>() {
            @Override
            public void onChanged(CheckEmailModel checkEmailModel) {
                if (checkEmailModel.getSuccess().equalsIgnoreCase("1")){
                    App.getSinltonPojo().setUserOTP(String.valueOf(checkEmailModel.getOtp()));
                    Toast.makeText(activity, String.valueOf(checkEmailModel.getOtp()), Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(activity, checkEmailModel.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void Dialog(){
        final View confirmdailog = layoutInflater.inflate(R.layout.dialog_verification, null);
        final AlertDialog dailogbox = new AlertDialog.Builder(OTPVerificationActivity.this).create();
        dailogbox.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dailogbox.setCancelable(false);
        dailogbox.setView(confirmdailog);

        confirmdailog.findViewById(R.id.btn_done_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(OTPVerificationActivity.this, TermsAndConditionsActivity.class));
                // finishAffinity();

            }
        });
        dailogbox.show();
    }


    private void VerifyOTP() {

        String OTP1 = otp1.getText().toString();
        String OTP2 = otp2.getText().toString();
        String OTP3 = otp3.getText().toString();
        String OTP4 = otp4.getText().toString();
        String TOKEN = OTP1 + OTP2 + OTP3 + OTP4;

        if (App.getSinltonPojo().getUserOTP().equals(TOKEN)){
            Dialog();
        }
        else {
            Toast.makeText(this, "OTP doen't match", Toast.LENGTH_SHORT).show();
        }

    }

//
//    private void ResendVerifyOTP(){
//        String ID = App.getSharedPre().getProfileModel().getDetails().getId();
//        viewModel.ResendOTP(OTPVerificationActivity.this, ID).observe(
//                OTPVerificationActivity.this, new Observer<CheckPhoneNumberModel>() {
//                    @Override
//                    public void onChanged(CheckPhoneNumberModel checkPhoneNumberModel) {
//                        if (checkPhoneNumberModel.getSuccess().equalsIgnoreCase("1")) {
//                            App.getSharedPre().setProfileData(checkPhoneNumberModel);
//
//
//                        } else {
//
//                            Toast.makeText(OTPVerificationActivity.this, "OTP does'nt match !", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }


//

    private void OTPCode() {

        otp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 1) {
                    otp2.requestFocus();
                    otp1.setElevation(1);
                } else if (s.length() == 0) {

                }
            }
        });

        otp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() == 1) {
                    otp3.requestFocus();
                    otp2.setElevation(1);
                } else if (s.length() == 0) {

                    otp1.requestFocus();
                }
            }
        });

        otp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 1) {
                    otp4.requestFocus();
                    otp3.setElevation(1);
                } else if (s.length() == 0) {
                    otp2.requestFocus();
                }
            }
        });

        otp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void afterTextChanged(Editable s) {

                if (s.length() == 1) {
                    otp4.setElevation(1);
                } else if (s.length() == 0) {
                    otp3.requestFocus();
                }
            }
        });

    }
}
