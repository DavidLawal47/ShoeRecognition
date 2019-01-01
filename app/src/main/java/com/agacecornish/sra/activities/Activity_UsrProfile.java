package com.agacecornish.sra.activities;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;


import com.agacecornish.sra.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Activity_UsrProfile extends AppCompatActivity {




   // **Navigation Drawer**
    DrawerLayout drawer_layout;
     ActionBarDrawerToggle toggle;
    NavigationView nvDrawer;




    FirebaseAuth auth;
    FirebaseUser user;

    Button goBack;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);




    // *****Navigation Drawer*****

        drawer_layout = findViewById(R.id.drawer_layout);

        toggle = new ActionBarDrawerToggle(this,drawer_layout, R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawer_layout.addDrawerListener(toggle);
        toggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);








        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        textView = findViewById(R.id.Email_verified);



        goBackBtn();

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

