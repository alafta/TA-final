package com.example.projectfrontend;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CaseActivity2 extends AppCompatActivity {
    TextView textView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case2);
        textView = findViewById(R.id.textCase);
        button = findViewById(R.id.buttonClue);
        String currentLang = getCurrentLocale();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentLang.equals("en")) {
                    String text = "<font color = '#C31B1B'>If manager is aggresive</font>, then responsibility and number of project assigned increases but <font color = '#C31B1B'>subordinates receive fewer recognitions and acknowledgments (due to limited manager bandwith)</font>";
                    textView.setText(Html.fromHtml(text));
                }else if(currentLang.equals("in")) {
                    String text = "<font color = '#C31B1B'>Jika manajer agresif</font>, maka tanggung jawab dan jumlah proyek yang ditugaskan meningkat tetapi <font color = '#C31B1B'>bawahan menerima lebih sedikit pengakuan dan ucapan terimakasih (karena bandwidth manajer terbatas)</font>";
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