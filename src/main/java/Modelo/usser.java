/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mdavi
 */
public class usser {
    
    int Id_user;
    String User_name;
    String Passwordss;
    String permissions;
    
    public usser(){
        
    }

    public usser(String User_name, String Passwordss, String permissions) {
        this.User_name = User_name;
        this.Passwordss = Passwordss;
        this.permissions = permissions;
    }

    public int getId_user() {
        return Id_user;
    }

    public void setId_user(int Id_user) {
        this.Id_user = Id_user;
    }

    public String getUser_name() {
        return User_name;
    }

    public void setUser_name(String User_name) {
        this.User_name = User_name;
    }

    public String getPasswordss() {
        return Passwordss;
    }

    public void setPasswordss(String Passwordss) {
        this.Passwordss = Passwordss;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    
    
    
}
