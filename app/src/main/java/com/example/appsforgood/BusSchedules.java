package com.example.appsforgood;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Spinner;

import java.util.Arrays;

public class BusSchedules extends AppCompatActivity {
    Button backButton;

    String[] item = {"Route1", "Route2", "Route3", "Route4", "Route5", "Route6", "Route7",
            "Route 8", "Route9", "Route10", "Route 11", "Route 12", "Route 13", "Route 14",
            "Route 15", "Route 16", "Route 17", "Route 18", "Route 19", "Route 20", "Route 21",
            "Route 22", "Route 23", "Route 24", "Route 25", "Route 26", "Route 27","Route 28",
            "Route 29","Route 30", "Route 31"};
    AutoCompleteTextView autoCompleteTextView;


    ArrayAdapter<String> adapterItems;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_schedules);


        autoCompleteTextView = findViewById(R.id.auto_complete_txt);

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, item);

        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item2 = parent.getItemAtPosition(position).toString();

                Toast.makeText(getApplicationContext(),"Item: " + item2,Toast.LENGTH_SHORT).show();

            }
        });


        backButton=findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

}