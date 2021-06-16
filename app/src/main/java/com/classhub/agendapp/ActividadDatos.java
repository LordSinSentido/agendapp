package com.classhub.agendapp;

public class ActividadDatos {
    private String titulo;
    private String descripcion;
    private int imagen;

    public ActividadDatos(String titulo, String descripcion, int imagen) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getImagen() { return imagen; }
    public void setImagen(int foto) { this.imagen = imagen; }
}
