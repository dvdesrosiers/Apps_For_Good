// Import necessary classes and packages
package com.example.appsforgood;

import android.os.Bundle;

import android.app.Activity;
import android.view.View;

import android.content.Intent;
import android.widget.Button;


// MainActivity class definition, extending Activity class
public class MainActivity extends Activity {

    // Declaring buttons
    Button button;
    Button button2;
    Button button3;
    Button button4;


    // Override the onCreate method to initialize the activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                
        // Set the layout for the activity from the XML resource
        setContentView(R.layout.activity_main);

        // Initialize button1 and set onClickListener
        button=(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                //Create an Intent to start UpdatesAlerts activity
                Intent intent = new Intent(MainActivity.this, UpdatesAlerts.class);
               
                // Start the UpdatesAlerts activity
                startActivity(intent);
            }
        });

        // Initialize button2 and set onClickListener
        button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               
                // Create an Intent to start BusSchedules activity
                Intent intent = new Intent(MainActivity.this,BusSchedules.class);
                
                // Start the BusSchedules activity
                startActivity(intent);
            }
        });
        
        // Initialize button3 and set onClickListener
        button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            
            public void onClick(View v) {

                // Create an Intent to start Maps activity
                Intent intent = new Intent(MainActivity.this, Maps.class);
                            
                // Start the Maps activity
                startActivity(intent);
            }
        });
        
        // Initialize button4 and set onClickListener
        button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Create an Intent to start Feedback activity
                Intent intent = new Intent(MainActivity.this, Feedback.class);
                
                // Start the Feedback activity
                startActivity(intent);
            }
        });



    }


}
