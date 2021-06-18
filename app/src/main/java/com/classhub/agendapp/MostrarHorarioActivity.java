package com.classhub.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MostrarHorarioActivity extends AppCompatActivity {
    private TextView titulo;
    private TextView descripcion;
    private TextView fechaDeInicio;
    private TextView horaDeInicio;
    private TextView horaDeFin;
    private TextView ubicacion;
    private TextView recordatorio;

    private int id;

    ActividadDatos datos;
    BaseDeDatosControlador admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_horario);

        titulo = findViewById(R.id.mostrarhorarioTextoTituloR);
        descripcion = findViewById(R.id.mostrarhorarioTextoDescripcionR);
        fechaDeInicio = findViewById(R.id.mostrarhorarioTextoFechaDeInicioR);
        horaDeInicio = findViewById(R.id.mostrarhorarioHoraDeInicioR);
        horaDeFin = findViewById(R.id.mostrarhorarioTextoHoraDeFinR);
        ubicacion = findViewById(R.id.mostrarhorarioUbicacionR);
        recordatorio = findViewById(R.id.mostrarhorarioTextoRecordatorioR);

        datos = (ActividadDatos) getIntent().getSerializableExtra("datos");
        admin = new BaseDeDatosControlador(this, "baseDeDatos.db", null, 1);

        id = datos.getId();

        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();
        Cursor datos = baseDeDatos.rawQuery("select * from horarios where id = " + id, null);

        int totalDeRegistros = datos.getCount();

        if (totalDeRegistros > 0) {
            datos.moveToFirst();
            titulo.setText(datos.getString(1));
            descripcion.setText(datos.getString(2));
            fechaDeInicio.setText(datos.getString(6));
            horaDeInicio.setText(datos.getString(7));
            horaDeFin.setText(datos.getString(8));
            ubicacion.setText(datos.getString(9));
            recordatorio.setText(datos.getString(5));
        }
        baseDeDatos.close();
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.vista_mostrardatos, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        SQLiteDatabase baseDeDatos = admin.getReadableDatabase();

        if (baseDeDatos.delete("horarios", "id = " + id, null) > 0) {
            Toast.makeText(this, "Se ha eliminado el elemento", Toast.LENGTH_SHORT).show();
            finish();
        }

        baseDeDatos.close();
        return super.onOptionsItemSelected(item);
    }

    public void editar (View view) {
        Intent tarea = new Intent(this, EditarHorarioActivity.class);
        tarea.putExtra("id", id);
        startActivity(tarea);
    }
}