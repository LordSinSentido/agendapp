package com.classhub.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("");

        TimerTask actividad = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, MenuPrincipalActivity.class);
                startActivity(intent);
                finish();
            }
        };

        Timer espera = new Timer();
        espera.schedule(actividad, 2000);
    }
}