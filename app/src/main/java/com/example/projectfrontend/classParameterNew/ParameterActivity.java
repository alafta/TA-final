package com.example.projectfrontend.classParameterNew;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfrontend.HomeActivityNew.Home2Activity;
import com.example.projectfrontend.R;
import com.example.projectfrontend.TrizActivity;
import com.example.projectfrontend.classBAM.BamActivity;
import com.example.projectfrontend.classContradiction.ContramatrixActivity;
import com.example.projectfrontend.classPrinciple.PrinciplesActivity;
import com.example.projectfrontend.classUnderstanding.TrizUnderstanding;
import com.example.projectfrontend.database.DatabaseHelper;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class ParameterActivity extends AppCompatActivity implements RecyclerViewInterfaceP2 {

    RecyclerView recyclerView;
    adapterRecycleViewp2 adapterRecycleViewp2;
    DatabaseHelper myDB;
    ArrayList<String> id_p, nama_p, meaningP, saeP;
    ImageButton menuButton;
    DrawerLayout drawerLayout;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parameter);
        drawerLayout = findViewById(R.id.drawer_param);
        navView = findViewById(R.id.nav_view);

        SearchView searchviewp = findViewById(R.id.searcbar);
        searchviewp.setQueryHint("Search");

        recyclerView = findViewById(R.id.recycleViewp2);

        myDB = new DatabaseHelper(ParameterActivity.this);
        id_p = new ArrayList<>();
        nama_p = new ArrayList<>();
        meaningP = new ArrayList<>();
        saeP = new ArrayList<>();

        displayData();

        adapterRecycleViewp2 = new adapterRecycleViewp2(ParameterActivity.this, id_p, nama_p,meaningP,saeP, (RecyclerViewInterfaceP2) this);
        recyclerView.setAdapter(adapterRecycleViewp2);
        recyclerView.setLayoutManager(new LinearLayoutManager(ParameterActivity.this));

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

        menuButton = (ImageButton) findViewById(R.id.menu_param);
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
                        Intent home = new Intent(ParameterActivity.this, Home2Activity.class);
                        startActivity(home);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_undr:
                        Intent under = new Intent(ParameterActivity.this, ParameterActivity.class);
                        startActivity(under);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_what:
                        Intent intent = new Intent(ParameterActivity.this, TrizActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_bussi:
                        Intent bussi = new Intent(ParameterActivity.this, BamActivity.class);
                        startActivity(bussi);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_param:
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_princ:
                        Intent prince = new Intent(ParameterActivity.this, PrinciplesActivity.class);
                        startActivity(prince);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_cont:
                        Intent matrix = new Intent(ParameterActivity.this, ContramatrixActivity.class);
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

    void displayData() {
        Cursor cursor = myDB.readDataTableP();
        String currentLang = getCurrentLocale();

        if (currentLang.equals("en")) {
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    id_p.add(cursor.getString(0));
                    nama_p.add(cursor.getString(1));
                    meaningP.add(cursor.getString(3));
                    saeP.add(cursor.getString(5));
                }
            }
        } else if (currentLang.equals("in")) {
            if (cursor.getCount() == 0) {
                Toast.makeText(this, "no data", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    id_p.add(cursor.getString(0));
                    nama_p.add(cursor.getString(2)); // Changed index to 2
                    meaningP.add(cursor.getString(4)); // Changed index to 4
                    saeP.add(cursor.getString(6)); // Changed index to 6
                }
            }
        }
    }

    private String getCurrentLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        return prefs.getString("My_Lang", "en"); // Default to "en" if not set
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
        Intent intent = new Intent(ParameterActivity.this, penjelasanparameterActivity2.class);
        intent.putExtra("id_p",id);
        intent.putExtra("nama", nama);
        intent.putExtra("meaning", meaning);
        intent.putExtra("sae", sae);


        startActivity(intent);

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


}