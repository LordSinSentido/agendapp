package com.classhub.agendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.Calendar;

public class AgregarTareasActivity extends AppCompatActivity {
    private EditText titulo;
    private EditText descripcion;
    private EditText fechaDeFin;
    private EditText horaDeFin;
    private CheckBox estadoDeRecordatorio;
    private Spinner tipo;
    private Spinner recordatorio;

    private String[] opcionesDeTipo = {"Tarea", "Proyecto"};
    private String auxiliarDeTipo;

    private String[] opcionesDeRecordatorio = {"5 minutos antes", "10 minutos antes", "15 minutos antes", "30 minutos antes", "1 hora antes", "6 horas antes", "1 dia antes"};
    private String auxiliarDeRecordatorio;

    BaseDeDatosControlador admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_tareas);
        this.setTitle("Agregar nueva tarea o proyecto");

        titulo = findViewById(R.id.agregartareaEntradaTitulo);
        descripcion = findViewById(R.id.agregartareaEntradaDescripcion);
        fechaDeFin = findViewById(R.id.agregartareaEntradaFechaDeFin);
        horaDeFin = findViewById(R.id.agregartareaEntradaHoraDeFin);
        estadoDeRecordatorio = findViewById(R.id.agregartareaCheckRecordatorio);
        tipo = findViewById(R.id.agregartareaSpinnerTipo);
        recordatorio = findViewById(R.id.agregartareaSpinnerRecordatorio);

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
    }

    public void guardar (View view) {
        if (!titulo.getText().toString().isEmpty() && !fechaDeFin.getText().toString().isEmpty() && !horaDeFin.getText().toString().isEmpty()) {
            SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
            ContentValues datosAGuardar = new ContentValues();

            datosAGuardar.put("titulo", titulo.getText().toString());
            if (!descripcion.getText().toString().isEmpty()) {
                datosAGuardar.put("descripcion", descripcion.getText().toString());
            } else {
                datosAGuardar.put("descripcion", "");
            }
            if (auxiliarDeTipo.equals("Tarea")) {
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

            if (baseDeDatos != null) {
                try {
                    baseDeDatos.insert("tareas", null, datosAGuardar);
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