package com.northcoders.makemydayapp.ui.landingpageactivity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.databinding.ActivityAuthenticationBinding;
import com.northcoders.makemydayapp.model.User;
import com.northcoders.makemydayapp.ui.homepageactivity.HomePageAuthenticationClickHandler;

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