package com.northcoders.makemydayapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Authentication extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        Button buttonSignUp = findViewById(R.id.buttonSignUp);
        Button buttonLogin = findViewById(R.id.buttonLogin);

    }
}