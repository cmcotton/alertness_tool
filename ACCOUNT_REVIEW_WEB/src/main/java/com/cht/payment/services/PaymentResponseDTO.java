/*
 * 版權宣告: FDC all rights reserved.
 */
package com.cht.payment.services;

import java.io.Serializable;  

/** 
 * This is the default payment response DTO that every plugin 
 * must return back to the system 
 *  
 * @author dinuka 
 */  
public class PaymentResponseDTO implements Serializable {  
  
    /** 
     *  
     */  
    private static final long serialVersionUID = 2773607380706313950L;  
  
    /** 
     * The account number of the customer 
     */  
    private String accountNumber;  
  
    /** 
     * The first name of the customer 
     */  
    private String firstName;  
  
    /** 
     * The last name of the customer 
     */  
    private String lastName;  
  
    /** 
     * The remaining balance in the account of the customer 
     */  
    private Double availableBalance;  
  
    /** 
     * The balance reduced from the customer account 
     */  
    private Double reducedBalance;  
  
    /** 
     * The status code indicating whether the transaction was a success or not 
     */  
    private PaymentStatusCode statusCode = PaymentStatusCode.SUCCESS;  
  
    /** 
     * The transaction id assigned to the relevant transaction 
     */  
    private Long transationId;  
  
    public String getAccountNumber() {  
        return accountNumber;  
    }  
  
    public void setAccountNumber(String accountNumber) {  
        this.accountNumber = accountNumber;  
    }  
  
    public String getFirstName() {  
        return firstName;  
    }  
  
    public void setFirstName(String firstName) {  
        this.firstName = firstName;  
    }  
  
    public String getLastName() {  
        return lastName;  
    }  
  
    public void setLastName(String lastName) {  
        this.lastName = lastName;  
    }  
  
    public Double getAvailableBalance() {  
        return availableBalance;  
    }  
  
    public void setAvailableBalance(Double availableBalance) {  
        this.availableBalance = availableBalance;  
    }  
  
    public Double getReducedBalance() {  
        return reducedBalance;  
    }  
  
    public void setReducedBalance(Double reducedBalance) {  
        this.reducedBalance = reducedBalance;  
    }  
  
    public PaymentStatusCode getStatusCode() {  
        return statusCode;  
    }  
  
    public void setStatusCode(PaymentStatusCode statusCode) {  
        this.statusCode = statusCode;  
    }  
  
    public Long getTransationId() {  
        return transationId;  
    }  
  
    public void setTransationId(Long transationId) {  
        this.transationId = transationId;  
    }  
  
    @Override  
    public String toString() {  
        return "PaymentResponseDTO [accountNumber=" + accountNumber + ", firstName=" + firstName + ", lastName="  
                + lastName + ", availableBalance=" + availableBalance + ", reducedBalance=" + reducedBalance  
                + ", statusCode=" + statusCode + ", transationId=" + transationId + "]";  
    }  
  
} 
