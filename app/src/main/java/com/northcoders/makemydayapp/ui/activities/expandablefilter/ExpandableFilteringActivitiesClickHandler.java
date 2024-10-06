package com.northcoders.makemydayapp.ui.activities.expandablefilter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.northcoders.makemydayapp.model.ItineraryItem;
import com.northcoders.makemydayapp.ui.itinerary.Itinerary;

import java.util.HashMap;
import java.util.Map;

public class ExpandableFilteringActivitiesClickHandler {


    private static final String TAG = ExpandableFilteringActivitiesClickHandler.class.getName();
    private final Map<String, ItineraryItem> itineraryItemMap = new HashMap<>();

    public void putActivity(String key, ItineraryItem value){
        Log.i(TAG, "Add Activity to ClickHandler: key=" + key + ", value="+value);
        this.itineraryItemMap.put(key, value);
    }

    public void onClickSubmit(View view) {
        Intent intent = new Intent(view.getContext(), Itinerary.class);

        Log.i(TAG, "Click Handler");

        ItineraryItem morningItem   = this.itineraryItemMap.get(ExpandableFilteringActivities.MORNING_ACTIVITY);
        ItineraryItem afternoonItem = this.itineraryItemMap.get(ExpandableFilteringActivities.AFTERNOON_ACTIVITY);
        ItineraryItem eveningItem   = this.itineraryItemMap.get(ExpandableFilteringActivities.EVENING_ACTIVITY);

        Log.i(TAG, "Morning activity: " + morningItem);
        Log.i(TAG, "Afternoon activity: " + afternoonItem);
        Log.i(TAG, "Evening activity: " + eveningItem);

        intent.putExtra(ExpandableFilteringActivities.MORNING_ACTIVITY, morningItem);
        intent.putExtra(ExpandableFilteringActivities.AFTERNOON_ACTIVITY, afternoonItem);
        intent.putExtra(ExpandableFilteringActivities.EVENING_ACTIVITY, eveningItem);

        view.getContext().startActivity(intent);
    }
}
