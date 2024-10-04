package com.northcoders.makemydayapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.activities.ActivityView;
import com.northcoders.makemydayapp.service.EventRepository;

import java.util.ArrayList;
import java.util.List;

public class ChooseActivities extends AppCompatActivity {

    private EditText dateInput;
    private ChipGroup activityChipGroup, cuisineChipGroup;
    private Button submitButton;
    private EventRepository eventRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_activities);

        dateInput = findViewById(R.id.dateInput);
        activityChipGroup = findViewById(R.id.chip_group);
        cuisineChipGroup = findViewById(R.id.chip_group_restaurants);
        submitButton = findViewById(R.id.button_submit);
        eventRepository = new EventRepository(getApplication());

        cuisineChipGroup.setVisibility(View.GONE);

        activityChipGroup.setOnCheckedStateChangeListener((group, checkedIds) -> {
            boolean restaurantSelected = false;

            for (Integer checkedId : checkedIds) {
                Chip selectedChip = findViewById(checkedId);
                if (selectedChip != null && selectedChip.getText().toString().equalsIgnoreCase("Restaurants")) {
                    restaurantSelected = true;
                    cuisineChipGroup.setVisibility(View.VISIBLE);
                }
            }

//            Hide cuisine if restaurant is not selected
            if (!restaurantSelected) {
                cuisineChipGroup.setVisibility(View.GONE);
                cuisineChipGroup.clearCheck();
            }
        });

        submitButton.setOnClickListener(s -> {
            List<Integer> selectedActivities = activityChipGroup.getCheckedChipIds();
            if (selectedActivities.isEmpty()) {
                Toast.makeText(this, "Please select at least one activity.", Toast.LENGTH_SHORT).show();
                return;
            }

//            Get selected activity names
            List<String> selectedActivityNames = new ArrayList<>();
            for (Integer checkedId : selectedActivities) {
                Chip selectedChip = findViewById(checkedId);
                if (selectedChip != null) {
                    selectedActivityNames.add(selectedChip.getText().toString());
                }
            }

//            Check if Restaurant is selected, if so check cuisines are also selected
            boolean restaurantSelected = selectedActivityNames.contains("Restaurants");
            List<String> selectedCuisines = new ArrayList<>();
            if (restaurantSelected) {
                List<Integer> selectedCuisineIds = cuisineChipGroup.getCheckedChipIds();
                if (selectedCuisineIds.isEmpty()) {
                    Toast.makeText(this, "Please select at least one cuisine.", Toast.LENGTH_SHORT).show();
                    return;
            }

//                Get selected cuisine names
                for (Integer checkedId : selectedCuisineIds) {
                    Chip selectedCuisineChip = findViewById(checkedId);
                    if (selectedCuisineChip != null) {
                        selectedCuisines.add(selectedCuisineChip.getText().toString());
                    }
                }
            }

//            Get date from EditText
            String date = dateInput.getText().toString();

//          Call the method to make API requests
            makeApiRequests(date, selectedActivityNames, selectedCuisines);

        });
    }

    private void makeApiRequests(String date, List<String> selectedActivities, List<String> selectedCuisines) {
        eventRepository.getEventsByPreferences(date, selectedActivities, selectedCuisines).observe(this, events -> {

            if (events != null) {
                Intent intent = new Intent(ChooseActivities.this, ActivityView.class);
                intent.putParcelableArrayListExtra("eventList", new ArrayList<>(events));
                startActivity(intent);
            } else {
                Toast.makeText(this, "No events found. Please try again.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}