/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mdavi
 */
public class product {
    
    int Id_product;
    String Name_product;
    int Id_measure;

    public product(){
        
    }

    public product(String Name_product, int Id_measure) {
        this.Name_product = Name_product;
        this.Id_measure = Id_measure;
    }
    
    public int getId_product() {
        return Id_product;
    }

    public void setId_product(int Id_product) {
        this.Id_product = Id_product;
    }

    public String getName_product() {
        return Name_product;
    }

    public void setName_product(String Name_product) {
        this.Name_product = Name_product;
    }

    public int getId_measure() {
        return Id_measure;
    }

    public void setId_measure(int Id_measure) {
        this.Id_measure = Id_measure;
    }
    
    
    
    
}
