package com.example.appsforgood;

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

public class BusSchedules extends AppCompatActivity {
    Button backButton;


    String[] item = {"Route1", "Route2", "Route3", "Route4", "Route5", "Route6", "Route7",
            "Route 8", "Route9", "Route10", "Route 11", "Route 12", "Route 13", "Route 14",
            "Route 15", "Route 16", "Route 17", "Route 18", "Route 19", "Route 20", "Route 21",
            "Route 22", "Route 23", "Route 24", "Route 25", "Route 26", "Route 27","Route 28",
            "Route 29","Route 30", "Route 31"};
    String[] routeStops;
    AutoCompleteTextView autoCompleteTextView;


    ArrayAdapter<String> adapterItems;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_schedules);
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, item);
        autoCompleteTextView.setAdapter(adapterItems);


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String item2 = parent.getItemAtPosition(position).toString().toLowerCase();
                InputStream is = getResources().openRawResource(R.raw.routelog);

                String[] fields = new String[40];

                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line = "";
                try{
                    while((line = reader.readLine()) != null){
                            fields = line.split(",");
                            if (fields[0].equals(item2)){
                                Intent test = new Intent(BusSchedules.this, StopsShow.class);
                                test.putExtra("item",fields);
                                startActivity(test);
                            }
                    }




                } catch (IOException e) {

                }


            }



        });
    }
    }