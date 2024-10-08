package com.northcoders.makemydayapp.service;

import com.northcoders.makemydayapp.model.MMDEvent;
import com.northcoders.makemydayapp.model.Place;
import com.northcoders.makemydayapp.model.Restaurant;
import com.northcoders.makemydayapp.model.User;

import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EventsApiService {

    @GET("makemyday/events")
    Call<List<MMDEvent>> getAllEventsByPreferences(/*@Query("date") String date,*/ @Query("type") List<String> events);

    @GET("makemyday/restaurants")
    Call<List<Restaurant>>getAllRestaurantByType(@Query("type") List<String> cuisineList);

    @GET("makemyday/places")
    Call<List<Place>>getAllPlacesByType(@Query("type") List<String> placesList);
}
