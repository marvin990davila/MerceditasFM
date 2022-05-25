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
public class training {
    
    int Id_training;
    int Id_name_training;
    int Id_person_trainer;
    int Id_person;
    Date Date_training;
    
    
    public training(){
        
    }

    public training(int Id_name_training, int Id_person_trainer, int Id_person, Date Date_training) {
        this.Id_name_training = Id_name_training;
        this.Id_person_trainer = Id_person_trainer;
        this.Id_person = Id_person;
        this.Date_training = Date_training;
    }

    public int getId_training() {
        return Id_training;
    }

    public void setId_training(int Id_training) {
        this.Id_training = Id_training;
    }

    public int getId_name_training() {
        return Id_name_training;
    }

    public void setId_name_training(int Id_name_training) {
        this.Id_name_training = Id_name_training;
    }

    public int getId_person_trainer() {
        return Id_person_trainer;
    }

    public void setId_person_trainer(int Id_person_trainer) {
        this.Id_person_trainer = Id_person_trainer;
    }

    public int getId_person() {
        return Id_person;
    }

    public void setId_person(int Id_person) {
        this.Id_person = Id_person;
    }

    public Date getDate_training() {
        return Date_training;
    }

    public void setDate_training(Date Date_training) {
        this.Date_training = Date_training;
    }
    
    
    
}
