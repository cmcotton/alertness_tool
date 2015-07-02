/*
 * 版權宣告: FDC all rights reserved.
 */
package com.cht.payment.services;

import java.util.HashMap;  
import java.util.Map;  
  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.integration.message.GenericMessage;  
import org.springframework.stereotype.Component;  
  
@Component("paymentService")  
public class PaymentServiceImpl implements PaymentService {  
  
    @Autowired  
    private CentralPaymentGateway gateway;  
  
    @Override  
    public PaymentResponseDTO makePayment(PaymentRequestDTO paymentRequestDTO) {  
        /** 
         * Here you can do any validation checks for null values if you need 
         * and throw any relevant exception as needed. For simplicity purposes 
         * i have not done so here. 
         */  
  
        /** 
         * In the header we specify the banking system this message needs to be routed to 
 
         * Then in the 
         */  
        Map headerMap = new HashMap();  
        headerMap.put("BANKING_SYSTEM", paymentRequestDTO.getBankingSystem());  
        headerMap.put("ACTION", SystemActions.PAYMENT.toString());  
        GenericMessage<PaymentRequestDTO> paymentRequestMsg = new GenericMessage<PaymentRequestDTO>(paymentRequestDTO,  
                headerMap);  
        PaymentResponseDTO paymentResponseDTO = gateway.makePayment(paymentRequestMsg);  
  
        if (paymentResponseDTO.getStatusCode() == PaymentStatusCode.FAILURE) {  
            /** 
             * Throw relevant exception 
             */  
        }  
        return paymentResponseDTO;  
  
    }  
  
} 
