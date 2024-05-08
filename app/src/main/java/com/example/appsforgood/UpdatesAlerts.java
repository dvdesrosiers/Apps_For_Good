package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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

public class UpdatesAlerts extends AppCompatActivity {
Button backButton;
EditText inputText;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updates_alerts);
        backButton=findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UpdatesAlerts.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        FirebaseApp.initializeApp(UpdatesAlerts.this);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        RecyclerView recyclerView = findViewById(R.id.recycler);

        FloatingActionButton add = findViewById(R.id.addAlert);
        add.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(
                        UpdatesAlerts.this, AddAlertActivity.class));
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

                    AlertAdapter adapter = new AlertAdapter(UpdatesAlerts.this, arrayList);
                    recyclerView.setAdapter(adapter);
                    adapter.setOnItemClickListener(new AlertAdapter.OnItemClickListener() {
                        @Override
                        public void onClick(Alerts Alerts) {
                            App.alert = Alerts;
                            startActivity(new Intent(UpdatesAlerts.this, EditAlertActivity.class));
                        }
                    });
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdatesAlerts.this, "Failed to get all alerts list", Toast.LENGTH_SHORT).show();
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

                            AlertAdapter adapter = new AlertAdapter(UpdatesAlerts.this, arrayList);
                            recyclerView.setAdapter(adapter);
                            adapter.setOnItemClickListener(new AlertAdapter.OnItemClickListener() {
                                @Override
                                public void onClick(Alerts Alerts) {
                                    App.alert = Alerts;
                                    startActivity(new Intent(UpdatesAlerts.this, EditAlertActivity.class));
                                }
                            });
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(UpdatesAlerts.this, "Failed to get all alerts list", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }


}


