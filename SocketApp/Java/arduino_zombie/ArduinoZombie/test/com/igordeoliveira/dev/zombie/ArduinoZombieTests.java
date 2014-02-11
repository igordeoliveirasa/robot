/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.igordeoliveira.dev.zombie;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author igor
 */
public class ArduinoZombieTests {
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void testPinModeOutput() {
        int pin = 3;
        IArduinoZombie arduino = new ArduinoZombie();
        boolean result = arduino.pinMode(pin, Arduino.OUTPUT).flush();
        assertTrue(result);
    }
    
    @Test
    public void testDigitalWritePin3() {
        int pin = 3;
        IArduinoZombie arduino = new ArduinoZombie();
        boolean result = arduino.pinMode(pin, Arduino.OUTPUT).digitalWrite(pin, Arduino.HIGH).flush();
        assertTrue(result);
    }
    
    @Test
    public void testAnalogWritePin3() {
        int pin = 3;
        IArduinoZombie arduino = new ArduinoZombie();
        boolean result = arduino.pinMode(pin, Arduino.OUTPUT).analogWrite(pin, 200).flush();
        assertTrue(result);
    }
}