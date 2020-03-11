package com.example.learnplaylive.Drawer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.learnplaylive.R;

public class SecurityAndLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout changeEmailLl;
    private TextView changePasswordTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security_and_login);
        findIds();
    }

    private void findIds() {
        TextView textView=findViewById(R.id.tv_header);
        textView.setText("Security & Login");

        ImageView back= findViewById(R.id.back_topbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);

        changeEmailLl = findViewById(R.id.changeEmailLl);
        changeEmailLl.setOnClickListener(this);

        changePasswordTv = findViewById(R.id.changePasswordTv);
        changePasswordTv.setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_topbar:
                onBackPressed();
                break;

            case R.id.changeEmailLl:
                startActivity(new Intent(SecurityAndLoginActivity.this, ChangeEmailActivity.class));
                break;
            case R.id.changePasswordTv:
                startActivity(new Intent(SecurityAndLoginActivity.this, ChangePasswordActivity.class));
                break;


        }
    }
}
