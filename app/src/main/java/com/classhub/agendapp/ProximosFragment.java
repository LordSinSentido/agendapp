package com.classhub.agendapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProximosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProximosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView recyclerProximos;
    ArrayList<ActividadDatos> listaActividades;

    public ProximosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProximosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProximosFragment newInstance(String param1, String param2) {
        ProximosFragment fragment = new ProximosFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_proximos, container, false);

        listaActividades = new ArrayList<>();
        recyclerProximos = vista.findViewById(R.id.recyclerIdProximos);
        recyclerProximos.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        llenarLista();

        AdapterDatosProximos adpater = new AdapterDatosProximos(listaActividades);
        adpater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),
                        "Abriendo: " + listaActividades.get(recyclerProximos.getChildAdapterPosition(view)).getTitulo(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        recyclerProximos.setAdapter(adpater);

        return vista;
    }

    private void llenarLista() {

        listaActividades.add(new ActividadDatos("Planeación estratégica y Habilidades directivas", "De 9:30am a 11:10am", R.drawable.icono_horario));
        listaActividades.add(new ActividadDatos("Matemáticas", "Entregar los ejercicios de la página 34 y 35", R.drawable.icono_tareas));
        listaActividades.add(new ActividadDatos("Examen de ciencias", "De 12:50am a 13:40am", R.drawable.icono_eventos));
    }
}