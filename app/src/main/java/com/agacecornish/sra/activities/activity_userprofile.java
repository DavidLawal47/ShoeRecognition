package com.agacecornish.sra.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.agacecornish.sra.R;

public class activity_userprofile extends AppCompatActivity {

    Button goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);


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
