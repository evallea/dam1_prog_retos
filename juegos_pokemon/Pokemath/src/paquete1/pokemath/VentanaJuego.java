package paquete1.pokemath;

import javax.swing.*;
import java.awt.event.*;
import java.util.Random;
import java.sql.*;

/**
 * Esta clase representa la ventana de juego del PokéMath. El juego permite a los jugadores resolver operaciones matemáticas y competir contra un oponente.
 */
public class VentanaJuego extends javax.swing.JFrame {

    // Conexión con la BBDD:
    ConexionMySQL conDB = new ConexionMySQL();
    Connection conn = conDB.conectarMySQL();

    // Variables:
    Puntuaciones puntuaciones = new Puntuaciones();
    Timer temporizador;
    String nombreJugador;
    String operacionActual;
    int tiempoRestante;
    int vidaJugador = 100;
    int vidaOponente = 100;
    int respuestaCorrecta;
    int nivel = 1;
    int aciertos = 0;
    int fallos = 0;

    /**
     * Constructor de VentanaJuego que toma un objeto Puntuaciones como argumento.
     *
     * @param puntuaciones Objeto Puntuaciones para almacenar y mostrar las puntuaciones del jugador.
     */
    public VentanaJuego(Puntuaciones puntuaciones) {

        initComponents();

        generarOperacion();

        setLocationRelativeTo(null); // Centra la aplicación.

        barraJugador.setValue(100); // Valor inicial de la barra de vida del jugador.
        barraOponente.setValue(100); // Valor inicial de la barra de vida del oponente.

        this.puntuaciones = puntuaciones;

        tiempoRestante = 10;
        temporizador = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tiempoRestante--;
                actualizarTiempoRestante();
                if (tiempoRestante <= 0) {
                    verificarRespuesta(null);
                    tiempoRestante = 10;
                }
            }
        });
        actualizarTiempoRestante();
        temporizador.start();

        // El siguiente código es para que se puedan enviar las operaciones pulsando ENTER:
        campoRespuestaJugador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    botonAtacar.doClick(); // Simula un clic en el botón "ATACAR".
                }
            }
        });
    }

    /**
     * Establece el nombre del jugador.
     *
     * @param nombreJugador El nombre del jugador.
     */
    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    /**
     * Genera una operación matemática aleatoria basada en el nivel actual.
     */
    public void generarOperacion() {
        Random random = new Random();
        int num1 = random.nextInt(12) + 1;
        int num2 = random.nextInt(12) + 1;
        int operador = 0;
        if (nivel == 1) {
            operador = random.nextInt(2); // 0 = suma, 1 = resta.
        } else if (nivel == 2) {
            operador = 2 + random.nextInt(2); // 2 = multiplicación, 3 = división.
        }
        switch (operador) {
            case 0:
                operacionActual = num1 + " + " + num2;
                respuestaCorrecta = num1 + num2;
                break;
            case 1:
                operacionActual = num1 + " - " + num2;
                respuestaCorrecta = num1 - num2;
                break;
            case 2:
                operacionActual = num1 + " * " + num2;
                respuestaCorrecta = num1 * num2;
                break;
            case 3:
                operacionActual = (num1 * num2) + " / " + num2;
                respuestaCorrecta = num1;
                break;
        }
        textoOperacionIA.setText(operacionActual);
    }

    /**
     * Verifica si la respuesta proporcionada por el jugador es correcta o no. Actualiza las barras de vida del jugador y del oponente en consecuencia.
     *
     * @param respuestaJugador La respuesta proporcionada por el jugador, o null si el jugador no proporcionó una respuesta válida.
     */
    public void verificarRespuesta(Integer respuestaJugador) {
        if (respuestaJugador == null || !respuestaJugador.equals(respuestaCorrecta)) {
            vidaJugador -= 10;
            fallos++;
        } else {
            vidaOponente -= 10;
            aciertos++;
        }

        if (vidaJugador <= 0) {

            temporizador.stop();

            puntuaciones.setVentanaJuego(this);
            puntuaciones.setVidaRestante(vidaJugador);
            puntuaciones.setAciertos(aciertos);
            puntuaciones.setFallos(fallos);

            actualizarBarrasVida(vidaJugador, vidaOponente);
            String titulo = "¡DERROTA!";
            String mensaje = "TU POKÉMON SE HA DEBILITADO...";
            JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.PLAIN_MESSAGE);

            this.setVisible(false);
            puntuaciones.setVisible(true);

            try {
                Statement sentencia = conn.createStatement();
                sentencia.executeUpdate("insert into pokemon "
                        + "values(NULL,'" + nombreJugador + "','" + (aciertos)
                        + "','" + fallos + "')");
                sentencia.close();
            } catch (Exception e) {
                System.err.println("Hubo una excepción");
            }

        } else {
            generarOperacion();
        }

        if (vidaOponente <= 0) {
            temporizador.stop();
            nivel++;

            if (nivel == 3) {

                temporizador.stop();

                puntuaciones.setVentanaJuego(this);
                puntuaciones.setVidaRestante(vidaJugador);
                puntuaciones.setAciertos(aciertos);
                puntuaciones.setFallos(fallos);

                actualizarBarrasVida(vidaJugador, vidaOponente);
                String titulo = "¡VICTORIA!";
                String mensaje = "HAS DERROTADO A TODOS LOS OPONENTES.";
                JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.PLAIN_MESSAGE);
                this.setVisible(false);
                puntuaciones.setVisible(true);

                try {
                    Statement sentencia = conn.createStatement();
                    sentencia.executeUpdate("insert into pokemon "
                            + "values(NULL,'" + nombreJugador + "','" + (aciertos)
                            + "','" + fallos + "')");
                    sentencia.close();
                } catch (Exception e) {
                    System.err.println("Hubo una excepción");
                }

            } else {
                pasarAlSiguienteNivel();
            }
        }

        actualizarBarrasVida(vidaJugador, vidaOponente);
    }

    /**
     * Actualiza las barras de vida del jugador y del oponente.
     *
     * @param vidaJugador Vida restante del jugador.
     * @param vidaOponente Vida restante del oponente.
     */
    public void actualizarBarrasVida(int vidaJugador, int vidaOponente) {
        this.barraJugador.setValue(vidaJugador);
        this.barraOponente.setValue(vidaOponente);
    }

    /**
     * Actualiza el tiempo restante en el temporizador.
     */
    public void actualizarTiempoRestante() {
        this.textoTemporizador.setText(Integer.toString(tiempoRestante));
        this.textoTemporizador.repaint();
    }

    /**
     * Reinicia el temporizador.
     */
    public void reiniciarTemporizador() {
        tiempoRestante = 10;
        actualizarTiempoRestante();
    }

    /**
     * Pasa al siguiente nivel, restableciendo la vida del oponente y mostrando un mensaje al jugador.
     */
    public void pasarAlSiguienteNivel() {
        vidaOponente = 100; // Restablece la vida del oponente a 100.
        barraOponente.setValue(100); // Actualiza la barra de vida del oponente.

        if (nivel == 2) { // Solo muestra el mensaje al pasar del nivel 1 al nivel 2.
            temporizador.stop(); // Detiene el temporizador antes de mostrar el JOptionPane.

            String titulo = "¡NIVEL COMPLETADO!";
            String mensaje = "AHORA TE ENFRENTARÁS A MULTIPLICACIONES Y DIVISIONES.";
            JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.PLAIN_MESSAGE);

            temporizador.start(); // Reinicia el temporizador después de cerrar el JOptionPane.

            generarOperacion(); // Genera una nueva operación.
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        barraJugador = new javax.swing.JProgressBar();
        barraOponente = new javax.swing.JProgressBar();
        textoTemporizador = new javax.swing.JLabel();
        campoRespuestaJugador = new javax.swing.JTextField();
        botonAtacar = new javax.swing.JButton();
        avatarPikachu = new javax.swing.JLabel();
        avatarHaunter = new javax.swing.JLabel();
        separadorTemporizador = new javax.swing.JSeparator();
        panelOperacionIA = new javax.swing.JPanel();
        textoOperacionIA = new javax.swing.JLabel();
        textoHaunter = new javax.swing.JLabel();
        textoPikachu = new javax.swing.JLabel();
        botonAyuda = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 500));
        setResizable(false);
        setSize(new java.awt.Dimension(500, 500));

        barraJugador.setPreferredSize(new java.awt.Dimension(156, 25));

        barraOponente.setPreferredSize(new java.awt.Dimension(156, 25));

        textoTemporizador.setFont(new java.awt.Font("Consolas", 1, 30)); // NOI18N
        textoTemporizador.setForeground(new java.awt.Color(255, 51, 51));
        textoTemporizador.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoTemporizador.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        textoTemporizador.setPreferredSize(new java.awt.Dimension(96, 50));

        campoRespuestaJugador.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        campoRespuestaJugador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoRespuestaJugador.setPreferredSize(new java.awt.Dimension(96, 50));
        campoRespuestaJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                campoRespuestaJugadorActionPerformed(evt);
            }
        });

        botonAtacar.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        botonAtacar.setText("FIGHT");
        botonAtacar.setBorder(null);
        botonAtacar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonAtacar.setMaximumSize(new java.awt.Dimension(96, 50));
        botonAtacar.setMinimumSize(new java.awt.Dimension(96, 50));
        botonAtacar.setPreferredSize(new java.awt.Dimension(96, 50));
        botonAtacar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAtacarActionPerformed(evt);
            }
        });

        avatarPikachu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paquete1/pokemath/pikachu.gif"))); // NOI18N
        avatarPikachu.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 3, true));

        avatarHaunter.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paquete1/pokemath/haunter.gif"))); // NOI18N
        avatarHaunter.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 3, true));

        separadorTemporizador.setForeground(new java.awt.Color(51, 51, 51));
        separadorTemporizador.setPreferredSize(new java.awt.Dimension(96, 10));

        panelOperacionIA.setBackground(new java.awt.Color(255, 255, 255));
        panelOperacionIA.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 51, 51), 3, true));

        textoOperacionIA.setBackground(new java.awt.Color(255, 255, 255));
        textoOperacionIA.setFont(new java.awt.Font("Consolas", 1, 60)); // NOI18N
        textoOperacionIA.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoOperacionIA.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        textoOperacionIA.setPreferredSize(new java.awt.Dimension(450, 100));

        javax.swing.GroupLayout panelOperacionIALayout = new javax.swing.GroupLayout(panelOperacionIA);
        panelOperacionIA.setLayout(panelOperacionIALayout);
        panelOperacionIALayout.setHorizontalGroup(
            panelOperacionIALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(textoOperacionIA, javax.swing.GroupLayout.DEFAULT_SIZE, 442, Short.MAX_VALUE)
        );
        panelOperacionIALayout.setVerticalGroup(
            panelOperacionIALayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelOperacionIALayout.createSequentialGroup()
                .addGap(0, 21, Short.MAX_VALUE)
                .addComponent(textoOperacionIA, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        textoHaunter.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        textoHaunter.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        textoHaunter.setText("HAUNTER LV. 37");
        textoHaunter.setMaximumSize(new java.awt.Dimension(156, 25));
        textoHaunter.setMinimumSize(new java.awt.Dimension(156, 25));
        textoHaunter.setPreferredSize(new java.awt.Dimension(156, 25));

        textoPikachu.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        textoPikachu.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        textoPikachu.setText("PIKACHU LV. 32");
        textoPikachu.setMaximumSize(new java.awt.Dimension(156, 25));
        textoPikachu.setMinimumSize(new java.awt.Dimension(156, 25));
        textoPikachu.setPreferredSize(new java.awt.Dimension(156, 25));

        botonAyuda.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paquete1/pokemath/help.png"))); // NOI18N
        botonAyuda.setMaximumSize(new java.awt.Dimension(50, 50));
        botonAyuda.setMinimumSize(new java.awt.Dimension(50, 50));
        botonAyuda.setPreferredSize(new java.awt.Dimension(50, 50));
        botonAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAyudaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelOperacionIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(avatarPikachu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(barraJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoPikachu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoRespuestaJugador, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(separadorTemporizador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(textoTemporizador, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(botonAtacar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(botonAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(avatarHaunter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(barraOponente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoHaunter, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoPikachu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoHaunter, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(barraJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(avatarPikachu))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(barraOponente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(campoRespuestaJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(botonAtacar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(avatarHaunter)))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(56, Short.MAX_VALUE)
                        .addComponent(textoTemporizador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(separadorTemporizador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(142, 142, 142)
                        .addComponent(botonAyuda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                .addComponent(panelOperacionIA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método que se ejecuta al hacer clic en el botón "Atacar". Verifica la respuesta proporcionada por el jugador y actualiza las barras de vida.
     *
     * @param evt Evento asociado al clic en el botón "Atacar".
     */
    private void botonAtacarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAtacarActionPerformed
        try {
            int respuestaJugador = Integer.parseInt(campoRespuestaJugador.getText());
            verificarRespuesta(respuestaJugador);
        } catch (NumberFormatException ex) {
            // Si el jugador no ha ingresado un número válido, lo tratamos como una respuesta incorrecta.
            verificarRespuesta(null);
        }
        campoRespuestaJugador.setText("");
        reiniciarTemporizador(); // Reinicia el temporizador después de enviar la respuesta.
    }//GEN-LAST:event_botonAtacarActionPerformed

    /**
     * Método que se ejecuta cuando se realiza una acción en el campo "campoRespuestaJugador".
     *
     * @param evt Evento asociado a la acción en el campo "campoRespuestaJugador".
     */
    private void campoRespuestaJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_campoRespuestaJugadorActionPerformed

    }//GEN-LAST:event_campoRespuestaJugadorActionPerformed

    /**
     * Método que se ejecuta al hacer clic en el botón "Ayuda". Muestra la respuesta correcta en el campo "campoRespuestaJugador".
     *
     * @param evt Evento asociado al clic en el botón "Ayuda".
     */
    private void botonAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAyudaActionPerformed
        campoRespuestaJugador.setText(Integer.toString(respuestaCorrecta));
    }//GEN-LAST:event_botonAyudaActionPerformed

    /**
     * Método principal para ejecutar la aplicación.
     *
     * @param args Argumentos de la línea de comandos.
     */
    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Puntuaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Puntuaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Puntuaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Puntuaciones.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Puntuaciones puntuaciones = new Puntuaciones();
                new VentanaJuego(puntuaciones).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel avatarHaunter;
    private javax.swing.JLabel avatarPikachu;
    public javax.swing.JProgressBar barraJugador;
    public javax.swing.JProgressBar barraOponente;
    public javax.swing.JButton botonAtacar;
    private javax.swing.JButton botonAyuda;
    public javax.swing.JTextField campoRespuestaJugador;
    private javax.swing.JPanel panelOperacionIA;
    private javax.swing.JSeparator separadorTemporizador;
    private javax.swing.JLabel textoHaunter;
    public javax.swing.JLabel textoOperacionIA;
    private javax.swing.JLabel textoPikachu;
    public javax.swing.JLabel textoTemporizador;
    // End of variables declaration//GEN-END:variables
}
