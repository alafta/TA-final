package com.example.projectfrontend.classWhatIsTriz;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfrontend.R;


public class whatistrizind extends AppCompatActivity{

    private TextView judulwit;
    private TextView kontenwit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.halaman_whatistriz);
        judulwit = findViewById(R.id.judulwhatistriz);
        kontenwit = findViewById(R.id.penjelasantriz);
        judulwit.setText(R.string.whatistrizind);
        kontenwit.setText(R.string.penjelasantrizind);
    }
}