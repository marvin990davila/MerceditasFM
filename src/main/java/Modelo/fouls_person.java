/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mdavi
 */
public class fouls_person {
    
    int Id_fouls_person;
    int Id_person;
    int Id_fouls;
    String Text_fouls;

    
    public fouls_person(){
        
    }

    public fouls_person(int Id_person, int Id_fouls, String Text_fouls) {
        this.Id_person = Id_person;
        this.Id_fouls = Id_fouls;
        this.Text_fouls = Text_fouls;
    }
    
    public int getId_fouls_person() {
        return Id_fouls_person;
    }

    public void setId_fouls_person(int Id_fouls_person) {
        this.Id_fouls_person = Id_fouls_person;
    }

    public int getId_person() {
        return Id_person;
    }

    public void setId_person(int Id_person) {
        this.Id_person = Id_person;
    }

    public int getId_fouls() {
        return Id_fouls;
    }

    public void setId_fouls(int Id_fouls) {
        this.Id_fouls = Id_fouls;
    }

    public String getText_fouls() {
        return Text_fouls;
    }

    public void setText_fouls(String Text_fouls) {
        this.Text_fouls = Text_fouls;
    }
    
}
