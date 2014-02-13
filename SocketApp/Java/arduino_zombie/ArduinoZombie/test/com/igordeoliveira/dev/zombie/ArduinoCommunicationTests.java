/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.igordeoliveira.dev.zombie;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
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
public class ArduinoCommunicationTests {
    
    private ArduinoCommunication communication;
    private Socket sockMock = mock(Socket.class);
    private PrintWriter out = mock(PrintWriter.class);
    private BufferedReader in = mock(BufferedReader.class);
    
    private int pin = 3;
    
    @Before
    public void setUp() {
    }
    
    @Test
    public void testValidConnection() {
        try {
            Socket socket = new Socket("192.168.1.200", 41085);
            communication = new ArduinoCommunication(new PrintWriter(socket.getOutputStream()), new BufferedReader(new InputStreamReader(socket.getInputStream())), "123");
            socket.close();
        } catch (IOException ex) {
            fail();
        }
    }
    
    @Test
    public void testPMOU03_and_DWHI03() {
        try {
            Socket socket = new Socket("192.168.1.200", 41085);
            communication = new ArduinoCommunication(new PrintWriter(socket.getOutputStream()), new BufferedReader(new InputStreamReader(socket.getInputStream())), "123");
            String result = communication.println("PMOU03|DWHI03");
            assertEquals("ok", result);
            socket.close();
        } catch (IOException ex) {
            fail();
        }
    }
    
    @Test
    public void testDWLO03() {
        try {
            Socket socket = new Socket("192.168.1.200", 41085);
            communication = new ArduinoCommunication(new PrintWriter(socket.getOutputStream()), new BufferedReader(new InputStreamReader(socket.getInputStream())), "123");
            String result = communication.println("DWLO03");
            assertEquals("ok", result);
            socket.close();
        } catch (IOException ex) {
            fail();
        }
    }
    
}