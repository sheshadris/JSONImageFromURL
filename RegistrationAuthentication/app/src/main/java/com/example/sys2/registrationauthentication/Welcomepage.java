package com.example.sys2.registrationauthentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Welcomepage extends AppCompatActivity {
TextView textView;
Button getdetails;
String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcomepage);
        textView = findViewById(R.id.textView);
        getdetails = findViewById(R.id.button);
        str = getIntent().getStringExtra(MainActivity.NAME);
        textView.setText(str);
        getdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Welcomepage.this,ShowData.class);
                startActivity(intent);
            }
        });
    }
}
