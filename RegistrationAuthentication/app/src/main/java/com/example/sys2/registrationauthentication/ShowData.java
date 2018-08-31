package com.example.sys2.registrationauthentication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Map;

public class ShowData extends AppCompatActivity {
    ListView rcv;
    DatabaseHandler db ;
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
        rcv = findViewById(R.id.recyclerview);
       db = new DatabaseHandler(getApplicationContext());

        arrayList = db.getDataFromDatabase();

        rcv.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList));

        rcv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = (String) rcv.getItemAtPosition(i);
                Intent in = new Intent(ShowData.this,Display.class);
                in.putExtra("name",str);
                Log.e("str","value");
                startActivity(in);
            }
        });
    }
}
