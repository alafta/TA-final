package com.example.projectfrontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfrontend.classKontradiksiMatriks.kontradiksimatriks_ind;


public class home_ind extends AppCompatActivity {


    private TextView judul1;
    private TextView judul2;
    private TextView judul3;
    private Button Btntl;
    private Button Btnkm;
    private Button buttonEng;
    private Button buttonInd;
    private TextView petunjukBahasa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.halaman_home);
        judul1 = findViewById(R.id.trizHome);
        judul2= findViewById(R.id.forHome);
        judul3= findViewById(R.id.bamHome);
        Btntl =  findViewById(R.id.trizLearning);
        Btnkm = findViewById(R.id.buttonKontradiksiM);
        buttonEng = findViewById(R.id.eng);
        buttonInd = findViewById(R.id.ind);
        petunjukBahasa= findViewById(R.id.chooselanguage);
        judul1.setText(R.string.judul1ind);
        judul2.setText(R.string.judul2ind);
        judul3.setText(R.string.judul3ind);
        Btntl.setText(R.string.trizlearningind);
        Btnkm.setText(R.string.usecontradictionind);
        petunjukBahasa.setText(R.string.chooselanguageind);
        Btntl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home_ind.this, tlearning_ind.class);
                startActivity(intent);
            }
        });
        Btnkm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( home_ind.this, kontradiksimatriks_ind.class);
                startActivity(intent);
            }
        });
        buttonInd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( home_ind.this, home_ind.class);
                startActivity(intent);
            }
        });

        buttonEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( home_ind.this, HomeActivity.class);
                startActivity(intent);
            }
        });

    }


}