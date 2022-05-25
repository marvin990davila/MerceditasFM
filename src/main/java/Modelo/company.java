/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mdavi
 */
public class company {
    
    int Id_company;
    String Name_company;
    String Nit_company;
    
    
    public company(){
        
        
    }

    public company(String Name_company, String Nit_company) {
        this.Name_company = Name_company;
        this.Nit_company = Nit_company;
    }

    public int getId_company() {
        return Id_company;
    }

    public void setId_company(int Id_company) {
        this.Id_company = Id_company;
    }

    public String getName_company() {
        return Name_company;
    }

    public void setName_company(String Name_company) {
        this.Name_company = Name_company;
    }

    public String getNit_company() {
        return Nit_company;
    }

    public void setNit_company(String Nit_company) {
        this.Nit_company = Nit_company;
    }
    
    
    
    
    
}
