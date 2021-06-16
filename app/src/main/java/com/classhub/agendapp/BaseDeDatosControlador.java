package com.classhub.agendapp;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BaseDeDatosControlador extends SQLiteOpenHelper {
    public BaseDeDatosControlador(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase baseDeDatos) {
        String crearTareas = "create table tareas (id integer primary key autoincrement not null, titulo text not null, descripcion text, prioridad int not null, tipo text not null, recordatorio text, fechaDeFin date not null, horaDeFin time not null)";
        String crearHorario = "create table horarios (id integer primary key autoincrement not null, titulo text not null, descripcion text, prioridad int not null, tipo text not null, recordatorio text, fechaDeFin date not null, horaDeFin time not null, ubicacion text, horaDeInicio time not null)";
        String crearEvento = "create table eventos (id integer primary key autoincrement not null, titulo text not null, descripcion text, prioridad int not null, tipo text not null, recordatorio text, fechaDeFin date not null, horaDeFin time not null, ubicacion text, horaDeInicio time not null, fechaDeInicio date not null)";

        try {
            baseDeDatos.execSQL(crearTareas);
            baseDeDatos.execSQL(crearHorario);
            baseDeDatos.execSQL(crearEvento);

        } catch (SQLException e) {
            Toast.makeText(null, "Error: " + e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase baseDeDatos, int oldVersion, int newVersion) {

    }
}
