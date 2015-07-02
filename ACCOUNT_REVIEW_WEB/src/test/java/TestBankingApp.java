/*
 * 版權宣告: FDC all rights reserved.
 */
package test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cht.payment.services.PaymentRequestDTO;
import com.cht.payment.services.PaymentResponseDTO;
import com.cht.payment.services.PaymentService;

/**
 * This is a test class showing how it all comes together
 * 
 * @author dinuka
 */
public class TestBankingApp {

    public static void main(String[] args) {
        
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("context-config.xml",
                    "spring-integration-config.xml", "abc_bank_plugin-config.xml");

            PaymentService paymentService = (PaymentService) context.getBean("paymentService");

            PaymentRequestDTO paymentRequestDTO = new PaymentRequestDTO();
            PaymentResponseDTO paymentResponseDTO = paymentService.makePayment(paymentRequestDTO);

            /**
             * We just print out the resulting DTO returned from the plugin
             * 
             * as this is just a tutorial
             */
            System.out.println(paymentResponseDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}