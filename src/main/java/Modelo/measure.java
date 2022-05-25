/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mdavi
 */
public class measure {
    
    int Id_measure;
    String Name_measure;
    
    public measure(){
        
    }

    public measure(String Name_measure) {
        this.Name_measure = Name_measure;
    }
    
    public int getId_measure() {
        return Id_measure;
    }

    public void setId_measure(int Id_measure) {
        this.Id_measure = Id_measure;
    }

    public String getName_measure() {
        return Name_measure;
    }

    public void setName_measure(String Name_measure) {
        this.Name_measure = Name_measure;
    }
    
    
    
}
