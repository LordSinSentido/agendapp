package com.classhub.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.InputStreamReader;

public class ConfiguracionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);
    }

    public void Evento (View view){
        Intent intent = new Intent(this, MostrarEventoActivity.class);
        startActivity(intent);
    }
}