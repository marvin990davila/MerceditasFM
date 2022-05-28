/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Controler.CTTypeConBusines;
import Controler.ContarPerson;
import Controler.TCType_contact;
import Controler.conexcion;
import Modelo.tipe_contact_company;
import Modelo.type_contact;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mdavi
 */
public class Business extends javax.swing.JInternalFrame {

    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();

    public Business() {
        initComponents();
        cargarTypeBusines(boxTypeCompa);
        showTableBusines();
    }

    public void showTableBusines() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Empresa");
        model.addColumn("Nit");

        tlCompany.setModel(model);

        String[] datos = new String[6];
        try {

            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery("SELECT *  FROM company WHERE id_company > 3");

            while (rs.next()) {

                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                model.addRow(datos);

            }
            tlCompany.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }
    }

    public void actualizarDatoBusines() {
        int fila = tlCompany.getSelectedRow();
        System.out.println(fila);
        int idBos = Integer.parseInt(this.tlCompany.getValueAt(fila, 0).toString());
        String NaBos = tlCompany.getValueAt(fila, 1).toString();
        String Nnit = tlCompany.getValueAt(fila, 2).toString();

        if (NaBos.isEmpty() || Nnit.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacios porfabor llenar registros.");
        } else {

            try {
                PreparedStatement actu = conexcion.prepareStatement("UPDATE company SET name_company='" + NaBos + "', nit_company ='" + Nnit + "' WHERE ID_company = " + idBos + "");
                actu.executeUpdate();
                showTableBusines();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error" + e.toString());
            }

        }
    }

    public void guardarContacto() {

        int fila = tlCompany.getSelectedRow();
        System.out.println(fila);
        int idBusines = Integer.parseInt(this.tlCompany.getValueAt(fila, 0).toString());

        String TyCon = boxTypeCompa.getSelectedItem().toString();
        String[] RayTyCon = TyCon.split("-");

        System.out.println("Numero Bodega salida: " + RayTyCon[0]);

        String TyTexCont = txtTyCompany.getText();

        if (TyTexCont.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacio porfabor llenar registro.");
        } else {
            try ( Connection conexion = con.get_connection()) {
                try {
                    PreparedStatement ps = null;
                    String query = ("INSERT INTO contact_company(id_tipe_contact_company, text_contact_company,id_company)\n"
                            + "VALUE(" + RayTyCon[0] + ",'" + TyTexCont + "'," + idBusines + ")");

                    ps = conexion.prepareStatement(query);
                    ps.executeUpdate();
                    txtTyCompany.setText("");

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

    }

    public void mostrarInforContacto() {

        int fila = tlCompany.getSelectedRow();
        System.out.println(fila);
        int idBu = Integer.parseInt(this.tlCompany.getValueAt(fila, 0).toString());

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Typo de Contacto");
        model.addColumn("Info");

        tlContactCompany.setModel(model);

        String[] datos = new String[3];
        try {

            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery("SELECT co.ID_COMPANY, tcc.CONTACT_COMPANY , cc.TEXT_CONTACT_COMPANY\n"
                    + "FROM company co JOIN contact_company cc ON cc.ID_COMPANY=co.ID_COMPANY\n"
                    + "JOIN tipe_contact_company tcc ON cc.ID_TIPE_CONTACT_COMPANY=tcc.ID_TIPE_CONTACT_COMPANY WHERE co.ID_COMPANY = " + idBu + " ");

            while (rs.next()) {

                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                model.addRow(datos);

            }
            tlContactCompany.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }

    }

    public void actualizarContacto() {
        int fila = tlContactCompany.getSelectedRow();
        System.out.println(fila);
        int idBusines = Integer.parseInt(this.tlContactCompany.getValueAt(fila, 0).toString());
        String texContac = tlContactCompany.getValueAt(fila, 2).toString();

        if (texContac.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacios porfabor llenar registros.");
        } else {

            try {
                PreparedStatement actu = conexcion.prepareStatement("UPDATE contact_company SET TEXT_CONTACT_COMPANY =" + texContac + " WHERE ID_COMPANY = " + idBusines + "");
                actu.executeUpdate();
                mostrarInforContacto();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error" + e.toString());
            }

        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuActuBusines = new javax.swing.JMenuItem();
        jMenuIContactBusines = new javax.swing.JMenuItem();
        jMenuShouContactBus = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuActuContact = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        boxTypeCompa = new javax.swing.JComboBox<>();
        txtTyCompany = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tlCompany = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tlContactCompany = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtNameBusines = new javax.swing.JTextField();
        txtNitBusines = new javax.swing.JTextField();
        btGuarEmpresa = new javax.swing.JToggleButton();

        jMenuActuBusines.setText("Actualisar Empresa");
        jMenuActuBusines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuActuBusinesActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuActuBusines);

        jMenuIContactBusines.setText("Agregar Contacto de Empresa");
        jMenuIContactBusines.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIContactBusinesActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuIContactBusines);

        jMenuShouContactBus.setText("Mostrar contactos de Empresa");
        jMenuShouContactBus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuShouContactBusActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuShouContactBus);

        jMenuActuContact.setText("Actualisar Contacto");
        jMenuActuContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuActuContactActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuActuContact);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Empresas");

        jLabel11.setText("Tipo de Contacto a Guardar");

        jLabel10.setText("Escriva la informacion ");

        tlCompany.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tlCompany.setComponentPopupMenu(jPopupMenu1);
        tlCompany.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(tlCompany);

        tlContactCompany.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tlContactCompany.setComponentPopupMenu(jPopupMenu2);
        jScrollPane2.setViewportView(tlContactCompany);

        jLabel12.setText("Informacion de Contacto de la Empresa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxTypeCompa, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTyCompany, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 40, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addGap(19, 19, 19))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(boxTypeCompa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtTyCompany, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 329, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Empresas", jPanel1);

        jLabel1.setText("Nombre de la Empresa");

        jLabel2.setText("Nit de la Empresa");

        btGuarEmpresa.setText("Guardar nueva Empresa");
        btGuarEmpresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuarEmpresaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btGuarEmpresa)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(txtNameBusines, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNitBusines, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(461, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNameBusines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNitBusines, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(btGuarEmpresa)
                .addContainerGap(218, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nueva Empresa", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGuarEmpresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuarEmpresaActionPerformed
        // TODO add your handling code here:
        String N1Em = txtNameBusines.getText();
        String N2Em = txtNitBusines.getText();

        ContarPerson idid = new ContarPerson();

        int idss = idid.id_incre();
        System.out.println("Numero id: " + idss);

        if (N1Em.isEmpty() || N2Em.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacios porfabor llenar registros.");
        } else {

            System.out.println("Numero id: " + N1Em);
            System.out.println("Numero id: " + N2Em);

            try ( Connection conexion = con.get_connection()) {
                try {
                    PreparedStatement ps = null;
                    String query = ("INSERT INTO company(name_company,nit_company)\n"
                            + "VALUE('" + N1Em + "','" + N2Em + "')");

                    ps = conexion.prepareStatement(query);
                    ps.executeUpdate();
                    txtNameBusines.setText("");
                    txtNitBusines.setText("");
                    showTableBusines();

                    // JOptionPane.showMessageDialog(this, "Guardado.");
                } catch (Exception e) {
                    System.err.print(e.toString());
                    JOptionPane.showMessageDialog(this, "Ocurrio un error al guaredar.");
                }
            } catch (SQLException e) {
                System.err.print(e.toString());
                JOptionPane.showMessageDialog(this, "Ocurrio un error inesperado.\nFavor comunicarse con el administrador.");
            }

        }

    }//GEN-LAST:event_btGuarEmpresaActionPerformed

    private void jMenuActuBusinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuActuBusinesActionPerformed
        // TODO add your handling code here:
        actualizarDatoBusines();
    }//GEN-LAST:event_jMenuActuBusinesActionPerformed

    private void jMenuIContactBusinesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIContactBusinesActionPerformed
        // TODO add your handling code here:
        guardarContacto();
    }//GEN-LAST:event_jMenuIContactBusinesActionPerformed

    private void jMenuShouContactBusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuShouContactBusActionPerformed
        // TODO add your handling code here:
        mostrarInforContacto();
    }//GEN-LAST:event_jMenuShouContactBusActionPerformed

    private void jMenuActuContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuActuContactActionPerformed
        // TODO add your handling code here:
        actualizarContacto();
    }//GEN-LAST:event_jMenuActuContactActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxTypeCompa;
    private javax.swing.JToggleButton btGuarEmpresa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuActuBusines;
    private javax.swing.JMenuItem jMenuActuContact;
    private javax.swing.JMenuItem jMenuIContactBusines;
    private javax.swing.JMenuItem jMenuShouContactBus;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tlCompany;
    private javax.swing.JTable tlContactCompany;
    private javax.swing.JTextField txtNameBusines;
    private javax.swing.JTextField txtNitBusines;
    private javax.swing.JTextField txtTyCompany;
    // End of variables declaration//GEN-END:variables

    private void cargarTypeBusines(JComboBox c) {

        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        c.setModel(combo);
        CTTypeConBusines ctc = new CTTypeConBusines();

        try {
            Statement st = conexcion.createStatement();
            ResultSet rr = st.executeQuery("SELECT * FROM tipe_contact_company");

            while (rr.next()) {
                tipe_contact_company con = new tipe_contact_company();
                System.out.println(rr.getString(1));
                con.setId_tipe_contact_company(rr.getInt(1));
                con.setContact_company(rr.getString(2));
                ctc.Agregartype_contact(con);
                combo.addElement(con.getId_tipe_contact_company() + " - " + con.getContact_company());
                System.out.println("Exito....");
            }

        } catch (Exception e) {
            System.out.println("Error , no se puede mostrar combo" + e);
        }

    }
}
