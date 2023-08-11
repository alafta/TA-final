package com.example.projectfrontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfrontend.classCaseStudy.CasestudyInd;
import com.example.projectfrontend.classKontradiksiMatriks.tlkontradiksiInd;
import com.example.projectfrontend.classParameter.parameterind;
import com.example.projectfrontend.classPrinsipalSolusi.prinsipalsolusiInd;
import com.example.projectfrontend.classTrizforBAM.trizforbamind;
import com.example.projectfrontend.classWhatIsTriz.whatistrizind;


public class tlearning_ind extends AppCompatActivity{

    private TextView judul1;
    private TextView judul2;
    private Button btntl1;
    private Button btntl2;
    private Button btntl3;
    private Button btntl4;
    private Button btntl5;
    private Button btntl6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.halaman_trizlearning);
        judul1= findViewById(R.id.trizHome);
        judul2=findViewById(R.id.learning);
        btntl1 =  findViewById(R.id.tl1);
        btntl2 =  findViewById(R.id.tl2);
        btntl3 =  findViewById(R.id.tl3);
        btntl4 =  findViewById(R.id.tl4);
        btntl5 =  findViewById(R.id.tl5);
        btntl6 =  findViewById(R.id.tl6);
        judul1.setText(R.string.judultlearning1ind);
        judul2.setText(R.string.judultlearning2ind);
        btntl1.setText(R.string.whatistrizind);
        btntl2.setText(R.string.trizforbamind);
        btntl3.setText(R.string.ptrizind);
        btntl4.setText(R.string.pstrizind);
        btntl5.setText(R.string.contradictionMind);
        btntl6.setText(R.string.casestudyind);
        btntl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tlearning_ind.this, whatistrizind.class);
                startActivity(intent);
            }
        });
        btntl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tlearning_ind.this, trizforbamind.class);
                startActivity(intent);
            }
        });
        btntl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tlearning_ind.this, parameterind.class);
                startActivity(intent);
            }
        });
        btntl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tlearning_ind.this, prinsipalsolusiInd.class);
                startActivity(intent);
            }
        });
        btntl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tlearning_ind.this, tlkontradiksiInd.class);
                startActivity(intent);
            }
        });
        btntl6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tlearning_ind.this, CasestudyInd.class);
                startActivity(intent);
            }
        });
        getIncomingExtra();
    }
    private void getIncomingExtra() {
        if (getIntent().hasExtra("titleTl1indo") && getIntent().hasExtra("titleTl2indo") && getIntent().hasExtra("titleTl3indo")
                && getIntent().hasExtra("titleTl4indo") && getIntent().hasExtra("titleTl5indo") && getIntent().hasExtra("titleTl6indo")) {
            String btntl1indo = getIntent().getStringExtra("titleTl1indo");
            String btntl12ndo = getIntent().getStringExtra("titleTl2indo");
            String btntl3indo = getIntent().getStringExtra("titleTl3indo");
            String btntl4indo = getIntent().getStringExtra("titleTl4indo");
            String btntl5indo = getIntent().getStringExtra("titleTl5indo");
            String btntl6indo = getIntent().getStringExtra("titleTl6indo");
            btntl1.setText(btntl1indo);
            btntl2.setText(btntl12ndo);
            btntl3.setText(btntl3indo);
            btntl4.setText(btntl4indo);
            btntl5.setText(btntl5indo);
            btntl6.setText(btntl6indo);
        }
    }
}
