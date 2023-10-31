package com.example.projectfrontend;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class BaseActivity extends AppCompatActivity {

    private BroadcastReceiver languageChangedReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if ("LanguageChanged".equals(intent.getAction())) {
                recreate();
            }
        }
    };

    public LanguageManager languageManager;

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Unregister the BroadcastReceiver when the activity is destroyed
        unregisterReceiver(languageChangedReceiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        languageManager = new LanguageManager(this);

        // Retrieve the selected language from the LanguageManager
        String selectedLanguage = languageManager.getCurrentLocale();

        // Apply the selected language to the application
        Resources resources = getResources();
        Configuration configuration = resources.getConfiguration();
        Locale locale = languageManager.getCurrentLocaleObject();
        configuration.setLocale(locale);
        resources.updateConfiguration(configuration, resources.getDisplayMetrics());

        IntentFilter filter = new IntentFilter("LanguageChanged");
        registerReceiver(languageChangedReceiver, filter);
    }

    public interface OnLanguageChangedListener {
        void onLanguageChanged();
    }

    public OnLanguageChangedListener languageChangedListener;

    public void setOnLanguageChangedListener(OnLanguageChangedListener listener) {
        this.languageChangedListener = listener;
    }


}
