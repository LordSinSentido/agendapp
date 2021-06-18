package com.classhub.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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

        SharedPreferences configuraciones = getSharedPreferences("config.dat", MODE_PRIVATE);

        if (!configuraciones.getBoolean("existe", false)) {
            SharedPreferences.Editor editar = configuraciones.edit();
            editar.putString("tareasRecordatorio", "Sin recordatorio");
            editar.putString("eventosRecordatorio", "Sin recordatorio");
            editar.putString("horariosRecordatorio", "Sin recordatorio");
            editar.putBoolean("existe", true);
            editar.apply();
        }

        Timer espera = new Timer();
        espera.schedule(actividad, 2000);
    }
}