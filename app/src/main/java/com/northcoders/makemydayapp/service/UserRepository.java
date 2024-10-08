package com.northcoders.makemydayapp.service;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.northcoders.makemydayapp.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {

    private static final String TAG = UserRepository.class.getName();

    public Application application;
    private final MutableLiveData<User> mutableLiveData = new MutableLiveData<>();

    public UserRepository(Application application) {
        this.application = application;
    }


    public MutableLiveData<User> getUserDetailsThroughAuthentication(User credentials) {
        UserApiService userApiService = RetrofitInstance.getUserApiService();

        Call<User> call = userApiService.getUsersByCredentials(credentials);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if(user == null) {
                    // TODO: Remove the lines below
                    user = new User("Mickey", "Mouse", "mickey.mouse@disney.com", null);
                }
                mutableLiveData.setValue(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                // TODO: Remove the lines below
                User user = new User("Mickey", "Mouse", "mickey.mouse@disney.com", null);
                mutableLiveData.setValue(user);

                Log.e(TAG,"GET request", t);
                Toast.makeText(application.getApplicationContext(), "User not found returning Mickey Mouse", Toast.LENGTH_SHORT).show();


            }
        });

        return mutableLiveData;

    }

    public void addNewUser(User userToAdd) {
        UserApiService apiService = RetrofitInstance.getUserApiService();
        Call<User> call = apiService.addNewUser(userToAdd);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Toast.makeText(application.getApplicationContext(),
                        "User added to database",
                        Toast.LENGTH_LONG).show();
                Log.i("POST RESponse code ", Integer.toString(response.code()));
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),
                        "Unable to add User to database",
                        Toast.LENGTH_LONG).show();
                Log.e("POST REQ", t.getMessage());


            }
        });

    }

    public void deleteUser(Long id) {
        UserApiService apiService = RetrofitInstance.getUserApiService();
        Call<String> call = apiService.deleteUser(id);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(application.getApplicationContext(),
                        "User deleted from the database",
                        Toast.LENGTH_LONG).show();
                Log.i("DELETE Response code ", Integer.toString(response.code()));
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),
                        "Unable to delete the User from the database",
                        Toast.LENGTH_LONG).show();
                Log.e("DELETE REQ", t.getMessage());

            }
        });
    }

    public void updateUser(Long id, User newDetailsUser) {
        //TODO
    }

}









