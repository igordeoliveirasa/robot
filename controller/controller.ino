#include <WiServer.h>

#define WIRELESS_MODE_INFRA	1
#define WIRELESS_MODE_ADHOC	2

// Wireless configuration parameters ----------------------------------------
unsigned char local_ip[] = {192,168,1,200};	// IP address of WiShield
unsigned char gateway_ip[] = {192,168,1,1};	// router or gateway IP address
unsigned char subnet_mask[] = {255,255,255,0};	// subnet mask for the local network
const prog_char ssid[] PROGMEM = {"millenniunfalcon"};		// max 32 bytes

unsigned char security_type = 3;	// 0 - open; 1 - WEP; 2 - WPA; 3 - WPA2

// WPA/WPA2 passphrase
const prog_char security_passphrase[] PROGMEM = {"123mudar!"};	// max 64 characters

// WEP 128-bit keys
// sample HEX keys
prog_uchar wep_keys[] PROGMEM = { 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d,	// Key 0
				  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,	// Key 1
				  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,	// Key 2
				  0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00	// Key 3
				};

// setup the wireless mode
// infrastructure - connect to AP
// adhoc - connect to another WiFi device
unsigned char wireless_mode = WIRELESS_MODE_INFRA;

unsigned char ssid_len;
unsigned char security_passphrase_len;
// End of wireless configuration parameters ----------------------------------------


// SIGNALS
int sd1 = 4;
int sd2 = 5;
int se1 = 6;
int se2 = 7;
// 3 available yet..


void left_forward() {
  digitalWrite(se1, HIGH); 
  digitalWrite(se2, LOW);  
}

void right_forward() {
  digitalWrite(sd1, LOW);  
  digitalWrite(sd2, HIGH);   
}

void left_backward() {
  digitalWrite(se1, LOW); 
  digitalWrite(se2, HIGH);  
}

void right_backward() {
  digitalWrite(sd1, HIGH);  
  digitalWrite(sd2, LOW);   
}

void forward() {
  left_forward();
  right_forward();
}
void backward() {
  left_backward();
  right_backward();
}

void left_stop() {
  digitalWrite(se1, LOW); 
  digitalWrite(se2, LOW);    
}

void right_stop() {
  digitalWrite(sd1, LOW);  
  digitalWrite(sd2, LOW);     
}

void tostop() {
  left_stop();
  right_stop();
}

void left() {
 left_forward();
 right_backward(); 
}

void left2() {
 //left_forward();
 right_forward(); 
}

void right() {
 right_forward(); 
 left_backward();
}


// This is our page serving function that generates web pages
boolean sendMyPage(char* URL) {
  
    // Check if the requested URL matches "/"
    if (strcmp(URL, "/") == 0) {
        // Use WiServer's print and println functions to write out the page content
        WiServer.print("<html>");
        WiServer.print("Hello World!");
        WiServer.print("</html>");
        
        // URL was recognized
        return true;
    }
    else if (strcmp(URL, "/forward") == 0) {
        // Use WiServer's print and println functions to write out the page content
        WiServer.print("<html>");
        WiServer.print("front!");
        WiServer.print("</html>");
        
        forward();
        delay(500);
        tostop();
        
        // URL was recognized
        return true;
    }
    else if (strcmp(URL, "/backward") == 0) {
        // Use WiServer's print and println functions to write out the page content
        WiServer.print("<html>");
        WiServer.print("back!");
        WiServer.print("</html>");
        
        backward();
        delay(500);
        tostop();
          
        // URL was recognized
        return true;
    }
    else if (strcmp(URL, "/left") == 0) {
        // Use WiServer's print and println functions to write out the page content
        WiServer.print("<html>");
        WiServer.print("left!");
        WiServer.print("</html>");
        
        left();
        delay(1500);
        tostop();
          
        // URL was recognized
        return true;
    }
  else if (strcmp(URL, "/left2") == 0) {
        // Use WiServer's print and println functions to write out the page content
        WiServer.print("<html>");
        WiServer.print("left!");
        WiServer.print("</html>");
        
        left2();
        delay(1500);
        tostop();
          
        // URL was recognized
        return true;
    }    
    else if (strcmp(URL, "/right") == 0) {
        // Use WiServer's print and println functions to write out the page content
        WiServer.print("<html>");
        WiServer.print("right!");
        WiServer.print("</html>");
        
        right();
        delay(1500);
        tostop();
          
        // URL was recognized
        return true;
    }
    
    else if (strcmp(URL, "/stop") == 0) {
        // Use WiServer's print and println functions to write out the page content
        WiServer.print("<html>");
        WiServer.print("right!");
        WiServer.print("</html>");
        
        tostop();
          
        // URL was recognized
        return true;
    }
    
    // URL not found
    return false;
}

// the setup routine runs once when you press reset:
void setup() {                
  pinMode(se1, OUTPUT);     
  pinMode(se2, OUTPUT);     
  pinMode(sd1, OUTPUT);     
  pinMode(sd2, OUTPUT);

  WiServer.init(sendMyPage);
  Serial.begin(57600);
  WiServer.enableVerboseMode(true); 
}

// the loop routine runs over and over again forever:
void loop() {
  
  /*
  forward();
  delay(1000);             
  tostop();
  delay(1000);             
  backward();
  delay(1000);             
  tostop();
  delay(1000);             
  left();
  delay(1000);             
  tostop();
  delay(1000);             
  right();
  delay(1000);             
  */
  WiServer.server_task();  
  delay(10);
}
