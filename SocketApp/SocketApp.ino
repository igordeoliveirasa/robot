/*
 * Socket App
 *
 * A simple socket application example using the WiShield 1.0
 */

#include <WiShield.h>

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
prog_uchar wep_keys[] PROGMEM = {	0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d,	// Key 0
									0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,	0x00,	// Key 1
									0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,	0x00,	// Key 2
									0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00,	0x00	// Key 3
								};

// setup the wireless mode
// infrastructure - connect to AP
// adhoc - connect to another WiFi device
unsigned char wireless_mode = WIRELESS_MODE_INFRA;

unsigned char ssid_len;
unsigned char security_passphrase_len;
//---------------------------------------------------------------------------

char buffer[20];


void setup()
{
        Serial.begin(9600);
	WiFi.init();
}

int ret;
char option[5] = "";        
char str_pin[5] = "";
int pin = 0;
int power = 0;
void loop()
{
    WiFi.run();  
  
    
    //PMOU04|PMOU05|PMOU06|PMOU07|DWHI04|DWLO05|DWHI06|DWLO07
    //Serial.print("teste");
    memset(str_pin, 0x00, sizeof(str_pin));      
    pin = 0;
      
      //PM
      if (buffer[0] == 'P' && buffer[1] == 'M') {  
        strncpy(str_pin, buffer+4, 2);        
        pin = atoi(str_pin);
        
        if (buffer[2] == 'O' && buffer[3] == 'U') {
            pinMode(pin, OUTPUT);
        }
         else if (buffer[2] == 'I' && buffer[3] == 'N') {
            pinMode(pin, INPUT);
        }
      }          
      else if (buffer[0] == 'D' && buffer[1] == 'W') {
        strncpy(str_pin, buffer+4, 2);
        pin = atoi(str_pin);

        if (buffer[2] == 'H' && buffer[3] == 'I') {
            digitalWrite(pin, HIGH);
        }
        else if (buffer[2] == 'L' && buffer[3] == 'O') {
            digitalWrite(pin, LOW);
        }
      }        
      delay(10);
}


