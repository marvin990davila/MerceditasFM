/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import Modelo.product;
import java.sql.Connection;
import java.util.ArrayList;



public class CTProduct {
    
    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();
    
    ArrayList<product>listaProduct;
    
    public CTProduct(){
        listaProduct = new ArrayList();
    }
    
    public void AgregarProduct(product p){
        listaProduct.add(p);
    }
    
}
