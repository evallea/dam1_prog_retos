package com.mycompany.pokemon;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // CREACIÓN DE LOS OBJETOS DE LOS ATAQUES:
        Ataque golpeCabeza = new Ataque("GOLPE CABEZA", 6, "Normal");
        Ataque cuchillada = new Ataque("CUCHILLADA", 6, "Normal");
        Ataque hojaAfilada = new Ataque("HOJA AFILADA", 6, "Planta");
        Ataque hidrobomba = new Ataque("HIDROBOMBA", 10, "Agua");
        Ataque llamarada = new Ataque("LLAMARADA", 8, "Fuego");
        Ataque punioHielo = new Ataque("PUÑO HIELO", 4, "Hielo");
        Ataque punioTrueno = new Ataque("PUÑO TRUENO", 4, "Eléctrico");
        Ataque bofetonLodo = new Ataque("BOFETÓN LODO", 4, "Tierra");

        // CREACIÓN DE LOS OBJETOS DE LOS POKÉMON:
        Pokemon bulbasaur = new Pokemon("BULBASAUR", "Planta", golpeCabeza, hojaAfilada, bofetonLodo, 100, 45);
        Pokemon squirtle = new Pokemon("SQUIRTLE", "Agua", golpeCabeza, hidrobomba, punioHielo, 100, 40);
        Pokemon charmander = new Pokemon("CHARMANDER", "Fuego", cuchillada, llamarada, punioTrueno, 100, 50);

        // VARIABLES:
        Scanner lectura = new Scanner(System.in); // Se declara la variable "lectura" de la clase "Scanner" para poder hacer introducciones por teclado.
        Pokemon miPokemon = null; // Se declara la variable "miPokemon" de la clase "Pokemon" con valor "null" para guardar el Pokemon elegido por el jugador.
        Pokemon rivalPokemon = null; // Se declara la variable "rivalPokemon" de la clase "Pokemon" con valor "null" para guardar el Pokemon elegido por el rival.
        int opcionJugador; // Se declara la variable "opcion" para que pueda iniciarse el bucle "switch" por primera vez.
        String continuar; // Se declara la variable "continuar" para otorgarle valor posteriormente y así confirmar o denegar el comienzo del combate.

        // MENÚ DE SELECCIÓN DE LOS POKÉMON:
        do {

            do {

                System.out.println("\nElige un Pokémon (introduce el número):");
                System.out.println("1. BULBASAUR (Planta/Veneno)");
                System.out.println("2. SQUIRTLE (Agua)");
                System.out.println("3. CHARMANDER (Fuego)");

                opcionJugador = lectura.nextInt();

                switch (opcionJugador) {
                    case 1:
                        miPokemon = bulbasaur;
                        rivalPokemon = charmander;
                        break;
                    case 2:
                        miPokemon = squirtle;
                        rivalPokemon = bulbasaur;
                        break;
                    case 3:
                        miPokemon = charmander;
                        rivalPokemon = squirtle;
                        break;
                    default:
                        System.out.println("\n¡Debes elegir alguno de los tres!");
                        break;
                }

            } while (opcionJugador != 1 && opcionJugador != 2 && opcionJugador != 3);

            System.out.println("\nHas elegido a " + miPokemon.getNombre() + ":");

            if (miPokemon.getNombre().equals("BULBASAUR")) {

                System.out.println("((|))\n"
                        + "( { o_}\n"
                        + " u uu ");

            } else if (miPokemon.getNombre().equals("SQUIRTLE")) {

                System.out.println("  ( o_)\n"
                        + "@(#|\\)\\\n"
                        + "  u u");

            } else if (miPokemon.getNombre().equals("CHARMANDER")) {

                System.out.println("$  (o_)\n"
                        + " \\((´)´\n"
                        + "  u u");

            }

            System.out.println("\n¡Tu oponente ha elegido a " + rivalPokemon.getNombre() + "!");

            // MENSAJE DE COMIENZO DEL COMBATE:
            do {

                System.out.println("\n¿Comenzar el combate? (S/N)");

                continuar = lectura.next();

                if (continuar.equalsIgnoreCase("S")) {

                    // COMIENZA EL COMBATE:
                    Combate combate = new Combate(miPokemon, rivalPokemon); // Se crea el objeto combate con los parámetros que identifican al jugador y al rival.
                    combate.iniciarCombate(); // Se llama al método para iniciar el combate.

                } else if (!continuar.equalsIgnoreCase("S") && !continuar.equalsIgnoreCase("N")) {

                    System.out.println("\nDebes responder con 'S' para confirmar o 'N' para denegar.");
                }

            } while (!continuar.equalsIgnoreCase("S") && !continuar.equalsIgnoreCase("N"));

        } while (continuar.equalsIgnoreCase("N"));
    }
}
