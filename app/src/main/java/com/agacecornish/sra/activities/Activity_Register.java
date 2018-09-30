package com.agacecornish.sra.activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agacecornish.sra.R;
import com.agacecornish.sra.models.User;


public class Activity_Register extends AppCompatActivity implements View.OnClickListener{

    private TextView mName, mEmail, mPassword, mCpassword;
    Button registerButton, sign_In;
    private User regUser;

    RelativeLayout screen1, btn, signIn;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            screen1.setVisibility(View.VISIBLE);
            }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__register);



        screen1 = (RelativeLayout) findViewById(R.id.screen1);

        handler.postDelayed(runnable, 3000);

        mName = (TextView) findViewById(R.id.name);
        mEmail = (TextView) findViewById(R.id.email);
        mPassword = (TextView) findViewById(R.id.password);
        mCpassword = (TextView) findViewById(R.id.c_password);
        registerButton = (Button) findViewById(R.id.btn);
        registerButton.setOnClickListener(this);

       // sign_In = (Button) findViewById(R.id.sign_in);





    }

    @Override
    public void onClick(View v) {

        regUser = new User(mName.toString(), mEmail.toString(), mPassword.toString());
        Log.d(regUser.toString(), "User Registered");

        Toast.makeText(v.getContext(), "Click here",
                Toast.LENGTH_SHORT).show();
    }
}
