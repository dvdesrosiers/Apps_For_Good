package com.example.appsforgood;

// Import necessary classes and packages
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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

public class Feedback extends AppCompatActivity {
    
    // Declare Button for going back to MainActivity
    Button backButton;

    // Override the onCreate method to initialize the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set the layout for the activity from the XML resource
        setContentView(R.layout.activity_feedback);

        // Initialize back button and set OnClickListener to return to MainActivity
        backButton=findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Feedback.this, MainActivity.class));

            }
        });
        
        // Access Firestore instance
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Initialize input fields and button
        TextInputEditText date = findViewById(R.id.dateET);
        TextInputEditText issue = findViewById(R.id.issueET);
        MaterialButton addAlert = findViewById(R.id.addAlert);

        // Set OnClickListener for the button to add feedback
        addAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                // Create a map to store feedback data
                Map<String, Object> alert = new HashMap<>();

                // Put date and issue in the map
                alert.put("date", Objects.requireNonNull(date.getText()).toString());
                alert.put("issue", Objects.requireNonNull(issue.getText()).toString());

                // Add the feedback data to the Firestore collection
                db.collection("feedback").add(alert).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        // Show a success message if feedback is sent successfully
                        Toast.makeText(Feedback.this, "Feedback sent successfully", Toast.LENGTH_SHORT).show();
                       //close the activity 
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        
                        // Show an error message if failed to send feedback
                        Toast.makeText(Feedback.this, "Failed to send feedback", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
