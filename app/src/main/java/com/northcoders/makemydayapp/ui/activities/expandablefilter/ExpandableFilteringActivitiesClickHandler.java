package com.northcoders.makemydayapp.ui.activities.expandablefilter;

import android.util.Log;
import android.view.View;

import com.northcoders.makemydayapp.model.ItineraryItem;

import java.util.HashMap;
import java.util.Map;

public class ExpandableFilteringActivitiesClickHandler implements View.OnClickListener{


    private static final String TAG = ExpandableFilteringActivitiesClickHandler.class.getName();
    private Map<String, ItineraryItem> itineraryItemMap = new HashMap<>();


    public ExpandableFilteringActivitiesClickHandler(Map<String, ItineraryItem> itineraryItemMap) {
        this.itineraryItemMap = itineraryItemMap;
    }

    @Override
    public void onClick(View v) {
        //TODO

        ItineraryItem morningItem = this.itineraryItemMap.get(ExpandableFilteringActivities.MORNING_ACTIVITY);
        ItineraryItem afternoonItem = this.itineraryItemMap.get(ExpandableFilteringActivities.AFTERNOON_ACTIVITY);
        ItineraryItem eveningItem = this.itineraryItemMap.get(ExpandableFilteringActivities.EVENING_ACTIVITY);

        Log.i(TAG, "Morning activity: " + morningItem);
        Log.i(TAG, "Afternoon activity: " + afternoonItem);
        Log.i(TAG, "Evening activity: " + eveningItem);

    }
}
