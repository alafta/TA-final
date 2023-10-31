package com.example.projectfrontend.classContradiction;

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
import androidx.viewpager.widget.ViewPager;

import com.example.projectfrontend.BaseActivity;
import com.example.projectfrontend.CaseActivity;
import com.example.projectfrontend.CaseActivity2;
import com.example.projectfrontend.HomeActivityNew.Home2Activity;
import com.example.projectfrontend.R;
import com.example.projectfrontend.TrizActivity;
import com.example.projectfrontend.classBAM.BamActivity;
import com.example.projectfrontend.classParameterNew.ParameterActivity;
import com.example.projectfrontend.classPrinciple.PrinciplesActivity;
import com.example.projectfrontend.classUnderstanding.TrizUnderstanding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ContramatrixActivity extends BaseActivity implements BaseActivity.OnLanguageChangedListener, contRecyclerInterface {
    ViewPager viewPager;
    contAdapter adapter;
    List<contModel> models;
    RecyclerView recyclerView;
    CaseAdapter caseAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<CaseItemModel> data;
    /*-------nav------*/
    ImageButton menuButton;
    DrawerLayout drawerLayout;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contramatrix);
        drawerLayout = findViewById(R.id.drawer_cont);
        navView = findViewById(R.id.nav_view);

        models = new ArrayList<>();
        models.add(new contModel(R.drawable.cont3, getString(R.string.contmatDesc1)));
        models.add(new contModel(R.drawable.cont2, getString(R.string.contmatDesc2)));

        adapter = new contAdapter(models, this);

        viewPager = findViewById(R.id.contPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        recyclerView = findViewById(R.id.recyclerCase);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        data = new ArrayList<>();
        for (int i = 0; i < CaseItem.number.length; i++) {
            data.add(new CaseItemModel(
                    CaseItem.number[i],
                    CaseItem.caseStudy[i],
                    CaseItem.type[i]
            ));
        }

        caseAdapter = new CaseAdapter(this, data, this);
        recyclerView.setAdapter(caseAdapter);

        menuButton = (ImageButton) findViewById(R.id.menu_contra);
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
                        Intent home = new Intent(ContramatrixActivity.this, Home2Activity.class);
                        startActivity(home);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_undr:
                        Intent under = new Intent(ContramatrixActivity.this, TrizUnderstanding.class);
                        startActivity(under);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_what:
                        Intent intent = new Intent(ContramatrixActivity.this, TrizActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_bussi:
                        Intent bussi = new Intent(ContramatrixActivity.this, BamActivity.class);
                        startActivity(bussi);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_param:
                        Intent param = new Intent(ContramatrixActivity.this, ParameterActivity.class);
                        startActivity(param);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_princ:
                        Intent prince = new Intent(ContramatrixActivity.this, PrinciplesActivity.class);
                        startActivity(prince);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_cont:
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
    public void onItemClick(int position, CaseItemModel model) {

        switch (model.type) {
            case CASE1:
                Intent case1 = new Intent(ContramatrixActivity.this, CaseActivity.class);
                startActivity(case1);
                break;
            case CASE2:
                Intent case2 = new Intent(ContramatrixActivity.this, CaseActivity2.class);
                startActivity(case2);
                break;
        }

    }

    @Override
    public void onLanguageChanged() {
        recreate();
    }
}