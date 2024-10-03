package com.northcoders.makemydayapp.ui.homepageactivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.lifecycle.LiveData;

import com.northcoders.makemydayapp.model.User;
import com.northcoders.makemydayapp.ui.landingpageactivity.LandingPageActivity;
import com.northcoders.makemydayapp.ui.mainactivity.MainActivityViewModel;
import com.northcoders.makemydayapp.ui.signupactivity.SignUpActivity;

public class HomePageAuthenticationClickHandler {

    private final HomePageActivity context;
    private final MainActivityViewModel viewModel;
    private final User credentials;

    public HomePageAuthenticationClickHandler(User credentials, HomePageActivity context, MainActivityViewModel viewModel) {
        this.viewModel = viewModel;
        this.context = context;
        this.credentials = credentials;
    }

    public void onSignupClick(View view) {
        Intent intent = new Intent(this.context, SignUpActivity.class);

        this.context.startActivity(intent);
    }

    public void onLoginClick(View view) {

        Intent intent = new Intent(this.context, LandingPageActivity.class);

        LiveData<User> liveData = this.viewModel.getUserDetails(credentials);

        liveData.observe(this.context, user-> {

            if(user != null){
                context.startActivity(intent);
            }
        });

    }


}
