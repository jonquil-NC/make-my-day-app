package com.northcoders.makemydayapp.ui.authenticationactivity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.databinding.ActivitySingInBinding;
import com.northcoders.makemydayapp.model.User;
import com.northcoders.makemydayapp.ui.mainactivity.MainActivityViewModel;

public class SignInActivity extends AppCompatActivity implements LifecycleOwner {

    private ActivitySingInBinding signinBinding;
    private User user;
    private AuthenticationClickHandler clickHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sing_in);

        this.signinBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_sing_in
        );

        MainActivityViewModel viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);
        this.user = new User();
        this.clickHandler = new AuthenticationClickHandler(this.user, this, viewModel);

        this.signinBinding.setClickHandler(this.clickHandler);
        this.signinBinding.setCredentials(this.user);


    }
}