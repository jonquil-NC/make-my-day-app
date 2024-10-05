package com.northcoders.makemydayapp.ui.activities;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.activities.ActivityView;

import java.util.ArrayList;
import java.util.List;

public class ChooseActivityClickHandlers implements ChipGroup.OnCheckedStateChangeListener, View.OnClickListener {

    private static final String TAG = ChooseActivities.class.getName();

    private AppCompatActivity appCompatActivity;

    public ChooseActivityClickHandlers(AppCompatActivity appCompatActivity){
        this.appCompatActivity = appCompatActivity;
    }

    public void onCloseRestaurantGroupChips(View view) {
        Log.i(TAG, "Making the Restaurant group gone.");
        this.setChipGroupRestaurantVisible(false);
    }


    @Override
    public void onCheckedChanged(@NonNull ChipGroup group, @NonNull List<Integer> checkedIds) {

        Log.i(TAG, "Checked Group: ids: " + checkedIds);

        if (checkedIds.contains(R.id.chip_restaurants)) {
            Chip restaurantChip = this.appCompatActivity.findViewById(R.id.chip_restaurants);
            ChipGroup cuisineChipGroup = this.appCompatActivity.findViewById(R.id.chip_group_restaurants);
            Log.i(TAG, "Selected Chip: " + restaurantChip);
            if (restaurantChip.isChecked()) {
                Log.i(TAG, "Making the Restaurant group visible.");
                this.setChipGroupRestaurantVisible(true);
            } else {
                Log.i(TAG, "Making the Restaurant group gone.");
                this.setChipGroupRestaurantVisible(false);
            }
        }
    }

    @Override
    public void onClick(View v) {
        ChipGroup activityChipGroup = this.appCompatActivity.findViewById(R.id.chip_group);
        List<Integer> selectedActivities = activityChipGroup.getCheckedChipIds();
        if (selectedActivities.isEmpty()) {
            Toast.makeText(this.appCompatActivity, "Please select at least one activity.", Toast.LENGTH_SHORT).show();
            return;
        }

//            Get selected activity names
        List<String> selectedActivityNames = new ArrayList<>();
        for (Integer checkedId : selectedActivities) {
            Chip selectedChip = this.appCompatActivity.findViewById(checkedId);
            if (selectedChip != null) {
                selectedActivityNames.add(selectedChip.getText().toString().toLowerCase());
            }
        }

//            Check if Restaurant is selected, if so check cuisines are also selected
        boolean restaurantSelected = selectedActivityNames.contains("restaurants");
        List<String> selectedCuisines = new ArrayList<>();
        if (restaurantSelected) {
            ChipGroup cuisineChipGroup = this.appCompatActivity.findViewById(R.id.chip_group_restaurants);
            List<Integer> selectedCuisineIds = cuisineChipGroup.getCheckedChipIds();
            if (selectedCuisineIds.isEmpty()) {
                Toast.makeText(this.appCompatActivity, "Please select at least one cuisine.", Toast.LENGTH_SHORT).show();
                return;
            }

//                Get selected cuisine names
            for (Integer checkedId : selectedCuisineIds) {
                Chip selectedCuisineChip = this.appCompatActivity.findViewById(checkedId);
                if (selectedCuisineChip != null) {
                    selectedCuisines.add(selectedCuisineChip.getText().toString().toLowerCase());
                }
            }
        }

//            Get date from EditText
        EditText dateInput = this.appCompatActivity.findViewById(R.id.dateInput);
        String date = dateInput.getText().toString();

        Log.i(TAG, "List of activities:  " + selectedActivities);
        Log.i(TAG, "List of restaurants: " + selectedCuisines);

//          Call the method to make API requests
        //TODO: makeApiRequests(date, selectedActivityNames, selectedCuisines);

    }

    private void setChipGroupRestaurantVisible(boolean isVisible) {
        ChipGroup cuisineChipGroup = this.appCompatActivity.findViewById(R.id.chip_group_restaurants);
        ChipGroup activityChipGroup = this.appCompatActivity.findViewById(R.id.chip_group);

        int cuisineChipGroupVisible = isVisible ? View.VISIBLE : View.GONE;
        int activityChipGroupVisible = isVisible ? View.GONE : View.VISIBLE;

        Log.i(TAG, "Making the Restaurant visible: " + isVisible);
        cuisineChipGroup.setVisibility(cuisineChipGroupVisible);
        activityChipGroup.setVisibility(activityChipGroupVisible);

    }

// TODO:
//    private void makeApiRequests(String date, List<String> selectedActivities, List<String> selectedCuisines) {
//        eventRepository.getEventsByPreferences(date, selectedActivities, selectedCuisines).observe(this, events -> {
//
//            if (events != null) {
//                Intent intent = new Intent(ChooseActivities.this, ActivityView.class);
//                intent.putParcelableArrayListExtra("eventList", new ArrayList<>(events));
//                startActivity(intent);
//            } else {
//                Toast.makeText(this, "No events found. Please try again.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
}
