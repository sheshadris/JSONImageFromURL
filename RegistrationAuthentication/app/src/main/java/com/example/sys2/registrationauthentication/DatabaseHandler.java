package com.example.sys2.registrationauthentication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "App_Data";
    public static final String NAME = "username";
    public static final String PASSWORD = "password";
    public static final String MAIL = "mail";
    public static final String PHONENUMBER = "phone";


    //Table nama

    public DatabaseHandler(Context context) {

        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists registration(username varchar(50), password varchar(50), mail varchar(50),phone varchar(10))");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public int login(String username, String pass) {
        int count = 0;
        SQLiteDatabase db1 = getReadableDatabase();
        Cursor cursor = db1.rawQuery("select * from registration where username ='" + username + "' and password='" + pass + "'", null);
        Log.e("cursor", "moved");
        cursor.moveToFirst();
//		String get=null;

        if (cursor != null) {
            do {
//
                count = cursor.getCount();

            } while (cursor.moveToNext());
        }
        Log.e(String.valueOf(count), "value");
        return count;
    }

    public void insert(String username, String pass, String mail, String phonenumber) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL("insert into registration values('" + username + "','" + pass + "','" + mail + "','" + phonenumber + "')");
        Log.e("database", "done");
    }

    public ArrayList<String> getDataFromDatabase() {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        ArrayList<String> data = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM registration", null);
        if (cursor != null && cursor.moveToFirst()) {

            while (!cursor.isAfterLast()) {

                int c1 = cursor.getColumnIndex(NAME);
//                map.put("PASSWORD", cursor.getString(cursor.getColumnIndex(PASSWORD)));
//                map.put("MAIL", cursor.getString(cursor.getColumnIndex(MAIL)));
//                map.put("PHONENUMBER", cursor.getString(cursor.getColumnIndex(PHONENUMBER)));
                String s = cursor.getString(c1);
                data.add(s);
                cursor.moveToNext();
            }
        }
        return data;
    }

    public ArrayList<Map<String, String>> getDataFromDatabaseToShow(String name) {
        SQLiteDatabase db1 = getReadableDatabase();
        ArrayList<Map<String, String>> data = new ArrayList<>();
        Cursor cur = db1.rawQuery("select * from registration where username ='" + name + "'", null);
        cur.moveToFirst();
        if (cur != null && cur.moveToFirst()) {
            while (!cur.isAfterLast()) {
                Map<String, String> map = new HashMap<>();
              map.put(NAME, cur.getString(cur.getColumnIndex(NAME)));
                map.put(PASSWORD, cur.getString(cur.getColumnIndex(PASSWORD)));
                map.put(MAIL, cur.getString(cur.getColumnIndex(MAIL)));
               map.put(PHONENUMBER, cur.getString(cur.getColumnIndex(PHONENUMBER)));
                data.add(map);
                cur.moveToNext();

                data.add(map);
                cur.moveToNext();
            }
        }
            return data;

    }

}

