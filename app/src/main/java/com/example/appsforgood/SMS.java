package com.example.appsforgood;

import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.appsforgood.databinding.ActivitySmsBinding;

public class SMS extends AppCompatActivity {

    //initialize variable
    EditText editTextPhone,editTextMessage;
    Button btnSent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        //assign variable
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextMessage = findViewById(R.id.editTextMessage);
        btnSent = findViewById(R.id.btnSent);

        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ContextCompat.checkSelfPermission(SMS.this, android.Manifest.permission.SEND_SMS)
                        == PackageManager.PERMISSION_GRANTED) {
                    sendSMS();
                } else {
                    ActivityCompat.requestPermissions(SMS.this, new String[]{android.Manifest.permission.SEND_SMS}, 100);
                }
            }
        });


        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        //check condition
        if (requestCode==100 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            sendSMS();
        }
        else {
            //when permission is denied
            Toast.makeText(this,"Permission Denied!", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendSMS(){
        //get value form editText
        String phone = editTextPhone.getText().toString();
        String message = editTextMessage.getText().toString();

        //check condition if string is empty or not
        if (!phone.isEmpty() && !message.isEmpty()){
            SmsManager smsManager = SmsManager.getDefault();
            //send message
            smsManager.sendTextMessage(phone,null,message,null, null);
            //display Toast msg
            Toast.makeText(this,"SMS sent successfully", Toast.LENGTH_SHORT).show();
        } else {
            //when string is empty then display toast msg
            Toast.makeText(this,"Please enter phone and message", Toast.LENGTH_SHORT).show();
        }
    }

}