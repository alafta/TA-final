package com.example.projectfrontend.classTrizforBAM;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfrontend.R;


public class trizforbamind extends AppCompatActivity{

    private TextView judulbam;
    private TextView subJudulbam;
    private TextView konten;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.halaman_trizforbam);
        judulbam = findViewById(R.id.judultrizforbam);
        subJudulbam = findViewById(R.id.subjudultrizforbam);
        konten = findViewById(R.id.penjelasantrizforbam);
        judulbam.setText(R.string.trizforbamind);
        subJudulbam.setText(R.string.subtitletrizforbamind);
        konten.setText(R.string.penjelasantrizforbam_ind);

        TextView judultrizuntukbisnis = findViewById(R.id.trizbisnis);
        TextView penjelasantrizuntukbisnis = findViewById(R.id.penjelasantrizbisnis);
        TextView judultrizmanajemen = findViewById(R.id.trizmanajemen);
        TextView penjelasantrizuntukmanajemen = findViewById(R.id.penjelasantrizmanajemen);

        judultrizuntukbisnis.setText(R.string.trizuntukbisnis);
        penjelasantrizuntukbisnis.setText(R.string.penjelasantrizforbisnis_ind);
        judultrizmanajemen.setText(R.string.trizuntukmanajemen);
        penjelasantrizuntukmanajemen.setText(R.string.penjelasantrizformanajemen_ind);
    }
}
