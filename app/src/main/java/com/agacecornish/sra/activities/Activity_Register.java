package com.agacecornish.sra.activities;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agacecornish.sra.R;
import com.agacecornish.sra.models.User;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Activity_Register extends AppCompatActivity implements View.OnClickListener{

    private EditText mName, mEmail, mPassword, mCpassword;
    Button registerButton, sign_In;
    private User regUser;

    //temp
    private static int regUserCounter = 0;

    private DatabaseReference mDatabase;

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

        //getting instance of database
        mDatabase = FirebaseDatabase.getInstance().getReference();



        screen1 = (RelativeLayout) findViewById(R.id.screen1);

        handler.postDelayed(runnable, 3000);

        mName =  findViewById(R.id.name);
        mEmail =  findViewById(R.id.email);
        mPassword =  findViewById(R.id.password);
        mCpassword =  findViewById(R.id.c_password);
        registerButton = (Button) findViewById(R.id.btn);
        registerButton.setOnClickListener(this);

       // sign_In = (Button) findViewById(R.id.sign_in);

    }

    @Override
    public void onClick(View v) {

        //creating user
        regUser = new User(mName.getText().toString(), mEmail.getText().toString(),
                mPassword.getText().toString());

        //adding user to database
        mDatabase.child("users").child("uID"+regUserCounter).setValue(regUser);

        //increment do remove later
        regUserCounter++;

        Log.d(regUser.toString(), "User Registered");

        Toast.makeText(v.getContext(), "Click here",
                Toast.LENGTH_SHORT).show();
    }
}
