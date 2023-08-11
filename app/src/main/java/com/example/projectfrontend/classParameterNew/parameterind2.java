package com.example.projectfrontend.classParameterNew;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfrontend.R;
import com.example.projectfrontend.database.DatabaseHelper;

import java.util.ArrayList;


public class parameterind2 extends AppCompatActivity implements RecyclerViewInterfaceP2 {

    RecyclerView recyclerView;
    DatabaseHelper myDB;
    ArrayList<String> id_p, nama_p, meaningP, saeP;
    adapterRecycleViewp2 adapterRecycleViewp2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_parameters);
        TextView judulParameter = findViewById(R.id.judul39p);
        judulParameter.setText(R.string.ptrizind);

        SearchView searchviewp = findViewById(R.id.searchbuttonp);
        searchviewp.setQueryHint("Cari");

        recyclerView = findViewById(R.id.recycleViewp);

        myDB = new DatabaseHelper(parameterind2.this);
        id_p = new ArrayList<>();
        nama_p = new ArrayList<>();
        meaningP = new ArrayList<>();
        saeP = new ArrayList<>();

        displayData();

        adapterRecycleViewp2 = new adapterRecycleViewp2(parameterind2.this, id_p, nama_p,meaningP,saeP, this);
        recyclerView.setAdapter(adapterRecycleViewp2);
        recyclerView.setLayoutManager(new LinearLayoutManager(parameterind2.this));

        searchviewp.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                Log.e("hgh",newText);
                return false;
            }
        });
    }

    void displayData(){
        Cursor cursor = myDB.readDataTableP();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"no data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id_p.add(cursor.getString(0));
                nama_p.add(cursor.getString(2));
                meaningP.add(cursor.getString(4));
                saeP.add(cursor.getString(6));
            }
        }
    }
    private void filter(String text) {
        ArrayList<String> filteredList = new ArrayList<>();
        ArrayList<String> filteredListId = new ArrayList<>();
        ArrayList<String> filterm = new ArrayList<>();
        ArrayList<String> filters = new ArrayList<>();

        int i = 0;
        for (String item : nama_p) {
            if (item.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
                filteredListId.add(id_p.get(i));
                filterm.add(meaningP.get(i));
                filters.add(saeP.get(i));
                Log.e("data : ", "add");

            }
            i++;
        }
        adapterRecycleViewp2.filterList(filteredList, filteredListId, filterm,filters);
    }

    @Override
    public void onItemClick(int id, String nama, String meaning, String sae) {
        Intent intent = new Intent(parameterind2.this, penjelasanparameterind2.class);
        intent.putExtra("id_p",id);
        intent.putExtra("nama", nama);
        intent.putExtra("meaning", meaning);
        intent.putExtra("sae", sae);


        startActivity(intent);
    }
}
