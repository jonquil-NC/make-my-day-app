package com.northcoders.makemydayapp.ui.signupactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

        this.user = new User();

        this.signUpActivityBinding =  DataBindingUtil.setContentView(
                this,R.layout.activity_sign_up
        );
        MainActivityViewModel viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        this.signupClickHandler = new SignupClickHandler(this.user,this, viewModel);

        this.signUpActivityBinding.setUserToAdd(this.user);
        this.signUpActivityBinding.setSignupClickHandler(this.signupClickHandler);

//        buttonBackToLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Navigate back to the login screen
//                Intent intent = new Intent(SignUpActivity.this, Authentication.class);
//                startActivity(intent);
//                finish(); // Optionally finish the current activity
//            }
//        });
    }
}




