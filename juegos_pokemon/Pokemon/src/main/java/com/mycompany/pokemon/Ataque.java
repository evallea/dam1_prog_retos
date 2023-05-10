package com.mycompany.pokemon;

public class Ataque {

    // ATRIBUTOS DE LOS ATAQUES:
    String nombre; // Nombre del ataque.
    int danio; // Daño que causa el ataque.
    String tipo; // Tipo de elemento del ataque.

    // CONSTRUCTOR DE LOS ATAQUES:
    public Ataque(String nombre, int danio, String tipo) {
        this.nombre = nombre;
        this.danio = danio;
        this.tipo = tipo;
    }

    // GETS Y SETS:
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDanio() {
        return danio;
    }

    public void setDanio(int danio) {
        this.danio = danio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
