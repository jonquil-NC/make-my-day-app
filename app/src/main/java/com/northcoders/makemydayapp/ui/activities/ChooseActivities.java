package com.northcoders.makemydayapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.activities.ActivityView;
import com.northcoders.makemydayapp.databinding.ActivityChooseActivitiesBinding;
import com.northcoders.makemydayapp.service.EventRepository;
import com.northcoders.makemydayapp.ui.mainactivity.MainActivityViewModel;
import com.northcoders.makemydayapp.ui.signupactivity.SignupClickHandler;

import java.util.ArrayList;
import java.util.List;

public class ChooseActivities extends AppCompatActivity {

    private static final String TAG = ChooseActivities.class.getName();

    private ActivityChooseActivitiesBinding activityChooseActivitiesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_activities);

        this.activityChooseActivitiesBinding =  DataBindingUtil.setContentView(
                this,R.layout.activity_choose_activities
        );

        ChipGroup activityChipGroup = findViewById(R.id.chip_group);
        ChooseActivityClickHandlers handlers = new ChooseActivityClickHandlers(this);
        activityChipGroup.setOnCheckedStateChangeListener(handlers);

        this.activityChooseActivitiesBinding.setClickHandler(handlers);

        ChipGroup cuisineChipGroup = findViewById(R.id.chip_group_restaurants);
        cuisineChipGroup.setVisibility(View.GONE);

        Button submitButton = findViewById(R.id.button_submit);
        submitButton.setOnClickListener(handlers);


    }
}