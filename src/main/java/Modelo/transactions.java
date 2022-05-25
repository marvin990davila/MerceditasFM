/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mdavi
 */
public class transactions {
    
    int Id_transaction;
    String Name_transaction;
    
    public transactions(){
        
    }

    public transactions(String Name_transaction) {
        this.Name_transaction = Name_transaction;
    }

    public int getId_transaction() {
        return Id_transaction;
    }

    public void setId_transaction(int Id_transaction) {
        this.Id_transaction = Id_transaction;
    }

    public String getName_transaction() {
        return Name_transaction;
    }

    public void setName_transaction(String Name_transaction) {
        this.Name_transaction = Name_transaction;
    }
    
    
}
