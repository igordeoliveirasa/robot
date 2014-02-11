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

    public ArduinoZombie(String address, int port, String password) throws Exception {
        if (!address.equals("192.168.1.200") || port != 41085 || !password.equals("123")) {
            throw new Exception("");
        }
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
