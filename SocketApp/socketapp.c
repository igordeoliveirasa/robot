
/******************************************************************************

  Filename:		socketapp.c
  Description:	Simple socket programming example for the WiShield 1.0

 ******************************************************************************

  TCP/IP stack and driver for the WiShield 1.0 wireless devices

  Copyright(c) 2009 Async Labs Inc. All rights reserved.

  This program is free software; you can redistribute it and/or modify it
  under the terms of version 2 of the GNU General Public License as
  published by the Free Software Foundation.

  This program is distributed in the hope that it will be useful, but WITHOUT
  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
  FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License for
  more details.

  You should have received a copy of the GNU General Public License along with
  this program; if not, write to the Free Software Foundation, Inc., 59
  Temple Place - Suite 330, Boston, MA  02111-1307, USA.

  Contact Information:
  <asynclabs@asynclabs.com>

   Author               Date        Comment
  ---------------------------------------------------------------
   AsyncLabs			06/06/2009	Initial version

 *****************************************************************************/

/*
 * This is a short example of how to write uIP applications using
 * protosockets.
 */

/*
 * We define the application state (struct socket_app_state) in the
 * socketapp.h file, so we need to include it here. We also include
 * uip.h (since this cannot be included in socketapp.h) and
 * <string.h>, since we use the memcpy() function in the code.
 */
#include "Arduino.h"
#include "socketapp.h"
#include "uip.h"
#include <string.h>



/*
 * Declaration of the protosocket function that handles the connection
 * (defined at the end of the code).
 */
static int handle_connection(struct socket_app_state *s);
/*---------------------------------------------------------------------------*/
/*
 * The initialization function. We must explicitly call this function
 * from the system initialization code, some time after uip_init() is
 * called.
 */
void socket_app_init(void)
{
  /* We start to listen for connections on TCP port 1000. */
  uip_listen(HTONS(41085));
}
/*---------------------------------------------------------------------------*/
/*
 * In socketapp.h we have defined the UIP_APPCALL macro to
 * socket_app_appcall so that this function is uIP's application
 * function. This function is called whenever an uIP event occurs
 * (e.g. when a new connection is established, new data arrives, sent
 * data is acknowledged, data needs to be retransmitted, etc.).
 */
void socket_app_appcall(void)
{
  /*
   * The uip_conn structure has a field called "appstate" that holds
   * the application state of the connection. We make a pointer to
   * this to access it easier.
   */
  struct socket_app_state *s = &(uip_conn->appstate);

  /*
   * If a new connection was just established, we should initialize
   * the protosocket in our applications' state structure.
   */
  if(uip_connected()) {
    PSOCK_INIT(&s->p, s->inputbuffer, sizeof(s->inputbuffer));
  }

  /*
   * Finally, we run the protosocket function that actually handles
   * the communication. We pass it a pointer to the application state
   * of the current connection.
   */
  handle_connection(s);
}
/*---------------------------------------------------------------------------*/
/*
 * This is the protosocket function that handles the communication. A
 * protosocket function must always return an int, but must never
 * explicitly return - all return statements are hidden in the PSOCK
 * macros.
 */
 
int p3 = 3;
int p4 = 4;
int p5 = 5;
int p6 = 6;
int p7 = 7;
 
static int handle_connection(struct socket_app_state *s)
{

  
  PSOCK_BEGIN(&s->p);
  
  memset(s->inputbuffer, 0x00, sizeof(s->inputbuffer));
  PSOCK_SEND_STR(&s->p, "ok\n");
  PSOCK_READTO(&s->p, '\n');
  
  if (strncmp(s->inputbuffer, "123", 3)==0) {
    
    PSOCK_SEND_STR(&s->p, "ok\n");

    while (1) {
      memset(s->inputbuffer, 0x00, sizeof(s->inputbuffer));
      PSOCK_READTO(&s->p, '\n');
      
      if (strncmp(s->inputbuffer, "exit", 4)==0) {
        PSOCK_SEND_STR(&s->p, "ok\n");
        break;   
      }
      
      //PMOU01, PMIN12
      //DWHI03, DWLO04
      
      char* tokenizer = "|";
      char* t = strtok(s->inputbuffer, tokenizer);     
      while (t!=NULL) {
        char token[20] = "";
        strncpy(token, t, 20);
        
        char command[5] = "";
        char option[5] = "";        
        char str_pin[5] = "";
        int pin = 0;
        strncpy(command, token, 2);        
        
        if (strncmp(command, "PM", 2)==0) {
          strncpy(option, token+2, 2);        
          strncpy(str_pin, token+4, 2);        
          pin = atoi(str_pin);
          
          if (strncmp(option, "OU", 2)==0) {
              pinMode(pin, OUTPUT);
              PSOCK_SEND_STR(&s->p, "ok\n");
          }
          else if (strncmp(option, "IN", 2)==0) {
              pinMode(pin, INPUT);
              PSOCK_SEND_STR(&s->p, "ok\n");
          }
          else {
            PSOCK_SEND_STR(&s->p, "fail\n");
          }
        }          
        else if (strncmp(command, "DW", 2)==0) {
          strncpy(option, token+2, 2);        
          strncpy(str_pin, token+4, 2);        
          pin = atoi(str_pin);

          if (strncmp(option, "HI", 2)==0) {
              digitalWrite(pin, HIGH);
              PSOCK_SEND_STR(&s->p, "ok\n");
          }
          else if (strncmp(option, "LO", 2)==0) {
              digitalWrite(pin, LOW);
              PSOCK_SEND_STR(&s->p, "ok\n");
          }
          else {
            PSOCK_SEND_STR(&s->p, "fail\n");
          }
        }        
        //AW25504
        else if (strncmp(command, "AW", 2)==0) {
          strncpy(option, token+2, 3);
          int power = atoi(option);
          strncpy(str_pin, token+5, 2);        
          pin = atoi(str_pin);
          analogWrite(pin, power);
          PSOCK_SEND_STR(&s->p, "ok\n");
        }
        else {
            PSOCK_SEND_STR(&s->p, "fail\n");
        }
        t = strtok(NULL, tokenizer);
      }
    }      
  }
  else {
    PSOCK_SEND_STR(&s->p, "Access Denied!\n");
  }

  digitalWrite(p3, LOW);
  digitalWrite(p4, LOW);
  digitalWrite(p5, LOW);
  digitalWrite(p6, LOW);  
  digitalWrite(p7, LOW);  
     
  PSOCK_CLOSE(&s->p);
  PSOCK_END(&s->p);
}
/*---------------------------------------------------------------------------*/
