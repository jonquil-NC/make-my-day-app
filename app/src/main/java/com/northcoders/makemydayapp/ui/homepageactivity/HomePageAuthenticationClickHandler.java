package com.northcoders.makemydayapp.ui.homepageactivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.northcoders.makemydayapp.model.User;
import com.northcoders.makemydayapp.ui.mainactivity.MainActivityViewModel;
import com.northcoders.makemydayapp.ui.signupactivity.SignUpActivity;

public class HomePageAuthenticationClickHandler {

    private Context context;
    private MainActivityViewModel viewModel;

    public HomePageAuthenticationClickHandler(Context context, MainActivityViewModel viewModel ) {
        this.viewModel = viewModel;
        this.context = context;
    }
    public void onSignupClick(View view){
        Intent intent = new Intent(this.context, SignUpActivity.class);
        this.context.startActivity(intent);
    }

    public void onLoginClick(View view) {

    }


}
