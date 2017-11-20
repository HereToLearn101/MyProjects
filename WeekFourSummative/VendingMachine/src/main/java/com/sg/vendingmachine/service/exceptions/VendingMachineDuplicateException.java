/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service.exceptions;

/**
 *
 * @author tedis
 */
public class VendingMachineDuplicateException extends Exception {
    
    public VendingMachineDuplicateException(String message) {
        super(message);
    }

    public VendingMachineDuplicateException(String message,
            Throwable cause) {
        super(message, cause);
    }
    
}
