/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package carrinho;

import com.igordeoliveira.dev.zombie.ArduinoCommunication;
import com.igordeoliveira.dev.zombie.ArduinoConstants;
import com.igordeoliveira.dev.zombie.ArduinoZombie;

/**
 *
 * @author igor
 */
public class Carrinho {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        ArduinoZombie arduino = ArduinoZombie.getInstance("192.168.1.200", 41085, "123");
        
        int l1 = 4;
        int l2 = 5;
        int r1 = 6;
        int r2 = 7;
        
        while (true) {
            arduino.pinMode(r1, ArduinoConstants.OUTPUT).
                    digitalWrite(r1, ArduinoConstants.HIGH).
                    pinMode(r2, ArduinoConstants.OUTPUT).
                    digitalWrite(r2, ArduinoConstants.LOW).
                    pinMode(l1, ArduinoConstants.OUTPUT).
                    digitalWrite(l1, ArduinoConstants.HIGH).
                    pinMode(l2, ArduinoConstants.OUTPUT).
                    digitalWrite(l2, ArduinoConstants.LOW).                
                    flush();

            Thread.sleep(2000);


            arduino.digitalWrite(l1, ArduinoConstants.LOW).
                    digitalWrite(l2, ArduinoConstants.LOW).
                    digitalWrite(r1, ArduinoConstants.LOW).
                    digitalWrite(r2, ArduinoConstants.LOW).
                    flush();

            Thread.sleep(2000);
        }
        /**/
    }
}
