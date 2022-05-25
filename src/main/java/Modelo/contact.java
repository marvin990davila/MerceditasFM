/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mdavi
 */
public class contact {
    int Id_contact;
    int Id_type_contact;
    String Text_contact;
    int Id_person;
    
    public contact(){
        
    }

    public contact(int Id_type_contact, String Text_contact, int Id_person) {
        this.Id_type_contact = Id_type_contact;
        this.Text_contact = Text_contact;
        this.Id_person = Id_person;
    }

    public int getId_contact() {
        return Id_contact;
    }

    public void setId_contact(int Id_contact) {
        this.Id_contact = Id_contact;
    }

    public int getId_type_contact() {
        return Id_type_contact;
    }

    public void setId_type_contact(int Id_type_contact) {
        this.Id_type_contact = Id_type_contact;
    }

    public String getText_contact() {
        return Text_contact;
    }

    public void setText_contact(String Text_contact) {
        this.Text_contact = Text_contact;
    }

    public int getId_person() {
        return Id_person;
    }

    public void setId_person(int Id_person) {
        this.Id_person = Id_person;
    }
    
    
    
}
