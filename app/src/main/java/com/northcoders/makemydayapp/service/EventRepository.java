package com.northcoders.makemydayapp.service;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.northcoders.makemydayapp.model.MMDEvent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventRepository {

    private final Application application;
    private final MutableLiveData<List<MMDEvent>> mutableLiveData= new MutableLiveData<>();

    public EventRepository(Application application) {
        this.application = application;
    }

//    public MutableLiveData<List<MMDEvent>> getEventsByPreferences(/*Add list of preferences*/) {
//        EventsApiService eventsApiService = RetrofitInstance.getEventsApiService();
////        Call<List<MMDEvent>> listEventCall = eventsApiService.getAllEventsByPreferences(/*Add list of preferences*/);

    public MutableLiveData<List<MMDEvent>> getEventsByPreferences(String date, List<String> activities, List<String> cuisines) {
        EventsApiService eventsApiService = RetrofitInstance.getEventsApiService();

        Call<List<MMDEvent>> listEventCall;

//        If cuisine was not selected, call API without cuisine parameter, else API with cuisine
        if (cuisines == null || cuisines.isEmpty()){

            listEventCall = eventsApiService.getAllEventsByPreferencesWithoutCuisine(date, activities);
        } else {

            listEventCall = eventsApiService.getAllEventsByPreferences(date, activities, cuisines);
        }

        listEventCall.enqueue(new Callback<List<MMDEvent>>() {
            @Override
            public void onResponse(Call<List<MMDEvent>> call, Response<List<MMDEvent>> response) {
                    List<MMDEvent> eventList = response.body();
                    mutableLiveData.setValue(eventList);
            }

            @Override
            public void onFailure(Call<List<MMDEvent>> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),
                        "Unable to retrieve the list of Events as desired." ,
                        Toast.LENGTH_LONG).show();
                Log.e("GET REQ",t.getMessage());
            }
        });


        return mutableLiveData;
    }
}
