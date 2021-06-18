package com.classhub.agendapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HorariosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HorariosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    LinearLayout mensaje;
    RecyclerView recyclerHorarios;
    ArrayList<ActividadDatos> listaActividades;
    BaseDeDatosControlador admin;
    int cantidad = 0;

    public HorariosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HorariosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HorariosFragment newInstance(String param1, String param2) {
        HorariosFragment fragment = new HorariosFragment();
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
        View vista = inflater.inflate(R.layout.fragment_horarios, container, false);

        mensaje = vista.findViewById(R.id.horariosSinActividades);
        admin = new BaseDeDatosControlador(getContext(), "baseDeDatos.db", null, 1);

        ActividadDatos actividadDatos = null;
        listaActividades = new ArrayList<>();
        recyclerHorarios = vista.findViewById(R.id.recyclerIdHorarios);
        recyclerHorarios.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        SQLiteDatabase db = admin.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM horarios order by fechaDeFin asc, horaDeFin asc", null);

        cantidad = cursor.getCount();

        if(cantidad>0){
            mensaje.setVisibility(View.GONE);
            cursor.moveToFirst();
            do{
                actividadDatos = new ActividadDatos();
                actividadDatos.setTitulo(cursor.getString(1));
                actividadDatos.setDescripcion(cursor.getString(2));
                actividadDatos.setTipo(cursor.getString(4));
                actividadDatos.setId(cursor.getInt(0));
                actividadDatos.setImagen(R.drawable.icono_tareas);
                listaActividades.add(actividadDatos);
            }while(cursor.moveToNext());
        }
        else {
            mensaje.setVisibility(View.VISIBLE);
        }
        db.close();

        AdapterDatosProximos adpater = new AdapterDatosProximos(listaActividades);
        adpater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tarea = new Intent(getContext(), MostrarHorarioActivity.class);
                tarea.putExtra("datos", listaActividades.get(recyclerHorarios.getChildAdapterPosition(view)));
                startActivity(tarea);
            }
        });
        recyclerHorarios.setAdapter(adpater);

        return vista;
    }
}