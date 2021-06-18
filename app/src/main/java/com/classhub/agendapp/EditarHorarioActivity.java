package com.classhub.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
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

public class EditarHorarioActivity extends AppCompatActivity {
    private EditText titulo;
    private EditText descripcion;
    private EditText fechaDeInicio;
    private EditText horaDeInicio;
    private EditText horaDeFin;
    private EditText ubicacion;
    private CheckBox estadoDeRecordatorio;
    private Spinner recordatorio;

    private String[] opcionesDeRecordatorio = {"5 minutos antes", "10 minutos antes", "15 minutos antes", "30 minutos antes", "1 hora antes", "6 horas antes", "1 dia antes"};
    private String auxiliarDeRecordatorio;

    BaseDeDatosControlador admin;

    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_horario);

        titulo = findViewById(R.id.editarhorarioEntradaTitulo);
        descripcion = findViewById(R.id.editarhorarioEntradaDescripcion);
        fechaDeInicio = findViewById(R.id.editarhorarioEntradaFechaDeInicio);
        horaDeInicio = findViewById(R.id.editarhorarioEntradaHoraDeInicio);
        horaDeFin = findViewById(R.id.editarhorarioEntradaHoraDeFin);
        ubicacion = findViewById(R.id.editarhorarioEntradaUbicacion);
        estadoDeRecordatorio = findViewById(R.id.editarhorarioCheckRecordatorio);
        recordatorio = findViewById(R.id.editarhorarioSpinnerRecordatorio);

        id = (int) getIntent().getSerializableExtra("id");

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

        admin = new BaseDeDatosControlador(this, "baseDeDatos.db", null, 1);

        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        Cursor busqueda = baseDeDatos.rawQuery("select * from horarios where id = " + id, null);

        if (busqueda.moveToFirst()) {
            titulo.setText(busqueda.getString(1));
            descripcion.setText(busqueda.getString(2));
            fechaDeInicio.setText(busqueda.getString(6));
            horaDeInicio.setText(busqueda.getString(7));
            horaDeFin.setText(busqueda.getString(8));
            ubicacion.setText(busqueda.getString(9));

            if (!busqueda.getString(5).equals("Sin recordatorio")) {
                estadoDeRecordatorio.setChecked(true);
                for (int i = 0; i < recordatorio.getCount(); i++) {
                    if (recordatorio.getItemAtPosition(i).equals(busqueda.getString(5))) {
                        recordatorio.setSelection(i);
                    }
                }
            }
        }
        baseDeDatos.close();
    }

    public void guardar (View view) {
        if (!titulo.getText().toString().isEmpty() && !fechaDeInicio.getText().toString().isEmpty() && !horaDeInicio.getText().toString().isEmpty() && !horaDeFin.getText().toString().isEmpty()) {
            SQLiteDatabase baseDeDatos = admin.getWritableDatabase();
            ContentValues datosAGuardar = new ContentValues();

            datosAGuardar.put("titulo", titulo.getText().toString());
            if (!descripcion.getText().toString().isEmpty()) {
                datosAGuardar.put("descripcion", descripcion.getText().toString());
            } else {
                datosAGuardar.put("descripcion", "");
            }
            datosAGuardar.put("prioridad", 1);
            datosAGuardar.put("tipo", "Horario");
            datosAGuardar.put("fechaDeFin", fechaDeInicio.getText().toString());
            datosAGuardar.put("horaDeInicio", horaDeFin.getText().toString());
            datosAGuardar.put("horaDeFin", horaDeFin.getText().toString());
            if (!ubicacion.getText().toString().isEmpty()) {
                datosAGuardar.put("ubicacion", ubicacion.getText().toString());
            } else {
                datosAGuardar.put("ubicacion", "");
            }
            if (estadoDeRecordatorio.isChecked()) {
                datosAGuardar.put("recordatorio", auxiliarDeRecordatorio);
            } else {
                datosAGuardar.put("recordatorio", "Sin recordatorio");
            }

            if (baseDeDatos != null) {
                try {
                    baseDeDatos.update("horarios", datosAGuardar, "id = " + id, null);
                    baseDeDatos.close();
                    Toast.makeText(this, R.string.mensajeEdito, Toast.LENGTH_SHORT).show();
                    finish();
                } catch (SQLException e){
                    Snackbar.make(this, view, "Error: " + e.toString(), BaseTransientBottomBar.LENGTH_LONG);
                    baseDeDatos.close();
                }
            }
        } else {
            Toast.makeText(this, R.string.mensajeFaltanDatos, Toast.LENGTH_SHORT).show();        }
    }
}