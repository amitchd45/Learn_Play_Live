package com.example.learnplaylive;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.learnplaylive.Actiivties.HomeActivity;
import com.example.learnplaylive.Model.LoginRegisterModel;
import com.example.learnplaylive.SharedPreference.App;
import com.example.learnplaylive.SharedPreference.AppConstants;
import com.example.learnplaylive.ViewModel.LoginRegisterViewModel;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class AddYourInformationActivity extends AppCompatActivity implements View.OnClickListener {

    private CircleImageView img_add;
    private Button btnsubmit;
    private TextView tvHeader;
    private static final int GALLERY = 1;
    private ImageView backimg;
    private CircleImageView userImage;
    private String imagepath="";
    private EditText userDOB,  userGender, userPhone, userAddress;
    private String S_userImage, S_userGender, S_userPhone, S_userDOB, S_userAddress;
    private LoginRegisterViewModel viewModel;
    private Activity activity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_your_information);
        viewModel = ViewModelProviders.of(this).get(LoginRegisterViewModel.class);

        initIds();
        tvHeader.setText("Add your information");
    }

    private void initIds() {

        activity = AddYourInformationActivity.this;

        img_add=findViewById(R.id.add_image_information);
        btnsubmit=findViewById(R.id.btn_submit_information);
        tvHeader=findViewById(R.id.tv_header);
        backimg=findViewById(R.id.back_topbar);

        img_add.setOnClickListener(this);
        btnsubmit.setOnClickListener(this);
        backimg.setOnClickListener(this);

        userImage = findViewById(R.id.userImage);
        userDOB = findViewById(R.id.userDOB);
        userDOB.setOnClickListener(this);
        userGender = findViewById(R.id.userGender);
        userPhone = findViewById(R.id.userPhone);
        userAddress = findViewById(R.id.userAddress);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add_image_information:
                OpenGallery();
                break;

            case R.id.btn_submit_information:
                validate();
                break;

            case R.id.back_topbar:
                onBackPressed();
                break;

            case R.id.userDOB:
                PickDate();
                break;
        }
    }

    private void validate(){


        S_userImage = imagepath;
        S_userGender = userGender.getText().toString().trim();
        S_userPhone = userPhone.getText().toString().trim();
        S_userDOB = userDOB.getText().toString().trim();
        S_userAddress = userAddress.getText().toString().trim();


        if (S_userImage.isEmpty()){
            Toast.makeText(this, "Please Select any Image", Toast.LENGTH_SHORT).show();
        } else if (S_userGender.isEmpty()){
            userGender.setError("Enter your gender");
        } else if (S_userPhone.isEmpty()){
            userPhone.setError("Enter your Phone Number");
        } else if (S_userDOB.isEmpty()){
            userDOB.setError("Enter your Date of Birth");
        } else if (S_userAddress.isEmpty()){
            userAddress.setError("Enter your Address");
        } else{
            UserRegister();
        }
    }

    private void UserRegister() {
        String reg_id = "12345";
        String device_type = "Android";
        final String login_type = "normal";

        File file = new File(imagepath);
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);

        RequestBody name=RequestBody.create(MediaType.get("text/plain"), App.getSinltonPojo().getUserName());
        RequestBody email=RequestBody.create(MediaType.get("text/plain"), App.getSinltonPojo().getUserEmail());
        RequestBody password=RequestBody.create(MediaType.get("text/plain"), App.getSinltonPojo().getUserPassword());
        RequestBody gender=RequestBody.create(MediaType.get("text/plain"), S_userGender);
        RequestBody phone=RequestBody.create(MediaType.get("text/plain"), S_userPhone);
        RequestBody dob=RequestBody.create(MediaType.get("text/plain"), S_userDOB);
        RequestBody address=RequestBody.create(MediaType.get("text/plain"), S_userAddress);
        RequestBody regId=RequestBody.create(MediaType.get("text/plain"), reg_id);
        RequestBody deviceType=RequestBody.create(MediaType.get("text/plain"), device_type);
        RequestBody loginType=RequestBody.create(MediaType.get("text/plain"), login_type);

        viewModel.register(activity, name, email, password, body,gender,phone,dob,address, regId, deviceType, loginType).observe(AddYourInformationActivity.this, new Observer<LoginRegisterModel>() {
            @Override
            public void onChanged(LoginRegisterModel loginRegisterModel) {
                if (loginRegisterModel.getSuccess().equalsIgnoreCase("1")){
                    App.getSinltonPojo().setSocialLoginStatus(null);
                    App.getAppPreference().saveLoginDetail(loginRegisterModel);
                    App.getAppPreference().saveString(AppConstants.TOKEN,"1");
                    startActivity(new Intent(activity,HomeActivity.class));
                    Toast.makeText(activity, loginRegisterModel.getMessage(), Toast.LENGTH_SHORT).show();
                    finishAffinity();
                }else {
                    Toast.makeText(activity, loginRegisterModel.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });




//        startActivity(new Intent(AddYourInformationActivity.this, HomeActivity.class));
    }

    private void OpenGallery(){

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                String resultUri = result.getUri().getPath();
                Glide.with(this).load(resultUri).into(userImage);
                imagepath = resultUri;
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    private void PickDate() {
        final Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH);
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog mDatePicker = new DatePickerDialog(AddYourInformationActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mcurrentDate.set(Calendar.YEAR, year);
                mcurrentDate.set(Calendar.MONTH, month);
                mcurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                userDOB.setText(sdf.format(mcurrentDate.getTime()));
            }
        }, mYear, mMonth, mDay);

        long value = mcurrentDate.getTimeInMillis();
        mDatePicker.getDatePicker().setMaxDate(value);
        mDatePicker.setTitle("Choose Birth Date");
        mDatePicker.show();
    }

}
