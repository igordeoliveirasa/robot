/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.igordeoliveira.dev.zombie;

import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


/**
 *
 * @author igor
 */
public class ArduinoZombieTests {
    
    private IArduinoZombie arduino;
    private ArduinoCommunication communicationMock = mock(ArduinoCommunication.class);
    private int pin = 3;
    
    @Before
    public void setUp() {
        this.arduino = new ArduinoZombie(communicationMock);
    }
    
    @Test
    public void testPinModeOutput() {
        boolean result = arduino.pinMode(pin, ArduinoConstants.OUTPUT).flush();
        assertTrue(result);
    }
    
    @Test
    public void testDigitalWritePin3() {
        boolean result = arduino.pinMode(pin, ArduinoConstants.OUTPUT).digitalWrite(pin, ArduinoConstants.HIGH).flush();
        assertTrue(result);
    }
    
    @Test
    public void testAnalogWritePin3() {
        boolean result = arduino.pinMode(pin, ArduinoConstants.OUTPUT).analogWrite(pin, 200).flush();
        assertTrue(result);
    }
}