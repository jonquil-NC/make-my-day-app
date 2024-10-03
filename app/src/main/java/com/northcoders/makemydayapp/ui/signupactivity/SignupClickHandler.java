package com.northcoders.makemydayapp.ui.signupactivity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.northcoders.makemydayapp.model.User;
import com.northcoders.makemydayapp.ui.mainactivity.MainActivity;
import com.northcoders.makemydayapp.ui.mainactivity.MainActivityViewModel;

import org.apache.commons.lang3.StringUtils;

public class SignupClickHandler {

    private User user;
    private Context context;
    private MainActivityViewModel viewModel;

    public SignupClickHandler(User user,Context context, MainActivityViewModel viewModel ) {
        this.user = user;
        this.viewModel = viewModel;
        this.context = context;
    }



    public void onSignupNewUser(View view){
        if(StringUtils.isAnyEmpty(this.user.getEmail(), this.user.getLastname(), this.user.getPassword())) {
            Toast.makeText(context, "First name, last name, email, username and password cannot be empty", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        Intent intent = new Intent(context, MainActivity.class);

        // String name, String lastname, String username, String email, String password
        User userToAdd = new User(
                user.getFirstname(), user.getLastname(), user.getEmail(), user.getPassword()
        );

        viewModel.addNewUser(userToAdd);

    }


}
