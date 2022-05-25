/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mdavi
 */
public class tipe_contact_company {
    
    int Id_tipe_contact_company;
    String Contact_company;

    public tipe_contact_company(){
        
    }

    public tipe_contact_company(String Contact_company) {
        this.Contact_company = Contact_company;
    }
    
    public int getId_tipe_contact_company() {
        return Id_tipe_contact_company;
    }

    public void setId_tipe_contact_company(int Id_tipe_contact_company) {
        this.Id_tipe_contact_company = Id_tipe_contact_company;
    }

    public String getContact_company() {
        return Contact_company;
    }

    public void setContact_company(String Contact_company) {
        this.Contact_company = Contact_company;
    }
    
    
    
}
