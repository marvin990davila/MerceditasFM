/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Controler.ContarPerson;
import Controler.TCType_contact;
import Controler.conexcion;
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
public class Providerss extends javax.swing.JInternalFrame {

    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();

    public Providerss() {
        initComponents();
        cargarTypeCommrr(boxTypeCor);
        showTableProduct();
    }

    public void showTableProduct() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("1 Nombre");
        model.addColumn("2 Nombre");
        model.addColumn("3 Nombre");
        model.addColumn("1 Apellido");
        model.addColumn("2 Apellido");
        tlProveedores.setModel(model);

        String[] datos = new String[6];
        try {

            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery("SELECT pe.ID_PERSON,pe.NAME1,pe.NAME2,pe.NAME3,pe.LAST_NAME1,pe.LAST_NAME2\n"
                    + "FROM person_classification pc JOIN people pe ON pc.id_person=pe.ID_PERSON\n"
                    + "JOIN company co ON pc.id_company=co.ID_COMPANY\n"
                    + "JOIN class_person cp ON pc.id_class_person=cp.ID_CLASS_PERSON\n"
                    + "JOIN status_person sp ON pc.id_status=sp.ID_STATUS WHERE pc.id_class_person = 2");

            while (rs.next()) {

                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);

                model.addRow(datos);

            }
            tlProveedores.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }
    }

    public void actualizarDatosPro() {
        int fila = tlProveedores.getSelectedRow();
        System.out.println(fila);
        int idProve = Integer.parseInt(this.tlProveedores.getValueAt(fila, 0).toString());
        String N1name = tlProveedores.getValueAt(fila, 1).toString();
        String N2name = tlProveedores.getValueAt(fila, 2).toString();
        String N3name = tlProveedores.getValueAt(fila, 3).toString();
        String N1las = tlProveedores.getValueAt(fila, 4).toString();
        String N2las = tlProveedores.getValueAt(fila, 5).toString();

        if (N1name.isEmpty() || N2name.isEmpty() || N3name.isEmpty() || N1las.isEmpty() || N2las.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacios por favor llenar registros.");
        } else {

            try {
                PreparedStatement actu = conexcion.prepareStatement("UPDATE people SET name1='" + N1name + "', name2 ='" + N2name + "', name3 = '" + N3name + "',\n"
                        + "last_name1 = '" + N1las + "',last_name2 = '" + N2las + "' WHERE ID_person = " + idProve + "");
                actu.executeUpdate();
                showTableProduct();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error" + e.toString());
            }

        }
    }

    public void guardarContacto() {

        int fila = tlProveedores.getSelectedRow();
        System.out.println(fila);
        int idPerson = Integer.parseInt(this.tlProveedores.getValueAt(fila, 0).toString());

        String TyCon = boxTypeCor.getSelectedItem().toString();
        String[] RayTyCon = TyCon.split("-");

        System.out.println("Numero Bodega salida: " + RayTyCon[0]);

        String TyTexCont = txtTypeCpr.getText();

        if (TyTexCont.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacio por favor llenar registro.");
        } else {
            try ( Connection conexion = con.get_connection()) {
                try {
                    PreparedStatement ps = null;
                    String query = ("INSERT INTO contact(id_type_contact, text_contact,id_person)\n"
                            + "VALUE(" + RayTyCon[0] + ",'" + TyTexCont + "'," + idPerson + ")");

                    ps = conexion.prepareStatement(query);
                    ps.executeUpdate();
                    txtTypeCpr.setText("");

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

    }

    public void mostrarInforContacto() {

        int fila = tlProveedores.getSelectedRow();
        System.out.println(fila);
        int idPe = Integer.parseInt(this.tlProveedores.getValueAt(fila, 0).toString());

        /**/
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Typo de Contacto");
        model.addColumn("Info");

        tlContacProvee.setModel(model);

        String[] datos = new String[3];
        try {

            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery("SELECT co.ID_CONTACT,ty.name_type_contact, co.text_contact\n"
                    + "FROM  type_contact ty JOIN contact co ON ty.id_type_contact=co.id_type_contact\n"
                    + "JOIN people pe ON co.ID_PERSON=pe.ID_PERSON WHERE pe.ID_PERSON = " + idPe + " ");

            while (rs.next()) {

                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                model.addRow(datos);

            }
            tlContacProvee.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }

    }

    public void actualizarContacto() {
        int fila = tlContacProvee.getSelectedRow();
        System.out.println(fila);
        int idPerson = Integer.parseInt(this.tlContacProvee.getValueAt(fila, 0).toString());
        String texContac = tlContacProvee.getValueAt(fila, 2).toString();

        if (texContac.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacios por favor llenar registros.");
        } else {

            try {
                PreparedStatement actu = conexcion.prepareStatement("UPDATE contact SET text_contact =" + texContac + " WHERE ID_CONTACT = " + idPerson + "");
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
        jMenuActuaPro = new javax.swing.JMenuItem();
        jMenuContactPro = new javax.swing.JMenuItem();
        jMenuMosContPro = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuActuContP = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tlProveedores = new javax.swing.JTable();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tlContacProvee = new javax.swing.JTable();
        boxTypeCor = new javax.swing.JComboBox<>();
        txtTypeCpr = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        txtNamePr1 = new javax.swing.JTextField();
        txtNamePr2 = new javax.swing.JTextField();
        txtNamePr3 = new javax.swing.JTextField();
        txtLasNamePr1 = new javax.swing.JTextField();
        txtLasNamePr2 = new javax.swing.JTextField();
        btGuardPro = new javax.swing.JButton();

        jMenuActuaPro.setText("Actualisar Proveedor");
        jMenuActuaPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuActuaProActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuActuaPro);

        jMenuContactPro.setText("Agregar Coantacto a Proveedor");
        jMenuContactPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuContactProActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuContactPro);

        jMenuMosContPro.setText("Mostrar contactos del Proveedor");
        jMenuMosContPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuMosContProActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuMosContPro);

        jMenuActuContP.setText("Actualisar Contacto");
        jMenuActuContP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuActuContPActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuActuContP);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Proveedores");

        jLabel1.setText("Tipo de Contacto a Guardar");

        jLabel10.setText("Escriba la informacion");

        tlProveedores.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tlProveedores.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tlProveedores);

        jLabel12.setText("Informacion de Contacto de la Persona");

        tlContacProvee.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tlContacProvee.setComponentPopupMenu(jPopupMenu2);
        jScrollPane2.setViewportView(tlContacProvee);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxTypeCor, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTypeCpr, javax.swing.GroupLayout.PREFERRED_SIZE, 366, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel12))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(9, 9, 9)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel10)
                    .addComponent(boxTypeCor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTypeCpr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 455, Short.MAX_VALUE))
                    .addComponent(jScrollPane1)))
        );

        jTabbedPane1.addTab("Proveedores", jPanel1);

        jLabel2.setText("Primer Nombre");

        jLabel3.setText("Segundo Nombre");

        jLabel4.setText("Tercer Nombre");

        jLabel5.setText("Primer Apellido");

        jLabel6.setText("Segundo Apellido");

        txtNamePr3.setText("--");
        txtNamePr3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNamePr3ActionPerformed(evt);
            }
        });

        btGuardPro.setText("Guardar Proveedor");
        btGuardPro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardProActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator3)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNamePr1)
                            .addComponent(txtLasNamePr1))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6)
                            .addComponent(txtNamePr2)
                            .addComponent(txtLasNamePr2))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNamePr3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(95, 95, 95)
                        .addComponent(btGuardPro)))
                .addContainerGap(436, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNamePr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamePr2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNamePr3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLasNamePr1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLasNamePr2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btGuardPro)
                .addContainerGap(312, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nuevo Proveedor", jPanel2);

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

    private void txtNamePr3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNamePr3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNamePr3ActionPerformed

    private void btGuardProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardProActionPerformed
        // TODO add your handling code here:
        String N1Em = txtNamePr1.getText();
        String N2Em = txtNamePr2.getText();
        String N3Em = txtNamePr3.getText();
        String Las1Em = txtLasNamePr1.getText();
        String Las2Em = txtLasNamePr2.getText();

        ContarPerson idid = new ContarPerson();

        int idss = idid.id_incre();
        System.out.println("Numero id: " + idss);

        if (N1Em.isEmpty() || N2Em.isEmpty() || N3Em.isEmpty() || Las1Em.isEmpty() || Las2Em.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacios por favor llenar registros.");
        } else {

            System.out.println("Numero id: " + N1Em);
            System.out.println("Numero id: " + N2Em);
            System.out.println("Numero id: " + N3Em);
            System.out.println("Numero id: " + Las1Em);
            System.out.println("Numero id: " + Las2Em);

            try ( Connection conexion = con.get_connection()) {
                try {
                    PreparedStatement ps = null;
                    String query = ("INSERT INTO people(NAME1,NAME2,NAME3,last_name1,last_name2)\n"
                            + "VALUE('" + N1Em + "','" + N2Em + "','" + N3Em + "','" + Las1Em + "','" + Las2Em + "')");

                    ps = conexion.prepareStatement(query);
                    ps.executeUpdate();

                    // JOptionPane.showMessageDialog(this, "Guardado.");
                } catch (Exception e) {
                    System.err.print(e.toString());
                    JOptionPane.showMessageDialog(this, "Ocurrio un error al guardar.");
                }
            } catch (SQLException e) {
                System.err.print(e.toString());
                JOptionPane.showMessageDialog(this, "Ocurrio un error inesperado.\nFavor comunicarse con el administrador.");
            }
            //*********

            try ( Connection conexion = con.get_connection()) {
                try {
                    PreparedStatement ps = null;
                    String query = ("INSERT INTO person_classification(id_person,id_company,id_class_Person,id_status)\n"
                            + "VALUE(" + idss + ",3,2,4)");
                    ps = conexion.prepareStatement(query);
                    ps.executeUpdate();

                    showTableProduct();

                    txtNamePr1.setText("");
                    txtNamePr2.setText("");
                    txtNamePr3.setText("");
                    txtLasNamePr1.setText("");
                    txtLasNamePr2.setText("");

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
    }//GEN-LAST:event_btGuardProActionPerformed

    private void jMenuContactProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuContactProActionPerformed
        // TODO add your handling code here:
        guardarContacto();
    }//GEN-LAST:event_jMenuContactProActionPerformed

    private void jMenuActuaProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuActuaProActionPerformed
        // TODO add your handling code here:
        actualizarDatosPro();
    }//GEN-LAST:event_jMenuActuaProActionPerformed

    private void jMenuActuContPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuActuContPActionPerformed
        // TODO add your handling code here:
        actualizarContacto();
    }//GEN-LAST:event_jMenuActuContPActionPerformed

    private void jMenuMosContProActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuMosContProActionPerformed
        // TODO add your handling code here:
        mostrarInforContacto();
    }//GEN-LAST:event_jMenuMosContProActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxTypeCor;
    private javax.swing.JButton btGuardPro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JMenuItem jMenuActuContP;
    private javax.swing.JMenuItem jMenuActuaPro;
    private javax.swing.JMenuItem jMenuContactPro;
    private javax.swing.JMenuItem jMenuMosContPro;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tlContacProvee;
    private javax.swing.JTable tlProveedores;
    private javax.swing.JTextField txtLasNamePr1;
    private javax.swing.JTextField txtLasNamePr2;
    private javax.swing.JTextField txtNamePr1;
    private javax.swing.JTextField txtNamePr2;
    private javax.swing.JTextField txtNamePr3;
    private javax.swing.JTextField txtTypeCpr;
    // End of variables declaration//GEN-END:variables

    private void cargarTypeCommrr(JComboBox c) {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        c.setModel(combo);
        TCType_contact ctc = new TCType_contact();

        try {
            Statement st = conexcion.createStatement();
            ResultSet rr = st.executeQuery("SELECT * FROM type_contact");

            while (rr.next()) {
                type_contact con = new type_contact();
                System.out.println(rr.getString(1));
                con.setId_type_contact(rr.getInt(1));
                con.setName_type_contact(rr.getString(2));
                ctc.Agregartype_contact(con);
                combo.addElement(con.getId_type_contact() + " - " + con.getName_type_contact());
                System.out.println("Exito....");
            }

        } catch (Exception e) {
            System.out.println("Error, no se puede mostrar combo" + e);
        }
    }
}
