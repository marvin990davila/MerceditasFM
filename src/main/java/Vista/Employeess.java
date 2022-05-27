/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Controler.CTCellar;
import Controler.ContarPerson;
import Controler.TCType_contact;
import Controler.conexcion;
import Modelo.cellar;
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

public class Employeess extends javax.swing.JInternalFrame {

    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();

    public Employeess() {
        initComponents();
        cargarTypeCon(boxTypeContact);
        showTableProduct();
    }

    public void showTableProduct() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("DPI");
        model.addColumn("1 Nombre");
        model.addColumn("2 Nombre");
        model.addColumn("3 Nombre");
        model.addColumn("1 Apellido");
        model.addColumn("2 Apellido");
        model.addColumn("Cumpleaños");
        model.addColumn("Nit");
        model.addColumn("Fecha Contratación");
        model.addColumn("Estado Empleado");
        tlEmpleados.setModel(model);

        String[] datos = new String[11];
        try {

            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery("SELECT pe.ID_PERSON,pe.DPI,pe.NAME1,pe.NAME2,pe.NAME3,pe.LAST_NAME1,pe.LAST_NAME2,pe.BIRTHDAY,pe.NIT_PERSON,\n"
                    + "pc.DATE_STATUS,sp.NAME_STATUS_PERSON \n"
                    + "FROM person_classification pc JOIN people pe ON pc.id_person=pe.ID_PERSON\n"
                    + "JOIN company co ON pc.id_company=co.ID_COMPANY\n"
                    + "JOIN class_person cp ON pc.id_class_person=cp.ID_CLASS_PERSON\n"
                    + "JOIN status_person sp ON pc.id_status=sp.ID_STATUS WHERE pc.id_class_person = 1");

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
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                model.addRow(datos);

            }
            tlEmpleados.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }
    }

    public void actualizarDatos() {
        int fila = tlEmpleados.getSelectedRow();
        System.out.println(fila);
        int idPerson = Integer.parseInt(this.tlEmpleados.getValueAt(fila, 0).toString());
        String DDpi = tlEmpleados.getValueAt(fila, 1).toString();
        String N1name = tlEmpleados.getValueAt(fila, 2).toString();
        String N2name = tlEmpleados.getValueAt(fila, 3).toString();
        String N3name = tlEmpleados.getValueAt(fila, 4).toString();
        String N1las = tlEmpleados.getValueAt(fila, 5).toString();
        String N2las = tlEmpleados.getValueAt(fila, 6).toString();
        String dateBir = tlEmpleados.getValueAt(fila, 7).toString();
        String Dnit = tlEmpleados.getValueAt(fila, 8).toString();

        if (DDpi.isEmpty() || N1name.isEmpty() || N2name.isEmpty() || N3name.isEmpty() || N1las.isEmpty() || N2las.isEmpty() || Dnit.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacios porfabor llenar registros.");
        } else {

            int Mdpi = Integer.parseInt(DDpi);
            try {
                PreparedStatement actu = conexcion.prepareStatement("UPDATE people SET dpi =" + Mdpi + ", name1='" + N1name + "', name2 ='" + N2name + "', name3 = '" + N3name + "',\n"
                        + "last_name1 = '" + N1las + "',last_name2 = '" + N2las + "',birthday = '" + Date.valueOf(dateBir) + "',nit_person = '" + Dnit + "' WHERE ID_person = " + idPerson + "");
                actu.executeUpdate();
                showTableProduct();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error" + e.toString());
            }

        }
    }

    public void guardarContacto() {

        int fila = tlEmpleados.getSelectedRow();
        System.out.println(fila);
        int idPerson = Integer.parseInt(this.tlEmpleados.getValueAt(fila, 0).toString());

        String TyCon = boxTypeContact.getSelectedItem().toString();
        String[] RayTyCon = TyCon.split("-");

        System.out.println("Numero Bodega salida: " + RayTyCon[0]);

        String TyTexCont = txtTypeContact.getText();

        if (TyTexCont.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacio porfabor llenar registro.");
        } else {
            try ( Connection conexion = con.get_connection()) {
                try {
                    PreparedStatement ps = null;
                    String query = ("INSERT INTO contact(id_type_contact, text_contact,id_person)\n"
                            + "VALUE(" + RayTyCon[0] + ",'" + TyTexCont + "'," + idPerson + ")");

                    ps = conexion.prepareStatement(query);
                    ps.executeUpdate();
                    txtTypeContact.setText("");

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

        int fila = tlEmpleados.getSelectedRow();
        System.out.println(fila);
        int idPe = Integer.parseInt(this.tlEmpleados.getValueAt(fila, 0).toString());

        /**/
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Typo de Contacto");
        model.addColumn("Info");

        tlContactEmploy.setModel(model);

        String[] datos = new String[3];
        try {

            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery("SELECT pe.ID_PERSON,ty.name_type_contact, co.text_contact\n"
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
            tlContactEmploy.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }

    }

    public void actualizarContacto() {
        int fila = tlContactEmploy.getSelectedRow();
        System.out.println(fila);
        int idPerson = Integer.parseInt(this.tlContactEmploy.getValueAt(fila, 0).toString());
        String texContac = tlContactEmploy.getValueAt(fila, 2).toString();

        if (texContac.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacios porfabor llenar registros.");
        } else {

            try {
                PreparedStatement actu = conexcion.prepareStatement("UPDATE contact SET text_contact =" + texContac + " WHERE ID_person = " + idPerson + "");
                actu.executeUpdate();
                mostrarInforContacto();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error" + e.toString());
            }

        }
    }

    public void EstatusPersonContratado() {

        int fila = tlEmpleados.getSelectedRow();
        System.out.println(fila);
        int idPersom = Integer.parseInt(this.tlEmpleados.getValueAt(fila, 0).toString());
        String Status = tlEmpleados.getValueAt(fila, 10).toString();
        String cont = "Contratado" ;
        
        System.out.println(Status);
        
        if (Status.equals(cont)) {
            JOptionPane.showMessageDialog(null, "Esta persona ya se encuentra Contratada");
        } else {
            try {
                PreparedStatement actu = conexcion.prepareStatement("UPDATE person_classification SET ID_STATUS = 1 WHERE ID_person =  " + idPersom + "");
                actu.executeUpdate();
                showTableProduct();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error" + e.toString());
            }
        }
    }

    public void EstatusPersonSuspendido() {

        int fila = tlEmpleados.getSelectedRow();
        System.out.println(fila);
        int idPersom = Integer.parseInt(this.tlEmpleados.getValueAt(fila, 0).toString());
        String Status = tlEmpleados.getValueAt(fila, 10).toString();
        String cont = "Suspendido" ;
        
        System.out.println(Status);
        
        if (Status.equals(cont)) {
            JOptionPane.showMessageDialog(null, "Esta persona ya se encuentra Suspendido");
        } else {
            try {
                PreparedStatement actu = conexcion.prepareStatement("UPDATE person_classification SET ID_STATUS = 2 WHERE ID_person =  " + idPersom + "");
                actu.executeUpdate();
                showTableProduct();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error" + e.toString());
            }
        }

    }

    public void EstatusPersonDespedido() {

        int fila = tlEmpleados.getSelectedRow();
        System.out.println(fila);
        int idPersom = Integer.parseInt(this.tlEmpleados.getValueAt(fila, 0).toString());
        String Status = tlEmpleados.getValueAt(fila, 10).toString();
        String cont = "Despedido" ;
        
        System.out.println(Status);
        
        if (Status.equals(cont)) {
            JOptionPane.showMessageDialog(null, "Esta persona ya se encuentra Despedido");
        } else {
            try {
                PreparedStatement actu = conexcion.prepareStatement("UPDATE person_classification SET ID_STATUS = 3 WHERE ID_person =  " + idPersom + "");
                actu.executeUpdate();
                showTableProduct();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error" + e.toString());
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuActuEmpleado = new javax.swing.JMenuItem();
        jMenuGuardar = new javax.swing.JMenuItem();
        jMenuMostrar = new javax.swing.JMenuItem();
        jMenuContratado = new javax.swing.JMenuItem();
        jMenuSuspendido = new javax.swing.JMenuItem();
        jMenuDespedido = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuActualisarContact = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        jLayeredPane1 = new javax.swing.JLayeredPane();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tlEmpleados = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tlContactEmploy = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        txtTypeContact = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        boxTypeContact = new javax.swing.JComboBox<>();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtPriNameEm = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtSecNameEm = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTerNameEm = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtPriApeEm = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtSecApeEmple = new javax.swing.JTextField();
        jDateBirthdayEm = new com.toedter.calendar.JDateChooser();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtNitEm = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtDpiEm = new javax.swing.JTextField();
        btNewEmploy = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jDateContraEm = new com.toedter.calendar.JDateChooser();
        jLabel9 = new javax.swing.JLabel();

        jMenuActuEmpleado.setText("Actualizar Empleado");
        jMenuActuEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuActuEmpleadoActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuActuEmpleado);

        jMenuGuardar.setText("Guardar Contacto de Persona");
        jMenuGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuGuardarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuGuardar);

        jMenuMostrar.setText("Mostar Informacion de Contacto de la persona");
        jMenuMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuMostrarActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuMostrar);

        jMenuContratado.setText("Contratar Persona");
        jMenuContratado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuContratadoActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuContratado);

        jMenuSuspendido.setText("Suspender Persona");
        jMenuSuspendido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSuspendidoActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuSuspendido);

        jMenuDespedido.setText("Despedir Persona");
        jMenuDespedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuDespedidoActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuDespedido);

        jMenuActualisarContact.setText("Actualisar Contacto");
        jMenuActualisarContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuActualisarContactActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuActualisarContact);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Empleados");

        tlEmpleados.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tlEmpleados.setComponentPopupMenu(jPopupMenu1);
        jScrollPane1.setViewportView(tlEmpleados);

        tlContactEmploy.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tlContactEmploy.setComponentPopupMenu(jPopupMenu2);
        tlContactEmploy.setFocusCycleRoot(true);
        jScrollPane2.setViewportView(tlContactEmploy);

        jLabel10.setText("Escriva la informacion ");

        jLabel11.setText("Tipo de Contacto a Guardar");

        jLabel12.setText("Informacion de Contacto de la Persona");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(boxTypeContact, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtTypeContact, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTypeContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(boxTypeContact, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 4, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Empleados", jPanel1);

        jLabel1.setText("Primer Nombre");

        jLabel2.setText("Segundo Nombre");

        jLabel3.setText("Tercer Nombre");

        txtTerNameEm.setText("--");

        jLabel4.setText("Primer Nombre");

        jLabel5.setText("Primer Nombre");

        jDateBirthdayEm.setDateFormatString("yyyy-MM-dd");

        jLabel6.setText("Fecha de Nacimiento");

        jLabel7.setText("Nit");

        jLabel8.setText("DPI");

        btNewEmploy.setText("Guardar Empleado");
        btNewEmploy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewEmployActionPerformed(evt);
            }
        });

        jDateContraEm.setDateFormatString("yyyy-MM-dd");

        jLabel9.setText("Fecha de Contratacion");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator3)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDpiEm, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtNitEm, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPriNameEm, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4)
                            .addComponent(txtPriApeEm, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(txtSecNameEm, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtSecApeEmple, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateBirthdayEm, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(txtTerNameEm, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel9)
                        .addGap(18, 18, 18)
                        .addComponent(jDateContraEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(btNewEmploy)))
                .addContainerGap(612, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtDpiEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtNitEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTerNameEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtSecNameEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(18, 18, 18)
                        .addComponent(txtPriNameEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jLabel6)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPriApeEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSecApeEmple, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateBirthdayEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btNewEmploy)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jDateContraEm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)))
                .addContainerGap(124, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Nuevo Empleado", jPanel2);

        jLayeredPane1.setLayer(jTabbedPane1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jLayeredPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1)
                .addContainerGap())
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

    private void btNewEmployActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewEmployActionPerformed
        // Buton Guardar Employ

        String tDpi = txtDpiEm.getText();
        String N1Em = txtPriNameEm.getText();
        String N2Em = txtSecNameEm.getText();
        String N3Em = txtTerNameEm.getText();
        String Las1Em = txtPriApeEm.getText();
        String Las2Em = txtSecApeEmple.getText();
        String tNitEm = txtNitEm.getText();
        String Tfecha = ((JTextField) jDateBirthdayEm.getDateEditor().getUiComponent()).getText();
        String TfeConEm = ((JTextField) jDateContraEm.getDateEditor().getUiComponent()).getText();

        ContarPerson idid = new ContarPerson();

        int idss = idid.id_incre();
        System.out.println("Numero id: " + idss);

        if (jDateBirthdayEm.getCalendar() != null) {
            if (jDateContraEm.getCalendar() != null) {

                if (tDpi.isEmpty() || N1Em.isEmpty() || N2Em.isEmpty() || N3Em.isEmpty() || Las1Em.isEmpty() || Las2Em.isEmpty() || tNitEm.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Campos vacios porfabor llenar registros.");
                } else {

                    int Mdpi = Integer.parseInt(tDpi);
                    System.out.println("Numero id: " + Mdpi);
                    System.out.println("Numero id: " + N1Em);
                    System.out.println("Numero id: " + N2Em);
                    System.out.println("Numero id: " + N3Em);
                    System.out.println("Numero id: " + Las1Em);
                    System.out.println("Numero id: " + Las2Em);
                    System.out.println("Numero id: " + tNitEm);
                    System.out.println("Numero id: " + Tfecha);
                    System.out.println("Numero id: " + TfeConEm);

                    try ( Connection conexion = con.get_connection()) {
                        try {
                            PreparedStatement ps = null;
                            String query = ("INSERT INTO people(dpi,NAME1,NAME2,NAME3,last_name1,last_name2,birthday,nit_person)\n"
                                    + "VALUE(" + Mdpi + ",'" + N1Em + "','" + N2Em + "','" + N3Em + "','" + Las1Em + "','" + Las2Em + "','" + Date.valueOf(Tfecha) + "','" + tNitEm + "')");

                            ps = conexion.prepareStatement(query);
                            ps.executeUpdate();

                            // JOptionPane.showMessageDialog(this, "Guardado.");
                        } catch (Exception e) {
                            System.err.print(e.toString());
                            JOptionPane.showMessageDialog(this, "Ocurrio un error al guaredar.");
                        }
                    } catch (SQLException e) {
                        System.err.print(e.toString());
                        JOptionPane.showMessageDialog(this, "Ocurrio un error inesperado.\nFavor comunicarse con el administrador.");
                    }
                    //*********

                    try ( Connection conexion = con.get_connection()) {
                        try {
                            PreparedStatement ps = null;
                            String query = ("INSERT INTO person_classification(id_person,id_company,id_class_Person,id_status,date_status)\n"
                                    + "VALUE(" + idss + ",1,1,1,'" + Date.valueOf(TfeConEm) + "')");
                            ps = conexion.prepareStatement(query);
                            ps.executeUpdate();

                            showTableProduct();
                            txtDpiEm.setText("");
                            txtPriNameEm.setText("");
                            txtSecNameEm.setText("");
                            txtTerNameEm.setText("");
                            txtPriApeEm.setText("");
                            txtSecApeEmple.setText("");
                            txtNitEm.setText("");

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
            } else {
                JOptionPane.showMessageDialog(this, "Campos fecha vacios porfabor llenar registros.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Campos fecha vacios porfabor llenar registros.");
        }
    }//GEN-LAST:event_btNewEmployActionPerformed

    private void jMenuActuEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuActuEmpleadoActionPerformed
        // TODO add your handling code here:
        actualizarDatos();
    }//GEN-LAST:event_jMenuActuEmpleadoActionPerformed

    private void jMenuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuGuardarActionPerformed
        // TODO add your handling code here:
        guardarContacto();
    }//GEN-LAST:event_jMenuGuardarActionPerformed

    private void jMenuMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuMostrarActionPerformed
        // TODO add your handling code here:
        mostrarInforContacto();
    }//GEN-LAST:event_jMenuMostrarActionPerformed

    private void jMenuActualisarContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuActualisarContactActionPerformed
        // TODO add your handling code here:
        actualizarContacto();
    }//GEN-LAST:event_jMenuActualisarContactActionPerformed

    private void jMenuContratadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuContratadoActionPerformed
        // TODO add your handling code here:
        EstatusPersonContratado();
    }//GEN-LAST:event_jMenuContratadoActionPerformed

    private void jMenuSuspendidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSuspendidoActionPerformed
        // TODO add your handling code here:
        EstatusPersonSuspendido();
    }//GEN-LAST:event_jMenuSuspendidoActionPerformed

    private void jMenuDespedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuDespedidoActionPerformed
        // TODO add your handling code here:
        EstatusPersonDespedido();
    }//GEN-LAST:event_jMenuDespedidoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxTypeContact;
    private javax.swing.JButton btNewEmploy;
    private com.toedter.calendar.JDateChooser jDateBirthdayEm;
    private com.toedter.calendar.JDateChooser jDateContraEm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JMenuItem jMenuActuEmpleado;
    private javax.swing.JMenuItem jMenuActualisarContact;
    private javax.swing.JMenuItem jMenuContratado;
    private javax.swing.JMenuItem jMenuDespedido;
    private javax.swing.JMenuItem jMenuGuardar;
    private javax.swing.JMenuItem jMenuMostrar;
    private javax.swing.JMenuItem jMenuSuspendido;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tlContactEmploy;
    private javax.swing.JTable tlEmpleados;
    private javax.swing.JTextField txtDpiEm;
    private javax.swing.JTextField txtNitEm;
    private javax.swing.JTextField txtPriApeEm;
    private javax.swing.JTextField txtPriNameEm;
    private javax.swing.JTextField txtSecApeEmple;
    private javax.swing.JTextField txtSecNameEm;
    private javax.swing.JTextField txtTerNameEm;
    private javax.swing.JTextField txtTypeContact;
    // End of variables declaration//GEN-END:variables

    private void cargarTypeCon(JComboBox c) {
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
            System.out.println("Error , no se puede mostrar combo" + e);
        }
    }
}
