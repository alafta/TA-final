package com.example.projectfrontend;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.projectfrontend.classCaseStudy.casestudyActivity;
import com.example.projectfrontend.classKontradiksiMatriks.tlkontradiksiActivity;
import com.example.projectfrontend.classParameter.parametersActivity;
import com.example.projectfrontend.classPrinsipalSolusi.prinsipalsolusiActivity;
import com.example.projectfrontend.classTrizforBAM.trizforbamActivity;
import com.example.projectfrontend.classWhatIsTriz.whatistrizActivity;


public class tlearningActivity extends AppCompatActivity{

    private Button btntl1;
    private Button btntl2;
    private Button btntl3;
    private Button btntl4;
    private Button btntl5;
    private Button btntl6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.halaman_trizlearning);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        btntl1 =  findViewById(R.id.tl1);
        btntl2 =  findViewById(R.id.tl2);
        btntl3 =  findViewById(R.id.tl3);
        btntl4 =  findViewById(R.id.tl4);
        btntl5 =  findViewById(R.id.tl5);
        btntl6 =  findViewById(R.id.tl6);
        btntl1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tlearningActivity.this, whatistrizActivity.class);
                startActivity(intent);
            }
        });
        btntl2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tlearningActivity.this, trizforbamActivity.class);
                startActivity(intent);
            }
        });
        btntl3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tlearningActivity.this, parametersActivity.class);
                startActivity(intent);
            }
        });
        btntl4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tlearningActivity.this, prinsipalsolusiActivity.class);
                startActivity(intent);
            }
        });
        btntl5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tlearningActivity.this, tlkontradiksiActivity.class);
                startActivity(intent);
            }
        });
        btntl6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(tlearningActivity.this, casestudyActivity.class);
                startActivity(intent);
            }
        });


    }
}
