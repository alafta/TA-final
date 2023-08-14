package com.example.projectfrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CaseActivity extends AppCompatActivity {
    TextView textView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);
        textView = findViewById(R.id.textCase);
        button = findViewById(R.id.buttonClue);
        String currentLang = getCurrentLocale();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentLang.equals("en")) {
                    String text = "<font color = '#C31B1B'>If employee productivity doubled</font>, then company will be able to meet customer demand but <font color = '#C31B1B'>employee health worsen</font>";
                    textView.setText(Html.fromHtml(text));
                }else if(currentLang.equals("in")) {
                    String text = "<font color = '#C31B1B'>Jika produktivitas karyawan meningkat dua kali lipat</font>, maka perusahaan akan dapat memenuhi permintaan pelanggan tetapi <font color = '#C31B1B'>kesehatan karyawan memburuk</font>";
                    textView.setText(Html.fromHtml(text));

                }
            }
        });


    }

    private String getCurrentLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        return prefs.getString("My_Lang", "en");
    }

}