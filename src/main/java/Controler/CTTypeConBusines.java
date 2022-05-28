/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import Modelo.tipe_contact_company;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author mdavi
 */
public class CTTypeConBusines {
    
    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();
    
    ArrayList<tipe_contact_company>listaCellar;
    
    public CTTypeConBusines(){
        listaCellar = new ArrayList();
    }
    
    public void Agregartype_contact(tipe_contact_company c){
        listaCellar.add(c);
    }
    
}
