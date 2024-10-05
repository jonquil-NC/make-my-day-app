package com.northcoders.makemydayapp.ui.activities.expandablefilter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;

import com.northcoders.makemydayapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableFilteringActivities extends AppCompatActivity {

    ExpandableListView expandableListView1;
    ExpandableListView expandableListView2;
    ExpandableFilteringActivitiesAdapter adapter1;
    ExpandableFilteringActivitiesAdapter adapter2;
    List<String> listGroup;
    HashMap<String, List<String>> listChild;
    Button addGroupButton, addChildButton, removeChildButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_filtering_activities);

        expandableListView1 = findViewById(R.id.expandableListView1);
        expandableListView2 = findViewById(R.id.expandableListView2);

        addGroupButton = findViewById(R.id.addGroupButton);
        addChildButton = findViewById(R.id.addChildButton);
        removeChildButton = findViewById(R.id.removeChildButton);

        // Initialize data
        initData();

        // Set up adapters for both ExpandableListViews
        adapter1 = new ExpandableFilteringActivitiesAdapter(this, listGroup, listChild);
        adapter2 = new ExpandableFilteringActivitiesAdapter(this, listGroup, listChild);

        expandableListView1.setAdapter(adapter1);
        expandableListView2.setAdapter(adapter2);

        // Add new group dynamically
        addGroupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newGroup = "Group " + (listGroup.size() + 1);
                listGroup.add(newGroup);
                listChild.put(newGroup, new ArrayList<>()); // Initialize empty child list
                adapter1.notifyDataSetChanged(); // Notify the adapter that the data has changed
                adapter2.notifyDataSetChanged();
            }
        });

        // Add a new child to the first group dynamically
        addChildButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> group1Items = listChild.get(listGroup.get(0)); // Access the first group
                group1Items.add("New Child " + (group1Items.size() + 1));
                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        });

        // Remove the last child from the first group dynamically
        removeChildButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> group1Items = listChild.get(listGroup.get(0)); // Access the first group
                if (!group1Items.isEmpty()) {
                    group1Items.remove(group1Items.size() - 1); // Remove the last child
                    adapter1.notifyDataSetChanged();
                    adapter2.notifyDataSetChanged();
                }
            }
        });
    }

    // Method to initialize the data
    private void initData() {
        listGroup = new ArrayList<>();
        listChild = new HashMap<>();

        // Add initial group titles
        listGroup.add("Group 1");
        listGroup.add("Group 2");

        // Add child items to groups
        List<String> group1Items = new ArrayList<>();
        group1Items.add("Item 1");
        group1Items.add("Item 2");

        List<String> group2Items = new ArrayList<>();
        group2Items.add("Item 3");
        group2Items.add("Item 4");

        listChild.put(listGroup.get(0), group1Items);
        listChild.put(listGroup.get(1), group2Items);
    }
}