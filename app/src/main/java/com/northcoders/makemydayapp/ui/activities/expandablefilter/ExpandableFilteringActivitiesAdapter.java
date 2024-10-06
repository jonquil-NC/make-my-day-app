package com.northcoders.makemydayapp.ui.activities.expandablefilter;

import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.model.ItineraryItem;

import java.util.HashMap;
import java.util.List;

public class ExpandableFilteringActivitiesAdapter extends BaseExpandableListAdapter {


    private static final String TAG = ExpandableFilteringActivitiesAdapter.class.getName();


    private final Context context;
    private final List<String> listGroupTitles;
    private final HashMap<String, List<ItineraryItem>> listChildData;

    public ExpandableFilteringActivitiesAdapter(Context context, List<String> listGroupTitles,
                                                HashMap<String, List<ItineraryItem>> listChildData) {
        this.context = context;
        this.listGroupTitles = listGroupTitles;
        this.listChildData = listChildData;
    }

    @Override
    public int getGroupCount() {
        return listGroupTitles.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        Log.i(TAG, "Group position: " + groupPosition);
        Log.i(TAG, "Group title: " + listGroupTitles.get(groupPosition));
        Log.i(TAG, "Map child Data: " + listChildData);


        return listChildData.get(listGroupTitles.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return listGroupTitles.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listChildData.get(listGroupTitles.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String groupTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(android.R.layout.simple_expandable_list_item_1, null);
        }
        TextView groupTextView = convertView.findViewById(android.R.id.text1);
        groupTextView.setText(groupTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final ItineraryItem itineraryItem = (ItineraryItem) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_filtering_activity_item, null);
        }
        TextView childTextView = convertView.findViewById(R.id.item_text);
        childTextView.setText(itineraryItem.getDisplay());

// Set drag listener
        convertView.setOnLongClickListener(v -> {
            ClipData.Item item = new ClipData.Item(itineraryItem.getDisplay());
            ClipData dragData = new ClipData(itineraryItem.getDisplay(), new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN}, item);

            View.DragShadowBuilder myShadow = new View.DragShadowBuilder(v);
            v.startDragAndDrop(dragData, myShadow, itineraryItem, 0);
            return true;
        });


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

