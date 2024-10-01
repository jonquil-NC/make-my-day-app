package com.northcoders.makemydayapp.mainactivity;


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.northcoders.makemydayapp.model.User;
import com.northcoders.makemydayapp.service.UserRepository;


import java.util.List;

public class MainActivityViewModel extends AndroidViewModel{

    private UserRepository userRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.userRepository = new UserRepository(application);
    }

    public LiveData<List<User>> getAllUsers(){
        return userRepository.getMutableLiveData();
    }

    public void addedAlbum(User user){
        userRepository.addNewUser(user);
    }

//    public void updateUser(Long id,User user){
//        albumRepository.updateUser(id,user);
//    }

    public void deleteUser(Long id){
        userRepository.deleteUser(id);
    }

}










}
