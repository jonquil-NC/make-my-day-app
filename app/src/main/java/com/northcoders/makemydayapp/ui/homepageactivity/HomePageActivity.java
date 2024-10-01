package com.northcoders.makemydayapp.ui.homepageactivity;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.databinding.ActivityAuthenticationBinding;
import com.northcoders.makemydayapp.model.User;
import com.northcoders.makemydayapp.ui.mainactivity.MainActivityViewModel;

public class HomePageActivity extends AppCompatActivity {

    private ActivityAuthenticationBinding authenticationBinding;
    private User user;
    private HomePageAuthenticationClickHandler clickHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_authentication);

        this.authenticationBinding = DataBindingUtil.setContentView(
                this, R.layout.activity_authentication
        );

        MainActivityViewModel viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);
        this.user = new User();
        this.clickHandler = new HomePageAuthenticationClickHandler(this.user, this, viewModel);

        this.authenticationBinding.setClickHandler(this.clickHandler);
        this.authenticationBinding.setCredentials(this.user);


    }
}