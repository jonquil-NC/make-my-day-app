package com.northcoders.makemydayapp.ui.authenticationactivity;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;

import com.northcoders.makemydayapp.model.User;
import com.northcoders.makemydayapp.ui.activities.ChooseActivities;
import com.northcoders.makemydayapp.ui.landingpageactivity.LandingPageActivity;
import com.northcoders.makemydayapp.ui.mainactivity.MainActivityViewModel;
import com.northcoders.makemydayapp.ui.signupactivity.SignUpActivity;

public class AuthenticationClickHandler {

    private final SignInActivity context;
    private final MainActivityViewModel viewModel;
    private final User credentials;

    public AuthenticationClickHandler(User credentials, SignInActivity context, MainActivityViewModel viewModel) {
        this.viewModel = viewModel;
        this.context = context;
        this.credentials = credentials;
    }

    public void onSignupClick(View view) {
        Intent intent = new Intent(this.context, SignUpActivity.class);

        this.context.startActivity(intent);
    }

    public void onLoginClick(View view) {



        LiveData<User> liveData = this.viewModel.getUserDetails(credentials);

        Toast.makeText(context, "Logging in", Toast.LENGTH_SHORT).show();

        liveData.observe(this.context, user-> {

            if(user != null){
                Intent intent = new Intent(this.context, ChooseActivities.class);
                Toast.makeText(context, "User Found.", Toast.LENGTH_SHORT).show();
                context.startActivity(intent);
            } else {
                Toast.makeText(context, "User not Found.", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
