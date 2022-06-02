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
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mdavi
 */
public class Customerrs extends javax.swing.JInternalFrame {

    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();

    public Customerrs() {
        initComponents();
        cargarTypeComm(boxTypeCont);
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
        model.addColumn("Nit");
        tlClientes.setModel(model);

        String[] datos = new String[7];
        try {

            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery("SELECT pe.ID_PERSON,pe.NAME1,pe.NAME2,pe.NAME3,pe.LAST_NAME1,pe.LAST_NAME2,pe.NIT_PERSON\n"
                    + "FROM person_classification pc JOIN people pe ON pc.id_person=pe.ID_PERSON\n"
                    + "JOIN company co ON pc.id_company=co.ID_COMPANY\n"
                    + "JOIN class_person cp ON pc.id_class_person=cp.ID_CLASS_PERSON\n"
                    + "JOIN status_person sp ON pc.id_status=sp.ID_STATUS WHERE pc.id_class_person = 3");

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
                datos[6] = rs.getString(7);
                
                model.addRow(datos);

            }
            tlClientes.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }
    }

    public void actualizarDatosCli() {
        int fila = tlClientes.getSelectedRow();
        System.out.println(fila);
        int idCliente = Integer.parseInt(this.tlClientes.getValueAt(fila, 0).toString());
        String N1name = tlClientes.getValueAt(fila, 1).toString();
        String N2name = tlClientes.getValueAt(fila, 2).toString();
        String N3name = tlClientes.getValueAt(fila, 3).toString();
        String N1las = tlClientes.getValueAt(fila, 4).toString();
        String N2las = tlClientes.getValueAt(fila, 5).toString();
        String Dnit = tlClientes.getValueAt(fila, 6).toString();

        if (N1name.isEmpty() || N2name.isEmpty() || N3name.isEmpty() || N1las.isEmpty() || N2las.isEmpty() || Dnit.isEmpty()) {
            JOptionPane.showMessageDialog(this, "!!!Campos vacios por favor llenar registros.");
        } else {

            try {
                PreparedStatement actu = conexcion.prepareStatement("UPDATE people SET name1='" + N1name + "', name2 ='" + N2name + "', name3 = '" + N3name + "',\n"
                        + "last_name1 = '" + N1las + "',last_name2 = '" + N2las + "',nit_person = '" + Dnit + "' WHERE ID_person = " + idCliente + "");
                actu.executeUpdate();
                showTableProduct();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error" + e.toString());
            }

        }
    }

    public void guardarContacto() {

        int fila = tlClientes.getSelectedRow();
        System.out.println(fila);
        int idPerson = Integer.parseInt(this.tlClientes.getValueAt(fila, 0).toString());

        String TyCon = boxTypeCont.getSelectedItem().toString();
        String[] RayTyCon = TyCon.split("-");

        System.out.println("Numero Bodega salida: " + RayTyCon[0]);

        String TyTexCont = txtTypeCont.getText();

        if (TyTexCont.isEmpty()) {
            JOptionPane.showMessageDialog(this, "!!!Campos vacio por favor llenar registro.");
        } else {
            try ( Connection conexion = con.get_connection()) {
                try {
                    PreparedStatement ps = null;
                    String query = ("INSERT INTO contact(id_type_contact, text_contact,id_person)\n"
                            + "VALUE(" + RayTyCon[0] + ",'" + TyTexCont + "'," + idPerson + ")");

                    ps = conexion.prepareStatement(query);
                    ps.executeUpdate();
                    txtTypeCont.setText("");

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

        int fila = tlClientes.getSelectedRow();
        System.out.println(fila);
        int idPe = Integer.parseInt(this.tlClientes.getValueAt(fila, 0).toString());

        /**/
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Typo de Contacto");
        model.addColumn("Info");

        tlContacClientes.setModel(model);

        String[] datos = new String[3];
        try {

            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery("SELECT co.ID_CONTACT,ty.name_type_contact, co.text_contact\n" +
"FROM  type_contact ty JOIN contact co ON ty.id_type_contact=co.id_type_contact\n" +
"JOIN people pe ON co.ID_PERSON=pe.ID_PERSON WHERE pe.ID_PERSON = " + idPe + " ");

            while (rs.next()) {

                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                model.addRow(datos);

            }
            tlContacClientes.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }

    }
    
    
    
    
    public void actualizarContacto() {
        int fila = tlContacClientes.getSelectedRow();
        System.out.println(fila);
        int idPerson = Integer.parseInt(this.tlContacClientes.getValueAt(fila, 0).toString());
        String texContac = tlContacClientes.getValueAt(fila, 2).toString();



        if (texContac.isEmpty()) {
            JOptionPane.showMessageDialog(this, "!!!Campos vacios por favor llenar registros.");
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
        jMenuActuClient = new javax.swing.JMenuItem();
        jMenuGuardClien = new javax.swing.JMenuItem();
        jMenuMostContClient = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuActuContact = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        tlClientes = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tlContacClientes = new javax.swing.JTable();
        boxTypeCont = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtTypeCont = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        txtNitCl = new javax.swing.JTextField();
        txtPriName = new javax.swing.JTextField();
        txtSecName = new javax.swing.JTextField();
        txtTerName = new javax.swing.JTextField();
        txtPriLasName = new javax.swing.JTextField();
        txtSecLasName = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        btGuardarCliente = new javax.swing.JButton();

        jMenuActuClient.setText("Actualisar Cliente");
        jMenuActuClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuActuClientActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuActuClient);

        jMenuGuardClien.setText("Guardar Contacto de Cliente");
        jMenuGuardClien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuGuardClienActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuGuardClien);

        jMenuMostContClient.setText("Mostrar Contactos de Cliente");
        jMenuMostContClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuMostContClientActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuMostContClient);

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
        setTitle("Clientes");
        setToolTipText("");

        jLabel1.setText("Tipo de Contacto a Guardar");

        tlClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tlClientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tlClientes.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tlClientes);

        tlContacClientes.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tlContacClientes.setComponentPopupMenu(jPopupMenu2);
        jScrollPane2.setViewportView(tlContacClientes);

        jLabel10.setText("Escriba la informacion");

        jLabel12.setText("Informacion de Contacto de la Persona");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxTypeCont, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTypeCont, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(270, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(boxTypeCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(txtTypeCont, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE)))
        );

        jTabbedPane1.addTab("Clientes", jPanel1);

        jLabel7.setText("Nit");

        jLabel2.setText("Primer Nombre");

        jLabel3.setText("Segundo Nombre");

        jLabel4.setText("Tercer Nombre");

        jLabel5.setText("Primer Aplellido");

        jLabel6.setText("Segundo Apellido");

        txtTerName.setText("--");

        btGuardarCliente.setText("Guardar Cliente");
        btGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGuardarClienteActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNitCl, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPriName)
                            .addComponent(txtPriLasName))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtSecName))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTerName)))
                            .addComponent(txtSecLasName, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(91, 91, 91)
                        .addComponent(btGuardarCliente)))
                .addContainerGap(668, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtNitCl, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPriName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSecName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPriLasName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSecLasName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btGuardarCliente)
                .addContainerGap(163, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nuevo Cliente ", jPanel2);

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

    private void btGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGuardarClienteActionPerformed
        // TODO add your handling code here:
        
        
        String N1Em = txtPriName.getText();
        String N2Em = txtSecName.getText();
        String N3Em = txtTerName.getText();
        String Las1Em = txtPriLasName.getText();
        String Las2Em = txtSecLasName.getText();
        String tNitEm = txtNitCl.getText();
        

        ContarPerson idid = new ContarPerson();

        int idss = idid.id_incre();
        System.out.println("Numero id: " + idss);


                if (N1Em.isEmpty() || N2Em.isEmpty() || N3Em.isEmpty() || Las1Em.isEmpty() || Las2Em.isEmpty() || tNitEm.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "!!!Campos vacios porfabor llenar registros.");
                } else {

                    System.out.println("Numero id: " + N1Em);
                    System.out.println("Numero id: " + N2Em);
                    System.out.println("Numero id: " + N3Em);
                    System.out.println("Numero id: " + Las1Em);
                    System.out.println("Numero id: " + Las2Em);
                    System.out.println("Numero id: " + tNitEm);

                    try ( Connection conexion = con.get_connection()) {
                        try {
                            PreparedStatement ps = null;
                            String query = ("INSERT INTO people(NAME1,NAME2,NAME3,last_name1,last_name2,nit_person)\n"
                                    + "VALUE('" + N1Em + "','" + N2Em + "','" + N3Em + "','" + Las1Em + "','" + Las2Em + "','" + tNitEm + "')");

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
                                    + "VALUE(" + idss + ",2,3,4)");
                            ps = conexion.prepareStatement(query);
                            ps.executeUpdate();

                            showTableProduct();
                            
                            txtPriName.setText("");
                            txtSecName.setText("");
                            txtTerName.setText("");
                            txtPriLasName.setText("");
                            txtSecLasName.setText("");
                            txtNitCl.setText("");

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
    }//GEN-LAST:event_btGuardarClienteActionPerformed

    private void jMenuActuContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuActuContactActionPerformed
        // TODO add your handling code here:
        actualizarContacto();
    }//GEN-LAST:event_jMenuActuContactActionPerformed

    private void jMenuMostContClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuMostContClientActionPerformed
        // TODO add your handling code here:
        mostrarInforContacto();
    }//GEN-LAST:event_jMenuMostContClientActionPerformed

    private void jMenuGuardClienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuGuardClienActionPerformed
        // TODO add your handling code here:
        guardarContacto();
    }//GEN-LAST:event_jMenuGuardClienActionPerformed

    private void jMenuActuClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuActuClientActionPerformed
        // TODO add your handling code here:
        actualizarDatosCli();
    }//GEN-LAST:event_jMenuActuClientActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxTypeCont;
    private javax.swing.JButton btGuardarCliente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenuItem jMenuActuClient;
    private javax.swing.JMenuItem jMenuActuContact;
    private javax.swing.JMenuItem jMenuGuardClien;
    private javax.swing.JMenuItem jMenuMostContClient;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tlClientes;
    private javax.swing.JTable tlContacClientes;
    private javax.swing.JTextField txtNitCl;
    private javax.swing.JTextField txtPriLasName;
    private javax.swing.JTextField txtPriName;
    private javax.swing.JTextField txtSecLasName;
    private javax.swing.JTextField txtSecName;
    private javax.swing.JTextField txtTerName;
    private javax.swing.JTextField txtTypeCont;
    // End of variables declaration//GEN-END:variables

    private void cargarTypeComm(JComboBox c) {
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
