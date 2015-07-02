/*
 * 版權宣告: FDC all rights reserved.
 */
package com.paymentgateway.plugins;

import com.cht.payment.services.PaymentRequestDTO;
import com.cht.payment.services.PaymentResponseDTO;


  
/** 
 * This is the base plugin interface. All plugin developers should adhere to 
 
 * this interface when they write new plugins connecting to different banking 
 
 * systems. 
 *  
 * @author dinuka 
 */  
public interface BasePlugin {  
  
    public PaymentResponseDTO makePayment(PaymentRequestDTO paymentRequestDTO);  
  
}