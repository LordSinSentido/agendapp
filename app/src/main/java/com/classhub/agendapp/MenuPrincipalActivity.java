package com.classhub.agendapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class MenuPrincipalActivity extends AppCompatActivity {
    private TabLayout pestannas;
    private ViewPager actividades;
    ControladorMenuPestannas controlador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        pestannas = findViewById(R.id.menuprincipalTabLayoutPestannas);
        actividades = findViewById(R.id.menuprincipalViewPagerActividades);

        controlador = new ControladorMenuPestannas(getSupportFragmentManager(), pestannas.getTabCount());
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
    }
}