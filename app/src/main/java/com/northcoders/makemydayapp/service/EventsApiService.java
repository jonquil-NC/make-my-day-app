package com.northcoders.makemydayapp.service;

import com.northcoders.makemydayapp.model.MMDEvent;
import com.northcoders.makemydayapp.model.Restaurant;
import com.northcoders.makemydayapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EventsApiService {

//    @GET("events")
//    Call<List<MMDEvent>> getAllEventsByPreferences();

//  GET, with user selecting cuisine
    @GET("/events/preferences")
    Call<List<MMDEvent>> getAllEventsByPreferences(@Query("date") String date, @Query("activities") List<String> activities, @Query("cuisines") List<String> cuisines);

//  If user doesn't select cuisine
    @GET("/events/preferences")
    Call<List<MMDEvent>> getAllEventsByPreferencesWithoutCuisine(@Query("date") String date, @Query("activities") List<String> activities);

    @GET("/geoapify/restaurants/{type}")
    Call<List<Restaurant>>getAllRestaurantByType(@Path("type") String type);

}
