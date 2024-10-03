package com.northcoders.makemydayapp.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.northcoders.makemydayapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ChooseActivities extends AppCompatActivity {

    private EditText textDate;
    private ChipGroup chipGroup;
    private Button submitButton;
    private List<String> selectedActivities;
    private String selectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_activities);

        textDate = findViewById(R.id.dateInput);
        chipGroup = findViewById(R.id.chip_group);
        submitButton = findViewById(R.id.button_submit);

        selectedActivities = new ArrayList<>();

        setupChipSelection();

        submitButton.setOnClickListener(s -> handleSubmit());

    }

    private void setupChipSelection() {
        for (int i = 0; i < chipGroup.getChildCount(); i++) {
            View chip = chipGroup.getChildAt(i);
            if (chip instanceof Chip) {
                Chip activityChip = (Chip) chip;
                activityChip.setOnClickListener(v -> {
                    if (activityChip.isChecked()) {

                        activityChip.setChipBackgroundColorResource(R.color.colorSelected);
                        selectedActivities.add(activityChip.getText().toString());

                    } else {

                        activityChip.setChipBackgroundColorResource(R.color.white);
                        selectedActivities.remove(activityChip.getText().toString());

                    }
                });
            }
        }
    }

    private void handleSubmit() {
        selectedDate = textDate.getText().toString().trim();

        if (!isValidDate(selectedDate)) {
            Toast.makeText(this, "Please enter a valid date (DD/MM/YYYY)", Toast.LENGTH_SHORT).show();
            return;
        }

        if (selectedActivities.isEmpty()) {
            Toast.makeText(this, "Please select at least one activity", Toast.LENGTH_SHORT).show();
            return;
        }


        Intent intent = new Intent(ChooseActivities.this, ActivityView.class);
        intent.putExtra("selectedDate", selectedDate);
        intent.putStringArrayListExtra("selectedActivities", new ArrayList<>(selectedActivities));
        startActivity(intent);
    }

    private boolean isValidDate(String date) {
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
}