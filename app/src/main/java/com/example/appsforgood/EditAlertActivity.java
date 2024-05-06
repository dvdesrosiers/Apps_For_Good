package com.example.appsforgood;

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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class EditAlertActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_alert);

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        TextInputEditText time = findViewById(R.id.timeET);
        TextInputEditText date = findViewById(R.id.dateET);
        TextInputEditText issue = findViewById(R.id.issueET);
        MaterialButton save = findViewById(R.id.save);
        MaterialButton delete = findViewById(R.id.delete);

        time.setText(App.alert.getTime());
        date.setText(App.alert.getDate());
        issue.setText(App.alert.getIssue());

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("alerts").document(App.alert.getId()).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EditAlertActivity.this, "Alert deleted successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditAlertActivity.this, "Failed to delete", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> alert = new HashMap<>();
                alert.put("time", Objects.requireNonNull(time.getText()).toString());
                alert.put("date", Objects.requireNonNull(date.getText()).toString());
                alert.put("issue", Objects.requireNonNull(issue.getText()).toString());

                db.collection("alerts").document(App.alert.getId()).set(alert).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(EditAlertActivity.this, "Saved successfully", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(EditAlertActivity.this, "Failed to save changes", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}