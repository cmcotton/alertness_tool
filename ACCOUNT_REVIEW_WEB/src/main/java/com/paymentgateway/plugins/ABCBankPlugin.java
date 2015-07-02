/*
 * 版權宣告: FDC all rights reserved.
 */
package com.paymentgateway.plugins;

import com.cht.payment.services.PaymentRequestDTO;
import com.cht.payment.services.PaymentResponseDTO;
import com.cht.payment.services.PaymentStatusCode;
 
  
/** 
 * This is the plugin used to connect to the ABC banking system 
 * in order to do the payment transaction. 
 *  
 * @author dinuka 
 */  
public class ABCBankPlugin implements BasePlugin {  
  
    @Override  
    /** 
     * Right now we just return a mock value. But when the true implementation 
     * comes you will deal with any connection rellated information 
     * at this point. 
     */  
    public PaymentResponseDTO makePayment(PaymentRequestDTO paymentRequestDTO) {  
        PaymentResponseDTO paymentResponseDTO = new PaymentResponseDTO();  
        paymentResponseDTO.setAccountNumber("abc123");  
        paymentResponseDTO.setAvailableBalance(10000d);  
        paymentResponseDTO.setFirstName("Dinuka");  
        paymentResponseDTO.setLastName("Arseculeratne");  
        paymentResponseDTO.setReducedBalance(500d);  
        paymentResponseDTO.setStatusCode(PaymentStatusCode.SUCCESS);  
        paymentResponseDTO.setTransationId(1233424234l);  
        return paymentResponseDTO;  
    }  
  
}  

