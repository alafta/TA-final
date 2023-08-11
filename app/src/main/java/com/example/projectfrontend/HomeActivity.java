package com.example.projectfrontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfrontend.classKontradiksiMatriks.kontradiksimatriksActivity;


public class HomeActivity extends AppCompatActivity {


    private Button Btntl;
    private Button Btnkm;
    private Button buttonEng;
    private Button buttonInd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.halaman_home);
        Btntl =  findViewById(R.id.trizLearning);
        Btnkm = findViewById(R.id.buttonKontradiksiM);
        buttonEng = findViewById(R.id.eng);
        buttonInd = findViewById(R.id.ind);
        Btntl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, tlearningActivity.class);
                startActivity(intent);
            }
        });
        Btnkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( HomeActivity.this, kontradiksimatriksActivity.class);
                startActivity(intent);
            }
        });
        buttonInd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( HomeActivity.this, home_ind.class);
                startActivity(intent);
            }
        });

        buttonEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( HomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }



}