package com.northcoders.makemydayapp.ui.activities.expandablefilter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.northcoders.makemydayapp.R;
import com.northcoders.makemydayapp.model.Restaurant;

import java.util.HashMap;
import java.util.List;

public class ExpandableFilteringRestaurantAdapter extends BaseExpandableListAdapter {

    private final Context context;
    private final List<String> listGroupTitles;
    private final HashMap<String, List<Restaurant>> listChildData;

    public ExpandableFilteringRestaurantAdapter(Context context, List<String> listGroupTitles,
                                                HashMap<String, List<Restaurant>> listChildData) {
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
        final Restaurant childRestaurant = (Restaurant) getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.expandable_filtering_activity_item, null);
        }
        TextView childTextView = convertView.findViewById(R.id.item_text);
        CheckBox childCheckBox = convertView.findViewById(R.id.item_checkbox);

        childTextView.setText(childRestaurant.getName());
        // You can dynamically manage the checkbox state here

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}

