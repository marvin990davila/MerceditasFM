/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;


import Modelo.type_contact;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author mdavi
 */
public class TCType_contact {
    
    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();
    
    ArrayList<type_contact>listaCellar;
    
    public TCType_contact(){
        listaCellar = new ArrayList();
    }
    
    public void Agregartype_contact(type_contact c){
        listaCellar.add(c);
    }
    
}
