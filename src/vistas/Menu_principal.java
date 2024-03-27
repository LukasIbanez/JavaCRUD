/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vistas;

import controlador.ControladorArriendo;
import controlador.ControladorCliente;
import controlador.ControladorVehiculo;
import controlador.ControladorVendedor;
import javax.swing.SwingUtilities;
import java.sql.Connection;
import modelo.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


public class Menu_principal extends javax.swing.JFrame {

    
    public Menu_principal() {
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnCliente = new javax.swing.JButton();
        btnVehiculo = new javax.swing.JButton();
        btnVendedor = new javax.swing.JButton();
        btnArriendo = new javax.swing.JButton();
        jButtonSalir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("CRUD"));

        btnCliente.setText("CLIENTE");
        btnCliente.setActionCommand("btnCliente");
        btnCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClienteActionPerformed(evt);
            }
        });

        btnVehiculo.setText("VEHICULO");
        btnVehiculo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVehiculoActionPerformed(evt);
            }
        });

        btnVendedor.setText("VENDEDOR");
        btnVendedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVendedorActionPerformed(evt);
            }
        });

        btnArriendo.setText("ARRIENDO");
        btnArriendo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArriendoActionPerformed(evt);
            }
        });

        jButtonSalir.setForeground(new java.awt.Color(255, 51, 51));
        jButtonSalir.setText("SALIR");
        jButtonSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(102, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnVendedor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnArriendo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnVehiculo, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(98, 98, 98))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonSalir)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(btnCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVehiculo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVendedor)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnArriendo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jButtonSalir)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClienteActionPerformed

        SwingUtilities.invokeLater(() -> {
            Menu_cliente newframecliente = new Menu_cliente();
            ControladorCliente c = new ControladorCliente(newframecliente);
            newframecliente.setVisible(true);
            newframecliente.setLocationRelativeTo(null);
            this.dispose();
        });
    }//GEN-LAST:event_btnClienteActionPerformed

    private void btnVehiculoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVehiculoActionPerformed
        SwingUtilities.invokeLater(() -> {
            Menu_vehiculo newframevehiculo = new Menu_vehiculo();
            ControladorVehiculo v = new ControladorVehiculo(newframevehiculo);
            newframevehiculo.setVisible(true);
            newframevehiculo.setLocationRelativeTo(null);
            this.dispose();
        });
    }//GEN-LAST:event_btnVehiculoActionPerformed

    private void btnVendedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVendedorActionPerformed
        SwingUtilities.invokeLater(() -> {
            Menu_vendedor newframevendedor = new Menu_vendedor();
            ControladorVendedor v = new ControladorVendedor(newframevendedor);
            newframevendedor.setVisible(true);
            newframevendedor.setLocationRelativeTo(null);
            this.dispose();
        });
    }//GEN-LAST:event_btnVendedorActionPerformed

    private void btnArriendoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArriendoActionPerformed
        SwingUtilities.invokeLater(() -> {
        if (validarDatos()) {
            Menu_arriendo newframearriendo = new Menu_arriendo();
            ControladorArriendo a = new ControladorArriendo(newframearriendo);
            newframearriendo.setVisible(true);
            newframearriendo.setLocationRelativeTo(null);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "Deben existir datos en Clientes, Vendedores y Vehiculos.");
        }
    });
    }//GEN-LAST:event_btnArriendoActionPerformed

    private void jButtonSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButtonSalirActionPerformed

    private boolean validarDatos() {
        boolean datosValidos = false;
        Connection con = null;
        try {
            Conexion conexion = new Conexion();
            con = conexion.getConnection();
            if (con != null) {
                // Consulta para contar los registros en la tabla cliente
                String queryCliente = "SELECT COUNT(*) FROM cliente";
                // Consulta para contar los registros en la tabla vehiculo
                String queryVehiculo = "SELECT COUNT(*) FROM vehiculo";
                // Consulta para contar los registros en la tabla vendedor
                String queryVendedor = "SELECT COUNT(*) FROM vendedor";

                PreparedStatement stmtCliente = con.prepareStatement(queryCliente);
                PreparedStatement stmtVehiculo = con.prepareStatement(queryVehiculo);
                PreparedStatement stmtVendedor = con.prepareStatement(queryVendedor);

                ResultSet rsCliente = stmtCliente.executeQuery();
                ResultSet rsVehiculo = stmtVehiculo.executeQuery();
                ResultSet rsVendedor = stmtVendedor.executeQuery();

                // Obtener el recuento de registros de cada tabla
                rsCliente.next();
                int countCliente = rsCliente.getInt(1);

                rsVehiculo.next();
                int countVehiculo = rsVehiculo.getInt(1);

                rsVendedor.next();
                int countVendedor = rsVendedor.getInt(1);

                // Verificar si hay datos en todas las tablas
                if (countCliente > 0 && countVehiculo > 0 && countVendedor > 0) {
                    datosValidos = true;
                }

                // Cerrar las conexiones y los statements
                rsCliente.close();
                stmtCliente.close();

                rsVehiculo.close();
                stmtVehiculo.close();

                rsVendedor.close();
                stmtVendedor.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return datosValidos;
    }
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Menu_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Menu_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Menu_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Menu_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Menu_principal().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArriendo;
    private javax.swing.JButton btnCliente;
    private javax.swing.JButton btnVehiculo;
    private javax.swing.JButton btnVendedor;
    private javax.swing.JButton jButtonSalir;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
