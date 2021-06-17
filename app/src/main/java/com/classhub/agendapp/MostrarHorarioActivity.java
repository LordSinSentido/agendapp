package com.classhub.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MostrarHorarioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_horario);
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.vista_mostrardatos, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        Toast.makeText(this, "Si funciona", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}