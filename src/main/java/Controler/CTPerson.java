/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import Modelo.people;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author mdavi
 */
public class CTPerson {
    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();
    
    ArrayList<people>listaPeople;
    
    public CTPerson(){
        listaPeople = new ArrayList();
    }
    
    public void AgregarPeople(people p){
        listaPeople.add(p);
    }
}
