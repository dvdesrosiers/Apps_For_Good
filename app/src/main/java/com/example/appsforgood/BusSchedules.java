package com.example.appsforgood;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Spinner;

import java.util.ArrayList;

public class BusSchedules extends AppCompatActivity {


ArrayList<BusRoutes> busRoute = new ArrayList<>();

int[] busRouteImages = {R.drawable.ic_route_1,R.drawable.ic_route_2,
        R.drawable.ic_route_3, R.drawable.ic_route_4, R.drawable.ic_route_5, R.drawable.ic_route_6,
        R.drawable.ic_route_7,R.drawable.ic_route_11,R.drawable.ic_route_12, R.drawable.ic_route_12,
        R.drawable.ic_route_14,R.drawable.ic_route_15,R.drawable.ic_route_16,R.drawable.ic_route_19,
        R.drawable.ic_route_23,R.drawable.ic_route_24, R.drawable.ic_route_26, R.drawable.ic_route_27,
        R.drawable.ic_route_29, R.drawable.ic_route_30, R.drawable.ic_route_31,R.drawable.ic_route_33,
        R.drawable.ic_route_42,R.drawable.ic_route_825};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_schedules);

        RecyclerView recyclerView = findViewById(R.id.mRecyclerView);

        setUpBusRoutes();


        BusSchedule_Recycler_Adapter adapter = new BusSchedule_Recycler_Adapter(this, busRoute);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

    }

    private void setUpBusRoutes (){

        String[] busRouteNames = getResources().getStringArray(R.array.bus_routes_full_txt);

        for (int i =0; i <busRouteNames.length; i++) {

            busRoute.add(new BusRoutes(busRouteNames[i],busRouteImages[i]));

        }

    }
}