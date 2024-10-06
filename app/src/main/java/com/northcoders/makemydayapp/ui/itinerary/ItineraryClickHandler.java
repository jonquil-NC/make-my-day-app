package com.northcoders.makemydayapp.ui.itinerary;

import android.content.Intent;
import android.view.View;

import com.northcoders.makemydayapp.ui.activities.ChooseActivities;

public class ItineraryClickHandler {

    public void OnClickStartAgain(View view) {
        Intent intent = new Intent(view.getContext(), ChooseActivities.class);
        view.getContext().startActivity(intent);
    }
}
