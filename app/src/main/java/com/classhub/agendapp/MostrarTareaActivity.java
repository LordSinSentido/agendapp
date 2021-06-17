package com.classhub.agendapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MostrarTareaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_tarea);
    }

    public boolean onCreateOptionsMenu (Menu menu) {
        getMenuInflater().inflate(R.menu.vista_mostrardatos, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item) {
        Toast.makeText(this, "Si funciona", Toast.LENGTH_SHORT).show();
        return super.onOptionsItemSelected(item);
    }
}