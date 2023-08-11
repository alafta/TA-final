package com.example.projectfrontend.classBAM;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.projectfrontend.HomeActivityNew.Home2Activity;
import com.example.projectfrontend.R;
import com.example.projectfrontend.TrizActivity;
import com.example.projectfrontend.classContradiction.ContramatrixActivity;
import com.example.projectfrontend.classParameterNew.ParameterActivity;
import com.example.projectfrontend.classPrinciple.PrinciplesActivity;
import com.example.projectfrontend.classUnderstanding.TrizUnderstanding;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class BamActivity extends AppCompatActivity {
    ViewPager viewPager;
    bamAdapter adapter;
    List<bamModel> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    /*-------nav------*/
    ImageButton menuButton;
    DrawerLayout drawerLayout;
    NavigationView navView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bam);
        drawerLayout = findViewById(R.id.drawer_bam);
        navView = findViewById(R.id.nav_view);

        models = new ArrayList<>();
        models.add(new bamModel(R.drawable.contmat2, getString(R.string.bamDesc1), getString(R.string.bamDesc2)));
        models.add(new bamModel(R.drawable.contmat2, getString(R.string.busDesc1), getString(R.string.busDesc2)));
        models.add(new bamModel(R.drawable.contmat2, getString(R.string.manDesc1), getString(R.string.manDesc2)));
        models.add(new bamModel(R.drawable.contmat2, getString(R.string.manDesc1), getString(R.string.manDesc3)));

        adapter = new bamAdapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        menuButton = (ImageButton) findViewById(R.id.menu_bam);
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
                        Intent home = new Intent(BamActivity.this, Home2Activity.class);
                        startActivity(home);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_undr:
                        Intent under = new Intent(BamActivity.this, TrizUnderstanding.class);
                        startActivity(under);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_what:
                        Intent intent = new Intent(BamActivity.this, TrizActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_bussi:
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_param:
                        Intent param = new Intent(BamActivity.this, ParameterActivity.class);
                        startActivity(param);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_princ:
                        Intent prince = new Intent(BamActivity.this, PrinciplesActivity.class);
                        startActivity(prince);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_cont:
                        Intent matrix = new Intent(BamActivity.this, ContramatrixActivity.class);
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



}