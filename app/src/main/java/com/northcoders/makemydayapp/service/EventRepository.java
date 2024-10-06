package com.northcoders.makemydayapp.service;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.northcoders.makemydayapp.model.MMDEvent;
import com.northcoders.makemydayapp.model.Place;
import com.northcoders.makemydayapp.model.Restaurant;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRepository {

    private static final String TAG = EventRepository.class.getName();

    private final Application application;
    private final MutableLiveData<List<MMDEvent>> eventMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Restaurant>> restaurantsMutableLiveData = new MutableLiveData<>();
    private final MutableLiveData<List<Place>> placesMutableLiveData= new MutableLiveData<>();

    public EventRepository(Application application) {
        this.application = application;
    }


    public MutableLiveData<List<MMDEvent>> getEventsByPreferences(LocalDate localDate, List<String> eventsPref){
        EventsApiService eventsApiService = RetrofitInstance.getEventsApiService();

        Log.i(TAG, "List of events preferences: " + eventsPref);

        if(eventsPref.isEmpty()) {
            return eventMutableLiveData;
        }

        Call<List<MMDEvent>> eventsCall = eventsApiService.getAllEventsByPreferences(eventsPref);

        eventsCall.enqueue(new Callback<List<MMDEvent>>() {
            @Override
            public void onResponse(Call<List<MMDEvent>> call, Response<List<MMDEvent>> response) {
                List<MMDEvent> eventList = response.body();
                eventMutableLiveData.setValue(eventList);
            }

            @Override
            public void onFailure(Call<List<MMDEvent>> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),
                        "Unable to retrieve the list of Events as desired." ,
                        Toast.LENGTH_LONG).show();
                Log.e("GET REQ",t.getMessage());
            }
        });



        return eventMutableLiveData;
    }

    public MutableLiveData<List<Restaurant>> getRestaurantByTypes(List<String> cuisines) {
        EventsApiService eventsApiService = RetrofitInstance.getEventsApiService();

        Log.i(TAG, "List of events preferences: " + cuisines);

        if(cuisines.isEmpty()) {
            return restaurantsMutableLiveData;
        }

        Call<List<Restaurant>> eventsCall = eventsApiService.getAllRestaurantByType(cuisines);

        eventsCall.enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                List<Restaurant> restaurantList = response.body();
                restaurantsMutableLiveData.setValue(restaurantList);
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),
                        "Unable to retrieve the list of restaurants as desired." ,
                        Toast.LENGTH_LONG).show();
                Log.e("GET REQ",t.getMessage());
            }
        });


        return restaurantsMutableLiveData;

    }

    public MutableLiveData<List<Place>> getPlacesByTypes(List<String> places) {
        EventsApiService eventsApiService =  RetrofitInstance.getEventsApiService();

        Call<List<Place>> placesCall = eventsApiService.getAllPlacesByType(places);

        placesCall.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                List<Place> places = response.body();
                placesMutableLiveData.setValue(places);
            }

            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),
                        "Unable to retrieve the list of places as desired." ,
                        Toast.LENGTH_LONG).show();
                Log.e("GET REQ",t.getMessage());
            }
        });


        return placesMutableLiveData;

    }
}
