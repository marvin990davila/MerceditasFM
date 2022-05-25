/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mdavi
 */
public class activities {
    
    int Id_activitis;
    String Name_activities;
    double Price;

    public activities(){
        
    }

    public activities(String Name_activities, double Price) {
        this.Name_activities = Name_activities;
        this.Price = Price;
    }
    
    
    
    
    public int getId_activitis() {
        return Id_activitis;
    }

    public void setId_activitis(int Id_activitis) {
        this.Id_activitis = Id_activitis;
    }

    public String getName_activities() {
        return Name_activities;
    }

    public void setName_activities(String Name_activities) {
        this.Name_activities = Name_activities;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }
    
    
    
}
