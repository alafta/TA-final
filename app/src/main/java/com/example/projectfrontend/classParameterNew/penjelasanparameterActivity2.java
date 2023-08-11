package com.example.projectfrontend.classParameterNew;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfrontend.R;


public class penjelasanparameterActivity2 extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar();
        setContentView(R.layout.parameter_desc);
        String namaJudulP = getIntent().getStringExtra("nama");
        String meaningP = getIntent().getStringExtra("meaning");
        String saeP = getIntent().getStringExtra("sae");
        int idP = getIntent().getIntExtra("id_p",0);

        Button textPenjelasanp      = findViewById(R.id.number);
        Button judulPenjelasanP     = findViewById(R.id.judulp);
        TextView meaningPenjelasanP = findViewById(R.id.penjelasanp);
        TextView saePenjelasanP     = findViewById(R.id.penjelasansae);

        judulPenjelasanP.setText(namaJudulP);
        meaningPenjelasanP.setText(meaningP);
        saePenjelasanP.setText(saeP);
        textPenjelasanp.setText(String.valueOf(idP));
    }



}