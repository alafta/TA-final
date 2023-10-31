package com.example.projectfrontend.classPrinciple;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
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

import com.example.projectfrontend.BaseActivity;
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
import java.util.Locale;

public class PrinciplesActivity extends BaseActivity implements BaseActivity.OnLanguageChangedListener, RecyclerViewInterfacePs2 {
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

        setOnLanguageChangedListener(this);

        SearchView searchView = findViewById(R.id.searchbarps);
        searchView.setQueryHint("Search");

        myDB = new DatabaseHelper(PrinciplesActivity.this);
        id_ps = new ArrayList<>();
        nama_Ps = new ArrayList<>();

        displayData();

        adapterRecycleViewps2 = new adapterRecycleViewps2(PrinciplesActivity.this, id_ps, nama_Ps,this);
        recyclerView.setAdapter(adapterRecycleViewps2);
        recyclerView.setLayoutManager(new GridLayoutManager(PrinciplesActivity.this, 4));

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
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_undr:
                        Intent under = new Intent(PrinciplesActivity.this, TrizUnderstanding.class);
                        startActivity(under);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_what:
                        Intent intent = new Intent(PrinciplesActivity.this, TrizActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_bussi:
                        Intent bussi = new Intent(PrinciplesActivity.this, BamActivity.class);
                        startActivity(bussi);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_param:
                        Intent param = new Intent(PrinciplesActivity.this, ParameterActivity.class);
                        startActivity(param);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_princ:
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_cont:
                        Intent matrix = new Intent(PrinciplesActivity.this, ContramatrixActivity.class);
                        startActivity(matrix);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_languageEn:
                        setLocale("en");
                        recreate(); // Refresh the activity
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_languageIn:
                        setLocale("in");
                        recreate();
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                }
                return true;
            }
        });
    }

    public void setLocale(String languageCode) {
        // Store the selected language in a shared preference
        SharedPreferences preferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("language", languageCode);
        editor.apply();

        // Notify child activities that the language has changed
        if (languageChangedListener != null) {
            languageChangedListener.onLanguageChanged();
        }
        // Send a broadcast to notify all activities about the language change
        Intent intent = new Intent("LanguageChanged");
        sendBroadcast(intent);

        // Update the application's locale
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = new Locale(languageCode);
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
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
        try {
            Cursor cursor = myDB.readDataTablePs();
            String currentLang = languageManager.getCurrentLocale();

            if (cursor.getCount() == 0) {
                Log.d("MyTag", "No data found in the current language");
                Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
                return;
            }

            int nameColumnIndex = currentLang.equals("in") ? 2 : 1; // Adjust column index based on locale

            while (cursor.moveToNext()) {
                String id = cursor.getString(0);
                String name = cursor.getString(nameColumnIndex);
                id_ps.add(id);
                nama_Ps.add(name);

                // Log the data to the logcat
                Log.d("MyTag", "ID: " + id + ", Name: " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MyTag", "Error: " + e.getMessage());
            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
        }
    }





    @Override
    public void onItemClick(int id, String nama) {
        Intent intent = new Intent(PrinciplesActivity.this, penjelasanprinsipalActivity2.class);
        intent.putExtra("nama", nama);
        intent.putExtra("id", id);

        startActivity(intent);
    }

    @Override
    public void onLanguageChanged() {
        recreate(); // This will recreate the activity with the new locale
    }
}