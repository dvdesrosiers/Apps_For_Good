package com.example.appsforgood;

// Import statements for necessary Android libraries and components
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.appsforgood.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// This class models the process for adding an alert to the Firebase system
public class AddAlertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // layout for activity
        setContentView(R.layout.activity_add_alert);

        // Get an instance of the FirebaseFirestore for database connection
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Get references to input fields and the corresponding button in the layout
        TextInputEditText time = findViewById(R.id.timeET);
        TextInputEditText date = findViewById(R.id.dateET);
        TextInputEditText issue = findViewById(R.id.issueET);
        MaterialButton addAlert = findViewById(R.id.addAlert);

        // Set a click listener for the "Add Alert" button
        addAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a Map to store alert data
                Map<String, Object> alert = new HashMap<>();
                // Populate the Map with the data from the input fields
                alert.put("time", Objects.requireNonNull(time.getText()).toString());
                alert.put("date", Objects.requireNonNull(date.getText()).toString());
                alert.put("issue", Objects.requireNonNull(issue.getText()).toString());

                // Add the alert data to the "alerts" collection in Firestore
                db.collection("alerts").add(alert).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Show success message if alert was added successfully
                        Toast.makeText(AddAlertActivity.this, "Alert added successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(AddAlertActivity.this,UpdatesAlerts.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Show an error message if alert adding was failed
                        Toast.makeText(AddAlertActivity.this, "Failed to add alert", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
