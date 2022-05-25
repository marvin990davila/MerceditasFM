/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import java.util.ArrayList;
import Modelo.cellar;
import java.sql.Connection;

/*
import Modelo.cellar;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;*/

/**
 *
 * @author mdavi
 */
public class CTCellar {
    
    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();
    
    ArrayList<cellar>listaCellar;
    
    public CTCellar(){
        listaCellar = new ArrayList();
    }
    
    public void AgregarCellar(cellar c){
        listaCellar.add(c);
    }
    

}
