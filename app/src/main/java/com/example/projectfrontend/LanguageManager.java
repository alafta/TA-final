package com.example.projectfrontend;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.Locale;

public class LanguageManager {
    private final SharedPreferences preferences;

    public LanguageManager(Context context) {
        preferences = context.getSharedPreferences("MyAppPreferences", Context.MODE_PRIVATE);
    }

    public String getCurrentLocale() {
        return preferences.getString("language", "en");
    }

    public void setCurrentLocale(String locale) {
        preferences.edit().putString("language", locale).apply();
    }

    public Locale getCurrentLocaleObject() {
        return new Locale(getCurrentLocale());
    }
}
