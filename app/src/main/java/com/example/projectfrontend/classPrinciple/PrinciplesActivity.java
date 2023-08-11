package com.example.projectfrontend.classPrinciple;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfrontend.HomeActivityNew.Home2Activity;
import com.example.projectfrontend.R;
import com.example.projectfrontend.TrizActivity;
import com.example.projectfrontend.classBAM.BamActivity;
import com.example.projectfrontend.classContradiction.ContramatrixActivity;
import com.example.projectfrontend.classParameterNew.ParameterActivity;
import com.example.projectfrontend.classUnderstanding.TrizUnderstanding;
import com.example.projectfrontend.database.DatabaseHelper;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class PrinciplesActivity extends AppCompatActivity implements RecyclerViewInterfacePs2 {
    RecyclerView recyclerView;
    DatabaseHelper myDB;
    ArrayList<String> id_ps, nama_Ps;
    adapterRecycleViewps2 adapterRecycleViewps2;
    /*-------nav------*/
    ImageButton menuButton;
    DrawerLayout drawerLayout;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principles);
        drawerLayout = findViewById(R.id.drawer_principles);
        navView = findViewById(R.id.nav_view);

        recyclerView = findViewById(R.id.recyclePrinciples);

        SearchView searchView = findViewById(R.id.searchbarps);
        searchView.setQueryHint("Search");

        myDB = new DatabaseHelper(PrinciplesActivity.this);
        id_ps = new ArrayList<>();
        nama_Ps = new ArrayList<>();

        displayData();

        adapterRecycleViewps2 = new adapterRecycleViewps2(PrinciplesActivity.this, id_ps, nama_Ps,this);
        recyclerView.setAdapter(adapterRecycleViewps2);
        recyclerView.setLayoutManager(new GridLayoutManager(PrinciplesActivity.this, 3));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                Log.e("hgh", newText);
                return false;
            }
        });

        menuButton = (ImageButton) findViewById(R.id.menu_princs);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(R.id.nav_drawer))
                    drawerLayout.closeDrawer(R.id.nav_drawer);
                else drawerLayout.openDrawer(Gravity.END);
            }
        });

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.nav_home:
                        Intent home = new Intent(PrinciplesActivity.this, Home2Activity.class);
                        startActivity(home);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_undr:
                        Intent under = new Intent(PrinciplesActivity.this, TrizUnderstanding.class);
                        startActivity(under);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_what:
                        Intent intent = new Intent(PrinciplesActivity.this, TrizActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_bussi:
                        Intent bussi = new Intent(PrinciplesActivity.this, BamActivity.class);
                        startActivity(bussi);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_param:
                        Intent param = new Intent(PrinciplesActivity.this, ParameterActivity.class);
                        startActivity(param);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_princ:
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_cont:
                        Intent matrix = new Intent(PrinciplesActivity.this, ContramatrixActivity.class);
                        startActivity(matrix);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
//                    case R.id.nav_languageEn:
//                        LocaleManager.setLocale(context, "en"); // English
//                        recreate(); // Refresh the activity
//                        return true;
//                    case R.id.nav_languageIn:
//                        LocaleManager.setLocale(context, "in"); // English
//                        recreate(); // Refresh the activity
//                        return true;
                }
                return true;
            }
        });
    }

    private void filter(String newText) {
        ArrayList<String> filteredList = new ArrayList<>();
        ArrayList<String> filteredListId = new ArrayList<>();

        int i = 0;
        for (String item : nama_Ps) {
            if (item.toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(item);
                filteredListId.add(id_ps.get(i));
                Log.e("data", "add");
            }
            i++;
        }
        adapterRecycleViewps2.filterList(filteredList, filteredListId);
    }

    void displayData() {
        Cursor cursor = myDB.readDataTablePs();
        String currentLang = getCurrentLocale();

        if (currentLang.equals("en")) {
            if(cursor.getCount() == 0) {
                Toast.makeText(this, "no data",Toast.LENGTH_SHORT).show();
            }else {
                while (cursor.moveToNext()) {
                    id_ps.add(cursor.getString(0));
                    nama_Ps.add(cursor.getString(1));
                }
            }
        }else if (currentLang.equals("in")) {
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
            }else {
                while (cursor.moveToNext()) {
                    id_ps.add(cursor.getString(0));
                    nama_Ps.add(cursor.getString(2));
                }
            }
        }
    }

    private  String getCurrentLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        return prefs.getString("My_Lang", "en");
    }

    @Override
    public void onItemClick(int id, String nama) {
        Intent intent = new Intent(PrinciplesActivity.this, penjelasanprinsipalActivity2.class);
        intent.putExtra("nama", nama);
        intent.putExtra("id", id);

        startActivity(intent);
    }
}