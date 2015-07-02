/*
 * 版權宣告: FDC all rights reserved.
 */
package com.cht.payment.services;

/** 
 * This enum defines the system wide actions 
 * We use this name in our {@link PaymentRouter} 
 * to decide which channel to route the message 
 *  
 * @author dinuka 
 */  
public enum SystemActions {  
  
    PAYMENT {  
        @Override  
        public String toString() {  
  
            return "Payment";  
        }  
    }  
}  
