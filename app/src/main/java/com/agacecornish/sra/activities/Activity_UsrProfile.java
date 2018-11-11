package com.agacecornish.sra.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.agacecornish.sra.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class activity_userprofile extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();


        goBackBtn();



    }

    private void goBackBtn(){
        goBack = findViewById(R.id.btn_goback);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(activity_userprofile.this,Activity_Register.class));
            }
        });
    }

}
