package com.example.projectfrontend.classKontradiksiMatriks;

import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfrontend.R;


public class tlkontradiksiActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setContentView(R.layout.halaman_tlkontradiksi);
        ImageView gambarcontoh = findViewById(R.id.gambarcontoh);
        gambarcontoh.setImageResource(R.drawable.gambarcontohcm);
    }
}