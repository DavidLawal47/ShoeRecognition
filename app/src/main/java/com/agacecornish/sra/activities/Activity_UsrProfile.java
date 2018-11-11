package com.agacecornish.sra.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.agacecornish.sra.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Activity_UsrProfile extends AppCompatActivity {

    FirebaseAuth auth;
    FirebaseUser user;

    Button goBack;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        textView = findViewById(R.id.email_verified);


        goBackBtn();
        verifyStatus();


    }

    private void goBackBtn() {
        goBack = findViewById(R.id.btn_goback);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_UsrProfile.this, Activity_Register.class));
            }
        });

    }

    public void verifyStatus() {
        if (user.isEmailVerified()) {
            textView.setText("" +
                    "Email Verified");
        } else {
            textView.setText("Email NOT Verified");
        }
    }
}

//}
//
//

//