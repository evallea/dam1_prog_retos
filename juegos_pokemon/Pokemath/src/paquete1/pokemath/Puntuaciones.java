package paquete1.pokemath;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.*;

/**
 * Clase Puntuaciones para gestionar la puntuación y la vida de los jugadores en PokéMath.
 */
public class Puntuaciones extends javax.swing.JFrame {

    // Variables:
    private VentanaJuego ventanaJuego;
    private int aciertosAcumulados;
    private int fallosAcumulados;
    private int vidaJugador;

    public Puntuaciones() {

        initComponents();

        setLocationRelativeTo(null); // Centra la aplicación.

        // El siguiente código es para que se puedan enviar las operaciones pulsando ENTER:
        botonReintentar.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    botonReintentar.doClick(); // Simula un clic en el botón "ATACAR".
                }
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textoFallos = new javax.swing.JLabel();
        textoVidaRestante = new javax.swing.JLabel();
        textoAciertos = new javax.swing.JLabel();
        botonReintentar = new javax.swing.JButton();
        valorVidaRestante = new javax.swing.JLabel();
        valorAciertos = new javax.swing.JLabel();
        valorFallos = new javax.swing.JLabel();
        textoPuntuaciones = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        textoFallos.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        textoFallos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        textoFallos.setText("NÚMERO DE FALLOS:");
        textoFallos.setToolTipText("");
        textoFallos.setPreferredSize(new java.awt.Dimension(200, 50));

        textoVidaRestante.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        textoVidaRestante.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        textoVidaRestante.setText("VIDA RESTANTE:");
        textoVidaRestante.setPreferredSize(new java.awt.Dimension(200, 50));

        textoAciertos.setFont(new java.awt.Font("Consolas", 0, 18)); // NOI18N
        textoAciertos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        textoAciertos.setText("NÚMERO DE ACIERTOS:");
        textoAciertos.setPreferredSize(new java.awt.Dimension(200, 50));

        botonReintentar.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        botonReintentar.setText("REINTENTAR");
        botonReintentar.setBorder(null);
        botonReintentar.setMaximumSize(new java.awt.Dimension(150, 50));
        botonReintentar.setMinimumSize(new java.awt.Dimension(150, 50));
        botonReintentar.setPreferredSize(new java.awt.Dimension(150, 50));
        botonReintentar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonReintentarActionPerformed(evt);
            }
        });

        valorVidaRestante.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        valorVidaRestante.setPreferredSize(new java.awt.Dimension(50, 50));

        valorAciertos.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        valorAciertos.setPreferredSize(new java.awt.Dimension(50, 50));

        valorFallos.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        valorFallos.setPreferredSize(new java.awt.Dimension(50, 50));

        textoPuntuaciones.setFont(new java.awt.Font("Consolas", 1, 36)); // NOI18N
        textoPuntuaciones.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        textoPuntuaciones.setText("PUNTUACIONES");
        textoPuntuaciones.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        textoPuntuaciones.setMaximumSize(new java.awt.Dimension(300, 50));
        textoPuntuaciones.setMinimumSize(new java.awt.Dimension(300, 50));
        textoPuntuaciones.setPreferredSize(new java.awt.Dimension(300, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(botonReintentar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(100, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(textoPuntuaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoVidaRestante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoFallos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(textoAciertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(valorVidaRestante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(valorFallos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(valorAciertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(100, 100, 100))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(textoPuntuaciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(valorVidaRestante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(valorAciertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(valorFallos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(textoVidaRestante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(17, 17, 17)
                        .addComponent(textoAciertos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(textoFallos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(39, 39, 39)
                .addComponent(botonReintentar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método para manejar el evento del botón Reintentar.
     */
    private void botonReintentarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonReintentarActionPerformed
        ventanaJuego.nivel = 1; // Restablece el nivel del juego a 1.
        ventanaJuego.vidaJugador = 100; // Restablece la vida del jugador a 100.
        ventanaJuego.vidaOponente = 100; // Restablece la vida del oponente a 100.
        ventanaJuego.aciertos = 0; // Restablece los aciertos a 0.
        ventanaJuego.fallos = 0; // Restablece los fallos a 0.
        ventanaJuego.actualizarBarrasVida(ventanaJuego.vidaJugador, ventanaJuego.vidaOponente); // Actualiza las barras de vida.
        ventanaJuego.generarOperacion(); // Genera una nueva operación.
        ventanaJuego.setVisible(true); // Muestra la ventana de juego.

        setVisible(false); // Oculta la ventana de puntuaciones.

        ventanaJuego.reiniciarTemporizador(); // Reinicia el temporizador.
        ventanaJuego.temporizador.start();
    }//GEN-LAST:event_botonReintentarActionPerformed

    // GETS Y SETS:
    public void setVentanaJuego(VentanaJuego ventanaJuego) {
        this.ventanaJuego = ventanaJuego;
    }

    public void setVidaRestante(int vidaRestante) {
        valorVidaRestante.setText(String.valueOf(vidaRestante));
    }

    public void setAciertos(int aciertos) {
        valorAciertos.setText(String.valueOf(aciertos));
    }

    public void setFallos(int fallos) {
        valorFallos.setText(String.valueOf(fallos));
    }

    public void setAciertosAcumulados(int aciertosAcumulados) {
        this.aciertosAcumulados = aciertosAcumulados;
    }

    public void setFallosAcumulados(int fallosAcumulados) {
        this.fallosAcumulados = fallosAcumulados;
    }

    public int getAciertosAcumulados() {
        return aciertosAcumulados;
    }

    public int getFallosAcumulados() {
        return fallosAcumulados;
    }

    public int getVidaJugador() {
        return vidaJugador;
    }

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
                new Puntuaciones().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonReintentar;
    private javax.swing.JLabel textoAciertos;
    private javax.swing.JLabel textoFallos;
    private javax.swing.JLabel textoPuntuaciones;
    private javax.swing.JLabel textoVidaRestante;
    private javax.swing.JLabel valorAciertos;
    private javax.swing.JLabel valorFallos;
    private javax.swing.JLabel valorVidaRestante;
    // End of variables declaration//GEN-END:variables
}
