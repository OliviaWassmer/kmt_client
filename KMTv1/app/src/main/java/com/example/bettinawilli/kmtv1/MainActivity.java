package com.example.bettinawilli.kmtv1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button freitagBtn;
    Button samstagBtn;
    Button sonntagBtn;
    Button infoBtn;
    Button lageBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button Freitag
        freitagBtn = (Button) findViewById(R.id.freitagBtn);
        freitagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent freitagI = new Intent(getApplicationContext(), FreitagActivity.class);
                startActivity(freitagI);
            }
        });

        // Button Samstag
        samstagBtn = (Button) findViewById(R.id.samstagBtn);
        samstagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent samstagI = new Intent(getApplicationContext(), SamstagActivity.class);
                startActivity(samstagI);
            }
        });


        // Button Sonntag
        sonntagBtn = (Button) findViewById(R.id.sonntagBtn);
        sonntagBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sonntagI = new Intent(getApplicationContext(), SonntagActitvity.class);
                startActivity(sonntagI);
            }
        });

        // Button Informationen
        infoBtn = (Button) findViewById(R.id.infoBtn);
        infoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent infoI = new Intent(getApplicationContext(), InfoActivity.class);
                startActivity(infoI);
            }
        });

        // Button Lageplan
        lageBtn = (Button) findViewById(R.id.lageBtn);
        lageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent lageI = new Intent(getApplicationContext(), LageplanActivity.class);
                startActivity(lageI);
            }
        });
    }
}
