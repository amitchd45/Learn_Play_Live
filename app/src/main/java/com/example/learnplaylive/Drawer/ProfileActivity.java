package com.example.learnplaylive.Drawer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.learnplaylive.Model.LoginRegisterModel;
import com.example.learnplaylive.R;
import com.example.learnplaylive.SharedPreference.App;
import com.example.learnplaylive.SharedPreference.AppConstants;
import com.example.learnplaylive.ViewModel.LoginRegisterViewModel;
import com.google.android.material.snackbar.Snackbar;
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

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private Spinner profile_gender_spinner;
    private int mYear, mMonth, mDay;
    private  EditText et_name, et_phone, et_location, et_aboutMe, et_dob_profile , et_height;
    private ImageView edit;
    private Button btn_save_profile;
    private CircleImageView civ_profile, addNewImage;
    private String imagepath="", S_name, S_phone, S_address, S_aboutMe, S_dob, S_gender, S_height;
    private LoginRegisterViewModel viewModel;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        viewModel = ViewModelProviders.of(this).get(LoginRegisterViewModel.class);
        spinner();
        findIds();

    }

    private void findIds() {

        activity = ProfileActivity.this;
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("Edit Profile");
        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        edit= findViewById(R.id.editProfile_topbar);
        edit.setVisibility(View.VISIBLE);
        edit.setOnClickListener(this);

        et_dob_profile = findViewById(R.id.et_dob_profile);
        et_dob_profile.setOnClickListener(this);
        et_name = findViewById(R.id.et_name);
        et_phone = findViewById(R.id.et_phone);
        et_location = findViewById(R.id.et_location);
        et_aboutMe = findViewById(R.id.et_aboutMe);
        et_height = findViewById(R.id.et_height);
        btn_save_profile = findViewById(R.id.btn_save_profile);
        btn_save_profile.setOnClickListener(this);

        civ_profile = findViewById(R.id.civ_profile);
        addNewImage = findViewById(R.id.addNewImage);
        addNewImage.setOnClickListener(this);


        if (App.getAppPreference().getLoginDetail().getDetails().getLoginType()!=null){
            if (App.getAppPreference().getLoginDetail().getDetails().getLoginType().equalsIgnoreCase("normal")){
                Glide.with(ProfileActivity.this).load("http://apptech.mobi/learnPlayLiveApplication/"+App.getAppPreference().getLoginDetail().getDetails().getImage()).into(civ_profile);
            }
            else {
                Glide.with(ProfileActivity.this).load(App.getAppPreference().getLoginDetail().getDetails().getImage()).into(civ_profile);
            }
        }
//        Glide.with(ProfileActivity.this).load(App.getAppPreference().getLoginDetail().getDetails().getImage()).into(civ_profile);
        et_name.setText(App.getAppPreference().getLoginDetail().getDetails().getName());
        et_phone.setText(App.getAppPreference().getLoginDetail().getDetails().getPhone());
        et_location.setText(App.getAppPreference().getLoginDetail().getDetails().getAddress());
        et_dob_profile.setText(App.getAppPreference().getLoginDetail().getDetails().getDob());
        et_aboutMe.setText(App.getAppPreference().getLoginDetail().getDetails().getAboutMe());
        et_height.setText(App.getAppPreference().getLoginDetail().getDetails().getHeight());
        if (App.getAppPreference().getLoginDetail().getDetails().getGender().equalsIgnoreCase("female")){
            profile_gender_spinner.setSelection(0);
        }else if (App.getAppPreference().getLoginDetail().getDetails().getGender().equalsIgnoreCase("male")){
            profile_gender_spinner.setSelection(1);
        }else {
            profile_gender_spinner.setSelection(2);
        }


    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void clicable(){
        et_name.setEnabled(true);
        et_name.setShowSoftInputOnFocus(true);
        et_phone.setEnabled(true);
        et_location.setEnabled(true);
        et_aboutMe.setEnabled(true);
        et_height.setEnabled(true);
        et_dob_profile.setEnabled(true);
        profile_gender_spinner.setEnabled(true);
        addNewImage.setEnabled(true);
        edit.setVisibility(View.GONE);
        btn_save_profile.setVisibility(View.VISIBLE);


    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void clicableTrue(){
        et_name.setEnabled(false);
        et_name.setShowSoftInputOnFocus(false);
        et_phone.setEnabled(false);
        et_location.setEnabled(false);
        et_aboutMe.setEnabled(false);
        et_height.setEnabled(false);
        et_dob_profile.setEnabled(false);
        addNewImage.setEnabled(false);
        profile_gender_spinner.setEnabled(false);
        edit.setVisibility(View.VISIBLE);
        btn_save_profile.setVisibility(View.GONE);


    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar:
                onBackPressed();
                break;
            case R.id.et_dob_profile:
                PickDate();
                break;
            case R.id.editProfile_topbar:
                clicable();
                break;
            case R.id.btn_save_profile:
                clicableTrue();
                validate();
                break;
            case R.id.addNewImage:
                OpenGallery();
                break;
        }

    }

    private void validate() {

        S_name = et_name.getText().toString();
        S_phone = et_phone.getText().toString();
        S_address = et_location.getText().toString();
        S_aboutMe = et_aboutMe.getText().toString();
        S_dob = et_dob_profile.getText().toString();
        S_gender = String.valueOf(profile_gender_spinner.getSelectedItem()).trim();
        S_height = et_height.getText().toString();

//        if (S_name.isEmpty()){
//            et_name.setError("Please Enter your Name");
//        }else if (S_phone.isEmpty()){
//            et_phone.setError("Please Enter your Phone Number");
//        }else if (S_address.isEmpty()){
//            et_location.setError("Please Enter your Address");
//        }else if (S_aboutMe.isEmpty()){
//            et_aboutMe.setError("Please Enter About You");
//        }else if (S_dob.isEmpty()){
//            et_dob_profile.setError("Please Enter your Birthday");
//        }else if (S_height.isEmpty()){
//            et_height.setError("Please Enter your Height");
//        } else {
            updateProfile();
//        }





    }

    private void updateProfile() {
        MultipartBody.Part body;

        File file = new File(imagepath);
        if (!imagepath.equalsIgnoreCase("")) {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
             body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        }else {
            RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), "");
            body = MultipartBody.Part.createFormData("image", file.getName(), requestFile);
        }

        RequestBody name=RequestBody.create(MediaType.get("text/plain"),S_name);
        RequestBody gender=RequestBody.create(MediaType.get("text/plain"), S_gender);
        RequestBody phone=RequestBody.create(MediaType.get("text/plain"), S_phone);
        RequestBody dob=RequestBody.create(MediaType.get("text/plain"), S_dob);
        RequestBody address=RequestBody.create(MediaType.get("text/plain"), S_address);
        RequestBody aboutMe=RequestBody.create(MediaType.get("text/plain"), S_aboutMe);
        RequestBody height=RequestBody.create(MediaType.get("text/plain"), S_height);
        RequestBody userId=RequestBody.create(MediaType.get("text/plain"), App.getAppPreference().getLoginDetail().getDetails().getId());

        viewModel.updateProfile(activity,name,body,gender,phone,dob,address,aboutMe,height,userId).observe(ProfileActivity.this, new Observer<LoginRegisterModel>() {
            @Override
            public void onChanged(LoginRegisterModel loginRegisterModel) {
                if (loginRegisterModel.getSuccess().equalsIgnoreCase("1")){
                    App.getAppPreference().saveLoginDetail(loginRegisterModel);
                    App.getAppPreference().saveString(AppConstants.TOKEN,"1");
                    Toast.makeText(activity, loginRegisterModel.getMessage(), Toast.LENGTH_SHORT).show();

                }
            }
        });
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
                Glide.with(this).load(resultUri).into(civ_profile);
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
        DatePickerDialog mDatePicker = new DatePickerDialog(ProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mcurrentDate.set(Calendar.YEAR, year);
                mcurrentDate.set(Calendar.MONTH, month);
                mcurrentDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
                et_dob_profile.setText(sdf.format(mcurrentDate.getTime()));
            }
        }, mYear, mMonth, mDay);

        long value = mcurrentDate.getTimeInMillis();
        mDatePicker.getDatePicker().setMaxDate(value);
        mDatePicker.setTitle("Choose Birth Date");
        mDatePicker.show();
    }

    private void spinner(){
        profile_gender_spinner = findViewById(R.id.profile_gender_spinner);
        ArrayAdapter<String> profile_genderAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, android.R.id.text1);
        profile_genderAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        profile_gender_spinner.setAdapter(profile_genderAdapter);
        profile_genderAdapter.add("Female");
        profile_genderAdapter.add("Male");
        profile_genderAdapter.add("Other");
        profile_genderAdapter.notifyDataSetChanged();
        profile_gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setTextColor(getResources().getColor(R.color.black));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
