/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phil.hubtelpaymentapi.exceptions;

/**
 *
 * @author Philip
 */
public class PaymentException extends Exception {

    public PaymentException() {
        super();
    }    
    
    public PaymentException(String message) {
        super(message);
    }
}
