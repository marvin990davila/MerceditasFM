/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mdavi
 */
public class contact_company {
    
    int Id_contact_company;
    int Id_tipe_contact_company;
    String Text_contact_company;
    int Id_company;
    
    public contact_company(){
        
    }

    public contact_company(int Id_tipe_contact_company, String Text_contact_company, int Id_company) {
        this.Id_tipe_contact_company = Id_tipe_contact_company;
        this.Text_contact_company = Text_contact_company;
        this.Id_company = Id_company;
    }

    public int getId_contact_company() {
        return Id_contact_company;
    }

    public void setId_contact_company(int Id_contact_company) {
        this.Id_contact_company = Id_contact_company;
    }

    public int getId_tipe_contact_company() {
        return Id_tipe_contact_company;
    }

    public void setId_tipe_contact_company(int Id_tipe_contact_company) {
        this.Id_tipe_contact_company = Id_tipe_contact_company;
    }

    public String getText_contact_company() {
        return Text_contact_company;
    }

    public void setText_contact_company(String Text_contact_company) {
        this.Text_contact_company = Text_contact_company;
    }

    public int getId_company() {
        return Id_company;
    }

    public void setId_company(int Id_company) {
        this.Id_company = Id_company;
    }
    
    
    
}
