/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import Modelo.measure;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author mdavi
 */
public class CTMeasure {
    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();
    
    ArrayList<measure>listaMeasure;
    
    public CTMeasure(){
        listaMeasure = new ArrayList();
    }
    
    public void AgregarMeasure(measure m){
        listaMeasure.add(m);
    }
    
}
