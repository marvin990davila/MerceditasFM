/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author mdavi
 */
public class name_training {
    
    int Id_name_training;
    String Text_training;
    
    
    public name_training(){
        
    }

    public name_training(String Text_training) {
        this.Text_training = Text_training;
    }
    

    public int getId_name_training() {
        return Id_name_training;
    }

    public void setId_name_training(int Id_name_training) {
        this.Id_name_training = Id_name_training;
    }

    public String getText_training() {
        return Text_training;
    }

    public void setText_training(String Text_training) {
        this.Text_training = Text_training;
    }
    
    
}
