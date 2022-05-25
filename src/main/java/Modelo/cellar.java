/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mdavi
 */
public class cellar {
 
    int Id_cella;
    String Name_callar;
    
    
    public cellar(){
        
    }

    public cellar(String Name_callar) {
        this.Name_callar = Name_callar;
    }

    public int getId_cella() {
        return Id_cella;
    }

    public void setId_cella(int Id_cella) {
        this.Id_cella = Id_cella;
    }

    public String getName_callar() {
        return Name_callar;
    }

    public void setName_callar(String Name_callar) {
        this.Name_callar = Name_callar;
    }
    
    
    
    
}
