package com.classhub.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Toast;

public class ConfiguracionesActivity extends AppCompatActivity {
    private Spinner tareas;
    private Spinner eventos;
    private Spinner horarios;
    private CheckBox tareasCheck;
    private CheckBox eventosCheck;
    private CheckBox horariosCheck;

    private String[] opcionesDeRecordatorio = {"5 minutos antes", "10 minutos antes", "15 minutos antes", "30 minutos antes", "1 hora antes", "6 horas antes", "1 dia antes"};
    private String auxiliarDeTareas;
    private String auxiliarDeEventos;
    private String auxiliarDeHorarios;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuraciones);

        tareas = findViewById(R.id.configuracionesSpinnerTareas);
        eventos = findViewById(R.id.configuracionesSpinnerEventos);
        horarios = findViewById(R.id.configuracionesSpinnerHorarios);
        tareasCheck = findViewById(R.id.configuracionesCheckTareas);
        eventosCheck = findViewById(R.id.configuracionesCheckEventos);
        horariosCheck = findViewById(R.id.configuracionesCheckHorarios);

        SharedPreferences configuraciones = getSharedPreferences("config.dat", MODE_PRIVATE);

        ArrayAdapter<String> adaptadorDeTareas = new ArrayAdapter<>(this, R.layout.vista_spinners, opcionesDeRecordatorio);
        tareas.setAdapter(adaptadorDeTareas);
        auxiliarDeTareas = null;
        tareas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                auxiliarDeTareas = opcionesDeRecordatorio[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adaptadorDeEventos = new ArrayAdapter<>(this, R.layout.vista_spinners, opcionesDeRecordatorio);
        eventos.setAdapter(adaptadorDeEventos);
        auxiliarDeEventos = null;
        eventos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                auxiliarDeEventos = opcionesDeRecordatorio[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> adaptadorDeHorarios = new ArrayAdapter<>(this, R.layout.vista_spinners, opcionesDeRecordatorio);
        horarios.setAdapter(adaptadorDeHorarios);
        auxiliarDeHorarios = null;
        horarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                auxiliarDeHorarios = opcionesDeRecordatorio[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        tareasCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (tareasCheck.isChecked()) {
                    tareas.setVisibility(View.VISIBLE);
                } else {
                    tareas.setVisibility(View.GONE);
                }
            }
        });

        eventosCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (eventosCheck.isChecked()) {
                    eventos.setVisibility(View.VISIBLE);
                } else {
                    eventos.setVisibility(View.GONE);
                }
            }
        });

        horariosCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (horariosCheck.isChecked()) {
                    horarios.setVisibility(View.VISIBLE);
                } else {
                    horarios.setVisibility(View.GONE);
                }
            }
        });

        if (!configuraciones.getString("tareasRecordatorio", "").equals("Sin recordatorio")) {
            tareasCheck.setChecked(true);
            for (int i = 0; i < tareas.getCount(); i++) {
                if (tareas.getItemAtPosition(i).equals(configuraciones.getString("tareasRecordatorio", ""))) {
                    tareas.setSelection(i);
                }
            }
        }

        if (!configuraciones.getString("eventosRecordatorio", "").equals("Sin recordatorio")) {
            eventosCheck.setChecked(true);
            for (int i = 0; i < eventos.getCount(); i++) {
                if (eventos.getItemAtPosition(i).equals(configuraciones.getString("eventosRecordatorio", ""))) {
                    eventos.setSelection(i);
                }
            }
        }

        if (!configuraciones.getString("horariosRecordatorio", "").equals("Sin recordatorio")) {
            horariosCheck.setChecked(true);
            for (int i = 0; i < horarios.getCount(); i++) {
                if (horarios.getItemAtPosition(i).equals(configuraciones.getString("horariosRecordatorio", ""))) {
                    horarios.setSelection(i);
                }
            }
        }
    }

    public void gurdar (View view) {
        SharedPreferences configuraciones = getSharedPreferences("config.dat", MODE_PRIVATE);
        SharedPreferences.Editor editar = configuraciones.edit();
        if (tareasCheck.isChecked()) {
            editar.putString("tareasRecordatorio", auxiliarDeTareas);
        } else {
            editar.putString("tareasRecordatorio", "Sin recordatorio");
        }

        if (eventosCheck.isChecked()) {
            editar.putString("eventosRecordatorio", auxiliarDeEventos);
        } else {
            editar.putString("eventosRecordatorio", "Sin recordatorio");
        }

        if (horariosCheck.isChecked()) {
            editar.putString("horariosRecordatorio", auxiliarDeHorarios);
        } else {
            editar.putString("horariosRecordatorio", "Sin recordatorio");
        }

        editar.apply();
        finish();
    }
}