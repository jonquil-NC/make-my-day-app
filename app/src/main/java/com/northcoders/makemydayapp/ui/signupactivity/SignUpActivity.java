package com.northcoders.makemydayapp.ui.signupactivity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.databinding.ActivitySignUpBinding;
import com.northcoders.makemydayapp.model.User;
import com.northcoders.makemydayapp.ui.mainactivity.MainActivityViewModel;


public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding signUpActivityBinding;
    private SignupClickHandler signupClickHandler;

    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        this.signUpActivityBinding =  DataBindingUtil.setContentView(
                this,R.layout.activity_sign_up
        );
        MainActivityViewModel viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        this.user = new User();
        this.signupClickHandler = new SignupClickHandler(this.user,this, viewModel);

        this.signUpActivityBinding.setUserToAdd(this.user);
        this.signUpActivityBinding.setSignupClickHandler(this.signupClickHandler);

    }
}




