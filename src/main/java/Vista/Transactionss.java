/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Controler.conexcion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mdavi
 */
public class Transactionss extends javax.swing.JInternalFrame {
    
     conexcion con = new conexcion();
    Connection conexcion = con.get_connection();

   
    public Transactionss() {
        initComponents();
        showTableTransactionss();
    }

    public void showTableTransactionss(){
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Id");
        model.addColumn("Mombre de la Transacción");
        tlTransactionss.setModel(model);
        
        String[] datos = new String[2];
        try {
            
            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery("SELECT * FROM product");
            
            while (rs.next()) {

                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                model.addRow(datos);
        
            }
            tlTransactionss.setModel(model);
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"error" + e.toString());
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tlTransactionss = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Tipo de  transacción");

        tlTransactionss.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tlTransactionss.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tlTransactionss.setSelectionBackground(new java.awt.Color(153, 204, 255));
        jScrollPane1.setViewportView(tlTransactionss);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(363, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(118, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tlTransactionss;
    // End of variables declaration//GEN-END:variables
}
