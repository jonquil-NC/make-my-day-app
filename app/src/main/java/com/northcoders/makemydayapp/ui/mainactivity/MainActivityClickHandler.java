package com.northcoders.makemydayapp.ui.mainactivity;

import android.content.Intent;
import android.view.View;

import com.northcoders.makemydayapp.ui.authenticationactivity.SignInActivity;
import com.northcoders.makemydayapp.ui.signupactivity.SignUpActivity;

public class MainActivityClickHandler {

    public void onSignUpClick(View view) {
        Intent intent = new Intent(view.getContext(), SignUpActivity.class);

        view.getContext().startActivity(intent);
    }

    public void onSignInClick(View view) {
        Intent intent = new Intent(view.getContext(), SignInActivity.class);

        view.getContext().startActivity(intent);
    }
}
