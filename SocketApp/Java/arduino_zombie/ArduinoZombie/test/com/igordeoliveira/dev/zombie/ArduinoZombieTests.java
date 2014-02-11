/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.igordeoliveira.dev.zombie;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author igor
 */
public class ArduinoZombieTests {
    
    private IArduinoZombie arduino;
    private int pin = 3;
    
    @Before
    public void setUp() {
        try {
            this.arduino = new ArduinoZombie("192.168.1.200", 41085, "123");
        } catch (Exception ex) {
            Logger.getLogger(ArduinoZombieTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testInvalidAddress() {
        try {
            this.arduino = new ArduinoZombie("192.168.1.100", 41085, "123456");
            fail();
        } catch (Exception ex) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testWrongPassword() {
        try {
            this.arduino = new ArduinoZombie("192.168.1.200", 41085, "123456");
            fail();
        } catch (Exception ex) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testWrongPort() {
        try {
            this.arduino = new ArduinoZombie("192.168.1.200", 1085, "123456");
            fail();
        } catch (Exception ex) {
            assertTrue(true);
        }
    }
    
    @Test
    public void testPinModeOutput() {
        boolean result = arduino.pinMode(pin, Arduino.OUTPUT).flush();
        assertTrue(result);
    }
    
    @Test
    public void testDigitalWritePin3() {
        boolean result = arduino.pinMode(pin, Arduino.OUTPUT).digitalWrite(pin, Arduino.HIGH).flush();
        assertTrue(result);
    }
    
    @Test
    public void testAnalogWritePin3() {
        boolean result = arduino.pinMode(pin, Arduino.OUTPUT).analogWrite(pin, 200).flush();
        assertTrue(result);
    }
}