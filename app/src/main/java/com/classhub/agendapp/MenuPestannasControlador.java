package com.classhub.agendapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MenuPestannasControlador extends FragmentPagerAdapter {
    private int numeroDePestannas;

    public MenuPestannasControlador(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        numeroDePestannas = behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new ProximosFragment();
            case 1:
                return new TareasFragment();
            case 2:
                return new HorariosFragment();
            case 3:
                return new EventosFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numeroDePestannas;
    }
}
