/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author mdavi
 */
public class Conta {
    
    conexcion con = new conexcion();
    Connection conexcion = con.get_connection();
    
    public int id_incre(){
        int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            ps = conexcion.prepareCall("SELECT MAX(ID_INVENTORY)  FROM inventory");
            rs = ps.executeQuery();
            while(rs.next()){
                id= rs.getInt(1)+ 1;
            }
        }catch(Exception e){
            System.err.print(e.getMessage());
        }
        
        finally{
            try{
                ps.close();
                rs.close();
                conexcion.close();
            }catch(Exception e){
            System.err.print(e.getMessage());
        }
        }
         return id;      
    }
    
}
