package com.northcoders.makemydayapp.ui.activities;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.makemydayapp.R;

import java.util.List;

public class ActivityView extends AppCompatActivity {

    private static final String TAG = ActivityView.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        Get list of events from previous activity
        List<String> activitiesList = getIntent().getStringArrayListExtra("activitiesList");
        List<String> cuisineList = getIntent().getStringArrayListExtra("cuisineList");

        Log.i(TAG, "Activity list: " + activitiesList);
        Log.i(TAG, "Cuisine list: " + cuisineList);


        if (activitiesList != null && !activitiesList.isEmpty()) {
            RecyclerView recyclerView = findViewById(R.id.recycler_view_events);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}