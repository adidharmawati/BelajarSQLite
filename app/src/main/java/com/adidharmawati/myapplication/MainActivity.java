package com.adidharmawati.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    String[] list;
    ListView lvMahasiswa;
    Menu menu;
    protected Cursor cursor;
    Database database;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabTambah = findViewById(R.id.fab_tambah);
        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tambah = new Intent(MainActivity.this, CreateActivity.class);
                startActivity(tambah);
            }
        });

        ma = this;
        database = new Database(this);
        RefreshList();
    }

    public void RefreshList() {
        SQLiteDatabase db = database.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM mahasiswa",null);
        list = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int i = 0; i<cursor.getCount(); i++){
            cursor.moveToPosition(i);
            list[i] = cursor.getString(1).toString();
        }

        lvMahasiswa = (ListView) findViewById(R.id.lv_mahasiswa);
        lvMahasiswa.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1,list));
        lvMahasiswa.setSelected(true);

    }
}