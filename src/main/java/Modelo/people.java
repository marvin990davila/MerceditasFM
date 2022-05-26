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
public class people {
    
    int Id_person;
    int Dpi;
    String Name1;
    String Name2;
    String Name3;
    String Last_name1;
    String Last_name2;
    Date Birthday;
    String Nit_person;

    
    public people(){
        
    }

    public people(int Dpi, String Name1, String Name2, String Name3, String Last_name1, String Last_name2, Date Birthday, String Nit_person) {
        this.Dpi = Dpi;
        this.Name1 = Name1;
        this.Name2 = Name2;
        this.Name3 = Name3;
        this.Last_name1 = Last_name1;
        this.Last_name2 = Last_name2;
        this.Birthday = Birthday;
        this.Nit_person = Nit_person;
    }

    public int getId_person() {
        return Id_person;
    }

    public void setId_person(int Id_person) {
        this.Id_person = Id_person;
    }

    public int getDpi() {
        return Dpi;
    }

    public void setDpi(int Dpi) {
        this.Dpi = Dpi;
    }

    public String getName1() {
        return Name1;
    }

    public void setName1(String Name1) {
        this.Name1 = Name1;
    }

    public String getName2() {
        return Name2;
    }

    public void setName2(String Name2) {
        this.Name2 = Name2;
    }

    public String getName3() {
        return Name3;
    }

    public void setName3(String Name3) {
        this.Name3 = Name3;
    }

    public String getLast_name1() {
        return Last_name1;
    }

    public void setLast_name1(String Last_name1) {
        this.Last_name1 = Last_name1;
    }

    public String getLast_name2() {
        return Last_name2;
    }

    public void setLast_name2(String Last_name2) {
        this.Last_name2 = Last_name2;
    }

    public Date getBirthday() {
        return Birthday;
    }

    public void setBirthday(Date Birthday) {
        this.Birthday = Birthday;
    }

    public String getNit_person() {
        return Nit_person;
    }

    public void setNit_person(String Nit_person) {
        this.Nit_person = Nit_person;
    }

    
    
    
    
}
