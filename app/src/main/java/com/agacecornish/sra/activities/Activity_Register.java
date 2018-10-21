package com.agacecornish.sra.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.agacecornish.sra.R;
import com.agacecornish.sra.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Activity_Register extends AppCompatActivity implements View.OnClickListener{

    private EditText mName, mEmail, mPassword, mCpassword;
    Button registerButton, sign_In;
    private User regUser;

    //temp
    private static int regUserCounter = 0;

    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;

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

        //getting instance of firebase authentication
        firebaseAuth = FirebaseAuth.getInstance();



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

        String authEmail = mEmail.getText().toString();
        String authPwd = mPassword.getText().toString();

        if(TextUtils.isEmpty(authEmail)){
            Toast.makeText(getApplicationContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(authPwd) || authPwd.length() < 15){
            Toast.makeText(getApplicationContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(authEmail,authPwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Log.d(mName.toString(),"User Registered/Authenticated");
                            //startActivity(new Intent(getApplicationContext(),Activity_Register.class));
                            //finish();
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"E-mail or password is wrong",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        //creating user
        //regUser = new User(mName.getText().toString(), authEmail, authPwd);

        //adding user to database
        //mDatabase.child("users").child("uID"+regUserCounter).setValue(regUser);

        //increment do remove later
        //regUserCounter++;

        //Log.d(regUser.toString(), "User Registered");

        /*Toast.makeText(v.getContext(), "Click here",
                Toast.LENGTH_SHORT).show();*/
    }
}
