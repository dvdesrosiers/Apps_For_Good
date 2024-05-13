package com.example.appsforgood;

// Import necessary classes and packages
import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.DocumentSnapshot;

import org.w3c.dom.Document;

// BusSchedules class definition, extending AppCompatActivity
public class BusSchedules extends AppCompatActivity {

    // Declare Button for going back to MainActivity
    Button backButton;

    
    // Array of bus routes
    String[] item = {"Route1", "Route2", "Route3", "Route4", "Route5", "Route6", "Route7",
            "Route 11", "Route 12", "Route 14", "Route 15", "Route 16", "Route 19", "Route 23", "Route 24", "Route 26", "Route 27",
            "Route 29","Route 30", "Route 31", "Route 33", "Route 42", "Route 825"};
    String[] routeStops;
    AutoCompleteTextView autoCompleteTextView;


    ArrayAdapter<String> adapterItems;

    // Override the onCreate method to initialize the activity
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for the activity from the XML resource
        setContentView(R.layout.activity_bus_schedules);
        
        // Initialize back button and set OnClickListener to return to MainActivity
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BusSchedules.this, MainActivity.class));

            }
        });

        // Initialize AutoCompleteTextView and ArrayAdapter for bus routes
        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, item);
        autoCompleteTextView.setAdapter(adapterItems);

        
        // Set OnItemClickListener for AutoCompleteTextView to handle item selection
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
                // Get the selected item
                String item2 = parent.getItemAtPosition(position).toString().toLowerCase().replace(" ", "");
                InputStream is = getResources().openRawResource(R.raw.routelog);

                String[] fields = new String[100];

                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line = "";
                try{
                    // Read each line from the file
                    while((line = reader.readLine()) != null){
                            
                            // Split the line by comma
                            fields = line.split(",");

                            // Check if the first field matches the selected bus route
                            if (fields[0].equals(item2)){
                                // If match found, create an Intent to start StopsShow activity and pass data
                                Intent test = new Intent(BusSchedules.this, StopsShow.class);
                                test.putExtra("item",fields);
                                startActivity(test);
                            }
                    }




                } catch (IOException e) {
                // Handle IOException

                }


            }



        });
    }
    }
