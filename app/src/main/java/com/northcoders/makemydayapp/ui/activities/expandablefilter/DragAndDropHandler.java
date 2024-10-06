package com.northcoders.makemydayapp.ui.activities.expandablefilter;

import android.content.ClipData;
import android.content.Context;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.northcoders.makemydayapp.model.ItineraryItem;

import java.util.HashMap;
import java.util.Map;

public class DragAndDropHandler implements View.OnDragListener {
    private static final String TAG = DragAndDropHandler.class.getName();

    private LinearLayout dropContainer;
    private Context context;
    private Map<String,ItineraryItem> itineraryItemMap = new HashMap<>();
    private String key;

    public DragAndDropHandler(LinearLayout dropContainer, Context context, Map<String,ItineraryItem> itineraryItemMap, String key){
        this.dropContainer = dropContainer;
        this.context = context;
        this.itineraryItemMap = itineraryItemMap;
        this.key = key;
    }


    @Override
    public boolean onDrag(View v, DragEvent event) {
        switch (event.getAction()) {
            case DragEvent.ACTION_DRAG_STARTED:
                // Can handle drag event here
                return true;
            case DragEvent.ACTION_DROP:
                // Get the item from the ClipData
                ClipData.Item item = event.getClipData().getItemAt(0);
                String droppedItem = item.getText().toString();

                // Add the dropped item to the drop container (you can add TextView, ImageView, etc.)
                TextView newTextView = new TextView(context);
                newTextView.setText(droppedItem);
                dropContainer.addView(newTextView);

                final ItineraryItem itineraryItem = (ItineraryItem) event.getLocalState();

                Log.i(TAG,"Dropped Element in : "+ itineraryItem.getDisplay());
                this.itineraryItemMap.put(key, itineraryItem);

                return true;
            default:
                return true;
        }
    }
}
