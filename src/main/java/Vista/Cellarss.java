/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package Vista;

import Controler.CTCellar;
import Controler.CTPerson;
import Controler.CTProduct;
import Controler.CTTransaction;
import Controler.Conta;
import Controler.conexcion;
import Modelo.cellar;
import Modelo.inventory;
import Modelo.people;
import Modelo.product;
import Modelo.transactions;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableColumn;

/**
 *
 * @author mdavi
 */
public class Cellarss extends javax.swing.JInternalFrame {

    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();

    public Cellarss() {
        initComponents();
        cargarCellar(BoxCellar1);
        cargarTransactions(boxTransactions);
        cargarProduct(boxProduct);
        cargarCellar(BoxSalidaCaller);
        cargarPerson(boxEmployer);
        cargarPerson(boxEmployerSAlida);
        showTableCellar(0, null);

        /*inventory objeInventori= new inventory();
        objeInventori.sho*/
    }

    public void showTableCellar(int opbuscar, String valor) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID Ingreso");
        model.addColumn("No.Bodega");
        model.addColumn("Bodega");
        model.addColumn("Tipo de transaccion");
        model.addColumn("Serie Producto");
        model.addColumn("No.Producto");
        model.addColumn("Producto");
        model.addColumn("Cantidad");
        model.addColumn("Precio");
        model.addColumn("Total");
        model.addColumn("Fecha de Ingreso");
        model.addColumn("Persona del ingreso");

        tlCellar.setModel(model);

        String codsql;

