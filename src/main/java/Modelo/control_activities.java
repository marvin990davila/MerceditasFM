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
public class control_activities {
    
    
    int Id_personal_activities;
    int Id_person;
    Date Date_activities;
    int Id_activities;
    
    public control_activities(){
        
    }

    public control_activities(int Id_person, Date Date_activities, int Id_activities) {
        this.Id_person = Id_person;
        this.Date_activities = Date_activities;
        this.Id_activities = Id_activities;
    }

    public int getId_personal_activities() {
        return Id_personal_activities;
    }

    public void setId_personal_activities(int Id_personal_activities) {
        this.Id_personal_activities = Id_personal_activities;
    }

    public int getId_person() {
        return Id_person;
    }

    public void setId_person(int Id_person) {
        this.Id_person = Id_person;
    }

    public Date getDate_activities() {
        return Date_activities;
    }

    public void setDate_activities(Date Date_activities) {
        this.Date_activities = Date_activities;
    }

    public int getId_activities() {
        return Id_activities;
    }

    public void setId_activities(int Id_activities) {
        this.Id_activities = Id_activities;
    }
    
    
}
