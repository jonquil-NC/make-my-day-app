package com.northcoders.makemydayapp.ui.landingpageactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.model.User;

public class LandingPageActivity extends AppCompatActivity {


//    private  authenticationBinding;
    private User user;
    private LandingPageClickHandler clickHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);


    }
}