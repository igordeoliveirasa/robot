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

int PMOU04 = 4;
int PMOU05 = 5;
int PMOU06 = 6;
int PMOU07 = 7;

int DWHI04 = 14;
int DWHI05 = 15;
int DWHI06 = 16;
int DWHI07 = 17;

int DWLO04 = 24;
int DWLO05 = 25;
int DWLO06 = 26;
int DWLO07 = 27;


void setup()
{
        Serial.begin(9600);
	WiFi.init();
}

int cod = 0;
char * token;
char * tokenizer = "|";

void process(char* buffer) {
  
    cod = atoi(buffer);
    
    if (cod == PMOU04) {  
      pinMode(04, OUTPUT);
    }
    else if (cod == PMOU05) {  
      pinMode(05, OUTPUT);
    }
    else if (cod == PMOU06) {  
      pinMode(06, OUTPUT);
    }
    else if (cod == PMOU07) {  
      pinMode(07, OUTPUT);
    }
    
    //DigitalWrite HIGH
    else if (cod == DWHI04) {  
      digitalWrite(04, HIGH);
    }
    else if (cod == DWHI05) {  
      digitalWrite(05, HIGH);
    }
    else if (cod == DWHI06) {  
      digitalWrite(06, HIGH);
    }    
    else if (cod == DWHI07) {  
      digitalWrite(07, HIGH);
    }
    
    //DigitalWrite LOW
    else if (cod == DWLO04) {  
      digitalWrite(04, LOW);
    }
    else if (cod == DWLO05) {  
      digitalWrite(05, LOW);
    }
    else if (cod == DWLO06) {  
      digitalWrite(06, LOW);
    }    
    else if (cod == DWLO07) {  
      digitalWrite(07, LOW);
    }
    
}

void loop()
{
    WiFi.run();  
    
    token = strtok (buffer, tokenizer);
    
    while (token != NULL)
    {  
      process(token); 
      token = strtok (NULL, tokenizer);
    }   
    memset(buffer, 0x00, sizeof(buffer));
    cod = 0;

    delay(10);
}


