/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Controler.conexcion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mdavi
 */
public class Measuress extends javax.swing.JInternalFrame {

    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();

    public Measuress() {
        initComponents();
        showTableMeasuress();
    }

    public void showTableMeasuress() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Medida");
        model.addColumn("Acronimo");
        tlMeasuress.setModel(model);

        String[] datos = new String[3];
        try {

            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery("SELECT * FROM measure");

            while (rs.next()) {

                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                model.addRow(datos);

            }
            tlMeasuress.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }
    }

    public void actualizarMeasure() {
        int fila = tlMeasuress.getSelectedRow();
        System.out.println(fila);

        int idMeasure = Integer.parseInt(this.tlMeasuress.getValueAt(fila, 0).toString());
        String nameMea = tlMeasuress.getValueAt(fila, 1).toString();
        String Acrom = tlMeasuress.getValueAt(fila, 2).toString();

        System.out.println("La cantidad es: " + nameMea);
        System.out.println("El precio es : " + Acrom);

        if (nameMea.isEmpty() || Acrom.isEmpty()) {
            JOptionPane.showMessageDialog(this, "!!!Campos vacios por favor llenar registros.");
        } else {
            try {
                PreparedStatement actu = conexcion.prepareStatement("UPDATE measure SET NAME_measure ='" + nameMea + "', acronym= '" + Acrom + "' WHERE ID_measure = " + idMeasure + "");
                actu.executeUpdate();
                showTableMeasuress();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error" + e.toString());

            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        btActualiMeasu = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        tlMeasuress = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtMedida = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtAcronimo = new javax.swing.JTextField();
        btGuardarMedida = new javax.swing.JButton();

        btActualiMeasu.setText("Actualisar Medida");
        btActualiMeasu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActualiMeasuActionPerformed(evt);
            }
        });
        jPopupMenu1.add(btActualiMeasu);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Medidas");

        tlMeasuress.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tlMeasuress.setComponentPopupMenu(jPopupMenu1);
        tlMeasuress.setSelectionBackground(new java.awt.Color(153, 204, 255));
        jScrollPane1.setViewportView(tlMeasuress);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Medida");

        jLabel2.setText("Acronimo");

        btGuardarMedida.setText("Guardar Medida");
        btGuardarMedida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarMedidaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtMedida, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtAcronimo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btGuardarMedida)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(txtAcronimo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btGuardarMedida)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 329, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(247, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGuardarMedidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarMedidaActionPerformed
        // TODO add your handling code here:

        String txtMedi = txtMedida.getText();
        String txtActo = txtAcronimo.getText();

        if (txtMedi.isEmpty() || txtActo.isEmpty()) {
            JOptionPane.showMessageDialog(this, "!!!Campos vacios por favor llenar registros.");
        } else {
            try ( Connection conexion = con.get_connection()) {
                try {
                    PreparedStatement ps = null;
                    String query = ("INSERT INTO measure(name_measure,acronym)VALUES('" + txtMedi + "','" + txtActo + "')");
                    ps = conexion.prepareStatement(query);
                    ps.executeUpdate();
                    showTableMeasuress();

                    JOptionPane.showMessageDialog(this, "Guardado.");
                } catch (Exception e) {
                    System.err.print(e.toString());
                    JOptionPane.showMessageDialog(this, "Ocurrio un error al guardar.");
                }
            } catch (SQLException e) {
                System.err.print(e.toString());
                JOptionPane.showMessageDialog(this, "Ocurrio un error inesperado.\nFavor comunicarse con el administrador.");
            }
        }

    }//GEN-LAST:event_btGuardarMedidaActionPerformed

    private void btActualiMeasuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActualiMeasuActionPerformed
        // TODO add your handling code here:

        actualizarMeasure();
    }//GEN-LAST:event_btActualiMeasuActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem btActualiMeasu;
    private javax.swing.JButton btGuardarMedida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tlMeasuress;
    private javax.swing.JTextField txtAcronimo;
    private javax.swing.JTextField txtMedida;
    // End of variables declaration//GEN-END:variables
}
