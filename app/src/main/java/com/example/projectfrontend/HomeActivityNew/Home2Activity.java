package com.example.projectfrontend.HomeActivityNew;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.LocaleList;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfrontend.BaseActivity;
import com.example.projectfrontend.IntroActivity;
import com.example.projectfrontend.R;
import com.example.projectfrontend.TrizActivity;
import com.example.projectfrontend.classBAM.BamActivity;
import com.example.projectfrontend.classContradiction.ContramatrixActivity;
import com.example.projectfrontend.classParameterNew.ParameterActivity;
import com.example.projectfrontend.classPrinciple.PenjelasanPrinsipalSolusi2;
import com.example.projectfrontend.classPrinciple.PrinciplesActivity;
import com.example.projectfrontend.classPrinciple.PrinsipalSolusi2;
import com.example.projectfrontend.classPrinciple.penjelasanprinsipalActivity2;
import com.example.projectfrontend.classUnderstanding.TrizUnderstanding;
import com.example.projectfrontend.database.DatabaseHelper;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Locale;

public class Home2Activity extends BaseActivity implements BaseActivity.OnLanguageChangedListener, RecyclerViewInterfaceCm2 {
    DatabaseHelper myDB;
    ArrayList<Parameter> improveP;
    ArrayList<Parameter> worseningP;
    ArrayList<PrinsipalSolusi2> listPrinsipalSolusi2;
    ArrayList<PenjelasanPrinsipalSolusi2> listPenjelasanPrinsipalSolusi2;
    int selectedImproveId = -1;
    int selectedWorseningId = -1;
    ArrayList<Integer> idPs;
    AdapterCm2 adapter;
    Boolean start = true;
    private Button button;
    ImageButton menuButton;

    /*-------nav------*/
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;

