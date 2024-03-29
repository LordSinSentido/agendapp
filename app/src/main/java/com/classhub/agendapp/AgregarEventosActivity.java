package com.classhub.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class AgregarEventosActivity extends AppCompatActivity {
    private EditText titulo;
    private EditText descripcion;
    private EditText fechaDeInicio;
    private EditText horaDeInicio;
    private EditText fechaDeFin;
    private EditText horaDeFin;
    private EditText ubicacion;
    private CheckBox estadoDeRecordatorio;
    private Spinner tipo;
    private Spinner recordatorio;

    private String[] opcionesDeTipo = {"Evento", "Examen"};
    private String auxiliarDeTipo;

    private String[] opcionesDeRecordatorio = {"5 minutos antes", "10 minutos antes", "15 minutos antes", "30 minutos antes", "1 hora antes", "6 horas antes", "1 dia antes"};
    private String auxiliarDeRecordatorio;

    BaseDeDatosControlador admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_eventos);
        this.setTitle("Agregar nuevo evento o examen");

        titulo = findViewById(R.id.agregareventoEntradaTitulo);
        descripcion = findViewById(R.id.agregareventoEntradaDescripcion);
        fechaDeInicio = findViewById(R.id.agregareventoEntradaFechaDeInicio);
        horaDeInicio = findViewById(R.id.agregareventoEntradaHoraDeInicio);
        fechaDeFin = findViewById(R.id.agregareventoEntradaFechaDeFin);
        horaDeFin = findViewById(R.id.agregareventoEntradaHoraDeFin);
        ubicacion = findViewById(R.id.agregareventoEntradaUbicacion);
        estadoDeRecordatorio = findViewById(R.id.agregareventoCheckRecordatorio);
        tipo = findViewById(R.id.agregareventoSpinnerTipo);
        recordatorio = findViewById(R.id.agregareventoSpinnerRecordatorio);

        admin = new BaseDeDatosControlador(this, "baseDeDatos.db", null, 1);

        ArrayAdapter<String> adaptadorDeTipo = new ArrayAdapter<>(this, R.layout.vista_spinners, opcionesDeTipo);
        tipo.setAdapter(adaptadorDeTipo);
        auxiliarDeTipo = null;
        tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                auxiliarDeTipo = opcionesDeTipo[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adaptadorDeRecordatorio = new ArrayAdapter<>(this, R.layout.vista_spinners, opcionesDeRecordatorio);
        recordatorio.setAdapter(adaptadorDeRecordatorio);
        auxiliarDeRecordatorio = null;
        recordatorio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                auxiliarDeRecordatorio = opcionesDeRecordatorio[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        estadoDeRecordatorio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (estadoDeRecordatorio.isChecked()) {
                    recordatorio.setVisibility(View.VISIBLE);
                } else {
                    recordatorio.setVisibility(View.GONE);
                }
            }
        });

        SharedPreferences configuraciones = getSharedPreferences("config.dat", MODE_PRIVATE);
        estadoDeRecordatorio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (estadoDeRecordatorio.isChecked()) {
                    recordatorio.setVisibility(View.VISIBLE);
                } else {
                    recordatorio.setVisibility(View.GONE);
                }
            }
        });
        if (!configuraciones.getString("eventosRecordatorio", "").equals("Sin recordatorio")) {
            estadoDeRecordatorio.setChecked(true);
            for (int i = 0; i < recordatorio.getCount(); i++) {
                if (recordatorio.getItemAtPosition(i).equals(configuraciones.getString("eventosRecordatorio", ""))) {
                    recordatorio.setSelection(i);
                }
            }
        }
    }

    public void guardar (View view) {
        if (!titulo.getText().toString().isEmpty() && !fechaDeFin.getText().toString().isEmpty() && !horaDeFin.getText().toString().isEmpty() && !fechaDeInicio.getText().toString().isEmpty() && !horaDeInicio.getText().toString().isEmpty()) {
            SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
            ContentValues datosAGuardar = new ContentValues();

            datosAGuardar.put("titulo", titulo.getText().toString());
            if (!descripcion.getText().toString().isEmpty()) {
                datosAGuardar.put("descripcion", descripcion.getText().toString());
            } else {
                datosAGuardar.put("descripcion", "");
            }
            if (auxiliarDeTipo.equals("Evento")) {
                datosAGuardar.put("prioridad", 1);
            } else {
                datosAGuardar.put("prioridad", 0);
            }
            datosAGuardar.put("tipo", auxiliarDeTipo);
            if (estadoDeRecordatorio.isChecked()) {
                datosAGuardar.put("recordatorio", auxiliarDeRecordatorio);
            } else {
                datosAGuardar.put("recordatorio", "Sin recordatorio");
            }
            datosAGuardar.put("fechaDeFin", fechaDeFin.getText().toString());
            datosAGuardar.put("horaDeFin", horaDeFin.getText().toString());
            if (!ubicacion.getText().toString().isEmpty()) {
                datosAGuardar.put("ubicacion", ubicacion.getText().toString());
            } else {
                datosAGuardar.put("ubicacion", "");
            }
            datosAGuardar.put("fechaDeInicio", fechaDeInicio.getText().toString());
            datosAGuardar.put("horaDeInicio", horaDeInicio.getText().toString());

            if (baseDeDatos != null) {
                try {
                    baseDeDatos.insert("eventos", null, datosAGuardar);
                    baseDeDatos.close();
                    Toast.makeText(this, R.string.mensajeAgrego, Toast.LENGTH_SHORT).show();
                    finish();
                } catch (SQLException e){
                    Snackbar.make(this, view, "Error: " + e.toString(), BaseTransientBottomBar.LENGTH_LONG);
                    baseDeDatos.close();
                }
            }
        } else {
            Toast.makeText(this, R.string.mensajeFaltanDatos, Toast.LENGTH_SHORT).show();
        }
    }
}