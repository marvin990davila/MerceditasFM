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
public class NewCellar extends javax.swing.JInternalFrame {

    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();

    public NewCellar() {
        initComponents();
        showTableCellar();
    }

    public void showTableCellar() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Product");
        model.addColumn("Bodega");
        tlNCellar.setModel(model);

        String[] datos = new String[5];
        try {

            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery("SELECT * from cellar");

            while (rs.next()) {

                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);

                model.addRow(datos);

            }
            tlNCellar.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }
    }

    public void actualizarCellar() {
        int fila = tlNCellar.getSelectedRow();
        System.out.println(fila);

        int idCellar = Integer.parseInt(this.tlNCellar.getValueAt(fila, 0).toString());
        String namePro = tlNCellar.getValueAt(fila, 1).toString();

        System.out.println("La cantidad es: " + namePro);

        if (namePro.isEmpty()) {
            JOptionPane.showMessageDialog(this, "!!!Campos vacios por favor llenar registros.");
        } else {
            try {
                PreparedStatement actu = conexcion.prepareStatement("UPDATE cellar SET NAME_cellar ='" + namePro + "' WHERE ID_cella = " + idCellar + "");
                actu.executeUpdate();
                showTableCellar();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error" + e.toString());

            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuActuCellar = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tlNCellar = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNewCellar = new javax.swing.JTextField();
        btCellar = new javax.swing.JButton();

        jMenuActuCellar.setText("Catualisar nombre de Bodega");
        jMenuActuCellar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuActuCellarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuActuCellar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Nueva Bodega");

        tlNCellar.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tlNCellar.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tlNCellar);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nueva Bodega");

        btCellar.setText("Guardar Bodega");
        btCellar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCellarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(btCellar)
                    .addComponent(txtNewCellar, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtNewCellar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(btCellar)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 364, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Bodegas", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuActuCellarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuActuCellarActionPerformed
        // TODO add your handling code here:
        actualizarCellar();
    }//GEN-LAST:event_jMenuActuCellarActionPerformed

    private void btCellarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCellarActionPerformed
        // TODO add your handling code here:

        String nameCellar = txtNewCellar.getText();

        if (nameCellar.isEmpty()) {
            JOptionPane.showMessageDialog(this, "!!!Campos vacios por favor llenar registros.");
        } else {
            try ( Connection conexion = con.get_connection()) {
                try {
                    PreparedStatement ps = null;
                    String query = ("INSERT INTO cellar(name_cellar)VALUES('" + nameCellar + "')");
                    ps = conexion.prepareStatement(query);
                    ps.executeUpdate();
                    showTableCellar();
                    txtNewCellar.setText("");

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

    }//GEN-LAST:event_btCellarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btCellar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuItem jMenuActuCellar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tlNCellar;
    private javax.swing.JTextField txtNewCellar;
    // End of variables declaration//GEN-END:variables
}
