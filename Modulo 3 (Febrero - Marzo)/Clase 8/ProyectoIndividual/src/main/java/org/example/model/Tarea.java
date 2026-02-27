package org.example.model;

public class Tarea {
    private static int contadorId = 0;

    private int id;
    private String nombre;
    private String descripcion;
    private boolean estadoCompletado;

    public Tarea() {
    }

    public Tarea(String nombre, String descripcion) {
        this.id = ++contadorId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estadoCompletado = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isEstadoCompletado() {
        return estadoCompletado;
    }

    public void setEstadoCompletado(boolean estadoCompletado) {
        this.estadoCompletado = estadoCompletado;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", estadoCompletado=" + estadoCompletado +
                '}';
    }
}
