package com.northcoders.makemydayapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {

    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextUsername;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private Button buttonSubmit;
    private Button buttonBackToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editTextName = findViewById(R.id.editTextName);
        editTextSurname = findViewById(R.id.editTextSurname);
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonSubmit = findViewById(R.id.buttonSubmit);
        buttonBackToLogin = findViewById(R.id.buttonBackToLogin);

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editTextName.getText().toString();
                String surname = editTextSurname.getText().toString();
                String username = editTextUsername.getText().toString();
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                signUp(name, surname, username, email, password);
            }
        });

        buttonBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the login screen
                Intent intent = new Intent(SignUpActivity.this, Authentication.class);
                startActivity(intent);
                finish(); // Optionally finish the current activity
            }
        });
    }


    private void signUp(String name, String surname, String username, String email, String password) {
        // Create a User object with the input data
        User user = new User(name, surname, username, email, password);

        // Set up Retrofit for making the API call
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://your-api-url.com/") // Replace with your actual API URL
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create an instance of the API service
        ApiService apiService = retrofit.create(ApiService.class);

        // Make the API call to sign up the user
        Call<Void> call = apiService.signUp(user);

        // Handle the response from the API
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    // Sign up was successful, show a message
                    Toast.makeText(SignUpActivity.this, "Sign Up Successful", Toast.LENGTH_SHORT).show();
                } else {
                    // Sign up failed, show a failure message
                    Toast.makeText(SignUpActivity.this, "Sign Up Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                // Network or other error, show an error message
                Toast.makeText(SignUpActivity.this, "Network Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}




