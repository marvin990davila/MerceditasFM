/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mdavi
 */
public class status_person {
    
    int Id_status;
    String Name_status_person;
    
    
    public status_person(){
        
    }

    public status_person(String Name_status_person) {
        this.Name_status_person = Name_status_person;
    }

    public int getId_status() {
        return Id_status;
    }

    public void setId_status(int Id_status) {
        this.Id_status = Id_status;
    }

    public String getName_status_person() {
        return Name_status_person;
    }

    public void setName_status_person(String Name_status_person) {
        this.Name_status_person = Name_status_person;
    }
    
    
    
}