    /*-------popup------*/
    Dialog myDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.activity_home2);
        Spinner myspinner2 = findViewById(R.id.spinnerNew2);
        Spinner myspinner1 = findViewById(R.id.spinnerNew1);
        drawerLayout = findViewById(R.id.drawer_menu);
        navView = findViewById(R.id.nav_view);
        myDialog = new Dialog(this);

        listPrinsipalSolusi2 = new ArrayList<>();
        listPenjelasanPrinsipalSolusi2 = new ArrayList<>();
        idPs = new ArrayList<>();

        myDB = new DatabaseHelper(Home2Activity.this);
        improveP = new ArrayList<>();
        worseningP = new ArrayList<>();

        displayData();

        ArrayList<String> listParameterName = new ArrayList<>();

        listParameterName.add(getString(R.string.chooseP));
        for (Parameter p : improveP) {
            listParameterName.add(String.valueOf(p.getId()) + ". " + p.getNama());
        }

        ArrayAdapter<String> myAdapter1 = new ArrayAdapter<>(Home2Activity.this, R.layout.spinner_layout, listParameterName);
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<>(Home2Activity.this, R.layout.spinner_layout, listParameterName);
        myAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        myspinner1.setAdapter(myAdapter1);
        myspinner2.setAdapter(myAdapter2);

        myspinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);
                if (i > 0) {
                    start = false;
                    for (Parameter p : improveP) {
                        if (p.getId() == improveP.get(i - 1).getId()) {
                            selectedImproveId = p.getId();
                            if (!listPenjelasanPrinsipalSolusi2.isEmpty()) {
                                listPenjelasanPrinsipalSolusi2.clear();
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
//                ((TextView) adapterView.getChildAt(0)).setGravity(Gravity.CENTER);
                if (i > 0) {
                    start = false;
                    for (Parameter p : improveP) {
                        if (p.getId() == improveP.get(i - 1).getId()) {
                            selectedWorseningId = p.getId();
                            if (!listPenjelasanPrinsipalSolusi2.isEmpty()) {
                                listPenjelasanPrinsipalSolusi2.clear();
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

        button = (Button) findViewById(R.id.buttonTriz);
        button.setOnClickListener(v -> openTrizUnderstanding());

//        /*-------Hooks------*/
//        drawerLayout = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.nav_view);
//        toolbar = findViewById(R.id.toolbar);
//
//        /*-------toolbar------*/
//        setSupportActionBar(toolbar);
//
//        /*-------navbar menu------*/
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);

        menuButton = (ImageButton) findViewById(R.id.menu_button);
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
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_undr:
                        Intent under = new Intent(Home2Activity.this, TrizUnderstanding.class);
                        startActivity(under);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_what:
                        Intent intent = new Intent(Home2Activity.this, TrizActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_bussi:
                        Intent bussi = new Intent(Home2Activity.this, BamActivity.class);
                        startActivity(bussi);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_param:
                        Intent param = new Intent(Home2Activity.this, ParameterActivity.class);
                        startActivity(param);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_princ:
                        Intent prince = new Intent(Home2Activity.this, PrinciplesActivity.class);
                        startActivity(prince);
                        break;
                    case R.id.nav_cont:
                        Intent matrix = new Intent(Home2Activity.this, ContramatrixActivity.class);
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


    public void ShowPopup (View v) {
        TextView txtClose;
        myDialog.setContentView(R.layout.popup_info);
        txtClose = (TextView) myDialog.findViewById(R.id.close);
        txtClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();
    }


    public void openTrizUnderstanding() {
        Intent intent = new Intent(this, TrizUnderstanding.class);
        startActivity(intent);
    }

    void displayData() {
        Cursor cursor = myDB.readDataTableP();
        String currentLang = languageManager.getCurrentLocale();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
            return;
        }

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = (currentLang.equals("in")) ? cursor.getString(2) : cursor.getString(1);

            Parameter p = new Parameter(id, name);
            improveP.add(p);
            worseningP.add(p);

            // Log the data to the logcat
            Log.d("MyTag", "ID: " + id + ", Name: " + name);
        }
    }


    void getKontradiksi() {
        if (!idPs.isEmpty()) idPs.clear();
        Cursor cursor = myDB.readKontradiksi(selectedImproveId, selectedWorseningId);
        if (cursor.getCount() == 0) {
//            Toast.makeText(this, "Solution not available", Toast.LENGTH_SHORT).show();
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
        if (!listPrinsipalSolusi2.isEmpty()) {
            listPrinsipalSolusi2.clear();
        }

        for (int i : idPs) {
            Cursor cursor = myDB.readDataTablePsById(i);
            String currentLang = languageManager.getCurrentLocale();

            if (cursor.getCount() == 0) {
                Log.d("MyTag", "No data for ID: " + i);
                Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
            } else {
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(0);
                    String name, description;

                    if (currentLang.equals("in")) {
                        name = cursor.getString(1);
                        description = cursor.getString(2);
                    } else {
                        name = cursor.getString(2);
                        description = cursor.getString(1);
                    }

                    PrinsipalSolusi2 ps = new PrinsipalSolusi2(id, name, description);
                    listPrinsipalSolusi2.add(ps);

                    // Log the data to the logcat
                    Log.d("=== MyTag", "ID: " + id + ", Name: " + name + ", Description: " + description);
                }
            }
        }

        if (!listPrinsipalSolusi2.isEmpty()) {
            for (PrinsipalSolusi2 p : listPrinsipalSolusi2) {
                getPenjelasanPs(p.getId());
            }
        }
    }



    void getPenjelasanPs(int idPs) {
        Cursor cursor = myDB.readPenjelasanPsById(idPs);
        String currentLang = languageManager.getCurrentLocale();

        Log.d("@@@@MyTag", "Current Language: " + currentLang);

        if (cursor.getCount() == 0) {
            Log.d("!!!MyTag", "No data for ID: " + idPs);
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String name, description;

                if (currentLang.equals("in")) {
                    name = cursor.getString(2);
                    description = cursor.getString(1);
                } else {
                    name = cursor.getString(1);
                    description = cursor.getString(2);
                }

                Log.d("###MyTag", "ID: " + id + ", Name: " + name + ", Description: " + description);

                PenjelasanPrinsipalSolusi2 ps = new PenjelasanPrinsipalSolusi2(id, name, description, cursor.getInt(3));
                listPenjelasanPrinsipalSolusi2.add(ps);
            }
        }

        Log.d("111MyTag", "listPenjelasanPrinsipalSolusi2 size: " + listPenjelasanPrinsipalSolusi2.size());

        if (!listPrinsipalSolusi2.isEmpty() && !listPenjelasanPrinsipalSolusi2.isEmpty()) {
            adapter = new AdapterCm2(
                    Home2Activity.this, listPrinsipalSolusi2, listPenjelasanPrinsipalSolusi2, this);

            RecyclerView recyclerView;
            recyclerView = findViewById(R.id.recycleViewhasilCm);

            Log.d("***MyTag", "Setting up the adapter");

            recyclerView.setLayoutManager(new LinearLayoutManager(Home2Activity.this));
            recyclerView.setAdapter(adapter);
        }
    }


    @Override
    public void onItemClick(int id, String nama) {
        Log.d("item", "onItemClick: ");
        Intent intent = new Intent(Home2Activity.this, penjelasanprinsipalActivity2.class);
        intent.putExtra("nama", nama);
        intent.putExtra("id", id);

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);

        return true;
    }

    @Override
    public void onLanguageChanged() {
        recreate(); // This will recreate the activity with the new locale
    }



}