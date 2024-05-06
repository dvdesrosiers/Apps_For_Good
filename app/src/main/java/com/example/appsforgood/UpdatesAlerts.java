package com.example.appsforgood;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


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
        textView = (TextView) findViewById(R.id.textView);
        inputText = (EditText) findViewById(R.id.inputText);

    }
    public void updateText(View view) {
        textView.setText("Thank you!");
        System.out.println("Button pressed");
    }
}