package com.northcoders.makemydayapp.service;


import com.northcoders.makemydayapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserApiService {


        @GET("users")
        Call<User> getUsersByCredentials(String username, String password);

        @POST("users")
        Call<User> addNewUser(@Body User user);

        @PUT("users/{id}")
        Call<User> updateUser(@Path("id") long id, @Body User user);

        @DELETE("users/{id}")
        Call<String> deleteUser(@Path("id") long id);

    }

