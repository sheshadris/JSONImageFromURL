package com.example.sys2.registrationauthentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String username,pass,mail,phonenumber;
    TextView txt_username,txt_password,textdisplay;
    EditText edtusername,edtpassword;
    Button btnsignin,btnsignup;
    DatabaseHandler databaseHandler;
    public static final String NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_username = findViewById(R.id.username_txt);
        txt_password = findViewById(R.id.password_txt);
        textdisplay = findViewById(R.id.text);
        edtusername = findViewById(R.id.edt_username);
        edtpassword = findViewById(R.id.edt_password);
        btnsignin = findViewById(R.id.btn_signin);
        btnsignup = findViewById(R.id.btn_signup);

        databaseHandler = new DatabaseHandler(this);

        btnsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = 0;
                username = edtusername.getText().toString();
                pass = edtpassword.getText().toString();
                count =   databaseHandler.login(username,pass);
                if (count != 0) {
                    Toast.makeText(getApplicationContext(), "Successfully Logged in!", Toast.LENGTH_SHORT).show();

                    //User Logged in Successfully Launch You home screen activity
                    Intent intent=new Intent(MainActivity.this,Welcomepage.class);
                    intent.putExtra(NAME,username);
                    startActivity(intent);
                    finish();
                } else {

                    //User Logged in Failed
                    Toast.makeText(MainActivity.this, "Failed to log in , please try again", Toast.LENGTH_LONG).show();

                }
            }
        });

        btnsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,RegistrationPage.class);
                startActivity(intent);
            }
        });
    }


  }
