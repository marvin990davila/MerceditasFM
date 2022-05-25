/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.Date;

/**
 *
 * @author mdavi
 */
public class person_classification {
    
    int Id_person_classification;
    int Id_person;
    int Id_company;
    int Id_class_person;
    int Id_status;
    Date Date_status;
    
    public person_classification(){
        
    }

    public person_classification(int Id_person, int Id_company, int Id_class_person, int Id_status, Date Date_status) {
        this.Id_person = Id_person;
        this.Id_company = Id_company;
        this.Id_class_person = Id_class_person;
        this.Id_status = Id_status;
        this.Date_status = Date_status;
    }

    public int getId_person_classification() {
        return Id_person_classification;
    }

    public void setId_person_classification(int Id_person_classification) {
        this.Id_person_classification = Id_person_classification;
    }

    public int getId_person() {
        return Id_person;
    }

    public void setId_person(int Id_person) {
        this.Id_person = Id_person;
    }

    public int getId_company() {
        return Id_company;
    }

    public void setId_company(int Id_company) {
        this.Id_company = Id_company;
    }

    public int getId_class_person() {
        return Id_class_person;
    }

    public void setId_class_person(int Id_class_person) {
        this.Id_class_person = Id_class_person;
    }

    public int getId_status() {
        return Id_status;
    }

    public void setId_status(int Id_status) {
        this.Id_status = Id_status;
    }

    public Date getDate_status() {
        return Date_status;
    }

    public void setDate_status(Date Date_status) {
        this.Date_status = Date_status;
    }
    
    
    
}
