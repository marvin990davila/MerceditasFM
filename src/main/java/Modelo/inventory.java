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
public class inventory {
    
    int Id_inventory;
    int Id_cellar;
    int Id_transaction;
    int Set_product;
    int Id_product;
    double Amount;
    double Price;
    double Total;
    Date Date_admission;
    int id_person;

    public inventory(){
        
    }

    public inventory(int Id_cellar, int Id_transaction, int Set_product, int Id_product, double Amount, double Price, double Total, Date Date_admission, int id_person) {
        this.Id_cellar = Id_cellar;
        this.Id_transaction = Id_transaction;
        this.Set_product = Set_product;
        this.Id_product = Id_product;
        this.Amount = Amount;
        this.Price = Price;
        this.Total = Total;
        this.Date_admission = Date_admission;
        this.id_person = id_person;
    }

    public int getId_inventory() {
        return Id_inventory;
    }

    public void setId_inventory(int Id_inventory) {
        this.Id_inventory = Id_inventory;
    }

    public int getId_cellar() {
        return Id_cellar;
    }

    public void setId_cellar(int Id_cellar) {
        this.Id_cellar = Id_cellar;
    }

    public int getId_transaction() {
        return Id_transaction;
    }

    public void setId_transaction(int Id_transaction) {
        this.Id_transaction = Id_transaction;
    }

    public int getSet_product() {
        return Set_product;
    }

    public void setSet_product(int Set_product) {
        this.Set_product = Set_product;
    }

    public int getId_product() {
        return Id_product;
    }

    public void setId_product(int Id_product) {
        this.Id_product = Id_product;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public Date getDate_admission() {
        return Date_admission;
    }

    public void setDate_admission(Date Date_admission) {
        this.Date_admission = Date_admission;
    }

    public int getId_person() {
        return id_person;
    }

    public void setId_person(int id_person) {
        this.id_person = id_person;
    }

    
 
    
}
