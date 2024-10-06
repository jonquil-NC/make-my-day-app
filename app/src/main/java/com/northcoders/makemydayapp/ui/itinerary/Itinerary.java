package com.northcoders.makemydayapp.ui.itinerary;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.databinding.ActivityItineraryBinding;
import com.northcoders.makemydayapp.model.ItineraryItem;
import com.northcoders.makemydayapp.ui.activities.expandablefilter.ExpandableFilteringActivities;
import com.northcoders.makemydayapp.ui.mainactivity.MainActivityViewModel;

public class Itinerary extends AppCompatActivity {

    public static final String TAG = Itinerary.class.getName();

    ActivityItineraryBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itinerary);

        Log.i(TAG, "Opening Itinerary Activity");
        ItineraryItem morningItem   = this.getIntent().getParcelableExtra(ExpandableFilteringActivities.MORNING_ACTIVITY, ItineraryItem.class);
        Log.i(TAG, "Morning item: " + morningItem);

        ItineraryItem afternoonItem = this.getIntent().getParcelableExtra(ExpandableFilteringActivities.AFTERNOON_ACTIVITY, ItineraryItem.class);
        Log.i(TAG, "Afternoon item: " + afternoonItem);

        ItineraryItem eveningItem   = this.getIntent().getParcelableExtra(ExpandableFilteringActivities.EVENING_ACTIVITY, ItineraryItem.class);
        Log.i(TAG, "Evening item: " + eveningItem);

        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_itinerary);
        Log.i(TAG, "Creating the binding");

        this.binding.setMorningActivity(morningItem);
        this.binding.setAfternoonActivity(afternoonItem);
        this.binding.setEveningActivity(eveningItem);

        MainActivityViewModel viewModel = new ViewModelProvider(this)
                .get(MainActivityViewModel.class);

        this.binding.setClickHandler(new ItineraryClickHandler());

    }
}