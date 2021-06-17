package com.classhub.agendapp;

import java.io.Serializable;

public class ActividadDatos implements Serializable {
    private String titulo;
    private String descripcion;
    private String tipo;
    private int id;
    private int imagen;

    public ActividadDatos() {
    }

    public ActividadDatos(String titulo, String descripcion, String tipo, int id, int imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.id = id;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
