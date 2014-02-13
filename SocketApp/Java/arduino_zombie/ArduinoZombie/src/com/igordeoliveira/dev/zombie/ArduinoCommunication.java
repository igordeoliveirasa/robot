/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.igordeoliveira.dev.zombie;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author igor
 */
public class ArduinoCommunication {
    private PrintWriter out;
    private BufferedReader in;

    public PrintWriter getOut() {
        return out;
    }

    public void setOut(PrintWriter out) {
        this.out = out;
    }

    public BufferedReader getIn() {
        return in;
    }

    public void setIn(BufferedReader in) {
        this.in = in;
    }
    
    public ArduinoCommunication(PrintWriter out, BufferedReader in) throws IOException {
        this.out = out;
        this.in = in;
        in.readLine();
    }

    public String println(String string) throws IOException {
        out.println(string);
        out.flush();
        return in.readLine();
    }
}
