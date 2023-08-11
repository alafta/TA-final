package com.example.projectfrontend.HomeActivityNew;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
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
import com.example.projectfrontend.classPrinsipalSolusi.PenjelasanpsInd_activity;
import com.example.projectfrontend.classPrinsipalSolusi.PrinsipalSolusi;
import com.example.projectfrontend.database.DatabaseHelper;

import java.util.ArrayList;

public class kontradiksimatriks_ind2 extends AppCompatActivity implements RecyclerViewInterfaceCm2 {
    DatabaseHelper myDB;
    ArrayList<Parameter> improveP;
    ArrayList<Parameter> worseningP;
    ArrayList<PrinsipalSolusi> listPrinsipalSolusi;
    ArrayList<PenjelasanPrinsipalSolusi> listPenjelasanPrinsipalSolusi;
    int selectedImproveId = -1;
    int selectedWorseningId = -1;
    ArrayList<Integer> idPs;
    AdapterCmind2 adapter;
    Boolean start = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.halaman_kontradiksi);
        Spinner myspinner2 = findViewById(R.id.spinner2);
        Spinner myspinner1 = findViewById(R.id.spinner1);
        TextView judulcm = findViewById(R.id.judulwhatistriz);
        TextView setcontradiction = findViewById(R.id.textsetcontradiction);
        TextView improving = findViewById(R.id.textimprove);
        TextView worsening = findViewById(R.id.textworsening);
        TextView result = findViewById(R.id.textresult);
        TextView outputsolusi = findViewById(R.id.textrecycle);

        judulcm.setText("Matriks Kontradiksi");
        setcontradiction.setText("Menentukan Kontradiksi");
        improving.setText("Parameter yang ditingkatkan (Improving)");
        worsening.setText("Parameter hambatan (Worsening)");
        result.setText("Solusi yang tersedia");
        outputsolusi.setText("Solusi tidak tersedia");

        listPrinsipalSolusi = new ArrayList<>();
        listPenjelasanPrinsipalSolusi = new ArrayList<>();
        idPs = new ArrayList<>();

        myDB = new DatabaseHelper(kontradiksimatriks_ind2.this);
        improveP = new ArrayList<>();
        worseningP = new ArrayList<>();

        displayData();

        ArrayList<String> listParameterName = new ArrayList<>();
        listParameterName.add("pilih parameternya");
        for (Parameter p : improveP) {
            listParameterName.add(String.valueOf(p.getId()) + ". " + p.getNama());
        }

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<>(kontradiksimatriks_ind2.this, R.layout.spinner_layout, listParameterName);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(kontradiksimatriks_ind2.this, R.layout.spinner_layout, listParameterName);
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
                        cursor.getString(2)
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
            Toast.makeText(this, "Solusi tidak tersedia", Toast.LENGTH_SHORT).show();
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
                            cursor.getString(1),
                            cursor.getString(2)
                    );
                    listPrinsipalSolusi.add(ps);
                }
            }
        }
        if (!listPrinsipalSolusi.isEmpty()) {
            for (PrinsipalSolusi p : listPrinsipalSolusi) {
                Log.e("ADKW", String.valueOf(listPrinsipalSolusi.size()));
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
                        cursor.getString(2),
                        cursor.getString(1),
                        cursor.getInt(3)
                );
                listPenjelasanPrinsipalSolusi.add(ps);
            }
        }

        for (PenjelasanPrinsipalSolusi p : listPenjelasanPrinsipalSolusi) {
            Log.e("HJM", String.valueOf(listPenjelasanPrinsipalSolusi.size()));
        }

        if (!listPrinsipalSolusi.isEmpty() && !listPenjelasanPrinsipalSolusi.isEmpty()) {
            adapter = new AdapterCmind2(
                    kontradiksimatriks_ind2.this, listPrinsipalSolusi, listPenjelasanPrinsipalSolusi, this);

            RecyclerView recyclerView;
            recyclerView = findViewById(R.id.recycleViewhasilCm);

            recyclerView.setLayoutManager(new LinearLayoutManager(kontradiksimatriks_ind2.this));
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void onItemClick(int id, String nama) {
        Log.d("item", "onItemClick: ");
        Intent intent = new Intent(kontradiksimatriks_ind2.this, PenjelasanpsInd_activity.class);
        intent.putExtra("nama", nama);
        intent.putExtra("id", id);

        startActivity(intent);
    }
}
