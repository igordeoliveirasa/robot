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
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author igor
 */
public class ArduinoZombie {

    private ArduinoCommunication communication;

    public ArduinoCommunication getCommunication() {
        return communication;
    }
    private List<String> stack = new ArrayList<String>();
    
    
    public ArduinoZombie(ArduinoCommunication communication) {
        this.communication = communication;
    }
    
    public static ArduinoZombie getInstance(String address, int port, String password) {
        try {
            Socket socket = new Socket(address, port);
            ArduinoCommunication arduinoCommunication = new ArduinoCommunication(new PrintWriter(socket.getOutputStream()), new BufferedReader(new InputStreamReader(socket.getInputStream())), password);
            ArduinoZombie arduino = new ArduinoZombie(arduinoCommunication);
            return arduino;
        } catch (Exception ex) {
            return null;
        }
    }

    public ArduinoZombie pinMode(int pin, int pinType) {
        String command = "PM";
        
        if (pinType == ArduinoConstants.OUTPUT) {
            command += "OU";
        }
        else if (pinType == ArduinoConstants.INPUT) {
            command += "IN";
        }
        
        command += String.format("%02d", pin);
        
        this.stack.add(command);
        return this;
    }

    
    public ArduinoZombie digitalWrite(int pin, int digitalMessage) {
        String command = "DW";
        
        if (digitalMessage == ArduinoConstants.HIGH) {
            command += "HI";
        }
        else if (digitalMessage == ArduinoConstants.LOW) {
            command += "LO";
        }
        
        command += String.format("%02d", pin);
        
        this.stack.add(command);
        
        return this;
    }

    
    public ArduinoZombie analogWrite(int pin, int value) {
        String command = "AW";
        command += String.format("%03d", value);
        command += String.format("%02d", pin);
        this.stack.add(command);
        return this;
    }
    
    public List<String> getStack() {
        return stack;
    }
    
    public boolean flush() {
        try {
            String command = this.convertStackIntoCommand();
            String result = communication.println(command);
            stack.clear();
            return result.equals("ok");
        } catch (IOException ex) {
            Logger.getLogger(ArduinoZombie.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    

    public String convertStackIntoCommand() {
        String ret = "";
        String sep = "";
        for (String item : stack) {
            ret += sep + item;
            sep = "|";
        }
        return ret;
    }    
    
}
