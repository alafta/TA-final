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

public class CaseActivity2 extends BaseActivity implements BaseActivity.OnLanguageChangedListener {
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
        setContentView(R.layout.activity_case2);
        textView = findViewById(R.id.textCase);
        button = findViewById(R.id.buttonClue);
        String currentLang = getCurrentLocale();
        drawerLayout = findViewById(R.id.drawer_case2);
        navView = findViewById(R.id.nav_view);
        cardView = findViewById(R.id.cardChoose);

        cardView.setVisibility(View.GONE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentLang = languageManager.getCurrentLocale();

                if (cardView.getVisibility() == View.GONE) {
                    if(currentLang.equals("en")) {
                        String text = "<font color = '#00BA56'>If manager is aggresive</font>, then responsibility and number of project assigned increases but <font color = '#C31B1B'>subordinates receive fewer recognitions and acknowledgments (due to limited manager bandwith)</font>";
                        textView.setText(Html.fromHtml(text));
                    }else if(currentLang.equals("in")) {
                        String text = "<font color = '#00BA56'>Jika manajer agresif</font>, maka tanggung jawab dan jumlah proyek yang ditugaskan meningkat tetapi <font color = '#C31B1B'>bawahan menerima lebih sedikit pengakuan dan ucapan terimakasih (karena bandwidth manajer terbatas)</font>";
                        textView.setText(Html.fromHtml(text));
                    }
                    cardView.setVisibility(View.VISIBLE);
                } else {
                    textView.setText(R.string.case2);
                    cardView.setVisibility(View.GONE);
                }

            }
        });



//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(currentLang.equals("en")) {
//                    textView.setText(Html.fromHtml(text));
//                }else if(currentLang.equals("in")) {
//                    textView.setText(Html.fromHtml(text));
//
//                }
//            }
//        });

        menuButton = (ImageButton) findViewById(R.id.menuCase2);
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
                        Intent home = new Intent(CaseActivity2.this, Home2Activity.class);
                        startActivity(home);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_undr:
                        Intent under = new Intent(CaseActivity2.this, TrizUnderstanding.class);
                        startActivity(under);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_what:
                        Intent intent = new Intent(CaseActivity2.this, TrizActivity.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_bussi:
                        Intent bussi = new Intent(CaseActivity2.this, BamActivity.class);
                        startActivity(bussi);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_param:
                        Intent param = new Intent(CaseActivity2.this, ParameterActivity.class);
                        startActivity(param);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_princ:
                        Intent prince = new Intent(CaseActivity2.this, PrinciplesActivity.class);
                        startActivity(prince);
                        break;
                    case R.id.nav_cont:
                        Intent matrix = new Intent(CaseActivity2.this, ContramatrixActivity.class);
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

    private String getCurrentLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        return prefs.getString("My_Lang", "en");
    }

    @Override
    public void onLanguageChanged() {
        recreate();
    }
}