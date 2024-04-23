package com.example.appsforgood.firestore;

import android.os.Bundle;
import android.view.View;
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

public class AddAlertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alert);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        TextInputEditText time = findViewById(R.id.timeET);
        TextInputEditText date = findViewById(R.id.dateET);
        TextInputEditText issue = findViewById(R.id.issueET);
        MaterialButton addAlert = findViewById(R.id.addAlert);

        addAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> alert = new HashMap<>();
                alert.put("time", Objects.requireNonNull(time.getText()).toString());
                alert.put("date", Objects.requireNonNull(date.getText()).toString());
                alert.put("issue", Objects.requireNonNull(issue.getText()).toString());

                db.collection("alerts").add(alert).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(AddAlertActivity.this, "Alert added successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(AddAlertActivity.this, "Failed to add alert", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}