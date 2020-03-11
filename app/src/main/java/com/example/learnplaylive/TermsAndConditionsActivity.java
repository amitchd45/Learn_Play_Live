package com.example.learnplaylive;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.learnplaylive.Actiivties.HomeActivity;

public class TermsAndConditionsActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnAccept,btnSkip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms_and_conditions);

        initIds();

    }

    private void initIds() {

        btnAccept=findViewById(R.id.btn_accept_tnc);
        btnSkip=findViewById(R.id.btn_skip_tnc);

        btnSkip.setOnClickListener(this);
        btnAccept.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_accept_tnc:
                startActivity(new Intent(TermsAndConditionsActivity.this,AddYourInformationActivity.class));
                break;

            case R.id.btn_skip_tnc:
                startActivity(new Intent(TermsAndConditionsActivity.this, AddYourInformationActivity.class));
                break;


        }
    }
}
