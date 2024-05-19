package com.example.appsforgood;

// Import statements for necessary Android libraries and components
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.content.Intent;

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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// This class models the process to edit an existing alert
public class EditAlertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_alert);

        // Get an instance of the FirebaseFirestore to connect to the database
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        // Get references to the input fields and buttons in the layout
        TextInputEditText time = findViewById(R.id.timeET);
        TextInputEditText date = findViewById(R.id.dateET);
        TextInputEditText issue = findViewById(R.id.issueET);
        MaterialButton save = findViewById(R.id.save);
        MaterialButton delete = findViewById(R.id.delete);

        // Populate input fields with data of the existing aler
        time.setText(App.alert.getTime());
        date.setText(App.alert.getDate());
        issue.setText(App.alert.getIssue());

        // Set click listener for the delete button
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete the alert document from Firestore
                db.collection("alerts").document(App.alert.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        // Show success message if deletion is successful
                        Toast.makeText(EditAlertActivity.this, "Alert deleted successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditAlertActivity.this,UpdatesAlerts.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Show error message if deletion fails
                        Toast.makeText(EditAlertActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Set click listener for the save button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a Map to store the updated alert data
                Map<String, Object> alert = new HashMap<>();
                alert.put("time", Objects.requireNonNull(time.getText()).toString());
                alert.put("date", Objects.requireNonNull(date.getText()).toString());
                alert.put("issue", Objects.requireNonNull(issue.getText()).toString());

                // Update the alert document in Firestore
                db.collection("alerts").document(App.alert.getId()).set(alert).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        // Show success message if update is successful
                        Toast.makeText(EditAlertActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(EditAlertActivity.this,UpdatesAlerts.class));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Show error message if update fails
                        Toast.makeText(EditAlertActivity.this, "Failed to save changes", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
