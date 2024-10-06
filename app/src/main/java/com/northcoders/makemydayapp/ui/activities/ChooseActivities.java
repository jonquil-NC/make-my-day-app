package com.northcoders.makemydayapp.ui.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.databinding.ActivityChooseActivitiesBinding;

public class ChooseActivities extends AppCompatActivity {

    public final static String PLACE_LIST_NAME_EXTRA = "placesList";
    public final static String CUISINE_LIST_NAME_EXTRA = "cuisinesList";
    public final static String EVENTS_LIST_NAME_EXTRA = "eventsList";
    public final static String EVENTS_DATE_NAME_EXTRA = "eventsDate";

    private static final String TAG = ChooseActivities.class.getName();

    private ActivityChooseActivitiesBinding activityChooseActivitiesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_activities);

        this.activityChooseActivitiesBinding =  DataBindingUtil.setContentView(
                this,R.layout.activity_choose_activities
        );

        ChooseActivityClickHandlers handlers = new ChooseActivityClickHandlers(this);
        Chip restaurantChip = this.findViewById(R.id.chip_restaurants);
        restaurantChip.setOnCheckedChangeListener(handlers);

        this.activityChooseActivitiesBinding.setClickHandler(handlers);

        ChipGroup cuisineChipGroup = findViewById(R.id.chip_group_restaurants);
        cuisineChipGroup.setVisibility(View.GONE);

        Button submitButton = findViewById(R.id.chooseActivities_submit);
        submitButton.setOnClickListener(handlers);
        restaurantChip.setVisibility(View.VISIBLE);
    }
}