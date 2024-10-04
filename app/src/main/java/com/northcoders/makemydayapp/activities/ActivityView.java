package com.northcoders.makemydayapp.activities;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.model.MMDEvent;

import java.util.List;

public class ActivityView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


//        Get list of events from previous activity
        List<MMDEvent> eventList = getIntent().getParcelableArrayListExtra("eventList");

        if (eventList != null && !eventList.isEmpty()) {
            RecyclerView recyclerView = findViewById(R.id.recycler_view_events);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
    }
}