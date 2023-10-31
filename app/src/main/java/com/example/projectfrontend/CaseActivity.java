package com.example.projectfrontend;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.projectfrontend.HomeActivityNew.Home2Activity;
import com.example.projectfrontend.classBAM.BamActivity;
import com.example.projectfrontend.classContradiction.ContramatrixActivity;
import com.example.projectfrontend.classParameterNew.ParameterActivity;
import com.example.projectfrontend.classPrinciple.PrinciplesActivity;
import com.example.projectfrontend.classUnderstanding.TrizUnderstanding;
import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class CaseActivity extends BaseActivity implements BaseActivity.OnLanguageChangedListener {
    TextView textView;
    Button button;
    CardView cardView;
    /*-------nav------*/
    NavigationView navigationView;
    DrawerLayout drawerLayout;
    NavigationView navView;
    Toolbar toolbar;
    ImageButton menuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);
        textView = findViewById(R.id.textCase);
        button = findViewById(R.id.buttonClue);
        drawerLayout = findViewById(R.id.drawer_case);
        navView = findViewById(R.id.nav_view);
        cardView = findViewById(R.id.cardChoose);

        cardView.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentLang = languageManager.getCurrentLocale();

                if (cardView.getVisibility() == View.GONE) {
                    if(currentLang.equals("en")) {
                        String text = "<font color = '#00BA56'>If employee productivity doubled</font>, then company will be able to meet customer demand but <font color = '#C31B1B'>employee health worsen</font>";
                        textView.setText(Html.fromHtml(text));
                    }else if(currentLang.equals("in")) {
                        String text = "<font color = '#00BA56'>Jika produktivitas karyawan meningkat dua kali lipat</font>, maka perusahaan akan dapat memenuhi permintaan pelanggan tetapi <font color = '#C31B1B'>kesehatan karyawan memburuk</font>";
                        textView.setText(Html.fromHtml(text));
                    }
                    cardView.setVisibility(View.VISIBLE);
                } else {
                    textView.setText(R.string.case1);
                    cardView.setVisibility(View.GONE);
                }

            }
        });

        menuButton = (ImageButton) findViewById(R.id.menuCase1);
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
                        Intent home = new Intent(CaseActivity.this, Home2Activity.class);
                        startActivity(home);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_undr:
                        Intent under = new Intent(CaseActivity.this, TrizUnderstanding.class);
                        startActivity(under);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_what:
                        Intent intent = new Intent(CaseActivity.this, TrizActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_bussi:
                        Intent bussi = new Intent(CaseActivity.this, BamActivity.class);
                        startActivity(bussi);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_param:
                        Intent param = new Intent(CaseActivity.this, ParameterActivity.class);
                        startActivity(param);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_princ:
                        Intent prince = new Intent(CaseActivity.this, PrinciplesActivity.class);
                        startActivity(prince);
                        break;
                    case R.id.nav_cont:
                        Intent matrix = new Intent(CaseActivity.this, ContramatrixActivity.class);
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
    public void onLanguageChanged() {
        recreate();
    }
}