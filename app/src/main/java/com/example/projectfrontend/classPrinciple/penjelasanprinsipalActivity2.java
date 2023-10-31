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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfrontend.BaseActivity;
import com.example.projectfrontend.CaseActivity;
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


public class penjelasanprinsipalActivity2 extends BaseActivity implements BaseActivity.OnLanguageChangedListener{
    DatabaseHelper myDB;
    ArrayList<String> penjelasan;
    ArrayList<String> ilustrasi;
    adapterlist2 adapter;
    /*-------nav------*/
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    ImageButton menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar();
        setContentView(R.layout.principles_desc);
        myDB = new DatabaseHelper(this);
        penjelasan = new ArrayList<>();
        ilustrasi = new ArrayList<>();
        drawerLayout = findViewById(R.id.drawer_descps);
        navView = findViewById(R.id.nav_view);

        String namaJudulPs = getIntent().getStringExtra("nama");
        int idPs =  getIntent().getIntExtra("id", 0);

        Button judulPenjelasanPs = findViewById(R.id.judulps);
        Button nomorPenjelasanPs = findViewById(R.id.numberps);

        RecyclerView lvpenjelasansolusi = findViewById(R.id.solutionR);
        lvpenjelasansolusi.setLayoutManager(new LinearLayoutManager(this));
        adapter = new adapterlist2(this, penjelasan);
        lvpenjelasansolusi.setAdapter(adapter);

        RecyclerView lvpenjelasanilustrasi = findViewById(R.id.illuR);
        lvpenjelasanilustrasi.setLayoutManager(new LinearLayoutManager(this));
        adapter = new adapterlist2(this,ilustrasi);
        lvpenjelasanilustrasi.setAdapter(adapter);


        judulPenjelasanPs.setText(namaJudulPs);
        nomorPenjelasanPs.setText(String.valueOf(idPs));

        readData(idPs);

        menuButton = (ImageButton) findViewById(R.id.menuPrincdesc);
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
                        Intent home = new Intent(penjelasanprinsipalActivity2.this, Home2Activity.class);
                        startActivity(home);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_undr:
                        Intent under = new Intent(penjelasanprinsipalActivity2.this, TrizUnderstanding.class);
                        startActivity(under);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_what:
                        Intent intent = new Intent(penjelasanprinsipalActivity2.this, TrizActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_bussi:
                        Intent bussi = new Intent(penjelasanprinsipalActivity2.this, BamActivity.class);
                        startActivity(bussi);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_param:
                        Intent param = new Intent(penjelasanprinsipalActivity2.this, ParameterActivity.class);
                        startActivity(param);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_princ:
                        Intent prince = new Intent(penjelasanprinsipalActivity2.this, PrinciplesActivity.class);
                        startActivity(prince);
                        break;
                    case R.id.nav_cont:
                        Intent matrix = new Intent(penjelasanprinsipalActivity2.this, ContramatrixActivity.class);
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
        // menyimpan bahasa yang dipilih
        SharedPreferences preferences = getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("language", languageCode);
        editor.apply();

        // Notify child activities that the language has changed
        if (languageChangedListener != null) {
            languageChangedListener.onLanguageChanged();
        }
        // kirim broadcast ke seluruh aktivitas tentang perubahan bahasa
        Intent intent = new Intent("LanguageChanged");
        sendBroadcast(intent);

        // meng update local
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = new Locale(languageCode);
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());
    }

    private void readData(int id) {
        Cursor cursor = myDB.readPenjelasanPsById(id);
        Cursor cursor2 = myDB.readIlustrasiPsById(id);
        String currentLang = languageManager.getCurrentLocale();

        if (cursor.getCount() == 0 || cursor2.getCount() == 0) {
            Log.d("MyTag", "No data found");
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
            return;
        }

        while (cursor.moveToNext()) {
            String penjelasanData;
            if (currentLang.equals("en")) {
                penjelasanData = cursor.getString(1);
                Log.d("MyTag", "Penjelasan (English): " + penjelasanData);
            } else if (currentLang.equals("in")) {
                penjelasanData = cursor.getString(2);
                Log.d("MyTag", "Penjelasan (Indonesian): " + penjelasanData);
            } else {
                // Handle other languages or default case
                penjelasanData = cursor.getString(1);
                Log.d("MyTag", "Penjelasan (Default): " + penjelasanData);
            }
            penjelasan.add(penjelasanData);
        }

        while (cursor2.moveToNext()) {
            String ilustrasiData;
            if (currentLang.equals("en")) {
                ilustrasiData = cursor2.getString(1);
                Log.d("MyTag", "Ilustrasi (English): " + ilustrasiData);
            } else if (currentLang.equals("in")) {
                ilustrasiData = cursor2.getString(2);
                Log.d("MyTag", "Ilustrasi (Indonesian): " + ilustrasiData);
            } else {
                // Handle other languages or default case
                ilustrasiData = cursor2.getString(1);
                Log.d("MyTag", "Ilustrasi (Default): " + ilustrasiData);
            }
            ilustrasi.add(ilustrasiData);
        }
    }




    @Override
    public void onLanguageChanged() {
        recreate();
    }
}