/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.igordeoliveira.dev.zombie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author igor
 */
public class ArduinoZombieTests {
    
    ArduinoCommunication arduinoCommunication;
    ArduinoZombie arduino;
    Socket socket;
    @Before
    public void setUp() {
        try {
            socket = new Socket("192.168.1.200", 41085);
            arduinoCommunication = new ArduinoCommunication(new PrintWriter(socket.getOutputStream()), new BufferedReader(new InputStreamReader(socket.getInputStream())), "123");
            arduino = new ArduinoZombie(arduinoCommunication);
        } catch (Exception ex) {
            Logger.getLogger(ArduinoZombieTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @Test
    public void testGetInstance() {
        try {
            socket.close();
            ArduinoZombie arduino = ArduinoZombie.getInstance("192.168.1.200", 41085, "123");
            arduino.getCommunication().getIn().close();
            arduino.getCommunication().getOut().close();
            assertNotNull(arduino);
        } catch (Exception e) {
            fail();
        }
    }
    
        
    
    @After
    public void tearDown() {
        try {
            arduino.digitalWrite(04, ArduinoConstants.LOW).
                    digitalWrite(05, ArduinoConstants.LOW).
                    digitalWrite(06, ArduinoConstants.LOW).
                    digitalWrite(07, ArduinoConstants.LOW).
                    flush();
                    
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ArduinoZombieTests.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void testConvertStackIntoCommand1() {
        arduino.pinMode(4, ArduinoConstants.OUTPUT);
        assertEquals("4", arduino.convertStackIntoCommand());
    }

    @Test
    public void testConvertStackIntoCommand2() {
        arduino.pinMode(4, ArduinoConstants.OUTPUT).
                digitalWrite(4, ArduinoConstants.HIGH);
        
        assertEquals( ArduinoConstants.PMOU04 + "|" + ArduinoConstants.DWHI04, arduino.convertStackIntoCommand());
    }

    
    @Test
    public void testPinModeOutputWithoutFlush() {
        arduino.pinMode(4, ArduinoConstants.OUTPUT);
        assertEquals("4", arduino.getStack().get(0));
    }
    
    @Test
    public void testPinModeOutput() {
        boolean result = arduino.pinMode(4, ArduinoConstants.OUTPUT).flush();
        assertTrue(result);
    }
    
    @Test
    public void testDigitalWritePin4() {
        boolean result = arduino.pinMode(4, ArduinoConstants.OUTPUT).digitalWrite(4, ArduinoConstants.HIGH).flush();
        assertTrue(result);
    }
    
    @Test
    public void testDigitalWritePin4and5() {
        boolean result = arduino.
                pinMode(4, ArduinoConstants.OUTPUT).
                pinMode(5, ArduinoConstants.OUTPUT).
                digitalWrite(4, ArduinoConstants.HIGH).
                digitalWrite(5, ArduinoConstants.LOW).
                flush();
        assertTrue(result);
    }
    
    /*
    @Test
    public void testAnalogWritePin3() {
        boolean result = arduino.pinMode(3, ArduinoConstants.OUTPUT).analogWrite(3, 200).flush();
        assertTrue(result);
    }*/
    
    
    
}