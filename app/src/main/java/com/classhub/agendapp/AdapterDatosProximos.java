package com.classhub.agendapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDatosProximos extends RecyclerView.Adapter<AdapterDatosProximos.ViewHolderDatos> implements View.OnClickListener {

    ArrayList<ActividadDatos> listaActividades;
    private View.OnClickListener listener;

    public AdapterDatosProximos(ArrayList<ActividadDatos> listaActividades) {
        this.listaActividades = listaActividades;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.vista_lista, null, false);
        view.setOnClickListener(this);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.titulo.setText(listaActividades.get(position).getTitulo());
        holder.descripcion.setText(listaActividades.get(position).getDescripcion());
        holder.icono.setImageResource(listaActividades.get(position).getImagen());
    }

    @Override
    public int getItemCount() {
        return listaActividades.size();
    }

    public void setOnClickListener(View.OnClickListener listenerDato){
        this.listener = listenerDato;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView titulo, descripcion;
        ImageView icono;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tituloProximoFragment);
            descripcion = itemView.findViewById(R.id.descripcionProximoFragment);
            icono = itemView.findViewById(R.id.iconoProximoFragment);
        }
    }
}
