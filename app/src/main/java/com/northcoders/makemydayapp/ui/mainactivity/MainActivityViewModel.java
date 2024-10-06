package com.northcoders.makemydayapp.ui.mainactivity;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.northcoders.makemydayapp.model.MMDEvent;
import com.northcoders.makemydayapp.model.Place;
import com.northcoders.makemydayapp.model.Restaurant;
import com.northcoders.makemydayapp.model.User;
import com.northcoders.makemydayapp.service.EventRepository;
import com.northcoders.makemydayapp.service.UserRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class MainActivityViewModel extends AndroidViewModel{

    private UserRepository userRepository;
    private EventRepository eventRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepository(application);
        this.eventRepository = new EventRepository(application);
    }

    public LiveData<User> getUserDetails(User credentials){
        return userRepository.getUserDetailsThroughAuthentication(credentials);
    }

    public void addNewUser(User user){
        userRepository.addNewUser(user);
    }

    public void updateUser(Long id,User user){
        userRepository.updateUser(id,user);
    }

    public void deleteUser(Long id){
        userRepository.deleteUser(id);
    }

    public LiveData<List<Restaurant>> getRestaurantByCuisines(List<String> listCuisines){
        return this.eventRepository.getRestaurantByTypes(listCuisines);
    }

    public LiveData<List<MMDEvent>> getEventsByType(LocalDate date, List<String> listEventsType){
        return this.eventRepository.getEventsByPreferences(date, listEventsType);
    }

    public LiveData<List<Place>> getPlaceByType(List<String> listPlacesType){
        return this.eventRepository.getPlacesByTypes(listPlacesType);
    }


}

