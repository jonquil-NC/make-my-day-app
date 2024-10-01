package com.northcoders.makemydayapp.ui.mainactivity;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.northcoders.makemydayapp.model.User;
import com.northcoders.makemydayapp.service.EventRepository;
import com.northcoders.makemydayapp.service.UserRepository;

public class MainActivityViewModel extends AndroidViewModel{

    private UserRepository userRepository;
    private EventRepository eventRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepository(application);
        this.eventRepository = new EventRepository(application);
    }

    public LiveData<User> getUserDetails(String username, String password){
        return userRepository.getUserDetailsThroughAuthentication(username, password);
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

}

