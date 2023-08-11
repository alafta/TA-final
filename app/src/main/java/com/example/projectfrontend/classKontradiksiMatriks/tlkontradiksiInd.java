package com.example.projectfrontend.classKontradiksiMatriks;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfrontend.R;


public class tlkontradiksiInd extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.halaman_tlkontradiksi);
        TextView tlk = findViewById(R.id.judultlk);
        tlk.setText(R.string.contradictionMind);
        TextView judulpenjelasan = findViewById(R.id.judulpenjelasancm);
        TextView penjelasantrizforcm = findViewById(R.id.penjelasantrizforcm);
        TextView titlecarakerja = findViewById(R.id.carakerja);
        TextView penjelasancarakerja1 = findViewById(R.id.penjelasancarakerjacm);
        TextView penjelasancarakerja2 = findViewById(R.id.penjelasancarakerjacm2);
        ImageView gambarcontoh = findViewById(R.id.gambarcontoh);

        judulpenjelasan.setText(R.string.judulpenjelasancm_ind);
        penjelasantrizforcm.setText(R.string.penjelasankontradiksi_ind);
        titlecarakerja.setText(R.string.subtitle_carakerja_ind);
        penjelasancarakerja1.setText(R.string.penjelasan_carakerja_ind);
        penjelasancarakerja2.setText(R.string.penjelasan_carakerja_ind2);
        gambarcontoh.setImageResource(R.drawable.gambarcontohind);
    }
}