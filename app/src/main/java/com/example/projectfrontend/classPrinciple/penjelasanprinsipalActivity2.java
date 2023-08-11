package com.example.projectfrontend.classPrinciple;

import android.content.SharedPreferences;
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


public class penjelasanprinsipalActivity2 extends AppCompatActivity{
    DatabaseHelper myDB;
    ArrayList<String> penjelasanIng;
    ArrayList<String> ilustrasiIng;
    adapterlist2 adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar();
        setContentView(R.layout.principles_desc);
        myDB = new DatabaseHelper(this);
        penjelasanIng = new ArrayList<>();
        ilustrasiIng = new ArrayList<>();

        String namaJudulPs = getIntent().getStringExtra("nama");
        int idPs =  getIntent().getIntExtra("id", 0);

        Button judulPenjelasanPs = findViewById(R.id.judulps);
        Button nomorPenjelasanPs = findViewById(R.id.numberps);

        RecyclerView lvpenjelasansolusi = findViewById(R.id.solutionR);
        lvpenjelasansolusi.setLayoutManager(new LinearLayoutManager(this));
        adapter = new adapterlist2(this, penjelasanIng);
        lvpenjelasansolusi.setAdapter(adapter);

        RecyclerView lvpenjelasanilustrasi = findViewById(R.id.illuR);
        lvpenjelasanilustrasi.setLayoutManager(new LinearLayoutManager(this));
        adapter = new adapterlist2(this,ilustrasiIng);
        lvpenjelasanilustrasi.setAdapter(adapter);


        judulPenjelasanPs.setText(namaJudulPs);
        nomorPenjelasanPs.setText(String.valueOf(idPs));

        readData(idPs);

    }

    private void readData(int id) {
        Cursor cursor = myDB.readPenjelasanPsById(id);
        Cursor cursor2 = myDB.readIlustrasiPsById(id);
        String currentLang = getCurrentLocale();

        if (currentLang.equals("en")) {
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
            } //sampai sini ya ges
        }else if (currentLang.equals("in")) {
            if(cursor.getCount() == 0){
                Toast.makeText(this,"no data", Toast.LENGTH_SHORT).show();
            }if(cursor2.getCount()==0){
                Toast.makeText(this,"no data", Toast.LENGTH_SHORT).show();
            }
            else{
                while (cursor.moveToNext()){
                    penjelasanIng.add(cursor.getString(2));
                }
                while (cursor2.moveToNext()){
                    ilustrasiIng.add(cursor2.getString(2));
                }
            } //sampai sini ya ges
        }
    }

    private String getCurrentLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        return prefs.getString("My_Lang", "en");
    }

}