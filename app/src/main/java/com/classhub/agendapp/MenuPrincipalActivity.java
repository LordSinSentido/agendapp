package com.classhub.agendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class MenuPrincipalActivity extends AppCompatActivity {
    private TabLayout pestannas;
    private ViewPager actividades;
    private FloatingActionButton agregar;
    private FloatingActionButton agregarTarea;
    private FloatingActionButton agregarEvento;
    private FloatingActionButton agregarHorario;

    private boolean estadoBotonAgregar = false;

    ControladorMenuPestannasClass controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        pestannas = findViewById(R.id.menuprincipalTabLayoutPestannas);
        actividades = findViewById(R.id.menuprincipalViewPagerActividades);
        agregar = findViewById(R.id.menuprincipalBotonAgregar);
        agregarTarea = findViewById(R.id.menuprincipalBotonAgregarTarea);
        agregarEvento = findViewById(R.id.menuprincipalBotonAgregarEvento);
        agregarHorario = findViewById(R.id.menuprincipalBotonAgregarHorario);

        controlador = new ControladorMenuPestannasClass(getSupportFragmentManager(), pestannas.getTabCount());
        actividades.setAdapter(controlador);

        pestannas.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                actividades.setCurrentItem(tab.getPosition());

                switch (tab.getPosition()){
                    case 0:
                        controlador.notifyDataSetChanged();
                        break;
                    case 1:
                        controlador.notifyDataSetChanged();
                        break;
                    case 2:
                        controlador.notifyDataSetChanged();
                        break;
                    case 3:
                        controlador.notifyDataSetChanged();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        actividades.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(pestannas));

        agregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (estadoBotonAgregar) {
                    agregar.setRotation(0);
                    agregarTarea.setVisibility(View.INVISIBLE);
                    agregarEvento.setVisibility(View.INVISIBLE);
                    agregarHorario.setVisibility(View.INVISIBLE);
                    estadoBotonAgregar = false;
                } else {
                    agregar.setRotation(45);
                    agregarTarea.setVisibility(View.VISIBLE);
                    agregarEvento.setVisibility(View.VISIBLE);
                    agregarHorario.setVisibility(View.VISIBLE);
                    estadoBotonAgregar = true;
                }
            }
        });
        agregarTarea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IraAgregarTarea = new Intent (MenuPrincipalActivity.this, AgregarTareasActivity.class);
                startActivity(IraAgregarTarea);
                agregar.setRotation(0);
                agregarTarea.setVisibility(View.INVISIBLE);
                agregarEvento.setVisibility(View.INVISIBLE);
                agregarHorario.setVisibility(View.INVISIBLE);
                estadoBotonAgregar = false;
            }
        });
        agregarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IraAgregarEvento = new Intent(MenuPrincipalActivity.this, AgregarEventosActivity.class);
                startActivity(IraAgregarEvento);
                agregar.setRotation(0);
                agregarTarea.setVisibility(View.INVISIBLE);
                agregarEvento.setVisibility(View.INVISIBLE);
                agregarHorario.setVisibility(View.INVISIBLE);
                estadoBotonAgregar = false;
            }
        });
        agregarHorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IraAgregarHorario = new Intent(MenuPrincipalActivity.this, AgregarHorariosActivity.class);
                startActivity(IraAgregarHorario);
                agregar.setRotation(0);
                agregarTarea.setVisibility(View.INVISIBLE);
                agregarEvento.setVisibility(View.INVISIBLE);
                agregarHorario.setVisibility(View.INVISIBLE);
                estadoBotonAgregar = false;
            }
        });
    }

    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.vista_menu_principal_overflow, menu);
        return true;
    }

    public boolean onOptionsItemSelected (MenuItem item){
        Intent configuraciones = new Intent(this, ConfiguracionesActivity.class);
        startActivity(configuraciones);
        return super.onOptionsItemSelected(item);
    }
}