package com.classhub.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MostrarTareaActivity extends AppCompatActivity {
    private TextView titulo;
    private TextView descripcion;
    private TextView fechaDeFin;
    private TextView horaDeFin;
    private TextView tipo;
    private TextView recordatorio;

    private int id;

    ActividadDatos datos;
    BaseDeDatosControlador admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_tarea);

        titulo = findViewById(R.id.mostrartareaTextoTituloR);
        descripcion = findViewById(R.id.mostrartareaTextoDescripcionR);
        fechaDeFin = findViewById(R.id.mostrartareaTextoFechaDeFinR);
        horaDeFin = findViewById(R.id.mostrartareaTextoHoraDeFinR);
        tipo = findViewById(R.id.mostrartareaTextoTipoR);
        recordatorio = findViewById(R.id.mostrartareaTextoRecordatorioR);

        datos = (ActividadDatos) getIntent().getSerializableExtra("datos");
        admin = new BaseDeDatosControlador(this, "baseDeDatos.db", null, 1);

        id = datos.getId();

        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        Cursor datos = baseDeDatos.rawQuery("select * from tareas where id = " + id, null);

        int totalDeRegistros = datos.getCount();

        if (totalDeRegistros > 0) {
            datos.moveToFirst();
            titulo.setText(datos.getString(1));
            descripcion.setText(datos.getString(2));
            fechaDeFin.setText(datos.getString(6));
            horaDeFin.setText(datos.getString(7));
            tipo.setText(datos.getString(4));
            recordatorio.setText(datos.getString(5));
        }
        baseDeDatos.close();
    }

    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.vista_mostrardatos, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item) {
        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();

        if (baseDeDatos.delete("tareas", "id = " + id, null) > 0) {
            Toast.makeText(this, "Se ha eliminado el elemento", Toast.LENGTH_SHORT).show();
            finish();
        }

        baseDeDatos.close();
        return super.onOptionsItemSelected(item);
    }


}