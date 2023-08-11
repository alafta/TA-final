package com.example.projectfrontend.classParameter;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfrontend.R;


public class penjelasanparameterActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar();
        setContentView(R.layout.halaman_penjelasanp);
        String namaJudulP = getIntent().getStringExtra("nama");
        String meaningP = getIntent().getStringExtra("meaning");
        String saeP = getIntent().getStringExtra("sae");
        int idP = getIntent().getIntExtra("id_p",0);

        Button textPenjelasanp      = findViewById(R.id.buletpenjelasanp);
        Button judulPenjelasanP     = findViewById(R.id.kotakjudulp);
        TextView meaningPenjelasanP = findViewById(R.id.tvpenjelasanp);
        TextView saePenjelasanP     = findViewById(R.id.tvpenjelasansae);

        judulPenjelasanP.setText(namaJudulP);
        judulPenjelasanP.setTextSize(12F);
        meaningPenjelasanP.setText(meaningP);
        saePenjelasanP.setText(saeP);
        textPenjelasanp.setText(String.valueOf(idP));
    }



}