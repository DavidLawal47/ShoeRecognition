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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.security.cert.CertPathValidatorException;


public class Activity_Register extends AppCompatActivity implements View.OnClickListener{

    private EditText mFname, mLname, mEmail, mPassword, mCpassword;
    Button registerButton, signin_Button;
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



        screen1 =  findViewById(R.id.screen1);
        handler.postDelayed(runnable, 3000);

        mFname =  findViewById(R.id.fName);
        mLname = findViewById(R.id.lName);
        mEmail =  findViewById(R.id.email);
        mPassword =  findViewById(R.id.password);
        mCpassword =  findViewById(R.id.c_password);
        registerButton =  findViewById(R.id.btn);
        registerButton.setOnClickListener(this);


       //method being called
       backActivity();



    }

    //method that recognized as an email address
   

    //method allows the user to go back and forth from sign up page to sign in page
    private void backActivity(){
        signin_Button = findViewById(R.id.sign_in);
        signin_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Register.this,Main_Activity.class));
            }
        });

    }

    //Send verification mail to user method 11/11/2018 5:08am
    private void sendVerificationEmai()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    //email sent
                    Toast.makeText(getApplicationContext(), "Verification email sent", Toast.LENGTH_SHORT).show();

                    //after email is sent log user out
                    FirebaseAuth.getInstance().signOut();
                    startActivity(new Intent(Activity_Register.this, Main_Activity.class));
                    finish();
                }
                else{
                    //email not sent restart activity
                    Toast.makeText(getApplicationContext(), "ERROR! Verification NOT sent", Toast.LENGTH_SHORT).show();
                    overridePendingTransition(0,0);
                    finish();
                    overridePendingTransition(0,0);
                    startActivity(getIntent());

                }
                }
        });
    }

    //register user
    @Override
    public void onClick(View v) {

        String authEmail = mEmail.getText().toString();
        String authPwd = mPassword.getText().toString();
        String authFirstname = mFname.getText().toString();
        String authLastname = mLname.getText().toString();
        String authCpwd = mCpassword.getText().toString();

        if(TextUtils.isEmpty(authFirstname) || authFirstname.length() > 30){
            Toast.makeText(getApplicationContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),"Name cannot exceed 30 Characters",Toast.LENGTH_SHORT).show();
            return;
            }
        if(TextUtils.isEmpty(authLastname) || authLastname.length() > 30){
            Toast.makeText(getApplicationContext(),"Please fill in the required fields or Name cannot exceed 30 Characters",Toast.LENGTH_SHORT).show();
            Toast.makeText(getApplicationContext(),"Name cannot exceed 30 Characters",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(authEmail)){
            Toast.makeText(getApplicationContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();

            return;
        }
        if(TextUtils.isEmpty(authPwd) || authPwd.length() < 15){
            Toast.makeText(getApplicationContext(),"Please fill in the required fields",Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(authCpwd) || authCpwd != null && !authCpwd.equals(authPwd)){
            Toast.makeText(getApplicationContext(),"Please fill in the required fields or password doesn't match",Toast.LENGTH_SHORT).show();
            return;
        }





        firebaseAuth.createUserWithEmailAndPassword(authEmail, authPwd)
                .addOnCompleteListener(Activity_Register.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Activity_Register.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        sendVerificationEmai();
                       // progressBar.setVisibility(View.GONE);
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(Activity_Register.this, "Authentication failed." + task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            startActivity(new Intent(Activity_Register.this, Main_Activity.class));
                            backActivity();
                            finish();
                        }
                    }
                });

        //creating user
    regUser = new User(mFname.getText().toString(),mLname.getText().toString(),authEmail, authPwd);

//adding user to database
    mDatabase.child("users").child("uID"+regUserCounter).setValue(regUser);

//increment do remove later
    regUserCounter++;

        //Log.d(regUser.toString(), "User Registered");

        Toast.makeText(v.getContext(), "Click here",
                Toast.LENGTH_SHORT).show();
        }

}

