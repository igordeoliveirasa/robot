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
import org.mockito.cglib.core.Constants;

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
        int command = 0;
        
        if (pinType == ArduinoConstants.OUTPUT) {
            if (pin==04) {
                command = ArduinoConstants.PMOU04;
            } else if (pin==05) {
                command = ArduinoConstants.PMOU05;
            } else if (pin==06) {
                command = ArduinoConstants.PMOU06; 
            } else if (pin==07) {
                command = ArduinoConstants.PMOU07;
            }
        }
        this.stack.add(""+command);
        return this;
    }

    
    public ArduinoZombie digitalWrite(int pin, int digitalMessage) {
        int command = 0;
        
        if (digitalMessage == ArduinoConstants.LOW) {
            if (pin==04) {
                command = ArduinoConstants.DWLO04;
            } else if (pin==05) {
                command = ArduinoConstants.DWLO05;
            } else if (pin==06) {
                command = ArduinoConstants.DWLO06; 
            } else if (pin==07) {
                command = ArduinoConstants.DWLO07;
            }
        }
        else if (digitalMessage == ArduinoConstants.HIGH) {
            if (pin==04) {
                command = ArduinoConstants.DWHI04;
            } else if (pin==05) {
                command = ArduinoConstants.DWHI05;
            } else if (pin==06) {
                command = ArduinoConstants.DWHI06;
            } else if (pin==07) {
                command = ArduinoConstants.DWHI07;
            }            
        }
        
        this.stack.add(""+command);
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
        boolean ret = true;
        try {
            String command = this.convertStackIntoCommand();
            ret = communication.println(command).equals("ok");
            
            //for (String command : this.stack) {
                
              //  String result = communication.println(command);
               // ret = result.equals("ok");
                //if (!ret) {
                //    break;
               // }
            //}
        } catch (IOException ex) {
            Logger.getLogger(ArduinoZombie.class.getName()).log(Level.SEVERE, null, ex);
            ret = false;
        }
        stack.clear();
        return ret;
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
