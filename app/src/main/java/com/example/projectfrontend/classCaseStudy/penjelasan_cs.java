package com.example.projectfrontend.classCaseStudy;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfrontend.R;

public class penjelasan_cs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getSupportActionBar();
        setContentView(R.layout.halaman_penjelasancs);

        String title_cs = getIntent().getStringExtra("title_cs");
        String problem_cs = getIntent().getStringExtra("problem_cs");
        String worsening_cs = getIntent().getStringExtra("worsening_cs");
        String improving_cs = getIntent().getStringExtra("improving_cs");
        String solution_cs = getIntent().getStringExtra("solution_cs");

        TextView titleCs = findViewById(R.id.tvjudulpenjelasancs);
        TextView problemCs = findViewById(R.id.subjudulcs1);
        TextView worseningCs = findViewById(R.id.isiworsening);
        TextView improvingCs = findViewById(R.id.isiimprovement);
        TextView solutionCs = findViewById(R.id.isisolusi);

        titleCs.setText(title_cs);
        problemCs.setText(problem_cs);
        worseningCs.setText(worsening_cs);
        improvingCs.setText(improving_cs);
        solutionCs.setText(solution_cs);
    }
}
