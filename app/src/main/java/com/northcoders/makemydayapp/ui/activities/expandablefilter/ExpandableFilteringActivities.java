package com.northcoders.makemydayapp.ui.activities.expandablefilter;

import android.os.Bundle;
import android.util.Log;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.model.Restaurant;
import com.northcoders.makemydayapp.ui.activities.ChooseActivities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableFilteringActivities extends AppCompatActivity {


    private static final String TAG = ExpandableFilteringActivities.class.getName();

    private static final String GROUP_REST = "Restaurants";
    private static final String GROUP_PLACES = "Places";
    private static final String GROUP_EVENTS = "Activities";


    ExpandableListView expandableListViewActivities;
    ExpandableListView expandableListViewRestaurants;
    ExpandableFilteringActivitiesAdapter expandableFilteringActivitiesAdapter;
    List<String> listActivitiesTypes;
    HashMap<String, List<String>> groupOfActivitiesByType;
    HashMap<String, List<Restaurant>> groupOfRestaurants;
//    Button addGroupButton, addChildButton, removeChildButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_filtering_activities);

        this.expandableListViewActivities = findViewById(R.id.expandableListViewActivitiesFiltering);
        this.expandableListViewRestaurants = findViewById(R.id.expandableListViewRestaurantsFiltering);

//        addGroupButton = findViewById(R.id.addGroupButton);
//        addChildButton = findViewById(R.id.addChildButton);
//        removeChildButton = findViewById(R.id.removeChildButton);

        // Initialize data
        initData();

        // Set up adapters for both ExpandableListViews
        expandableFilteringActivitiesAdapter = new ExpandableFilteringActivitiesAdapter(this, listActivitiesTypes, groupOfActivitiesByType);

        expandableListViewActivities.setAdapter(expandableFilteringActivitiesAdapter);

        // Add new group dynamically
//        addGroupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String newGroup = "Group " + (listGroup.size() + 1);
//                listGroup.add(newGroup);
//                listChild.put(newGroup, new ArrayList<>()); // Initialize empty child list
//                adapter1.notifyDataSetChanged(); // Notify the adapter that the data has changed
//            }
//        });
//
//        // Add a new child to the first group dynamically
//        addChildButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<String> group1Items = listChild.get(listGroup.get(0)); // Access the first group
//                group1Items.add("New Child " + (group1Items.size() + 1));
//                adapter1.notifyDataSetChanged();
//
//            }
//        });
//
//        // Remove the last child from the first group dynamically
//        removeChildButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                List<String> group1Items = listChild.get(listGroup.get(0)); // Access the first group
//                if (!group1Items.isEmpty()) {
//                    group1Items.remove(group1Items.size() - 1); // Remove the last child
//                    adapter1.notifyDataSetChanged();
//                }
//            }
//        });
    }

    // Method to initialize the data
    private void initData() {
        List<String> placesList = this.getIntent().getStringArrayListExtra(ChooseActivities.PLACE_LIST_NAME_EXTRA);
        List<String> cuisineList = this.getIntent().getStringArrayListExtra(ChooseActivities.CUISINE_LIST_NAME_EXTRA);
        List<String> eventsList = this.getIntent().getStringArrayListExtra(ChooseActivities.EVENTS_LIST_NAME_EXTRA);

        Log.i(TAG, "Places list: " + placesList);
        Log.i(TAG, "Cuisine list: " + cuisineList);
        Log.i(TAG, "Events list: " + eventsList);

        listActivitiesTypes = new ArrayList<>();
        groupOfActivitiesByType = new HashMap<>();

        if(!placesList.isEmpty()) {
            listActivitiesTypes.add(GROUP_PLACES);
        }

        if(!cuisineList.isEmpty()) {
            listActivitiesTypes.add(GROUP_REST);
        }

        if(!eventsList.isEmpty()) {
            listActivitiesTypes.add(GROUP_EVENTS);
        }



    }
}