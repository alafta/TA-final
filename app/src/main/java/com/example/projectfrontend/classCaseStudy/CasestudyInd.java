package com.example.projectfrontend.classCaseStudy;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfrontend.R;

import java.util.ArrayList;

import com.example.projectfrontend.database.DatabaseHelper;


public class CasestudyInd extends AppCompatActivity implements RecyclerViewInterfaceCs{

    RecyclerView recyclerView;
    DatabaseHelper myDB;
    ArrayList<String> title_cs, problem_cs,worsening_cs,improving_cs, solution_cs;
    adapterRecycleCs adapterRecycleCs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_casestudy);

        TextView judulCs = findViewById(R.id.judultlcs);
        judulCs.setText("Studi Kasus");

        recyclerView = findViewById(R.id.recycleViewcs);

        myDB = new DatabaseHelper(CasestudyInd.this);
        title_cs=new ArrayList<>();
        problem_cs=new ArrayList<>();
        worsening_cs=new ArrayList<>();
        improving_cs=new ArrayList<>();
        solution_cs= new ArrayList<>();

        displayData();

        adapterRecycleCs = new adapterRecycleCs(CasestudyInd.this,title_cs,problem_cs,worsening_cs,improving_cs,solution_cs, this );
        recyclerView.setAdapter(adapterRecycleCs);
        recyclerView.setLayoutManager(new LinearLayoutManager(CasestudyInd.this));
    }

    void displayData(){
        Cursor cursor = myDB.readDataTableCs();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"no data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                title_cs.add(cursor.getString(2));
                problem_cs.add(cursor.getString(4));
                worsening_cs.add(cursor.getString(8));
                improving_cs.add(cursor.getString(6));
                solution_cs.add(cursor.getString(10));
            }
        }
    }

    @Override
    public void onItemClick(String nama,  String problem, String worsening, String improving, String solution) {
        Intent intent = new Intent(CasestudyInd.this, penjelasan_cs_ind.class);
        intent.putExtra("title_cs", nama);
        intent.putExtra("problem_cs", problem);
        intent.putExtra("worsening_cs", worsening);
        intent.putExtra("improving_cs", improving);
        intent.putExtra("solution_cs", solution);

        startActivity(intent);
    }
}