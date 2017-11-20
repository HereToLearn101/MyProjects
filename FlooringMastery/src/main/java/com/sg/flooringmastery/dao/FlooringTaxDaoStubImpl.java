/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tedis
 */
public class FlooringTaxDaoStubImpl implements FlooringTaxDao {
    
    private Tax tax;
    private List<Tax> taxes = new ArrayList<>();
    
    public FlooringTaxDaoStubImpl () {
        this.tax = new Tax("OH");
        tax.setTaxRate(new BigDecimal("6.25"));
        
        taxes.add(tax);
    }

    @Override
    public Tax addTax(Tax tax) throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tax removeTax(String state) throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Tax getTax(String state) throws FlooringPersistenceException {
        if (state.equals(tax.getState())) {
            return tax;
        }
        return null;
    }

    @Override
    public List<Tax> getAllTaxes() throws FlooringPersistenceException {
        return taxes;
    }

    @Override
    public void editTax(String oldTax, Tax updatedTax) throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
