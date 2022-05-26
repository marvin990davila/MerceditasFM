/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author mdavi
 */
public class Systemm extends javax.swing.JFrame {
    
   

    /**
     * Creates new form Systemm
     */
    public Systemm() { 
        
        
        
        initComponents();
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        menuBar2 = new java.awt.MenuBar();
        menu3 = new java.awt.Menu();
        menu4 = new java.awt.Menu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        Desk = new javax.swing.JLayeredPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jPerson = new javax.swing.JMenu();
        btCustomers = new javax.swing.JMenuItem();
        btProviders = new javax.swing.JMenuItem();
        btBusiness = new javax.swing.JMenuItem();
        btEmployees = new javax.swing.JMenuItem();
        btNewUser = new javax.swing.JMenuItem();
        btTrainings = new javax.swing.JMenuItem();
        jProduct = new javax.swing.JMenu();
        btcellar = new javax.swing.JMenuItem();
        btProducts = new javax.swing.JMenuItem();
        jTools = new javax.swing.JMenu();
        btMeasures = new javax.swing.JMenuItem();
        btNewCellar = new javax.swing.JMenuItem();
        btTipeContact = new javax.swing.JMenuItem();

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        menu3.setLabel("File");
        menuBar2.add(menu3);

        menu4.setLabel("Edit");
        menuBar2.add(menu4);

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocationByPlatform(true);
        setMaximumSize(new java.awt.Dimension(1920, 1055));
        setPreferredSize(new java.awt.Dimension(1172, 577));

        Desk.setToolTipText("");
        Desk.setPreferredSize(new java.awt.Dimension(1918, 1053));

        javax.swing.GroupLayout DeskLayout = new javax.swing.GroupLayout(Desk);
        Desk.setLayout(DeskLayout);
        DeskLayout.setHorizontalGroup(
            DeskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1185, Short.MAX_VALUE)
        );
        DeskLayout.setVerticalGroup(
            DeskLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 544, Short.MAX_VALUE)
        );

        jPerson.setText("Personas");

        btCustomers.setText("Clientes");
        btCustomers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCustomersActionPerformed(evt);
            }
        });
        jPerson.add(btCustomers);

        btProviders.setText("Proveedores");
        btProviders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProvidersActionPerformed(evt);
            }
        });
        jPerson.add(btProviders);

        btBusiness.setText("Empresas");
        btBusiness.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBusinessActionPerformed(evt);
            }
        });
        jPerson.add(btBusiness);

        btEmployees.setText("Empleados");
        btEmployees.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEmployeesActionPerformed(evt);
            }
        });
        jPerson.add(btEmployees);

        btNewUser.setText("Usuario");
        btNewUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btNewUserActionPerformed(evt);
            }
        });
        jPerson.add(btNewUser);

        btTrainings.setText("Capacitaciones");
        btTrainings.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTrainingsActionPerformed(evt);
            }
        });
        jPerson.add(btTrainings);

        jMenuBar1.add(jPerson);

        jProduct.setText("Productos");

        btcellar.setText("Bodegas");
        btcellar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btcellarActionPerformed(evt);
            }
        });
        jProduct.add(btcellar);

        btProducts.setText("Productos");
        btProducts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProductsActionPerformed(evt);
            }
        });
        jProduct.add(btProducts);

        jMenuBar1.add(jProduct);

        jTools.setText("Herramientas");

        btMeasures.setText("Medidas");
        btMeasures.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btMeasuresActionPerformed(evt);
            }
        });
        jTools.add(btMeasures);

        btNewCellar.setText("Nueva Bodegas");
        jTools.add(btNewCellar);

        btTipeContact.setText("Tipo de comtactos");
        btTipeContact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btTipeContactActionPerformed(evt);
            }
        });
        jTools.add(btTipeContact);

        jMenuBar1.add(jTools);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Desk, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1185, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Desk, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btcellarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btcellarActionPerformed
        // TODO add your handling code here:
        Cellarss windowCellar = new Cellarss();
        Desk.add(windowCellar);
        windowCellar.show();
    }//GEN-LAST:event_btcellarActionPerformed

    private void btNewUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btNewUserActionPerformed
        // TODO add your handling code here:
        Usserss windowUsserss = new Usserss();
        Desk.add(windowUsserss);
        windowUsserss.show();
        /*
        NewUssers windowNeuUsers = new NewUssers();
        Desk.add(windowNeuUsers);
        windowNeuUsers.show();*/
    }//GEN-LAST:event_btNewUserActionPerformed

    private void btProductsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProductsActionPerformed
        // TODO add your handling code here:
        Productss windowProduct = new Productss();
        Desk.add(windowProduct);
        windowProduct.show();
        
    }//GEN-LAST:event_btProductsActionPerformed

    private void btMeasuresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btMeasuresActionPerformed
        // TODO add your handling code here:
        Measuress windowMeasures = new Measuress();
        Desk.add(windowMeasures);
        windowMeasures.show();
        
        
    }//GEN-LAST:event_btMeasuresActionPerformed

    private void btCustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCustomersActionPerformed
        // TODO add your handling code here:
        Customerss windowCustomerss = new Customerss();
        Desk.add(windowCustomerss);
        windowCustomerss.show();
    }//GEN-LAST:event_btCustomersActionPerformed

    private void btProvidersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProvidersActionPerformed
        // TODO add your handling code here:
        Providerss windowProviderss = new Providerss();
        Desk.add(windowProviderss);
        windowProviderss.show();
    }//GEN-LAST:event_btProvidersActionPerformed

    private void btBusinessActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBusinessActionPerformed
        // TODO add your handling code here:
        Business windowBusiness = new Business();
        Desk.add(windowBusiness);
        windowBusiness.show();
    }//GEN-LAST:event_btBusinessActionPerformed

    private void btEmployeesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEmployeesActionPerformed
        // TODO add your handling code here:
        Employeess windowEmployeess = new Employeess();
        Desk.add(windowEmployeess);
        windowEmployeess.show();
    }//GEN-LAST:event_btEmployeesActionPerformed

    private void btTrainingsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTrainingsActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_btTrainingsActionPerformed

    private void btTipeContactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btTipeContactActionPerformed
        // TODO add your handling code here:
         TipeContact windowTipeContact = new TipeContact();
        Desk.add(windowTipeContact);
        windowTipeContact.show();
        
    }//GEN-LAST:event_btTipeContactActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Systemm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Systemm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Systemm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Systemm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Systemm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane Desk;
    private javax.swing.JMenuItem btBusiness;
    private javax.swing.JMenuItem btCustomers;
    private javax.swing.JMenuItem btEmployees;
    private javax.swing.JMenuItem btMeasures;
    private javax.swing.JMenuItem btNewCellar;
    private javax.swing.JMenuItem btNewUser;
    private javax.swing.JMenuItem btProducts;
    private javax.swing.JMenuItem btProviders;
    private javax.swing.JMenuItem btTipeContact;
    private javax.swing.JMenuItem btTrainings;
    private javax.swing.JMenuItem btcellar;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenu jPerson;
    private javax.swing.JMenu jProduct;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JMenu jTools;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.Menu menu3;
    private java.awt.Menu menu4;
    private java.awt.MenuBar menuBar1;
    private java.awt.MenuBar menuBar2;
    // End of variables declaration//GEN-END:variables

    

}
