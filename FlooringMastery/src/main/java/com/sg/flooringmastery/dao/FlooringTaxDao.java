/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.util.List;

/**
 *
 * @author tedis
 */
public interface FlooringTaxDao {
    Tax addTax(Tax tax) throws FlooringPersistenceException;
    
    Tax removeTax(String state) throws FlooringPersistenceException;
    
    Tax getTax(String state) throws FlooringPersistenceException;
    
    List<Tax> getAllTaxes() throws FlooringPersistenceException;
    
    void editTax(String oldTax, Tax updatedTax) throws FlooringPersistenceException;
}