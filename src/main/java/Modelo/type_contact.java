/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mdavi
 */
public class type_contact {
    
    int Id_type_contact;
    String Name_type_contact;
    
    public type_contact(){
        
    }

    public type_contact(String Name_type_contact) {
        this.Name_type_contact = Name_type_contact;
    }

    public int getId_type_contact() {
        return Id_type_contact;
    }

    public void setId_type_contact(int Id_type_contact) {
        this.Id_type_contact = Id_type_contact;
    }

    public String getName_type_contact() {
        return Name_type_contact;
    }

    public void setName_type_contact(String Name_type_contact) {
        this.Name_type_contact = Name_type_contact;
    }
    
    
}
