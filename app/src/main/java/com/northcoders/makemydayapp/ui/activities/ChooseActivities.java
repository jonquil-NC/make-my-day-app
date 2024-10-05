package com.northcoders.makemydayapp.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.chip.ChipGroup;
import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.databinding.ActivityChooseActivitiesBinding;

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