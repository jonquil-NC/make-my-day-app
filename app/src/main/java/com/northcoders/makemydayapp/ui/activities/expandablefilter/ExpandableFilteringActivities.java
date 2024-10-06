package com.northcoders.makemydayapp.ui.activities.expandablefilter;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.databinding.ActivityExpandableFilteringActivitiesBinding;
import com.northcoders.makemydayapp.model.ItineraryItem;
import com.northcoders.makemydayapp.model.MMDEvent;
import com.northcoders.makemydayapp.model.Place;
import com.northcoders.makemydayapp.model.Restaurant;
import com.northcoders.makemydayapp.ui.activities.ChooseActivities;
import com.northcoders.makemydayapp.ui.mainactivity.MainActivityViewModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExpandableFilteringActivities extends AppCompatActivity {


    private static final String TAG = ExpandableFilteringActivities.class.getName();

    private ActivityExpandableFilteringActivitiesBinding expandableFilteringActivitiesBinding;

    private static final String GROUP_REST = "Restaurants";
    private static final String GROUP_PLACES = "Places";
    private static final String GROUP_EVENTS = "Activities";

    public static final String MORNING_ACTIVITY = "morning";
    public static final String AFTERNOON_ACTIVITY = "afternoon";
    public static final String EVENING_ACTIVITY = "evening";

    ExpandableListView expandableListViewActivities;
    ExpandableFilteringActivitiesAdapter expandableFilteringActivitiesAdapter;
    List<String> listActivitiesTypes;
    HashMap<String, List<ItineraryItem>> groupOfItems= new HashMap<>();

    private LinearLayout dropMorningContainer;
    private LinearLayout dropAfternoonContainer;
    private LinearLayout dropEveningContainer;

    private Button submitButton;
    private Map<String,ItineraryItem> itineraryItemMap = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_filtering_activities);

        this.expandableFilteringActivitiesBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_expandable_filtering_activities);


        this.expandableListViewActivities = findViewById(R.id.expandableListViewItemsFiltering);
        this.submitButton = findViewById(R.id.submitItineraryButton);
        ExpandableFilteringActivitiesClickHandler clickHandler = new ExpandableFilteringActivitiesClickHandler();
        expandableFilteringActivitiesBinding.setClickHandler(clickHandler);

        this.dropMorningContainer = findViewById(R.id.dropMorningContainer);
        this.dropAfternoonContainer = findViewById(R.id.dropAfternoonContainer);
        this.dropEveningContainer = findViewById(R.id.dropEveningContainer);

        this.dropMorningContainer.setOnDragListener(new DragAndDropHandler(this.dropMorningContainer, this,clickHandler, MORNING_ACTIVITY));
        this.dropAfternoonContainer.setOnDragListener(new DragAndDropHandler(this.dropAfternoonContainer, this, clickHandler, AFTERNOON_ACTIVITY));
        this.dropEveningContainer.setOnDragListener(new DragAndDropHandler(this.dropEveningContainer, this, clickHandler, EVENING_ACTIVITY));



        // Initialize data
        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        initData(viewModel);

        // Set up adapters for both ExpandableListViews
        expandableFilteringActivitiesAdapter = new ExpandableFilteringActivitiesAdapter(this, listActivitiesTypes, groupOfItems);

        expandableListViewActivities.setAdapter(expandableFilteringActivitiesAdapter);


    }

    // Method to initialize the data
    private void initData(MainActivityViewModel viewModel) {
        List<String> placesList = this.getIntent().getStringArrayListExtra(ChooseActivities.PLACE_LIST_NAME_EXTRA);
        List<String> cuisineList = this.getIntent().getStringArrayListExtra(ChooseActivities.CUISINE_LIST_NAME_EXTRA);
        List<String> eventsList = this.getIntent().getStringArrayListExtra(ChooseActivities.EVENTS_LIST_NAME_EXTRA);
        LocalDate localDate = this.getIntent().getSerializableExtra(ChooseActivities.EVENTS_DATE_NAME_EXTRA, LocalDate.class);

        Log.i(TAG, "Places list: " + placesList);
        Log.i(TAG, "Cuisine list: " + cuisineList);
        Log.i(TAG, "Events list: " + eventsList);

        listActivitiesTypes = new ArrayList<>();

        if(!placesList.isEmpty()) {
            listActivitiesTypes.add(GROUP_PLACES);
            groupOfItems.put(GROUP_PLACES,List.of());
            LiveData<List<Place>> liveDataPlaces = viewModel.getPlaceByType(placesList);
            liveDataPlaces.observe(this, new Observer<List<Place>>() {
                @Override
                public void onChanged(List<Place> places) {
                    List<ItineraryItem> listOfPlaces = new ArrayList<>();
                    if(places != null){
                        for (Place place : places) {
                            ItineraryItem<Place> itineraryItem = new ItineraryItem<>(place);
                            listOfPlaces.add(itineraryItem);
                        }
                    }

                    groupOfItems.put(GROUP_PLACES, listOfPlaces);
                    expandableFilteringActivitiesAdapter.notifyDataSetChanged();
                }
            });
        }

        if(!cuisineList.isEmpty()) {
            listActivitiesTypes.add(GROUP_REST);
            groupOfItems.put(GROUP_REST,List.of());
            LiveData<List<Restaurant>> liveDataRestaurants = viewModel.getRestaurantByCuisines(cuisineList);

            liveDataRestaurants.observe(this, new Observer<List<Restaurant>>() {
                @Override
                public void onChanged(List<Restaurant> restaurants) {
                    List<ItineraryItem> listOfRestaurants = new ArrayList<>();
                    if(restaurants != null) {
                        for (Restaurant restaurant : restaurants) {
                            ItineraryItem<Restaurant> itineraryItem = new ItineraryItem<>(restaurant);
                            listOfRestaurants.add(itineraryItem);
                        }
                    }
                    groupOfItems.put(GROUP_REST, listOfRestaurants);
                    expandableFilteringActivitiesAdapter.notifyDataSetChanged();
                }
            });
        }

        if(!eventsList.isEmpty()) {
            listActivitiesTypes.add(GROUP_EVENTS);
            groupOfItems.put(GROUP_EVENTS,List.of());

            LiveData<List<MMDEvent>> liveDataEvents = viewModel.getEventsByType(localDate, eventsList);

            liveDataEvents.observe(this, events -> {
                List<ItineraryItem> listOfEvents = new ArrayList<>();
                if (events != null) {
                    for (MMDEvent event : events) {
                        ItineraryItem<MMDEvent> itineraryItem = new ItineraryItem<>(event);
                        listOfEvents.add(itineraryItem);
                    }
                }
                groupOfItems.put(GROUP_EVENTS, listOfEvents);
                expandableFilteringActivitiesAdapter.notifyDataSetChanged();
            });
        }

    }
}