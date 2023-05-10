package paquete1.pokemath;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import java.sql.*;

/**
 * Clase Inicio que representa la pantalla de inicio de un juego de matemáticas tipo Pokémon.
 */
public class Inicio extends javax.swing.JFrame {

    public Inicio() {

        initComponents();

        setLocationRelativeTo(null); // Centra la aplicación.

        // El siguiente código es para que se puedan enviar las operaciones pulsando ENTER:
        campoNombreJugador.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    botonComenzar.doClick(); // Simula un clic en el botón "ATACAR".
                }
            }
        });
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        campoNombreJugador = new javax.swing.JTextField();
        botonComenzar = new javax.swing.JButton();
        bienvenida = new javax.swing.JLabel();
        gifBienvenida = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(new java.awt.Dimension(500, 500));

        campoNombreJugador.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        campoNombreJugador.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        campoNombreJugador.setPreferredSize(new java.awt.Dimension(250, 50));

        botonComenzar.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        botonComenzar.setText("GO");
        botonComenzar.setBorder(null);
        botonComenzar.setPreferredSize(new java.awt.Dimension(50, 50));
        botonComenzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonComenzarActionPerformed(evt);
            }
        });

        gifBienvenida.setIcon(new javax.swing.ImageIcon(getClass().getResource("/paquete1/pokemath/bienvenida.gif"))); // NOI18N
        gifBienvenida.setRequestFocusEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(gifBienvenida)
                    .addComponent(bienvenida))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addComponent(campoNombreJugador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonComenzar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(95, 95, 95))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bienvenida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gifBienvenida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(campoNombreJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonComenzar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Método para manejar el evento del botón Comenzar.
     */
    private void botonComenzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonComenzarActionPerformed
        String nombreJugador = campoNombreJugador.getText().trim();
        if (nombreJugador.isEmpty()) {
            String titulo = "Error";
            String mensaje = "Debes escribir un nombre de jugador.";
            JOptionPane.showMessageDialog(null, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
        } else {
            Puntuaciones puntuaciones = new Puntuaciones();
            VentanaJuego ventanaJuego = new VentanaJuego(puntuaciones);
            ventanaJuego.setNombreJugador(nombreJugador);
            ventanaJuego.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_botonComenzarActionPerformed

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
                new Inicio().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bienvenida;
    private javax.swing.JButton botonComenzar;
    private javax.swing.JTextField campoNombreJugador;
    private javax.swing.JLabel gifBienvenida;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables
}
