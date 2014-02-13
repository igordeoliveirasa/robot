/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.igordeoliveira.dev.zombie;

/**
 *
 * @author igor
 */
public class ArduinoZombie implements IArduinoZombie {
    private ArduinoCommunication communication;
    
    public ArduinoZombie(ArduinoCommunication communication) {
        this.communication = communication;
    }
    
    @Override
    public IArduinoZombie pinMode(int pin, int pinType) {
        return this;
    }

    @Override
    public IArduinoZombie digitalWrite(int pin, int digitalMessage) {
        return this;
    }

    @Override
    public IArduinoZombie analogWrite(int pin, int value) {
        return this;
    }

    @Override
    public boolean flush() {
        return true;
    }
    
}
