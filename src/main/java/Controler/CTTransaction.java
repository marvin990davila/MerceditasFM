/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import Modelo.transactions;
import java.sql.Connection;
import java.util.ArrayList;

/**
 *
 * @author mdavi
 */
public class CTTransaction {
    
    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();
    
    ArrayList<transactions>listaTransactions;
    
    public CTTransaction(){
        listaTransactions = new ArrayList();
    }
    
    public void AgregarTransactions(transactions t){
        listaTransactions.add(t);
    }
}
