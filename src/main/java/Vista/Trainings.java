/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Controler.CTNewTraining;
import Controler.ContarPerson;
import Controler.TCType_contact;
import Controler.conexcion;
import Modelo.name_training;
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
public class Trainings extends javax.swing.JInternalFrame {

    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();

    public Trainings() {
        initComponents();
        cargarBoxTraining(boxTraining);
        showTableProduct();
        showTableTraining();
    }

    public void showTableProduct() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("DPI");
        model.addColumn("1 Nombre");
        model.addColumn("2 Nombre");
        model.addColumn("1 Apellido");
        model.addColumn("2 Apellido");

        tlEmployTraining.setModel(model);

        String[] datos = new String[6];
        try {

            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery("SELECT pe.ID_PERSON,pe.DPI,pe.NAME1,pe.NAME2,pe.LAST_NAME1,pe.LAST_NAME2\n"
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

                model.addRow(datos);

            }
            tlEmployTraining.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }
    }

    public void showTableTraining() {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Capacitación");
        model.addColumn("Descripción");

        tlNewTraining.setModel(model);

        String[] datos = new String[3];
        try {

            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery("SELECT * from name_training");

            while (rs.next()) {

                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                model.addRow(datos);

            }
            tlNewTraining.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }
    }

    public void AddTraining() {

        int fila = tlEmployTraining.getSelectedRow();
        System.out.println(fila);
        int idPerson = Integer.parseInt(this.tlEmployTraining.getValueAt(fila, 0).toString());

         System.out.println("Numero Bodega salida: " + idPerson);
        
        String TyCon = boxTraining.getSelectedItem().toString();
        String[] RayTyCon = TyCon.split("-");

        System.out.println("Numero Bodega salida: " + RayTyCon[0]);

        String TfeConEm = ((JTextField) jDateTraining.getDateEditor().getUiComponent()).getText();

        if (jDateTraining.getCalendar() != null) {
            try ( Connection conexion = con.get_connection()) {
                try {
                    PreparedStatement ps = null;
                    String query = ("INSERT INTO training(id_name_training,id_person,date_training)\n"
                            + "VALUE('" + RayTyCon[0] + "','" + idPerson + "','" + Date.valueOf(TfeConEm) + "')");

                    ps = conexion.prepareStatement(query);
                    ps.executeUpdate();
                    mostrarInforContacto();

                    

                    // JOptionPane.showMessageDialog(this, "Guardado.");
                } catch (Exception e) {
                    System.err.print(e.toString());
                    JOptionPane.showMessageDialog(this, "Ocurrio un error al guaredar.");
                }
            } catch (SQLException e) {
                System.err.print(e.toString());
                JOptionPane.showMessageDialog(this, "Ocurrio un error inesperado.\nFavor comunicarse con el administrador.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Campos fecha vacios porfabor llenar registros.");
        }
    }

    public void mostrarInforContacto() {

        int fila = tlEmployTraining.getSelectedRow();
        System.out.println(fila);
        int idPe = Integer.parseInt(this.tlEmployTraining.getValueAt(fila, 0).toString());

        /**/
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Id Capacitación");
        model.addColumn("Capacitación");
        model.addColumn("Fecha de Capacitación");

        tlPersonTraining.setModel(model);

        String[] datos = new String[4];
        try {

            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery("SELECT pe.ID_PERSON, tr.ID_NAME_TRAINING, nt.TEXT_TRAINING,tr.DATE_TRAINING\n"
                    + "FROM training tr JOIN people pe ON  tr.id_person=pe.ID_PERSON\n"
                    + "JOIN name_training nt ON tr.id_name_training=nt.ID_NAME_TRAINING WHERE pe.ID_PERSON = " + idPe + " ");

            while (rs.next()) {

                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);

                model.addRow(datos);

            }
            tlPersonTraining.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }

    }
    
    
    public void actualizarTraining() {
        int fila = tlPersonTraining.getSelectedRow();
        System.out.println(fila);

        int idProduct = Integer.parseInt(this.tlPersonTraining.getValueAt(fila, 0).toString());
        String idTr = tlPersonTraining.getValueAt(fila, 1).toString();

        int TidT = Integer.parseInt(idTr);
        
        System.out.println("La cantidad es: " + idTr);

        try {
            PreparedStatement actu = conexcion.prepareStatement("UPDATE training SET ID_NAME_TRAINING =" + TidT + " WHERE ID_PERSON = " + idProduct + "");
            actu.executeUpdate();
            mostrarInforContacto();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());

        }
    }
    
    
    public void actualizarNewTraining() {
        
        int fila = tlNewTraining.getSelectedRow();
        System.out.println(fila);

        int idProduct = Integer.parseInt(this.tlNewTraining.getValueAt(fila, 0).toString());
        String nametra = tlNewTraining.getValueAt(fila, 1).toString();
        String dest = tlNewTraining.getValueAt(fila, 2).toString();

        System.out.println("La cantidad es: " + nametra);
        System.out.println("El precio es : " + dest);

        try {
            PreparedStatement actu = conexcion.prepareStatement("UPDATE name_training SET TEXT_TRAINING ='" + nametra + "', description='" + dest + "' WHERE ID_NAME_TRAINING = " + idProduct + "");
            actu.executeUpdate();
            showTableTraining();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());

        }
    }
    
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuSouh = new javax.swing.JMenuItem();
        jMenuAdd = new javax.swing.JMenuItem();
        jPopupMenu2 = new javax.swing.JPopupMenu();
        jMenuCapa = new javax.swing.JMenuItem();
        jPopupMenu3 = new javax.swing.JPopupMenu();
        jMenuTrainActua = new javax.swing.JMenuItem();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tlEmployTraining = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        boxTraining = new javax.swing.JComboBox<>();
        jDateTraining = new com.toedter.calendar.JDateChooser();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tlPersonTraining = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tlNewTraining = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtNameTraining = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txaDescripTrai = new javax.swing.JTextArea();
        jButton1 = new javax.swing.JButton();

        jMenuSouh.setText("Mostrar Capacitaciones de la persona");
        jMenuSouh.setToolTipText("");
        jMenuSouh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuSouhActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuSouh);

        jMenuAdd.setText("Asignar capacitación");
        jMenuAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAddActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuAdd);

        jMenuCapa.setText("Actualisar Capacitación");
        jMenuCapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuCapaActionPerformed(evt);
            }
        });
        jPopupMenu2.add(jMenuCapa);

        jMenuTrainActua.setText("Actualisar campos de la Capacitación");
        jMenuTrainActua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuTrainActuaActionPerformed(evt);
            }
        });
        jPopupMenu3.add(jMenuTrainActua);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Capacitaciones");

        tlEmployTraining.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tlEmployTraining.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(tlEmployTraining);

        jLabel2.setText("Nombre de la Capacitación");

        jDateTraining.setDateFormatString("yyyy-MM-dd");

        jLabel4.setText("Fecha de la Capacitación");

        tlPersonTraining.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tlPersonTraining.setComponentPopupMenu(jPopupMenu2);
        jScrollPane4.setViewportView(tlPersonTraining);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxTraining, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateTraining, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel2)
                        .addComponent(boxTraining, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel4))
                    .addComponent(jDateTraining, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Capacitaciones", jPanel1);

        tlNewTraining.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tlNewTraining.setComponentPopupMenu(jPopupMenu3);
        jScrollPane1.setViewportView(tlNewTraining);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Nombre de la Capacitación");

        jLabel3.setText("Descripción de la Capacitación");

        txaDescripTrai.setColumns(20);
        txaDescripTrai.setRows(5);
        jScrollPane3.setViewportView(txaDescripTrai);

        jButton1.setText("Guardar nueva Capacitación");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(118, 118, 118))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 247, Short.MAX_VALUE)
                            .addComponent(txtNameTraining))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jButton1))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNameTraining, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 680, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Crear capacitación", jPanel3);

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

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:

        String tNaTra = txtNameTraining.getText();
        String NDes = txaDescripTrai.getText();

        if (tNaTra.isEmpty() || NDes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacios porfabor llenar registros.");
        } else {
            System.out.println("Numero id: " + tNaTra);
            System.out.println("Numero id: " + NDes);

            try ( Connection conexion = con.get_connection()) {
                try {
                    PreparedStatement ps = null;
                    String query = ("INSERT INTO name_training(text_training,description)\n"
                            + "VALUE('" + tNaTra + "','" + NDes + "');");

                    ps = conexion.prepareStatement(query);
                    ps.executeUpdate();
                    txtNameTraining.setText("");
                    txaDescripTrai.setText("");

                    showTableTraining();

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

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jMenuSouhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuSouhActionPerformed
        // TODO add your handling code here:
        mostrarInforContacto();
    }//GEN-LAST:event_jMenuSouhActionPerformed

    private void jMenuAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAddActionPerformed
        // TODO add your handling code here:
        AddTraining();
    }//GEN-LAST:event_jMenuAddActionPerformed

    private void jMenuCapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuCapaActionPerformed
        // TODO add your handling code here:
        actualizarTraining();
    }//GEN-LAST:event_jMenuCapaActionPerformed

    private void jMenuTrainActuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuTrainActuaActionPerformed
        // TODO add your handling code here:
        actualizarNewTraining();
    }//GEN-LAST:event_jMenuTrainActuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> boxTraining;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateTraining;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenuItem jMenuAdd;
    private javax.swing.JMenuItem jMenuCapa;
    private javax.swing.JMenuItem jMenuSouh;
    private javax.swing.JMenuItem jMenuTrainActua;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JPopupMenu jPopupMenu2;
    private javax.swing.JPopupMenu jPopupMenu3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tlEmployTraining;
    private javax.swing.JTable tlNewTraining;
    private javax.swing.JTable tlPersonTraining;
    private javax.swing.JTextArea txaDescripTrai;
    private javax.swing.JTextField txtNameTraining;
    // End of variables declaration//GEN-END:variables

    private void cargarBoxTraining(JComboBox c) {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        c.setModel(combo);
        CTNewTraining ctc = new CTNewTraining();

        try {
            Statement st = conexcion.createStatement();
            ResultSet rr = st.executeQuery("SELECT * FROM name_training");

            while (rr.next()) {
                name_training con = new name_training();
                System.out.println(rr.getString(1));
                con.setId_name_training(rr.getInt(1));
                con.setText_training(rr.getString(2));
                ctc.AgregarTraining(con);
                combo.addElement(con.getId_name_training() + " - " + con.getText_training());
                System.out.println("Exito....");
            }

        } catch (Exception e) {
            System.out.println("Error , no se puede mostrar combo" + e);
        }
    }
}
