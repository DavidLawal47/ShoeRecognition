package com.agacecornish.sra.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.agacecornish.sra.R;
import com.agacecornish.sra.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main_Activity extends AppCompatActivity implements View.OnClickListener {


    private FirebaseAuth firebaseAuth;

    private EditText mFname, mLname, mEmail, mPassword, mCpassword;
    private User regUser;
    Button login_Button, signup_Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_);

        mFname =  findViewById(R.id.fName);
        mLname = findViewById(R.id.lName);
        mEmail =  findViewById(R.id.email);
        mPassword =  findViewById(R.id.password);
        mCpassword =  findViewById(R.id.c_password);
        login_Button = findViewById(R.id.btn_login);
        login_Button.setOnClickListener(this);

        //method being called
       nextactivity();
        //userProfile();

        firebaseAuth = FirebaseAuth.getInstance();


    }

    //method allows the user to go back and forth from sign in page to sign up page
    private void nextactivity(){

        signup_Button = findViewById(R.id.sign_up);
        signup_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Main_Activity.this,Activity_Register.class));
            }
        });
    }
        //method that opens the user profile page after they click login button
    private void userProfile(){
        login_Button = findViewById(R.id.btn_login);
        login_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Main_Activity.this, Activity_UsrProfile.class));
            }
        });
    }

    //check if the user email is verified 11/11//2018
    private void checkIfEmailVerified()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        if (user.isEmailVerified())
        {
            finish();
            Toast.makeText(getApplicationContext(), "Email is now verified", Toast.LENGTH_SHORT).show();
        }
        else
        {
           // FirebaseAuth.getInstance().signOut();
            Toast.makeText(getApplicationContext(), "ERROR! Your email was not verified", Toast.LENGTH_SHORT).show();
            overridePendingTransition(0,0);
            finish();
            overridePendingTransition(0,0);
            startActivity(getIntent());
        }
    }




    public void onClick(View v) {

        String authEmail = mEmail.getText().toString();
        String authPwd = mPassword.getText().toString();

        Log.d(authEmail, "This is my email");
        Log.d(authPwd, "This is my password");

        if (TextUtils.isEmpty(authEmail)) {
            Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(authPwd)) {
            Toast.makeText(getApplicationContext(), "Please fill in the required fields", Toast.LENGTH_SHORT).show();
             return;
        }

        firebaseAuth.signInWithEmailAndPassword(authEmail, authPwd)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            nextactivity();

                            if (task.isSuccessful()) {
                               // Log.d(mName.toString(), "User Registered/Authenticated");
                                startActivity(new Intent(getApplicationContext(),Activity_UsrProfile.class));
                                userProfile();
                                nextactivity();
                                finish();

                            } else {
                                Toast.makeText(getApplicationContext(), "E-mail or password is wrong", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }
                    });


    }
}






