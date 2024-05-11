package com.example.appsforgood;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Arrays;


//receives and displays the stops associated with a particular route given from the previous page
public class StopsShow extends AppCompatActivity {
    AutoCompleteTextView autoCompleteTextView;
    Button backButton;
    static String stopCode;


    ArrayAdapter<String> adapterItems;


    //creates a back button, gets data from previous page and maps it to data in this page, runs the getStopCode method and the sendSMS method
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stops_show);
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StopsShow.this, MainActivity.class));
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Bundle extras = getIntent().getExtras();
        String[] stops = new String[0];
        if (extras != null) {
            stops = extras.getStringArray("item");
            assert stops != null;
            stops[0]="";
            stops[1]="";

            //The key argument here must match that used in the other activity
        }
        autoCompleteTextView = findViewById(R.id.auto_complete_txt);
        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, Objects.requireNonNull(stops));
        autoCompleteTextView.setAdapter(adapterItems);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item2 = parent.getItemAtPosition(position).toString();
                try {
                    stopCode = getStopCode(item2);
                    sendSMS();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    //finds the stop code from the CSV according to the stop name
    private String getStopCode(String stopName) throws IOException {
        InputStream is = getResources().openRawResource(R.raw.stopdata);

        String[] stops;

        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String line = "";

            while((line = reader.readLine()) != null) {
                stops = line.split(",");
                if (stops[1].equals(stopName)) {
                    return stops[0];
                }
            }

                return "0";
    }

    //checks for SMS permissions and opens the sendMessage method if true
    public void sendSMS(){
        if (ContextCompat.checkSelfPermission(StopsShow.this, android.Manifest.permission.SEND_SMS)
                == PackageManager.PERMISSION_GRANTED) {
            sendMessage(stopCode);
        } else {
            ActivityCompat.requestPermissions(StopsShow.this, new String[]{android.Manifest.permission.SEND_SMS}, 100);
        }
    }

    //checks for SMS permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        //check condition
        if (requestCode==100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sendMessage(stopCode);
        }
        else {
            //when permission is denied
            Toast.makeText(this,"Permission Denied!", Toast.LENGTH_SHORT).show();
        }
    }

    //sends an SMS message to the WRTA API with the given stop code and opens the SMSReceive class
    private void sendMessage(String stopNum){
        //get value form editText
        String phone = "41411";
        String message = "WRTA " + stopNum;

        //check condition if string is empty or not

            SmsManager smsManager = SmsManager.getDefault();
            //send message
            smsManager.sendTextMessage(phone,null,message,null, null);
            //display Toast msg
            Toast.makeText(this,"SMS sent successfully", Toast.LENGTH_SHORT).show();
            Intent test = new Intent(StopsShow.this, SMSReceive.class);
            test.putExtra("code",stopCode);
            startActivity(test);



    }

}
