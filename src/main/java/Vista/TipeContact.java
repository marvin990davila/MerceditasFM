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
public class TipeContact extends javax.swing.JInternalFrame {

    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();

    public TipeContact() {
        initComponents();
        showTableProduct();
    }

    public void showTableProduct() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id Tipo de Contacto");
        model.addColumn("Tipo de Contacto");

        tlTipoContac.setModel(model);

        String[] datos = new String[2];
        try {

            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery("SELECT * from type_contact");

            while (rs.next()) {

                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);

                model.addRow(datos);

            }
            tlTipoContac.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }
    }

    public void actualizarProducto() {
        int fila = tlTipoContac.getSelectedRow();
        System.out.println(fila);

        int idTipCont = Integer.parseInt(this.tlTipoContac.getValueAt(fila, 0).toString());
        String TipoContac = tlTipoContac.getValueAt(fila, 1).toString();

        System.out.println("La cantidad es: " + TipoContac);

        try {
            PreparedStatement actu = conexcion.prepareStatement("UPDATE product SET NAME_PRODUCT ='" + TipoContac + "' WHERE ID_PRODUCT = " + idTipCont + "");
            actu.executeUpdate();
            showTableProduct();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tlTipoContac = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        btGuarTipeContact = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtTipoContact = new javax.swing.JTextField();

        jMenuItem1.setText("Actualizar Tipo de Contacto");
        jPopupMenu1.add(jMenuItem1);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tipo de Contacto personas");

        tlTipoContac.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tlTipoContac);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btGuarTipeContact.setText("Guardar tipo contacto");
        btGuarTipeContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuarTipeContactActionPerformed(evt);
            }
        });

        jLabel1.setText("Nuebo tipo de contacto");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTipoContact, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btGuarTipeContact))))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtTipoContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(btGuarTipeContact)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 139, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("tab1", jPanel1);

        jLayeredPane1.setLayer(jTabbedPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGuarTipeContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuarTipeContactActionPerformed

        String TipComm = txtTipoContact.getText();

        if (TipComm.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacios porfabor llenar registros.");
        } else {
            try ( Connection conexion = con.get_connection()) {
                try {
                    PreparedStatement ps = null;
                    String query = ("INSERT INTO type_contact(NAME_TYPE_CONTACT)VALUES('" + TipComm + "')");
                    ps = conexion.prepareStatement(query);
                    ps.executeUpdate();
                    showTableProduct();
                    JOptionPane.showMessageDialog(this, "Guardado.");
                } catch (Exception e) {
                    System.err.print(e.toString());
                    JOptionPane.showMessageDialog(this, "Ocurrio un error al guaredar.");
                }
            } catch (SQLException e) {
                System.err.print(e.toString());
                JOptionPane.showMessageDialog(this, "Ocurrio un error inesperado.\nFavor comunicarse con el administrador.");
            }
        }
    }//GEN-LAST:event_btGuarTipeContactActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btGuarTipeContact;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tlTipoContac;
    private javax.swing.JTextField txtTipoContact;
    // End of variables declaration//GEN-END:variables
}
