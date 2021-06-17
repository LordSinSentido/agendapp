package com.classhub.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ConfiguracionesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);
    }

    public void tareas (View view) {
        Intent intent = new Intent(this, MostrarTareaActivity.class);
        startActivity(intent);
    }
}