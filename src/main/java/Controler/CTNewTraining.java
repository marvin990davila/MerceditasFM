/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import Modelo.name_training;
import Modelo.people;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author mdavi
 */
public class CTNewTraining {
    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();
    
    ArrayList<name_training>listaTraining;
    
    public CTNewTraining(){
        listaTraining = new ArrayList();
    }
    
    public void AgregarTraining(name_training p){
        listaTraining.add(p);
    }
    
}
