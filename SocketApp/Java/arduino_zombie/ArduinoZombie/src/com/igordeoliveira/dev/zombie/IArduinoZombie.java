/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.igordeoliveira.dev.zombie;

/**
 *
 * @author igor
 */
public interface IArduinoZombie {
    public boolean flush();
    public IArduinoZombie pinMode(int pin, int pinType);
    public IArduinoZombie digitalWrite(int pin, int digitalMessage);
    public IArduinoZombie analogWrite(int pin, int value);
}
