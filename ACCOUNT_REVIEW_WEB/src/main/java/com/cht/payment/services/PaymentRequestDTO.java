/*
 * 版權宣告: FDC all rights reserved.
 */
package com.cht.payment.services;

import java.io.Serializable;  

/** 
 * This DTO holds the data that needs to be passed to the 
 * relevant plugin in order to make a payment 
 *  
 * @author dinuka 
 */  
public class PaymentRequestDTO implements Serializable {  
  
    /** 
     *  
     */  
    private static final long serialVersionUID = 582470760696219645L;  
  
    /** 
     * The account number of the customer 
     */  
    private String accountNumber;  
  
    /** 
     * The amount needed to be reduced 
     */  
    private Double deductAmount;  
  
    /** 
     * The First Name of the customer 
     */  
    private String firstName;  
  
    /** 
     * The Last Name of the customer 
     */  
    private String lastName;  
  
    /** 
     * This should ideally be moved to a CommonDTO as this will be reused by all 
     * subsequent DTOs. Default banking system is "abc". The client needs to set 
     * which banking system is needed to connect to. 
     */  
    private String bankingSystem = "abc";  
  
    public String getAccountNumber() {  
        return accountNumber;  
    }  
  
    public void setAccountNumber(String accountNumber) {  
        this.accountNumber = accountNumber;  
    }  
  
    public Double getDeductAmount() {  
        return deductAmount;  
    }  
  
    public void setDeductAmount(Double deductAmount) {  
        this.deductAmount = deductAmount;  
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
  
    public String getBankingSystem() {  
        return bankingSystem;  
    }  
  
    public void setBankingSystem(String bankingSystem) {  
        this.bankingSystem = bankingSystem;  
    }  
  
    @Override  
    public String toString() {  
        return "PaymentRequestDTO [accountNumber=" + accountNumber + ", deductAmount=" + deductAmount + ", firstName="  
                + firstName + ", lastName=" + lastName + "]";  
    }  
  
}  
