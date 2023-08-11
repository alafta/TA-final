package com.example.projectfrontend.classUnderstanding;

import android.content.Intent;
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

public class TrizUnderstanding extends AppCompatActivity implements RecyclerInterface {

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
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_undr:
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_what:
                        Intent intent = new Intent(TrizUnderstanding.this, TrizActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_bussi:
                        Intent bussi = new Intent(TrizUnderstanding.this, BamActivity.class);
                        startActivity(bussi);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_param:
                        Intent param = new Intent(TrizUnderstanding.this, ParameterActivity.class);
                        startActivity(param);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_princ:
                        Intent prince = new Intent(TrizUnderstanding.this, PrinciplesActivity.class);
                        startActivity(prince);
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_cont:
                        Intent matrix = new Intent(TrizUnderstanding.this, ContramatrixActivity.class);
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


}