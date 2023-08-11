package com.example.projectfrontend.classPrinsipalSolusi;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfrontend.R;
import com.example.projectfrontend.database.DatabaseHelper;

import java.util.ArrayList;


public class penjelasanprinsipalActivity extends AppCompatActivity{
    DatabaseHelper myDB;
    ArrayList<String> penjelasanIng;
    ArrayList<String> ilustrasiIng;
    adapterlist adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar();
        setContentView(R.layout.halaman_penjelasanps);
        myDB = new DatabaseHelper(this);
        penjelasanIng = new ArrayList<>();
        ilustrasiIng = new ArrayList<>();

        String namaJudulPs = getIntent().getStringExtra("nama");
        int idPs =  getIntent().getIntExtra("id", 0);

        Button judulPenjelasanPs = findViewById(R.id.kotakjudulps);
        Button nomorPenjelasanPs = findViewById(R.id.buletpenjelasanps);

        RecyclerView lvpenjelasansolusi = findViewById(R.id.lvpenjelasanps);
        lvpenjelasansolusi.setLayoutManager(new LinearLayoutManager(this));
        adapter = new adapterlist(this, penjelasanIng);
        lvpenjelasansolusi.setAdapter(adapter);

        RecyclerView lvpenjelasanilustrasi = findViewById(R.id.lvilustrasips);
        lvpenjelasanilustrasi.setLayoutManager(new LinearLayoutManager(this));
        adapter = new adapterlist(this,ilustrasiIng);
        lvpenjelasanilustrasi.setAdapter(adapter);


        judulPenjelasanPs.setText(namaJudulPs);
        judulPenjelasanPs.setTextSize(12F);
        nomorPenjelasanPs.setText(String.valueOf(idPs));

        readData(idPs);

    }

    private void readData(int id) {
        Cursor cursor = myDB.readPenjelasanPsById(id);
        Cursor cursor2 = myDB.readIlustrasiPsById(id);
        if(cursor.getCount() == 0){
            Toast.makeText(this,"no data", Toast.LENGTH_SHORT).show();
        }if(cursor2.getCount()==0){
            Toast.makeText(this,"no data", Toast.LENGTH_SHORT).show();
        }
        else{
            while (cursor.moveToNext()){
                penjelasanIng.add(cursor.getString(1));
            }
            while (cursor2.moveToNext()){
                ilustrasiIng.add(cursor2.getString(1));
            }
        }
    }

}