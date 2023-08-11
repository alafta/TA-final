package com.example.projectfrontend.classKontradiksiMatriks;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.projectfrontend.R;
import com.example.projectfrontend.classPrinsipalSolusi.PenjelasanPrinsipalSolusi;
import com.example.projectfrontend.classPrinsipalSolusi.PrinsipalSolusi;
import com.example.projectfrontend.classPrinsipalSolusi.penjelasanprinsipalActivity;
import com.example.projectfrontend.database.DatabaseHelper;

import java.util.ArrayList;


public class kontradiksimatriksActivity extends AppCompatActivity implements RecyclerViewInterfaceCm{
    DatabaseHelper myDB;
    ArrayList<Parameter> improveP;
    ArrayList<Parameter> worseningP;
    ArrayList<PrinsipalSolusi> listPrinsipalSolusi;
    ArrayList<PenjelasanPrinsipalSolusi> listPenjelasanPrinsipalSolusi;
    int selectedImproveId = -1;
    int selectedWorseningId = -1;
    ArrayList<Integer> idPs;
    AdapterCm adapter;
    Boolean start = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.halaman_kontradiksi);
        Spinner myspinner2 = findViewById(R.id.spinner2);
        Spinner myspinner1 = findViewById(R.id.spinner1);

        listPrinsipalSolusi = new ArrayList<>();
        listPenjelasanPrinsipalSolusi = new ArrayList<>();
        idPs = new ArrayList<>();

        myDB = new DatabaseHelper(kontradiksimatriksActivity.this);
        improveP = new ArrayList<>();
        worseningP = new ArrayList<>();

        displayData();

        ArrayList<String> listParameterName = new ArrayList<>();
        listParameterName.add("choose the parameter");
        for (Parameter p : improveP) {
            listParameterName.add(String.valueOf(p.getId()) + ". " + p.getNama());
        }

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<>(kontradiksimatriksActivity.this, R.layout.spinner_layout, listParameterName);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(kontradiksimatriksActivity.this, R.layout.spinner_layout, listParameterName);
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner1.setAdapter(myAdapter1);
        myspinner2.setAdapter(myAdapter2);

        myspinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);
                if (i > 0) {
                    start = false;
                    for (Parameter p : improveP) {
                        if (p.getId() == improveP.get(i-1).getId()) {
                            selectedImproveId = p.getId();
                            if (!listPenjelasanPrinsipalSolusi.isEmpty()) {
                                listPenjelasanPrinsipalSolusi.clear();
                            }
                            getKontradiksi();
                        }
                    }
                } else {
                    if (!start) {
                        selectedImproveId = -1;
                        getKontradiksi();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        myspinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);
                if (i > 0) {
                    start = false;
                    for (Parameter p : improveP) {
                        if (p.getId() == improveP.get(i - 1).getId()) {
                            selectedWorseningId = p.getId();
                            if (!listPenjelasanPrinsipalSolusi.isEmpty()) {
                                listPenjelasanPrinsipalSolusi.clear();
                            }
                            getKontradiksi();
                        }
                    }
                } else {
                    if (!start) {
                        selectedWorseningId = -1;
                        getKontradiksi();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    void displayData() {
        Cursor cursor = myDB.readDataTableP();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                Parameter p = new Parameter(
                        cursor.getInt(0),
                        cursor.getString(1)
                );
                improveP.add(p);
                worseningP.add(p);
            }
        }
    }

    void getKontradiksi() {
        if (!idPs.isEmpty()) idPs.clear();
        Cursor cursor = myDB.readKontradiksi(selectedImproveId, selectedWorseningId);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Solution not available", Toast.LENGTH_SHORT).show();
            RecyclerView recyclerView;
            recyclerView = findViewById(R.id.recycleViewhasilCm);
            TextView outputSolusi = findViewById(R.id.textrecycle);
            outputSolusi.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            RecyclerView recyclerView;
            recyclerView = findViewById(R.id.recycleViewhasilCm);
            TextView outputSolusi = findViewById(R.id.textrecycle);
            outputSolusi.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            while (cursor.moveToNext()) {
                idPs.add(cursor.getInt(3));
            }

            getDataPs();
        }
    }

    void getDataPs() {
        if (!listPrinsipalSolusi.isEmpty()) listPrinsipalSolusi.clear();
        for (int i : idPs) {
            Cursor cursor = myDB.readDataTablePsById(i);
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    PrinsipalSolusi ps = new PrinsipalSolusi(
                            cursor.getInt(0),
                            cursor.getString(2),
                            cursor.getString(1)
                    );
                    listPrinsipalSolusi.add(ps);
                }
            }
        }
        if (!listPrinsipalSolusi.isEmpty()) {
            for (PrinsipalSolusi p : listPrinsipalSolusi) {
                getPenjelasanPs(p.getId());
            }
        }
    }

    void getPenjelasanPs(int idPs) {
        //if (!listPenjelasanPrinsipalSolusi.isEmpty()) listPenjelasanPrinsipalSolusi.clear();
        Cursor cursor = myDB.readPenjelasanPsById(idPs);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                PenjelasanPrinsipalSolusi ps = new PenjelasanPrinsipalSolusi(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3)
                );
                listPenjelasanPrinsipalSolusi.add(ps);
            }
        }


        if (!listPrinsipalSolusi.isEmpty() && !listPenjelasanPrinsipalSolusi.isEmpty()) {
            adapter = new AdapterCm(
                    kontradiksimatriksActivity.this, listPrinsipalSolusi, listPenjelasanPrinsipalSolusi,this);

            RecyclerView recyclerView;
            recyclerView = findViewById(R.id.recycleViewhasilCm);

            recyclerView.setLayoutManager(new LinearLayoutManager(kontradiksimatriksActivity.this));
            recyclerView.setAdapter(adapter);



        }

    }

    @Override
    public void onItemClick(int id, String nama) {
        Log.d("item", "onItemClick: ");
        Intent intent = new Intent(kontradiksimatriksActivity.this, penjelasanprinsipalActivity.class);
        intent.putExtra("nama", nama);
        intent.putExtra("id", id);

        startActivity(intent);
    }
}