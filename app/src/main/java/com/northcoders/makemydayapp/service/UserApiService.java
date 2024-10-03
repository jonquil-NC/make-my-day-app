package com.northcoders.makemydayapp.service;


import com.northcoders.makemydayapp.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApiService {


        @POST("makemyday/sign-in")
        Call<User> getUsersByCredentials(@Body User user);


        @GET("users")
        Call<User> getUsersByEmail(@Query("email") String email);

        @POST("makemyday/sign-up")
        Call<User> addNewUser(@Body User user);

        @PUT("users/{id}")
        Call<User> updateUser(@Path("id") long id, @Body User user);

        @DELETE("users/{id}")
        Call<String> deleteUser(@Path("id") long id);

    }

