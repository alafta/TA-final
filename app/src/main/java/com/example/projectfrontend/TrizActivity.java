package com.example.projectfrontend;

import android.animation.ArgbEvaluator;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.projectfrontend.HomeActivityNew.Home2Activity;
import com.example.projectfrontend.classBAM.BamActivity;
import com.example.projectfrontend.classContradiction.ContramatrixActivity;
import com.example.projectfrontend.classParameterNew.ParameterActivity;
import com.example.projectfrontend.classPrinciple.PrinciplesActivity;
import com.example.projectfrontend.classUnderstanding.TrizUnderstanding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class TrizActivity extends AppCompatActivity   {

    ViewPager viewPager;
    trizAdapter adapter;
    List<trizModel> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private BottomSheetBehavior bottomSheetBehavior;
    ImageButton menuButton;
    DrawerLayout drawerLayout;
    NavigationView navView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triz);
        drawerLayout = findViewById(R.id.drawer_what);
        navView = findViewById(R.id.nav_view);

        models = new ArrayList<>();
        models.add(new trizModel(R.drawable.illutriz, getString(R.string.page1)));
        models.add(new trizModel(R.drawable.illutriz, getString(R.string.page2)));
        models.add(new trizModel(R.drawable.illutriz, getString(R.string.page3)));
        models.add(new trizModel(R.drawable.illutriz, getString(R.string.page4)));
        models.add(new trizModel(R.drawable.illutriz, getString(R.string.page5)));

        adapter = new trizAdapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130, 0, 130, 0);



//        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                if (position < (adapter.getCount() -1) && position < (colors.length -1)) {
//                    viewPager.setBackgroundColor(
//                            (Integer) argbEvaluator.evaluate(
//                                    positionOffset,
//                                    colors[position],
//                                    colors[position +1]
//                            )
//                    );
//                }
//                else {
//                    viewPager.setBackgroundColor(colors[colors.length -1]);
//                }
//            }

//            @Override
//            public void onPageSelected(int position) {


        LinearLayout linearLayout = findViewById(R.id.bottom_sheet_layout);

        bottomSheetBehavior = BottomSheetBehavior.from(linearLayout);

        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });

        menuButton = (ImageButton) findViewById(R.id.menu_what);
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
                        Intent home = new Intent(TrizActivity.this, Home2Activity.class);
                        startActivity(home);
                        break;
                    case R.id.nav_undr:
                        Intent intent = new Intent(TrizActivity.this, TrizUnderstanding.class);
                        startActivity(intent);
                        break;
                    case R.id.nav_what:
                        drawerLayout.closeDrawer(R.id.nav_drawer);
                        break;
                    case R.id.nav_bussi:
                        Intent bussi = new Intent(TrizActivity.this, BamActivity.class);
                        startActivity(bussi);
                        break;
                    case R.id.nav_param:
                        Intent param = new Intent(TrizActivity.this, ParameterActivity.class);
                        startActivity(param);
                        break;
                    case R.id.nav_princ:
                        Intent prince = new Intent(TrizActivity.this, PrinciplesActivity.class);
                        startActivity(prince);
                        break;
                    case R.id.nav_cont:
                        Intent matrix = new Intent(TrizActivity.this, ContramatrixActivity.class);
                        startActivity(matrix);
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

//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//    }
}