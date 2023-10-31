package com.example.projectfrontend.classUnderstanding;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projectfrontend.BaseActivity;
import com.example.projectfrontend.HomeActivityNew.Home2Activity;
import com.example.projectfrontend.TrizActivity;
import com.example.projectfrontend.classBAM.BamActivity;
import com.example.projectfrontend.classContradiction.ContramatrixActivity;
import com.example.projectfrontend.classParameterNew.ParameterActivity;
import com.example.projectfrontend.classPrinciple.PrinciplesActivity;
import com.example.projectfrontend.R;
import com.example.projectfrontend.TrizActivity;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Locale;

public class TrizUnderstanding extends BaseActivity implements BaseActivity.OnLanguageChangedListener, RecyclerInterface {

    RecyclerView recyclerView;
    AdapterUnderstanding adapterUnderstanding;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<ItemModel> data;
    /*-------nav------*/
    ImageButton menuButton;
    DrawerLayout drawerLayout;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triz_understanding);
        drawerLayout = findViewById(R.id.drawer_un);
        navView = findViewById(R.id.nav_view);

        recyclerView = findViewById(R.id.recyclerUnderstanding);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();
        for (int i = 0; i < UnderstandingItem.title.length; i++){
            data.add(new ItemModel(
                    UnderstandingItem.icon[i],
                    UnderstandingItem.title[i],
                    UnderstandingItem.type[i]
            ));
        }

        adapterUnderstanding = new AdapterUnderstanding(this, data, this);
        recyclerView.setAdapter(adapterUnderstanding);

        menuButton = (ImageButton) findViewById(R.id.menu_buttonUn);
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
                        Intent home = new Intent(TrizUnderstanding.this, Home2Activity.class);
                        startActivity(home);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_undr:
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_what:
                        Intent intent = new Intent(TrizUnderstanding.this, TrizActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_bussi:
                        Intent bussi = new Intent(TrizUnderstanding.this, BamActivity.class);
                        startActivity(bussi);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_param:
                        Intent param = new Intent(TrizUnderstanding.this, ParameterActivity.class);
                        startActivity(param);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_princ:
                        Intent prince = new Intent(TrizUnderstanding.this, PrinciplesActivity.class);
                        startActivity(prince);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_cont:
                        Intent matrix = new Intent(TrizUnderstanding.this, ContramatrixActivity.class);
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

    @Override
    public void onItemClick(int position, ItemModel model) {

        switch (model.type){
            case ABOUT:
                //Intent
                Intent intent = new Intent(TrizUnderstanding.this, TrizActivity.class);
                startActivity(intent);
                break;
            case BAM:
                //Intent
                Intent bam = new Intent(TrizUnderstanding.this, BamActivity.class);
                startActivity(bam);
                break;
            case PARAMETER:
                Intent param = new Intent(TrizUnderstanding.this, ParameterActivity.class);
                startActivity(param);
                break;
            case PRINCIPLE:
                Intent prince = new Intent(TrizUnderstanding.this, PrinciplesActivity.class);
                startActivity(prince);
                break;
            case MATRIX:
                Intent matrix = new Intent(TrizUnderstanding.this, ContramatrixActivity.class);
                startActivity(matrix);
                break;
        }

    }


    @Override
    public void onLanguageChanged() {
        recreate();
    }
}