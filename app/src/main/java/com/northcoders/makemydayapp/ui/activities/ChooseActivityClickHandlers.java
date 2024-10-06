package com.northcoders.makemydayapp.ui.activities;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.ui.activities.expandablefilter.ExpandableFilteringActivities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChooseActivityClickHandlers implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

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
    public void onClick(View v) {
        ChipGroup activityChipGroup = this.appCompatActivity.findViewById(R.id.chip_group_places);
        List<Integer> selectedPlaces = activityChipGroup.getCheckedChipIds();

        ChipGroup listEventsChecked = this.appCompatActivity.findViewById(R.id.chip_group_events);
        List<Integer> selectedEvents = listEventsChecked.getCheckedChipIds();

        Chip restaurant = this.appCompatActivity.findViewById(R.id.chip_restaurants);

        if (selectedPlaces.isEmpty() && selectedEvents.isEmpty() && !restaurant.isChecked()) {
            Toast.makeText(this.appCompatActivity, "Please select at least one among places, restaurants, among events.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Get selected activity names
        ArrayList<String> selectedPlacesNames = new ArrayList<>();
        for (Integer checkedId : selectedPlaces) {
            Chip selectedChip = this.appCompatActivity.findViewById(checkedId);
            if (selectedChip != null) {
                selectedPlacesNames.add(selectedChip.getText().toString().toLowerCase());
            }
        }

        //  Check if Restaurant is selected, if so check cuisines are also selected
        ArrayList<String> selectedCuisines = new ArrayList<>();
        if (restaurant.isChecked()) {
            ChipGroup cuisineChipGroup = this.appCompatActivity.findViewById(R.id.chip_group_restaurants);
            List<Integer> selectedCuisineIds = cuisineChipGroup.getCheckedChipIds();

            if (selectedCuisineIds.isEmpty()) {
                Toast.makeText(this.appCompatActivity, "Please select at least one cuisine.", Toast.LENGTH_SHORT).show();
                return;
            }

            //  Get selected cuisine names
            for (Integer checkedId : selectedCuisineIds) {
                Chip selectedCuisineChip = this.appCompatActivity.findViewById(checkedId);
                if (selectedCuisineChip != null) {
                    selectedCuisines.add(selectedCuisineChip.getText().toString().toLowerCase());
                }
            }
        }

        //  Get selected activity names
        ArrayList<String> selectedEventsNames = new ArrayList<>();
        for (Integer checkedId : selectedEvents) {
            Chip selectedChip = this.appCompatActivity.findViewById(checkedId);
            if (selectedChip != null) {
                selectedEventsNames.add(selectedChip.getText().toString().toUpperCase());
            }
        }

//            Get date from EditText
        EditText dateInput = this.appCompatActivity.findViewById(R.id.dateInput);
        String date = dateInput.getText().toString();

        Log.i(TAG, "List of activities:  " + selectedPlacesNames);
        Log.i(TAG, "List of cuisine: " + selectedCuisines);
        Log.i(TAG, "List of events: " + selectedEventsNames);

        Intent intent = new Intent(this.appCompatActivity, ExpandableFilteringActivities.class);
        intent.putStringArrayListExtra(ChooseActivities.PLACE_LIST_NAME_EXTRA, selectedPlacesNames);
        intent.putStringArrayListExtra(ChooseActivities.CUISINE_LIST_NAME_EXTRA, selectedCuisines);
        intent.putStringArrayListExtra(ChooseActivities.EVENTS_LIST_NAME_EXTRA, selectedEventsNames);
        intent.putExtra(ChooseActivities.EVENTS_DATE_NAME_EXTRA, LocalDate.now());

        this.appCompatActivity.startActivity(intent);

    }



    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Log.i(TAG, "Checked Chip name: " + buttonView.getText());

        if ( buttonView.getId() == R.id.chip_restaurants) {
            ChipGroup cuisineChipGroup = this.appCompatActivity.findViewById(R.id.chip_group_restaurants);
            Log.i(TAG, "Making the Restaurant group visible.");
            this.setChipGroupRestaurantVisible(true);
        }
    }

    private void setChipGroupRestaurantVisible(boolean isVisible) {
        ChipGroup cuisineChipGroup = this.appCompatActivity.findViewById(R.id.chip_group_restaurants);
        ChipGroup activityChipGroup = this.appCompatActivity.findViewById(R.id.chip_group_places);
        ChipGroup eventsChipGroup = this.appCompatActivity.findViewById(R.id.chip_group_events);
        Chip restaurantChip = this.appCompatActivity.findViewById(R.id.chip_restaurants);
        Button submitBtn = this.appCompatActivity.findViewById(R.id.chooseActivities_submit);

        int cuisineChipGroupVisible = isVisible ? View.VISIBLE : View.GONE;
        int otherElementsVisibility = isVisible ? View.GONE : View.VISIBLE;

        Log.i(TAG, "Making the Restaurant visible: " + isVisible);
        cuisineChipGroup.setVisibility(cuisineChipGroupVisible);
        activityChipGroup.setVisibility(otherElementsVisibility);
        eventsChipGroup.setVisibility(otherElementsVisibility);
        restaurantChip.setVisibility(otherElementsVisibility);
        submitBtn.setVisibility(otherElementsVisibility);

    }
}
