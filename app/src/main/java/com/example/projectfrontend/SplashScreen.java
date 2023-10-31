package com.example.projectfrontend;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfrontend.HomeActivityNew.Home2Activity;

public class SplashScreen extends AppCompatActivity {
    private static final String PREFS_NAME = "MyPrefsFile";
    private boolean FirstTime = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        FirstTime = settings.getBoolean("FirstTime", true);

        new Handler() .postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;

                if (FirstTime) {
                    intent = new Intent(SplashScreen. this,IntroActivity.class);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("FirstTime", false);
                    editor.apply();
                } else {
                    intent = new Intent(SplashScreen. this, Home2Activity.class);
                }
                startActivity(intent);
                finish();

            }
        },3000);
    }
}