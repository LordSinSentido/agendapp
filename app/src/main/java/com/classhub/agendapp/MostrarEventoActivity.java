package com.classhub.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MostrarEventoActivity extends AppCompatActivity {
    private TextView titulo;
    private TextView descripcion;
    private TextView fechaDeInicio;
    private TextView horaDeInicio;
    private TextView fechaDeFin;
    private TextView horaDeFin;
    private TextView ubicacion;
    private TextView tipo;
    private TextView recordatorio;

    private int id;

    ActividadDatos datos;
    BaseDeDatosControlador admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_evento);
        this.setTitle("Datos del evento o examen");

        titulo = findViewById(R.id.mostrareventoTextoTituloR);
        descripcion = findViewById(R.id.mostrareventoTextoDescripcionR);
        fechaDeInicio = findViewById(R.id.mostrareventoFechaDeInicioR);
        horaDeInicio = findViewById(R.id.mostrareventoHoraDeInicioR);
        fechaDeFin = findViewById(R.id.mostrareventoTextoFechaDeFinR);
        horaDeFin = findViewById(R.id.mostrareventoTextoHoraDeFinR);
        ubicacion = findViewById(R.id.mostrareventoUbicacionR);
        tipo = findViewById(R.id.mostrareventoTextoTipoR);
        recordatorio = findViewById(R.id.mostrareventoTextoRecordatorioR);

        datos = (ActividadDatos) getIntent().getSerializableExtra("datos");
        admin = new BaseDeDatosControlador(this, "baseDeDatos.db", null, 1);

        id = datos.getId();

        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        Cursor datos = baseDeDatos.rawQuery("select * from eventos where id = " + id, null);

        int totalDeRegistros = datos.getCount();

        if (totalDeRegistros > 0) {
            datos.moveToFirst();
            titulo.setText(datos.getString(1));
            descripcion.setText(datos.getString(2));
            fechaDeInicio.setText(datos.getString(10));
            horaDeInicio.setText(datos.getString(9));
            fechaDeFin.setText(datos.getString(6));
            horaDeFin.setText(datos.getString(7));
            ubicacion.setText(datos.getString(8));
            tipo.setText(datos.getString(4));
            recordatorio.setText(datos.getString(5));
        }
        baseDeDatos.close();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.vista_mostrardatos, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();

        if (baseDeDatos.delete("eventos", "id = " + id, null) > 0) {
            Toast.makeText(this, "Se ha eliminado el elemento", Toast.LENGTH_SHORT).show();
            finish();
        }

        baseDeDatos.close();
        return super.onOptionsItemSelected(item);
    }

    public void editar (View view) {
        Intent tarea = new Intent(this, EditarEventoActivity.class);
        tarea.putExtra("id", id);
        startActivity(tarea);
    }

}