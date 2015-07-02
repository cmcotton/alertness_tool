/*
 * 版權宣告: FDC all rights reserved.
 */
package com.cht.payment.services;

import org.springframework.integration.message.GenericMessage;



/** 
 * This interface represents the common gateway which 
 * will be used by Spring Integration to wire up the plugins 
 * and also will be the central and first point of contact 
 * by any client calling our business layer 
 *  
 * @author dinuka 
 */  
public interface CentralPaymentGateway {  
  
    /** 
     * This method takes a parameter type of {@link GenericMessage} which wraps&lt;br&gt; 
     * an instance of {@link PaymentRequestDTO}. Usage of sending an instance of&lt;br&gt; 
     * Generic Message is so that we can add header values which can indicate&lt;br&gt; 
     * which banking system to call to 
     *  
     * @param paymentRequestDTO 
     * @return 
     */  
    public PaymentResponseDTO makePayment(GenericMessage<PaymentRequestDTO> paymentRequestDTO);  
} 
