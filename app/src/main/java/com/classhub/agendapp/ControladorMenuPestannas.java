package com.classhub.agendapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class ControladorMenuPestannas extends FragmentPagerAdapter {
    private int numeroDePestannas;

    public ControladorMenuPestannas(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        numeroDePestannas = behavior;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
        }
        return null;
    }

    @Override
    public int getCount() {
        return numeroDePestannas;
    }
}
