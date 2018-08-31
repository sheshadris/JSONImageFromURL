package com.example.sys2.registrationauthentication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

import static android.Manifest.permission.CALL_PHONE;
import static android.icu.lang.UProperty.NAME;
import static com.example.sys2.registrationauthentication.DatabaseHandler.MAIL;
import static com.example.sys2.registrationauthentication.DatabaseHandler.PASSWORD;
import static com.example.sys2.registrationauthentication.DatabaseHandler.PHONENUMBER;

public class Display extends AppCompatActivity {
    TextView textname, nametext, textemail, mail, textpass, pass, textphone, phonetext;
    ImageView btncall, btnmail;
    DatabaseHandler db ;
    ArrayList<Map<String, String>> arrayListData = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        textname = findViewById(R.id.textname);
        nametext = findViewById(R.id.name);
        textemail = findViewById(R.id.textemail);
        mail = findViewById(R.id.email);
        textpass = findViewById(R.id.textpass);
        pass = findViewById(R.id.pass);
        textphone = findViewById(R.id.textphone);
        phonetext = findViewById(R.id.phone);
        btncall = findViewById(R.id.imageViewcall);
        btnmail = findViewById(R.id.imageViewmail);
        db = new DatabaseHandler(this);
        String name = getIntent().getStringExtra("name");
        arrayListData = db.getDataFromDatabaseToShow(name);

        nametext.setText(name);
        mail.setText(arrayListData.get(0).get(MAIL));
        pass.setText(arrayListData.get(0).get(PASSWORD));
        phonetext.setText(arrayListData.get(0).get(PHONENUMBER));
        final String phonenumber = String.format("tel:%s",phonetext.getText().toString());

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkPermission()) {
                    Intent in = new Intent(Intent.ACTION_CALL);
                    in.setData(Uri.parse(phonenumber));

                    startActivity(in);
                }
                else {
                    requestPermission();
                    Toast.makeText(Display.this, "Access Denied", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmail();
            }
        });
    }
    private boolean checkPermission() {
        int FirstPermissionResult = ContextCompat.checkSelfPermission(Display.this, CALL_PHONE);
      //  int sec = ContextCompat.checkSelfPermission(FragmentsActivity.this, CAMERA);
        if(FirstPermissionResult == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {


        ActivityCompat.requestPermissions(Display.this, new String[]
                {CALL_PHONE, Manifest.permission.CAMERA}, 100);
    }
    protected void sendEmail() {
        Log.i("Send email", "");
      //  String TO = String.format(mail.getText().toString());
        String[] TO = {mail.getText().toString().trim()};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "This mail is from RegistrationAuthentication App ");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello, Thanks for installing");
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
         //   Log.i("Finished sending email...", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(Display.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
