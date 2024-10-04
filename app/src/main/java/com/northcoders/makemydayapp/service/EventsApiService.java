package com.northcoders.makemydayapp.service;

import com.northcoders.makemydayapp.model.MMDEvent;
import com.northcoders.makemydayapp.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EventsApiService {

    @GET("events")
    Call<List<MMDEvent>> getAllEventsByPreferences();


}
