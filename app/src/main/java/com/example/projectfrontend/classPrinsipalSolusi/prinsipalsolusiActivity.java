package com.example.projectfrontend.classPrinsipalSolusi;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfrontend.R;
import com.example.projectfrontend.database.DatabaseHelper;

import java.util.ArrayList;


public class prinsipalsolusiActivity extends AppCompatActivity implements RecyclerViewInterfacePs {

    RecyclerView recyclerView;
    DatabaseHelper myDB;
    ArrayList<String> id_ps, nama_ingPs;
    adapterRecycleViewps adapterRecycleViewps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_prinsipalsolusi);

        recyclerView = findViewById(R.id.recycleViewps);
        SearchView searchviewps = findViewById(R.id.searchbuttonps);
        searchviewps.setQueryHint("Search");

        myDB = new DatabaseHelper(prinsipalsolusiActivity.this);
        id_ps = new ArrayList<>();
        nama_ingPs = new ArrayList<>();

        displayData();

        adapterRecycleViewps = new adapterRecycleViewps(prinsipalsolusiActivity.this, id_ps, nama_ingPs, this);
        recyclerView.setAdapter(adapterRecycleViewps);
        recyclerView.setLayoutManager(new GridLayoutManager(prinsipalsolusiActivity.this,4));

        searchviewps.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

    private void filter(String text) {
        ArrayList<String> filteredList = new ArrayList<>();
        ArrayList<String> filteredListId = new ArrayList<>();

        int i = 0;
        for (String item : nama_ingPs) {
            if (item.toLowerCase().contains(text.toLowerCase())) {
                filteredList.add(item);
                filteredListId.add(id_ps.get(i));
                Log.e("data : ", "add");

            }
            i++;
        }
        adapterRecycleViewps.filterList(filteredList, filteredListId);
    }

    void displayData(){
        Cursor cursor = myDB.readDataTablePs();
        if(cursor.getCount() == 0){
            Toast.makeText(this,"no data", Toast.LENGTH_SHORT).show();
        }else{
            while (cursor.moveToNext()){
                id_ps.add(cursor.getString(0));
                nama_ingPs.add(cursor.getString(1));
            }
        }

    }

    @Override

    public void onItemClick(int id, String nama) {
        Intent intent = new Intent(prinsipalsolusiActivity.this, penjelasanprinsipalActivity.class);
        intent.putExtra("nama", nama);
        intent.putExtra("id", id);


        startActivity(intent);
    }

}
