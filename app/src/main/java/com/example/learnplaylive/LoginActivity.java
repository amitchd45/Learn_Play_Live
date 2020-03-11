package com.example.learnplaylive;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.learnplaylive.Actiivties.HomeActivity;
import com.example.learnplaylive.Drawer.ChangePasswordActivity;
import com.example.learnplaylive.Model.LoginRegisterModel;
import com.example.learnplaylive.SharedPreference.App;
import com.example.learnplaylive.SharedPreference.AppConstants;
import com.example.learnplaylive.SharedPreference.CommonUtils;
import com.example.learnplaylive.ViewModel.LoginRegisterViewModel;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookAuthorizationException;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;

import org.json.JSONObject;

import java.net.URL;
import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLogin;
    private TextView tvHeader, tvRegister, forgotPassword_Tv;
    private ImageView backimg, facebookLogin, gmailLogin;
    private LoginRegisterViewModel viewModel;
    private Activity activity;
    private EditText email_Login, password_login;
    private String S_userEmail, S_userPassword;
    private CallbackManager callbackManager;
    private String fbId = "", fbFirstName = "", fbLastName = "", fbEmail = "", fbSocialUserName = "", fbPhoneNumber = "", fbGender = "", fbDateOfBirth = "", fbCountry = "", fbProfilePicture = "";
    private static final int RC_SIGN_IN = 007;
    private String userName = "", userStringEmail = "", socialId = "", loginType, userImage = "", phoneNumber = "", gender = "", dateofbirth = "", country;
    private GoogleSignInClient mGoogleSignInClient;
    private URL fbProfilePicturenew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        viewModel = ViewModelProviders.of(this).get(LoginRegisterViewModel.class);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        FacebookSdk.sdkInitialize(getApplication());
        AppEventsLogger.activateApp(getApplication());
        callbackManager = CallbackManager.Factory.create();


        initIds();


    }

    private void initIds() {

        activity = LoginActivity.this;

        btnLogin = findViewById(R.id.btn_login_login);
        btnLogin.setOnClickListener(this);
        tvRegister = findViewById(R.id.tv_register_login);
        tvRegister.setOnClickListener(this);
        email_Login = findViewById(R.id.email_Login);
        password_login = findViewById(R.id.password_login);
        forgotPassword_Tv = findViewById(R.id.forgotPassword_Tv);
        forgotPassword_Tv.setOnClickListener(this);
        facebookLogin = findViewById(R.id.facebookLogin);
        facebookLogin.setOnClickListener(this);
        gmailLogin = findViewById(R.id.gmailLogin);
        gmailLogin.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_login_login:
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

            case R.id.tv_register_login:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;

            case R.id.forgotPassword_Tv:
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
                break;

            case R.id.facebookLogin:
                FBLogin();
                break;

            case R.id.gmailLogin:
                SignIn();
                break;
        }
    }


    private void validate() {
        S_userEmail = email_Login.getText().toString();
        S_userPassword = password_login.getText().toString();

        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]{2,4}";
        if (S_userEmail.isEmpty()) {
            email_Login.setError("Enter email");
        } else if (S_userEmail.isEmpty() || !S_userEmail.matches(emailPattern)) {
            email_Login.setError("Enter valid email");
        } else if (S_userPassword.isEmpty()) {
            password_login.setError("enter minimum 5 character password");
        } else {
//            login();
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }
    }

    private void login() {
        String reg_id = "12345";
        String device_type = "Android";
        viewModel.login(activity, S_userEmail, S_userPassword, device_type, reg_id).observe(LoginActivity.this, new Observer<LoginRegisterModel>() {
            @Override
            public void onChanged(LoginRegisterModel loginRegisterModel) {
                if (loginRegisterModel.getSuccess().equalsIgnoreCase("1")) {
                    App.getAppPreference().saveLoginDetail(loginRegisterModel);
                    App.getAppPreference().saveString(AppConstants.TOKEN, "1");
                    if (App.getSinltonPojo().getForgotPasswordStatus() != null) {
                        if (App.getSinltonPojo().getForgotPasswordStatus().equalsIgnoreCase("1")) {
                            startActivity(new Intent(LoginActivity.this, ChangePasswordActivity.class));
                        } else if (App.getSinltonPojo().getForgotPasswordStatus().equalsIgnoreCase("0")) {
                            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                        }
                    } else {
                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    }

                } else {
                    Toast.makeText(activity, loginRegisterModel.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void FBLogin() {
        if (CommonUtils.isNetworkConnected(activity)) {
            CommonUtils.showProgress(activity, "");
            LoginManager.getInstance().logInWithReadPermissions(activity, Arrays.asList("public_profile"));
            LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    CommonUtils.dismissProgress();

                    Log.d("onSuccess: ", loginResult.getAccessToken().getToken());
                    getFacebookData(loginResult);
//                    Toast.makeText(activity, ""+userStringEmail, Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancel() {
                    CommonUtils.dismissProgress();
                    Toast.makeText(activity, "Cancel", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onError(FacebookException error) {
                    CommonUtils.dismissProgress();
                    if (error instanceof FacebookAuthorizationException) {
                        if (AccessToken.getCurrentAccessToken() != null) {
                            LoginManager.getInstance().logOut();
                        }
                    }
                    FBLogin();
                }
            });
        } else {
            Toast.makeText(activity, "Network Issue", Toast.LENGTH_SHORT).show();
        }
    }

    private void getFacebookData(LoginResult loginResult) {
        CommonUtils.showProgress(activity, "");
        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                CommonUtils.dismissProgress();
                try {

                    if (object.has("id")) {
                        fbId = object.getString("id");
                        Log.e("LoginActivity", "id" + fbId);

                    }
                    //check permission first userName
                    if (object.has("first_name")) {
                        fbFirstName = object.getString("first_name");
                        Log.e("LoginActivity", "first_name" + fbFirstName);

                    }
                    //check permisson last userName
                    if (object.has("last_name")) {
                        fbLastName = object.getString("last_name");
                        Log.e("LoginActivity", "last_name" + fbLastName);
                    }
                    //check permisson email
                    if (object.has("email")) {
                        fbEmail = object.getString("email");
                        Log.e("LoginActivity", "email" + fbEmail);
                    }
                    if (object.has("phoneNumber")) {
                        fbPhoneNumber = object.getString("phoneNumber");
                        Log.e("LoginActivity", "email" + fbPhoneNumber);
                    }

                    if (object.has("gender")) {
                        fbGender = object.getString("gender");
                        Log.e("LoginActivity", "email" + fbGender);
                    }

                    if (object.has("dateofbirth")) {
                        fbDateOfBirth = object.getString("dateofbirth");
                        Log.e("LoginActivity", "email" + fbDateOfBirth);
                    }

                    if (object.has("country")) {
                        fbCountry = object.getString("country");
                        Log.e("LoginActivity", "email" + fbCountry);
                    }
                    JSONObject jsonObject = new JSONObject(object.getString("picture"));
                    if (jsonObject != null) {
                        JSONObject dataObject = jsonObject.getJSONObject("data");
                        Log.e("LoginActivity", "json oject get picture" + dataObject);
                        fbProfilePicturenew = new URL("https://graph.facebook.com/" + fbId + "/picture?width=500&height=500");
                        Log.e("LoginActivity", "json object=>" + object.toString());
                    }


                    fbSocialUserName = fbFirstName + " " + fbLastName;

                    userName = fbSocialUserName;
                    userStringEmail = fbEmail;
                    socialId = fbId;
                    phoneNumber = fbPhoneNumber;
                    gender = fbGender;
                    dateofbirth = fbDateOfBirth;
                    country = fbCountry;
                    loginType = "facebook";

                    if (fbProfilePicture != null) {
                        userImage = String.valueOf(fbProfilePicturenew);
                    } else {
                        userImage = "";
                    }


                    CheckSocialId();


                } catch (Exception e) {

                }
            }
        });

        Bundle bundle = new Bundle();
        Log.e("LoginActivity", "bundle set");
        bundle.putString("fields", "id, first_name, last_name,email,picture,gender,location");
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }

    private void SignIn() {

        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void handleSignInResult(GoogleSignInResult result) {
        CommonUtils.showProgress(activity, "");
        if (result.isSuccess()) {
            CommonUtils.dismissProgress();
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Log.d("Account: ", acct.getDisplayName());
            Log.d("Account: ", acct.getId());
            Log.d("Account: ", acct.getEmail());
            socialId = acct.getId();
            userName = acct.getDisplayName();
            userStringEmail = acct.getEmail();
            loginType = "Google";
            if (acct.getPhotoUrl() != null) {
                userImage = String.valueOf(acct.getPhotoUrl());
            } else {
                userImage = "";
            }
            App.getAppPreference().saveString(AppConstants.LOGIN_TYPE, "Google");
            CheckSocialId();
        } else {
            Toast.makeText(activity, "Something went wrong", Toast.LENGTH_SHORT).show();
            CommonUtils.dismissProgress();

        }
    }

    private void CheckSocialId() {
        viewModel.checkSocialId(activity, socialId).observe(LoginActivity.this, new Observer<LoginRegisterModel>() {
            @Override
            public void onChanged(LoginRegisterModel loginRegisterModel) {
                if (loginRegisterModel.getSuccess().equalsIgnoreCase("1")) {
                    Toast.makeText(activity, loginRegisterModel.getMessage(), Toast.LENGTH_SHORT).show();
                    App.getAppPreference().saveLoginDetail(loginRegisterModel);
                    App.getAppPreference().saveString(AppConstants.TOKEN, "1");
                    startActivity(new Intent(activity, HomeActivity.class));
                } else {
                    socialLogin();
                }

            }


        });
    }

    private void socialLogin() {
        viewModel.socialLogin(activity, userName, userStringEmail, phoneNumber, socialId, "Android", "12345", userImage, loginType).observe(LoginActivity.this, new Observer<LoginRegisterModel>() {
            @Override
            public void onChanged(LoginRegisterModel loginRegisterModel) {
                if (loginRegisterModel.getSuccess().equalsIgnoreCase("1")) {
                    Toast.makeText(activity, loginRegisterModel.getMessage(), Toast.LENGTH_SHORT).show();
                    App.getAppPreference().saveLoginDetail(loginRegisterModel);
                    App.getAppPreference().saveString(AppConstants.TOKEN, "1");
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                } else {
                    Toast.makeText(activity, loginRegisterModel.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult task = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(task);
        } else {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }
    }
}
