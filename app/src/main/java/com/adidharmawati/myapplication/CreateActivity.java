package com.adidharmawati.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateActivity extends AppCompatActivity {

    protected Cursor cursor;
    Database database;
    Button btnSimpan;
    EditText etNim, etNama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        database = new Database(this);
        etNim = findViewById(R.id.et_nim);
        etNama = findViewById(R.id.et_nama);
        btnSimpan = findViewById(R.id.btn_simpan);

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db = database.getWritableDatabase();
                db.execSQL("INSERT INTO mahasiswa(nim,nama) VALUES('"+etNim.getText().toString()+"', '"+etNama.getText().toString()+"')");
                Toast.makeText(CreateActivity.this,"Data Tersimpan",Toast.LENGTH_SHORT).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}