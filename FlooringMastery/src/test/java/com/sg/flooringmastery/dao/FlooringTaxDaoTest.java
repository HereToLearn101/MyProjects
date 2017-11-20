/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author tedis
 */
public class FlooringTaxDaoTest {

    private FlooringTaxDao taxDao = new FlooringTaxDaoImpl();

    public FlooringTaxDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        List<Tax> taxes = taxDao.getAllTaxes();
        for (Tax tax : taxes) {
            taxDao.removeTax(tax.getState());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addTax method, of class FlooringTaxDao.
     */
    @Test
    public void testAddTax() throws Exception {
        Tax tax = new Tax("OH");
        tax.setTaxRate(new BigDecimal("6.25"));

        taxDao.addTax(tax);

        Tax currentTax = taxDao.getTax(tax.getState());

        assertEquals(tax, currentTax);
    }

    /**
     * Test of removeTax method, of class FlooringTaxDao.
     */
    @Test
    public void testRemoveTax() throws Exception {
        Tax tax = new Tax("OH");
        tax.setTaxRate(new BigDecimal("6.25"));

        taxDao.addTax(tax);
        Tax removedTax = taxDao.removeTax(tax.getState());

        assertEquals(0, taxDao.getAllTaxes().size());
        assertEquals(tax, removedTax);
    }

    /**
     * Test of getTax method, of class FlooringTaxDao.
     *
     * Already tested with the add method above.
     */
    
    /**
     * Test of getAllTaxes method, of class FlooringTaxDao.
     */
    @Test
    public void testGetAllTaxes() throws Exception {
        Tax tax = new Tax("OH");
        tax.setTaxRate(new BigDecimal("6.25"));

        taxDao.addTax(tax);

        Tax tax2 = new Tax("IN");
        tax2.setTaxRate(new BigDecimal("25.00"));
        
        taxDao.addTax(tax2);
    }

    /**
     * Test of editTax method, of class FlooringTaxDao.
     */
    @Test
    public void testEditTax() throws Exception {
        Tax tax = new Tax("OH");
        tax.setTaxRate(new BigDecimal("6.25"));

        taxDao.addTax(tax);
        
        Tax editedTax = taxDao.getTax(tax.getState());
        editedTax.setTaxRate(new BigDecimal("2.00"));
        
        taxDao.editTax(tax.getState(), editedTax);
        
        Tax currentEditedTax = taxDao.getTax(editedTax.getState());
        
        assertEquals(editedTax, currentEditedTax);
    }

}
