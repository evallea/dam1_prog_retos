package com.mycompany.pokemon;

public class Pokemon {

    // ATRIBUTOS DE LOS POKÉMON:
    String nombre; // Nombre del Pokémon.
    String tipo; // Tipo de elemento del Pokémon.
    Ataque ataque1; // Ataque 1.
    Ataque ataque2; // Ataque 2.
    Ataque ataque3; // Ataque 3.
    int vida; // Puntos de salud del Pokémon.
    int velocidad; // Velocidad del Pokémon.

    // CONSTRUCTOR DE LOS POKÉMON:
    public Pokemon(String nombre, String tipo, Ataque ataque1, Ataque ataque2, Ataque ataque3, int vida, int velocidad) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.ataque1 = ataque1;
        this.ataque2 = ataque2;
        this.ataque3 = ataque3;
        this.vida = vida;
        this.velocidad = velocidad;
    }

    // GETS Y SETS:
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Ataque getAtaque1() {
        return ataque1;
    }

    public void setAtaque1(Ataque ataque1) {
        this.ataque1 = ataque1;
    }

    public Ataque getAtaque2() {
        return ataque2;
    }

    public void setAtaque2(Ataque ataque2) {
        this.ataque2 = ataque2;
    }

    public Ataque getAtaque3() {
        return ataque3;
    }

    public void setAtaque3(Ataque ataque3) {
        this.ataque3 = ataque3;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(int velocidad) {
        this.velocidad = velocidad;
    }
}
