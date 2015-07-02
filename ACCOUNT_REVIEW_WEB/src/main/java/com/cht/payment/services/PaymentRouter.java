/*
 * 版權宣告: FDC all rights reserved.
 */
package com.cht.payment.services;

import org.springframework.integration.Message;  

/** 
 * This is the base Router for All payment related functions 
 * We route the message based on the banking system and the action 
 * which comes in the header of the message. Ofcourse we can enhance this 
 * to put the message on an error queue if the {@link Message} does not have the 
 * relevant header values. 
 *  
 * @author dinuka 
 */  
public class PaymentRouter {  
  
    public String resolveBankChannel(Message message) {  
        return (String) message.getHeaders().get("BANKING_SYSTEM") + (String) message.getHeaders().get("ACTION")  
                + "Channel";  
    }  
} 
