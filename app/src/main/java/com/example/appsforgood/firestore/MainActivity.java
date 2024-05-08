package com.example.appsforgood.firestore;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appsforgood.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseApp.initializeApp(MainActivity.this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        RecyclerView recyclerView = findViewById(R.id.recycler);

        FloatingActionButton add = findViewById(R.id.addAlert);
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(
                        MainActivity.this, AddAlertActivity.class));
            }
        });

        db.collection("alerts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    ArrayList<Alerts> arrayList = new ArrayList<>();
                    for(QueryDocumentSnapshot doc: task.getResult()){
                        Alerts alert = doc.toObject(Alerts.class);
                        alert.setId(doc.getId());
                        arrayList.add(alert);
                    }

                    AlertAdapter adapter = new AlertAdapter(MainActivity.this, arrayList);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(new AlertAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(Alerts Alerts) {
                            App.alert = Alerts;
                            startActivity(new Intent(MainActivity.this, EditAlertActivity.class));
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Failed to get all alerts list", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton refresh = findViewById(R.id.refresh);
        refresh.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                db.collection("alerts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if(task.isSuccessful()) {
                            ArrayList<Alerts> arrayList = new ArrayList<>();
                            for(QueryDocumentSnapshot doc: task.getResult()){
                                Alerts alert = doc.toObject(Alerts.class);
                                alert.setId(doc.getId());
                                arrayList.add(alert);
                            }

                            AlertAdapter adapter = new AlertAdapter(MainActivity.this, arrayList);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new AlertAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(Alerts Alerts) {
                                    App.alert = Alerts;
                                    startActivity(new Intent(MainActivity.this, EditAlertActivity.class));
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to get all alerts list", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        readStopData();
    }

    private void readStopData(){
        InputStream is = getResources().openRawResource(R.raw.stopdata);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = "";
        try{
            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");

                Log.v("MainActivity",fields[0] + " " + fields[1] + " " + fields[2] + " " + fields[3]);
                Stops s = new Stops(parseInt(fields[0]),fields[1],parseDouble(fields[2]),parseDouble(fields[3]) );
            }
        } catch (IOException e) {

        }
    }

    private void readRouteData(){
        InputStream is = getResources().openRawResource(R.raw.routelog);

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = "";
        try{
            while((line = reader.readLine()) != null){
                String[] fields = line.split(",");

                Log.v("MainActivity",fields[0] + " " + fields[1] + " " + fields[2] + " " + fields[3]);
                Stops s = new Stops(parseInt(fields[0]),fields[1],parseDouble(fields[2]),parseDouble(fields[3]) );
            }
        } catch (IOException e) {

        }
    }
}