        if (opbuscar == 0 && valor == null) {
            codsql = "SELECT inv.ID_INVENTORY,inv.ID_CELLAR AS No,cl.NAME_CELLAR,tn.NAME_TRANSACTION,inv.SET_PRODUCT,inv.ID_PRODUCT AS No,pr.NAME_PRODUCT,inv.AMOUNT,inv.PRICE,inv.TOTAL\n"
                    + ",inv.DATE_ADMISSION,CONCAT_WS(' ',pe.NAME1,pe.name2,pe.NAME3,pe.LAST_NAME1,pe.LAST_NAME2)Nombre\n"
                    + "FROM inventory inv JOIN cellar cl ON inv.ID_CELLAR=cl.ID_CELLA\n"
                    + "JOIN product pr ON inv.ID_PRODUCT=pr.ID_PRODUCT\n"
                    + "JOIN transactions tn ON inv.ID_TRANSACTION=tn.ID_TRANSACTION\n"
                    + "JOIN people pe ON inv.ID_PERSON=pe.ID_PERSON";
        } else {
            if (opbuscar == 1 && valor != null) {
                int intvalor = Integer.parseInt(valor);

                codsql = "SELECT inv.ID_INVENTORY,inv.ID_CELLAR AS No,cl.NAME_CELLAR,tn.NAME_TRANSACTION,inv.SET_PRODUCT,inv.ID_PRODUCT AS No,pr.NAME_PRODUCT,inv.AMOUNT,inv.PRICE,inv.TOTAL\n"
                        + ",inv.DATE_ADMISSION,CONCAT_WS(' ',pe.NAME1,pe.name2,pe.NAME3,pe.LAST_NAME1,pe.LAST_NAME2)Nombre\n"
                        + "FROM inventory inv JOIN cellar cl ON inv.ID_CELLAR=cl.ID_CELLA\n"
                        + "JOIN product pr ON inv.ID_PRODUCT=pr.ID_PRODUCT\n"
                        + "JOIN transactions tn ON inv.ID_TRANSACTION=tn.ID_TRANSACTION\n"
                        + "JOIN people pe ON inv.ID_PERSON=pe.ID_PERSON where inv.ID_INVENTORY = " + intvalor + "";
            } else {

                if (opbuscar == 2 && valor != null) {

                    codsql = "SELECT inv.ID_INVENTORY,inv.ID_CELLAR AS No,cl.NAME_CELLAR,tn.NAME_TRANSACTION,inv.SET_PRODUCT,inv.ID_PRODUCT AS No,pr.NAME_PRODUCT,inv.AMOUNT,inv.PRICE,inv.TOTAL\n"
                            + ",inv.DATE_ADMISSION,CONCAT_WS(' ',pe.NAME1,pe.name2,pe.NAME3,pe.LAST_NAME1,pe.LAST_NAME2)Nombre\n"
                            + "FROM inventory inv JOIN cellar cl ON inv.ID_CELLAR=cl.ID_CELLA\n"
                            + "JOIN product pr ON inv.ID_PRODUCT=pr.ID_PRODUCT\n"
                            + "JOIN transactions tn ON inv.ID_TRANSACTION=tn.ID_TRANSACTION\n"
                            + "JOIN people pe ON inv.ID_PERSON=pe.ID_PERSON where cl.NAME_CELLAR = '" + valor + "'";
                } else {

                    if (opbuscar == 3 && valor != null) {

                        codsql = "SELECT inv.ID_INVENTORY,inv.ID_CELLAR AS No,cl.NAME_CELLAR,tn.NAME_TRANSACTION,inv.SET_PRODUCT,inv.ID_PRODUCT AS No,pr.NAME_PRODUCT,inv.AMOUNT,inv.PRICE,inv.TOTAL\n"
                                + ",inv.DATE_ADMISSION,CONCAT_WS(' ',pe.NAME1,pe.name2,pe.NAME3,pe.LAST_NAME1,pe.LAST_NAME2)Nombre\n"
                                + "FROM inventory inv JOIN cellar cl ON inv.ID_CELLAR=cl.ID_CELLA\n"
                                + "JOIN product pr ON inv.ID_PRODUCT=pr.ID_PRODUCT\n"
                                + "JOIN transactions tn ON inv.ID_TRANSACTION=tn.ID_TRANSACTION\n"
                                + "JOIN people pe ON inv.ID_PERSON=pe.ID_PERSON where tn.NAME_TRANSACTION = '" + valor + "'";
                    } else {

                        if (opbuscar == 4 && valor != null) {
                            int intvalor = Integer.parseInt(valor);

                            codsql = "SELECT inv.ID_INVENTORY,inv.ID_CELLAR AS No,cl.NAME_CELLAR,tn.NAME_TRANSACTION,inv.SET_PRODUCT,inv.ID_PRODUCT AS No,pr.NAME_PRODUCT,inv.AMOUNT,inv.PRICE,inv.TOTAL\n"
                                    + ",inv.DATE_ADMISSION,CONCAT_WS(' ',pe.NAME1,pe.name2,pe.NAME3,pe.LAST_NAME1,pe.LAST_NAME2)Nombre\n"
                                    + "FROM inventory inv JOIN cellar cl ON inv.ID_CELLAR=cl.ID_CELLA\n"
                                    + "JOIN product pr ON inv.ID_PRODUCT=pr.ID_PRODUCT\n"
                                    + "JOIN transactions tn ON inv.ID_TRANSACTION=tn.ID_TRANSACTION\n"
                                    + "JOIN people pe ON inv.ID_PERSON=pe.ID_PERSON where inv.SET_PRODUCT = " + intvalor + "";
                        } else {
                            if (opbuscar == 5 && valor != null) {

                                codsql = "SELECT inv.ID_INVENTORY,inv.ID_CELLAR AS No,cl.NAME_CELLAR,tn.NAME_TRANSACTION,inv.SET_PRODUCT,inv.ID_PRODUCT AS No,pr.NAME_PRODUCT,inv.AMOUNT,inv.PRICE,inv.TOTAL\n"
                                        + ",inv.DATE_ADMISSION,CONCAT_WS(' ',pe.NAME1,pe.name2,pe.NAME3,pe.LAST_NAME1,pe.LAST_NAME2)Nombre\n"
                                        + "FROM inventory inv JOIN cellar cl ON inv.ID_CELLAR=cl.ID_CELLA\n"
                                        + "JOIN product pr ON inv.ID_PRODUCT=pr.ID_PRODUCT\n"
                                        + "JOIN transactions tn ON inv.ID_TRANSACTION=tn.ID_TRANSACTION\n"
                                        + "JOIN people pe ON inv.ID_PERSON=pe.ID_PERSON where pr.NAME_PRODUCT = '" + valor + "'";
                            } else {
                                if (opbuscar == 6 && valor != null) {

                                    codsql = "SELECT inv.ID_INVENTORY,inv.ID_CELLAR AS No,cl.NAME_CELLAR,tn.NAME_TRANSACTION,inv.SET_PRODUCT,inv.ID_PRODUCT AS No,pr.NAME_PRODUCT,inv.AMOUNT,inv.PRICE,inv.TOTAL\n"
                                            + ",inv.DATE_ADMISSION,CONCAT_WS(' ',pe.NAME1,pe.name2,pe.NAME3,pe.LAST_NAME1,pe.LAST_NAME2)Nombre\n"
                                            + "FROM inventory inv JOIN cellar cl ON inv.ID_CELLAR=cl.ID_CELLA\n"
                                            + "JOIN product pr ON inv.ID_PRODUCT=pr.ID_PRODUCT\n"
                                            + "JOIN transactions tn ON inv.ID_TRANSACTION=tn.ID_TRANSACTION\n"
                                            + "JOIN people pe ON inv.ID_PERSON=pe.ID_PERSON where inv.DATE_ADMISSION = '" + valor + "'";
                                } else {
                                    if (opbuscar == 7 && valor != null) {

                                        codsql = "SELECT inv.ID_INVENTORY,inv.ID_CELLAR AS No,cl.NAME_CELLAR,tn.NAME_TRANSACTION,inv.SET_PRODUCT,inv.ID_PRODUCT AS No,pr.NAME_PRODUCT,inv.AMOUNT,inv.PRICE,inv.TOTAL\n"
                                                + ",inv.DATE_ADMISSION,CONCAT_WS(' ',pe.NAME1,pe.name2,pe.NAME3,pe.LAST_NAME1,pe.LAST_NAME2)Nombre\n"
                                                + "FROM inventory inv JOIN cellar cl ON inv.ID_CELLAR=cl.ID_CELLA\n"
                                                + "JOIN product pr ON inv.ID_PRODUCT=pr.ID_PRODUCT\n"
                                                + "JOIN transactions tn ON inv.ID_TRANSACTION=tn.ID_TRANSACTION\n"
                                                + "JOIN people pe ON inv.ID_PERSON=pe.ID_PERSON where pe.NAME1 = '" + valor + "'";
                                    } else {
                                        if (opbuscar == 8 && valor != null) {

                                            codsql = "SELECT inv.ID_INVENTORY,inv.ID_CELLAR AS No,cl.NAME_CELLAR,tn.NAME_TRANSACTION,inv.SET_PRODUCT,inv.ID_PRODUCT AS No,pr.NAME_PRODUCT,inv.AMOUNT,inv.PRICE,inv.TOTAL\n"
                                                    + ",inv.DATE_ADMISSION,CONCAT_WS(' ',pe.NAME1,pe.name2,pe.NAME3,pe.LAST_NAME1,pe.LAST_NAME2)Nombre\n"
                                                    + "FROM inventory inv JOIN cellar cl ON inv.ID_CELLAR=cl.ID_CELLA\n"
                                                    + "JOIN product pr ON inv.ID_PRODUCT=pr.ID_PRODUCT\n"
                                                    + "JOIN transactions tn ON inv.ID_TRANSACTION=tn.ID_TRANSACTION\n"
                                                    + "JOIN people pe ON inv.ID_PERSON=pe.ID_PERSON where pe.NAME1 = '" + valor + "'";
                                        } else {

                                            codsql = "SELECT inv.ID_INVENTORY,inv.ID_CELLAR AS No,cl.NAME_CELLAR,tn.NAME_TRANSACTION,inv.SET_PRODUCT,inv.ID_PRODUCT AS No,pr.NAME_PRODUCT,inv.AMOUNT,inv.PRICE,inv.TOTAL\n"
                                                    + ",inv.DATE_ADMISSION,CONCAT_WS(' ',pe.NAME1,pe.name2,pe.NAME3,pe.LAST_NAME1,pe.LAST_NAME2)Nombre\n"
                                                    + "FROM inventory inv JOIN cellar cl ON inv.ID_CELLAR=cl.ID_CELLA\n"
                                                    + "JOIN product pr ON inv.ID_PRODUCT=pr.ID_PRODUCT\n"
                                                    + "JOIN transactions tn ON inv.ID_TRANSACTION=tn.ID_TRANSACTION\n"
                                                    + "JOIN people pe ON inv.ID_PERSON=pe.ID_PERSON";
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        String[] datos = new String[12];

        try {

            Statement leer = conexcion.createStatement();
            ResultSet rs = leer.executeQuery(codsql);

            while (rs.next()) {

                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
                System.out.println(rs.getString(4));
                System.out.println(rs.getString(5));
                System.out.println(rs.getString(6));
                System.out.println(rs.getString(7));
                System.out.println(rs.getString(8));
                System.out.println(rs.getString(9));
                System.out.println(rs.getString(10));
                System.out.println(rs.getString(11));
                System.out.println(rs.getString(12));
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
                datos[11] = rs.getString(12);
                model.addRow(datos);

            }
            tlCellar.setModel(model);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }

    }

    /*
    public void tablebox(){
        initComponents();
       String[] datt = {"uno","dos"};
        JComboBox jcb = new JComboBox(datt);
        TableColumn tc = tlCellar.getColumnModel().getColumn(4);
        TableCellEditor tce = new DefaultCellEditor(jcb);
        tc.setCellEditor(tce);
    }*/
    public void actualizarDatos() {
        int fila = tlCellar.getSelectedRow();
        System.out.println(fila);
        int idInventori = Integer.parseInt(this.tlCellar.getValueAt(fila, 0).toString());
        String cantidad = tlCellar.getValueAt(fila, 5).toString();
        String monto = tlCellar.getValueAt(fila, 6).toString();

        System.out.println("La cantidad es: " + cantidad);
        System.out.println("El precio es : " + monto);

        Double ACantidad = Double.parseDouble(cantidad);
        Double APrcio = Double.parseDouble(monto);
        Double Atotal;

        Atotal = ACantidad * APrcio;

        System.out.println("La cantidad es: " + ACantidad);
        System.out.println("El precio es : " + APrcio);
        System.out.println("El Total es : " + Atotal);

        try {
            PreparedStatement actu = conexcion.prepareStatement("UPDATE inventory SET AMOUNT =" + cantidad + ", PRICE=" + monto + ", TOTAL =" + Atotal + " WHERE ID_INVENTORY = " + idInventori + "");
            actu.executeUpdate();
            showTableCellar(0, null);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "error" + e.toString());
        }

    }

    public void SalidasBodega() {
        int fila = tlCellar.getSelectedRow();
        System.out.println("fila " + fila);
        int idInventori = Integer.parseInt(this.tlCellar.getValueAt(fila, 0).toString());
        System.out.println("Idnu " + idInventori);
        String NoProdu = tlCellar.getValueAt(fila, 4).toString();
        String idProddu = tlCellar.getValueAt(fila, 5).toString();
        String cantidad = tlCellar.getValueAt(fila, 7).toString();
        String monto = tlCellar.getValueAt(fila, 8).toString();

        System.out.println("el codigo es " + idProddu);
        txtNoSalidaPro.setText(idProddu);

        String NoSPro = txtAmount.getText();
        String CantSPro = txtAmount.getText();

        String cellar1 = BoxSalidaCaller.getSelectedItem().toString();
        String[] Raycellar1 = cellar1.split("-");

        System.out.println("Numero Bodega salida: " + Raycellar1[0]);

        System.out.println("La cantidad es: " + cantidad);
        System.out.println("El precio es : " + monto);

        Double ANCanSali = Double.parseDouble(CantSPro);
        Double ANCantidad = Double.parseDouble(CantSPro);
        Double ACantidad = Double.parseDouble(cantidad);
        Double APrcio = Double.parseDouble(monto);
        Double Atotal;
        Double ANtotal;
        Double AResCanti;

        AResCanti = ACantidad - ANCanSali;
        Atotal = ACantidad * APrcio;
        ANtotal = ANCantidad * APrcio;

        System.out.println("La cantidad - es: " + AResCanti);
        System.out.println("La cantidad es: " + ACantidad);
        System.out.println("El precio es : " + APrcio);
        System.out.println("El Total es : " + Atotal);

        String Afecha = ((JTextField) jDateCellarSalida.getDateEditor().getUiComponent()).getText();

        String empleado = boxEmployer.getSelectedItem().toString();
        String[] Rayempleado = empleado.split("-");
        System.out.println("Numero empleado: " + Rayempleado[0]);

        if (AResCanti > -0.00000001) {

            try {
                PreparedStatement actu = conexcion.prepareStatement("UPDATE inventory SET AMOUNT =" + ACantidad + ", PRICE=" + APrcio + ", TOTAL =" + Atotal + " WHERE ID_INVENTORY = " + idInventori + "");
                actu.executeUpdate();
                showTableCellar(0, null);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error" + e.toString());
                System.out.println(e);
                e.printStackTrace();
                System.out.println(e.getMessage());
            }
            try {
                PreparedStatement actu = conexcion.prepareStatement("INSERT INTO inventory(id_cellar,id_transaction,set_product,id_product,amount,price,\n"
                        + "total,date_admission,id_person)\n"
                        + "VALUES(" + Raycellar1[0] + ",2," + NoProdu + "," + idProddu + "," + ANCantidad + "," + APrcio + "," + ANtotal + ",'" + Date.valueOf(Afecha) + "'," + Rayempleado[0] + ")");
                actu.executeUpdate();
                showTableCellar(0, null);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "error" + e.toString());
                System.out.println(e);
                e.printStackTrace();
                System.out.println(e.getMessage());
            }

        } else {
            JOptionPane.showMessageDialog(null, "error en la sentencia > " );
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menuBar1 = new java.awt.MenuBar();
        menu1 = new java.awt.Menu();
        menu2 = new java.awt.Menu();
        jCalendar1 = new com.toedter.calendar.JCalendar();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jCalendar2 = new com.toedter.calendar.JCalendar();
        jCalendar3 = new com.toedter.calendar.JCalendar();
        jPopupMenuInventori = new javax.swing.JPopupMenu();
        jMenuActualizar = new javax.swing.JMenuItem();
        jMenuBuscar = new javax.swing.JMenuItem();
        jTabbedCellar = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tlCellar = new javax.swing.JTable();
        boxBuscar = new javax.swing.JComboBox<>();
        txtBuscar = new javax.swing.JTextField();
        jSeparator4 = new javax.swing.JSeparator();
        btBuscar = new javax.swing.JButton();
        jSeparator5 = new javax.swing.JSeparator();
        btActualizar = new javax.swing.JButton();
        boxEmployerSAlida = new javax.swing.JComboBox<>();
        jDateCellarSalida = new com.toedter.calendar.JDateChooser();
        BoxSalidaCaller = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtCantidaSalida = new javax.swing.JTextField();
        txtNoSalidaPro = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        BoxCellar1 = new javax.swing.JComboBox<>();
        boxProduct = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtAmount = new javax.swing.JTextField();
        txtPrice = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        boxTransactions = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        boxEmployer = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        btEnBodega = new javax.swing.JButton();
        jDateCellar = new com.toedter.calendar.JDateChooser();

        menu1.setLabel("File");
        menuBar1.add(menu1);

        menu2.setLabel("Edit");
        menuBar1.add(menu2);

        jMenuActualizar.setText("Actualizar");
        jMenuActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuActualizarActionPerformed(evt);
            }
        });
        jPopupMenuInventori.add(jMenuActualizar);

        jMenuBuscar.setText("Buscar Actualisar");
        jMenuBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuBuscarActionPerformed(evt);
            }
        });
        jPopupMenuInventori.add(jMenuBuscar);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Bodegas");

        tlCellar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tlCellar.setComponentPopupMenu(jPopupMenuInventori);
        jScrollPane2.setViewportView(tlCellar);

        boxBuscar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Mostrar todos", "Id o No.", "Bodega", "Transacción", "No in Producto", "Producto", "Fecha", "Nombre", "Apellido" }));
        boxBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxBuscarActionPerformed(evt);
            }
        });

        btBuscar.setText("Buscar");
        btBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBuscarActionPerformed(evt);
            }
        });

        btActualizar.setText("Actualizar");
        btActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btActualizarActionPerformed(evt);
            }
        });

        boxEmployerSAlida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                boxEmployerSAlidaActionPerformed(evt);
            }
        });

        jDateCellarSalida.setDateFormatString("yyyy-MM-dd");

        jLabel1.setText("A Bodega");

        jLabel3.setText("No. Producto");

        jLabel10.setText("Cantidad de Producto");

        jLabel11.setText("que desea Trasferi");

        jLabel12.setText("Empleado");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator5)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addGap(7, 7, 7))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(boxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btBuscar)
                        .addGap(36, 36, 36)
                        .addComponent(btActualizar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNoSalidaPro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtCantidaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BoxSalidaCaller, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jDateCellarSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(boxEmployerSAlida, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addGap(136, 136, 136))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(boxBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBuscar)
                    .addComponent(btActualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(boxEmployerSAlida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12))
                            .addComponent(jDateCellarSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BoxSalidaCaller, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(txtCantidaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNoSalidaPro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 1090, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 568, Short.MAX_VALUE)
        );

        jTabbedCellar.addTab(" Inventario", jPanel1);

        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setText("Bodega");

        BoxCellar1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        BoxCellar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BoxCellar1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Producto");

        jLabel5.setText("Cantidad");

        jLabel6.setText("Empleado");

        txtAmount.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAmountActionPerformed(evt);
            }
        });

        jLabel7.setText("Fecha");

        jLabel8.setText("Tipo de transacción");

        boxTransactions.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Precio");

        boxEmployer.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btEnBodega.setText("Guardar");
        btEnBodega.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnBodegaActionPerformed(evt);
            }
        });

        jDateCellar.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel9))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(BoxCellar1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel8)
                                        .addGap(18, 18, 18)
                                        .addComponent(boxTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(85, 85, 85)
                                        .addComponent(jDateCellar, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(boxProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(26, 26, 26)
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jLabel6)
                        .addGap(18, 18, 18)
                        .addComponent(boxEmployer, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44)
                        .addComponent(btEnBodega)))
                .addContainerGap(597, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BoxCellar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(boxTransactions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(boxProduct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtAmount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9)
                        .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addComponent(jDateCellar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(boxEmployer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btEnBodega))
                .addGap(32, 32, 32)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(257, Short.MAX_VALUE))
        );

        jTabbedCellar.addTab("Ingreso", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedCellar, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedCellar, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuActualizarActionPerformed
        // TODO add your handling code here:
        actualizarDatos();

    }//GEN-LAST:event_jMenuActualizarActionPerformed

    private void jMenuBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuBuscarActionPerformed
        SalidasBodega();
    }//GEN-LAST:event_jMenuBuscarActionPerformed

    private void btEnBodegaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnBodegaActionPerformed
        // TODO add your handling code here:

        Conta idid = new Conta();

        int idss = idid.id_incre();
        System.out.println("Numero id: " + idss);

        String cellar1 = BoxCellar1.getSelectedItem().toString();
        String productos = boxProduct.getSelectedItem().toString();
        String Cantidad = txtAmount.getText();
        String Prcio = txtPrice.getText();
        String Tfecha = ((JTextField) jDateCellar.getDateEditor().getUiComponent()).getText();
        String tipoTransaccion = boxTransactions.getSelectedItem().toString();
        String empleado = boxEmployer.getSelectedItem().toString();

        String[] Raycellar1 = cellar1.split("-");
        System.out.println("Numero cellar1: " + Raycellar1[0]);

        String[] Rayproductos = productos.split("-");
        System.out.println("Numero productos: " + Rayproductos[0]);

        System.out.println("fecha   " + Tfecha);

        Double DCantidad = Double.parseDouble(Cantidad);
        Double DPrcio = Double.parseDouble(Prcio);
        Double Dtotal;

        Dtotal = DCantidad * DPrcio;

        System.out.println("La cantidad es: " + DCantidad);
        System.out.println("El precio es : " + DPrcio);
        System.out.println("El Total es : " + Dtotal);

        String[] RaytipoTransaccion = tipoTransaccion.split("-");
        System.out.println("Numero tipoTransaccion: " + RaytipoTransaccion[0]);

        String[] Rayempleado = empleado.split("-");
        System.out.println("Numero empleado: " + Rayempleado[0]);

        /*

        Date date = super.getDate();
        if (date != null)*/
        if (Cantidad.isEmpty() || Prcio.isEmpty() || Tfecha.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Campos vacios porfabor llenar registros.");
        } else {
            /*
            if (jDateCellar.isEnabled() ){
                JOptionPane.showMessageDialog(this, "Campos fecha vacios porfabor llenar registros.");
            } else {*/
                try ( Connection conexion = con.get_connection()) {
                    try {
                        PreparedStatement ps = null;
                        String query = ("INSERT INTO inventory(id_cellar,id_transaction,set_product,id_product,amount,price,\n"
                            + "total,date_admission,id_person)\n"
                            + "VALUES(" + Raycellar1[0] + "," + RaytipoTransaccion[0] + "," + idss + "," + Rayproductos[0] + "," + DCantidad + "," + DPrcio + "," + Dtotal + ",'" + Date.valueOf(Tfecha) + "'," + Rayempleado[0] + ")");
                        ps = conexion.prepareStatement(query);
                        ps.executeUpdate();

                        showTableCellar(0, null);

                        JOptionPane.showMessageDialog(this, "Producto Guardado Exitoso.");
                    } catch (Exception e) {
                        System.err.print(e.toString());
                        JOptionPane.showMessageDialog(this, "Ocurrio un error al guaredar.");
                    }
                } catch (SQLException e) {
                    System.err.print(e.toString());
                    JOptionPane.showMessageDialog(this, "Ocurrio un error inesperado.\nFavor comunicarse con el administrador.");
                }

            }

    }//GEN-LAST:event_btEnBodegaActionPerformed

    private void txtAmountActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAmountActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAmountActionPerformed

    private void BoxCellar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BoxCellar1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_BoxCellar1ActionPerformed

    private void boxEmployerSAlidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxEmployerSAlidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxEmployerSAlidaActionPerformed

    private void btActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btActualizarActionPerformed
        // TODO add your handling code here:
        actualizarDatos();
    }//GEN-LAST:event_btActualizarActionPerformed

    private void btBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBuscarActionPerformed
        // TODO add your handling code here:
        int opcion = boxBuscar.getSelectedIndex();
        String valorbus = txtBuscar.getText();
        showTableCellar(opcion, valorbus);
    }//GEN-LAST:event_btBuscarActionPerformed

    private void boxBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_boxBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_boxBuscarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> BoxCellar1;
    private javax.swing.JComboBox<String> BoxSalidaCaller;
    private javax.swing.JComboBox<String> boxBuscar;
    private javax.swing.JComboBox<String> boxEmployer;
    private javax.swing.JComboBox<String> boxEmployerSAlida;
    private javax.swing.JComboBox<String> boxProduct;
    private javax.swing.JComboBox<String> boxTransactions;
    private javax.swing.JButton btActualizar;
    private javax.swing.JButton btBuscar;
    private javax.swing.JButton btEnBodega;
    private com.toedter.calendar.JCalendar jCalendar1;
    private com.toedter.calendar.JCalendar jCalendar2;
    private com.toedter.calendar.JCalendar jCalendar3;
    private com.toedter.calendar.JDateChooser jDateCellar;
    private com.toedter.calendar.JDateChooser jDateCellarSalida;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
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
    private javax.swing.JMenuItem jMenuActualizar;
    private javax.swing.JMenuItem jMenuBuscar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPopupMenu jPopupMenuInventori;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JTabbedPane jTabbedCellar;
    private java.awt.Menu menu1;
    private java.awt.Menu menu2;
    private java.awt.MenuBar menuBar1;
    private javax.swing.JTable tlCellar;
    private javax.swing.JTextField txtAmount;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtCantidaSalida;
    private javax.swing.JTextField txtNoSalidaPro;
    private javax.swing.JTextField txtPrice;
    // End of variables declaration//GEN-END:variables

    private void cargarCellar(JComboBox c) {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        c.setModel(combo);
        CTCellar ctc = new CTCellar();

        try {
            Statement st = conexcion.createStatement();
            ResultSet rr = st.executeQuery("SELECT * FROM cellar");

            while (rr.next()) {
                cellar con = new cellar();
                System.out.println(rr.getString(1));
                con.setId_cella(rr.getInt(1));
                con.setName_callar(rr.getString(2));
                ctc.AgregarCellar(con);
                combo.addElement(con.getId_cella() + " - " + con.getName_callar());
                System.out.println("Exito....");
            }

        } catch (Exception e) {
            System.out.println("Error , no se puede mostrar combo" + e);
        }
    }

    private void cargarProduct(JComboBox p) {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        p.setModel(combo);
        CTProduct ctc = new CTProduct();

        try {
            Statement st = conexcion.createStatement();
            ResultSet rr = st.executeQuery("SELECT * FROM product");

            while (rr.next()) {
                product con = new product();
                System.out.println(rr.getString(1));
                con.setId_product(rr.getInt(1));
                con.setName_product(rr.getString(2));
                ctc.AgregarProduct(con);
                combo.addElement(con.getId_product() + " - " + con.getName_product());
                System.out.println("Exito....");
            }

        } catch (Exception e) {
            System.out.println("Error , no se puede mostrar combo" + e);
        }
    }

    private void cargarTransactions(JComboBox t) {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        t.setModel(combo);
        CTTransaction ctc = new CTTransaction();

        try {
            Statement st = conexcion.createStatement();
            ResultSet rr = st.executeQuery("SELECT * FROM transactions");

            while (rr.next()) {
                transactions con = new transactions();
                System.out.println(rr.getString(1));
                con.setId_transaction(rr.getInt(1));
                con.setName_transaction(rr.getString(2));
                ctc.AgregarTransactions(con);
                combo.addElement(con.getId_transaction() + " - " + con.getName_transaction());
                System.out.println("Exito....");
            }

        } catch (Exception e) {
            System.out.println("Error , no se puede mostrar combo" + e);
        }
    }

    private void cargarPerson(JComboBox pp) {
        DefaultComboBoxModel combo = new DefaultComboBoxModel();
        pp.setModel(combo);
        CTPerson ctc = new CTPerson();

        try {
            Statement st = conexcion.createStatement();
            ResultSet rr = st.executeQuery("SELECT * FROM people");

            while (rr.next()) {
                people con = new people();
                System.out.println(rr.getString(1));
                con.setId_person(rr.getInt(1));
                con.setName1(rr.getString(2));
                con.setName2(rr.getString(3));
                con.setLast_name1(rr.getString(5));
                con.setLast_name2(rr.getString(6));
                ctc.AgregarPeople(con);
                combo.addElement(con.getId_person() + " - " + con.getName1() + " " + con.getName2() + " " + con.getLast_name1() + " " + con.getLast_name2());
                System.out.println("Exito....");
            }
        } catch (Exception e) {
            System.out.println("Error , no se puede mostrar combo" + e);
        }
    }
}
