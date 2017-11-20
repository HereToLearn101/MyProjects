/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author tedis
 */
public class Change {
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;
    
    public Change(BigDecimal changeDueInPennies) {        
        BigDecimal remain = changeDueInPennies;
        if ((remain.compareTo(new BigDecimal("25")) == 1) || (remain.compareTo(new BigDecimal("25")) == 0)) {
            this.quarters = remain.divide(new BigDecimal("25"), 0, RoundingMode.DOWN).intValue();
            remain = remain.remainder(new BigDecimal("25"));
        }
        
        if ((remain.compareTo(new BigDecimal("10")) == 1) || (remain.compareTo(new BigDecimal("10")) == 0)) {
            this.dimes = remain.divide(new BigDecimal("10"), 0, RoundingMode.DOWN).intValue();
            remain = remain.remainder(new BigDecimal("10"));
            
        }
        
        if ((remain.compareTo(new BigDecimal("5")) == 1) || (remain.compareTo(new BigDecimal("5"))) == 0) {
            this.nickels = remain.divide(new BigDecimal("5"), 0, RoundingMode.DOWN).intValue();
            remain = remain.remainder(new BigDecimal("5"));
            System.out.println(remain);
        }
        
        this.pennies = remain.intValue();
        //System.out.println(this.pennies);
    }

    public int getQuarters() {
        return quarters;
    }

    public int getDimes() {
        return dimes;
    }

    public int getNickels() {
        return nickels;
    }

    public int getPennies() {
        return pennies;
    }
    
}
