package com.example.projectfrontend;

import android.animation.ArgbEvaluator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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
import java.util.Locale;

public class TrizActivity extends BaseActivity implements BaseActivity.OnLanguageChangedListener   {

    ViewPager viewPager;
    trizAdapter adapter;
    List<trizModel> models;
    Integer[] colors = null;
    ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private BottomSheetBehavior bottomSheetBehavior;
    ImageButton menuButton;
    DrawerLayout drawerLayout;
    NavigationView navView;
    private WebView webView;
    private WebView webView2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triz);
        drawerLayout = findViewById(R.id.drawer_what);
        navView = findViewById(R.id.nav_view);

        webView = findViewById(R.id.view1);
        String video = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/G15U_BQE5ME?si=o_O-g1ScnH2kUNW8\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        webView.loadData(video, "text/html", "utf-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());

        webView2 = findViewById(R.id.view2);
        String video2 = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/hu2SpPrcyWg?si=oxt4A96GRwji1_47\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        webView2.loadData(video2, "text/html", "utf-8");
        webView2.getSettings().setJavaScriptEnabled(true);
        webView2.setWebChromeClient(new WebChromeClient());

        models = new ArrayList<>();
        models.add(new trizModel(R.drawable.illutriz, getString(R.string.page1)));
        models.add(new trizModel(R.drawable.illutriz2, getString(R.string.page2)));
        models.add(new trizModel(R.drawable.illutriz3, getString(R.string.page3)));
        models.add(new trizModel(R.drawable.illutriz4, getString(R.string.page4)));
        models.add(new trizModel(R.drawable.illutriz5, getString(R.string.page5)));

        adapter = new trizAdapter(models, this);

        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(100, 0, 100, 0);





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
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_undr:
                        Intent intent = new Intent(TrizActivity.this, TrizUnderstanding.class);
                        startActivity(intent);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_what:
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_bussi:
                        Intent bussi = new Intent(TrizActivity.this, BamActivity.class);
                        startActivity(bussi);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_param:
                        Intent param = new Intent(TrizActivity.this, ParameterActivity.class);
                        startActivity(param);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_princ:
                        Intent prince = new Intent(TrizActivity.this, PrinciplesActivity.class);
                        startActivity(prince);
                        drawerLayout.closeDrawer(Gravity.END);
                        break;
                    case R.id.nav_cont:
                        Intent matrix = new Intent(TrizActivity.this, ContramatrixActivity.class);
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

    @Override
    public void onBackPressed() {
        // Handle back button press for WebView navigation
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }


//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//    }
}