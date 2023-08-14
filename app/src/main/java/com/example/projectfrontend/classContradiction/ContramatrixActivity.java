package com.example.projectfrontend.classContradiction;

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
import androidx.viewpager.widget.ViewPager;

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

public class ContramatrixActivity extends AppCompatActivity implements contRecyclerInterface {
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
        models.add(new contModel(R.drawable.contmat1, getString(R.string.contmatDesc1)));
        models.add(new contModel(R.drawable.contmat2, getString(R.string.contmatDesc2)));

        adapter = new contAdapter(models, this);

        viewPager = findViewById(R.id.contPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);

        recyclerView = findViewById(R.id.recyclerCase);
        recyclerView.setHasFixedSize(true);

        layoutManager = new GridLayoutManager(this, 3);
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
                        break;
                    case R.id.nav_undr:
                        Intent under = new Intent(ContramatrixActivity.this, TrizUnderstanding.class);
                        startActivity(under);
                        break;
                    case R.id.nav_what:
                        Intent intent = new Intent(ContramatrixActivity.this, TrizActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_bussi:
                        Intent bussi = new Intent(ContramatrixActivity.this, BamActivity.class);
                        startActivity(bussi);
                        break;
                    case R.id.nav_param:
                        Intent param = new Intent(ContramatrixActivity.this, ParameterActivity.class);
                        startActivity(param);
                        break;
                    case R.id.nav_princ:
                        Intent prince = new Intent(ContramatrixActivity.this, PrinciplesActivity.class);
                        startActivity(prince);
                        break;
                    case R.id.nav_cont:
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
}