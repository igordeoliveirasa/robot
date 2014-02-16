/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.igordeoliveira.robot;

import com.igordeoliveira.dev.zombie.ArduinoCommunication;
import com.igordeoliveira.dev.zombie.ArduinoConstants;
import com.igordeoliveira.dev.zombie.ArduinoZombie;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author igor
 */
public class Robot {

    /**
     * @param args the command line arguments
     */
    private ArduinoZombie arduino;
    private int l1 = 4;
    private int l2 = 5;
    private int r1 = 6;
    private int r2 = 7;
    
    public Robot() {
        arduino = ArduinoZombie.getInstance("192.168.1.200", 41085, "123");
        
        arduino.pinMode(r1, ArduinoConstants.OUTPUT).
                pinMode(r2, ArduinoConstants.OUTPUT).
                pinMode(l1, ArduinoConstants.OUTPUT).
                pinMode(l2, ArduinoConstants.OUTPUT).
                flush();
    }
    public boolean forward() {
        return arduino.digitalWrite(r1, ArduinoConstants.LOW).
                digitalWrite(r2, ArduinoConstants.HIGH).
                digitalWrite(l1, ArduinoConstants.LOW).
                digitalWrite(l2, ArduinoConstants.HIGH).                
                flush();
    }
    public boolean backward() {
        return arduino.digitalWrite(r1, ArduinoConstants.HIGH).
                digitalWrite(r2, ArduinoConstants.LOW).
                digitalWrite(l1, ArduinoConstants.HIGH).
                digitalWrite(l2, ArduinoConstants.LOW).                
                flush();
    }
    
    public boolean left_axis() {
        //esquerda
        return arduino.digitalWrite(r1, ArduinoConstants.LOW).
                digitalWrite(r2, ArduinoConstants.HIGH).
                digitalWrite(l1, ArduinoConstants.HIGH).
                digitalWrite(l2, ArduinoConstants.LOW).                
                flush();
        
    }
    
    public boolean left() {
        //esquerda
        return arduino.digitalWrite(r1, ArduinoConstants.LOW).
                digitalWrite(r2, ArduinoConstants.HIGH).
                digitalWrite(l1, ArduinoConstants.LOW).
                digitalWrite(l2, ArduinoConstants.LOW).                
                flush();
        
    }
    
    public boolean right_axis() {     
        return arduino.digitalWrite(r1, ArduinoConstants.HIGH).
                digitalWrite(r2, ArduinoConstants.LOW).
                digitalWrite(l1, ArduinoConstants.LOW).
                digitalWrite(l2, ArduinoConstants.HIGH).                
                flush();
    }
    
    public boolean right() {     
        return arduino.digitalWrite(r1, ArduinoConstants.LOW).
                digitalWrite(r2, ArduinoConstants.LOW).
                digitalWrite(l1, ArduinoConstants.LOW).
                digitalWrite(l2, ArduinoConstants.HIGH).                
                flush();
    }
    
    public boolean stop() {        
        return arduino.digitalWrite(l1, ArduinoConstants.LOW).
                digitalWrite(l2, ArduinoConstants.LOW).
                digitalWrite(r1, ArduinoConstants.LOW).
                digitalWrite(r2, ArduinoConstants.LOW).
                flush();
    } 
      /*      
    public static void main(String[] args) throws InterruptedException {
        Robot carrinho = new Robot();        
        int i = 0;
        while (true) {
            carrinho.forward();
            carrinho.stop();
            carrinho.backward();
            carrinho.stop();
            carrinho.left();
            carrinho.stop();
            carrinho.right();
            carrinho.stop();
            System.out.println("" + ++i);
        }
    }*/
}
