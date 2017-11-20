/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoStubImpl;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.exceptions.InsufficientFundsException;
import com.sg.vendingmachine.service.exceptions.InvalidUserChoiceException;
import com.sg.vendingmachine.service.exceptions.NoItemInventoryException;
import com.sg.vendingmachine.service.exceptions.WrongMoneyInputException;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author tedis
 */
public class VendingMachineServiceTest {
    
    private VendingMachineService service;
    
    
    public VendingMachineServiceTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        service = new VendingMachineServiceImpl(dao);
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of updateItemInventory method, of class VendingMachineService.
     */
    @Test
    public void testUpdateItemInventoryAndAcquireItemsWithInv() throws Exception {
        List<Item> items = service.acquireAllItemsWithInventory();
        
        assertTrue(items.get(0).getQuantity() > 0);
        
        int before = items.get(0).getQuantity();
        service.updateItemInventory(items.get(0).getItemName(), items.get(0));
        
        int afterUpdate = items.get(0).getQuantity();
        
        assertEquals((before - 1), afterUpdate);
    }

    /**
     * Test of processPayment method, of class VendingMachineService.
     * 
     * For this test I combined the processPayment, processChange, and
     * checkIfAllChangeIsZero method. Thought it would make more sense if I put
     * them all together.
     */
    @Test
    public void testProcessPaymentAndChangeAndFinish() throws Exception {
        List<Item> items = service.acquireAllItemsWithInventory();
        BigDecimal moneyMoneyOne = new BigDecimal("1.00");
        BigDecimal moneyMoneyTwo = new BigDecimal("1.50");
        
        service.processPayment(moneyMoneyOne, items.get(0));
        // Cash left after user's money have been subtracted from the item cost
        BigDecimal moneyLeftOne = service.getUserMoney();
        
        service.processPayment(moneyMoneyTwo, items.get(0));
        BigDecimal moneyLeftTwo = service.getUserMoney();
        
        // This part is for processPayment method
        assertEquals(new BigDecimal("0.00"), moneyLeftOne);
        assertEquals(new BigDecimal("0.50"), moneyLeftTwo);
        
        // This part is for the processChange method and checkIfAllChangeIsZero
        // method
        Change changeOne = service.processChange(moneyLeftOne);
        Change changeTwo = service.processChange(moneyLeftTwo);
        Boolean trueValue = service.checkIfAllChangeIsZero(changeOne);
        Boolean falseValue = service.checkIfAllChangeIsZero(changeTwo);
        assertTrue(trueValue);
        assertFalse(falseValue);
    }
    
    /**
     * Test of all the exceptions of processPayment method of class
     * VendingMachineService
     */
    
    @Test
    public void testProcessPaymentExceptions() throws Exception {
        List<Item> items = service.acquireAllItemsWithInventory();
        BigDecimal wrongUserMoneyInput = new BigDecimal("1.001");
        BigDecimal insufficientPayment = new BigDecimal("0.50");
        
        try {
            service.processPayment(wrongUserMoneyInput, items.get(0));
            fail("Expected WrongMoneyInputException");
        } catch (WrongMoneyInputException e) {
            
        }
        
        try {
            service.processPayment(insufficientPayment, items.get(0));
            fail("Expected InsufficientFundsException");
        } catch (InsufficientFundsException e) {
            
        }
    }

    /**
     * Test of finishProcessPayment method, of class VendingMachineService.
     */   
    @Test
    public void testFinishProcessPayment() throws Exception {
        List<Item> items = service.acquireAllItemsWithInventory();
        BigDecimal moneyMoney = new BigDecimal("1.50");
        
        service.processPayment(moneyMoney, items.get(0));
        
        // Before finishProcessPayment method is called user's money that was
        // supplied to make payment to item should be 0.50 left (user's money
        // subtracted from item cost)
        service.getUserMoney();
        service.finishProcessPayment();
        
        // getUserMoney method just returns private field member in service
        // implementation class
        assertEquals(new BigDecimal("0.00"), service.getUserMoney());
    }

    /**
     * Test of processChange method, of class VendingMachineService.
     * 
     * This method is already been used at another test method above.
     */

    /**
     * Test of checkItemInventory method, of class VendingMachineService.
     */
    @Test
    public void testCheckItemInventory() throws Exception {
        List<Item> items = service.acquireAllItemsWithInventory();
        
        service.checkItemInventory(items.get(0));
        
        Item item = new Item("Tobleron");
        item.setItemCost(new BigDecimal("1.00"));
        item.setQuantity(0);
        
        try {
            service.checkItemInventory(item);
            fail("Expected NoItemInventoryException");
        } catch (NoItemInventoryException e){
            
        }
    }

    /**
     * Test of checkUserChoice method, of class VendingMachineService.
     */
    @Test
    public void testCheckUserChoice() throws Exception {
        List<Item> items = service.acquireAllItemsWithInventory();
        
//        items variable of type List consists of one item at the moment so if my 
//        menu was displayed it would look like this:
//        
//        Please choose an item below:
//        '1': KitKat $1.00
//        'Exit': To Leave
//        Your choice? -> 
        
        // The choice here that I set up is '0', but in my real application I had it
        // set up where the user would pick '1', and had that converted to int and
        // subtracted by 1, so the int value would be 0. That 0 value would then
        // be used to look for the first item on my List of items.
        // But for now I'll just set it up like this for my checkUserChoice
        // method.
        String choice = "0";
        String wrongChoiceOne = "a;lsdkfj";
        String wrongChoiceTwo = "3";
        
        service.checkUserChoice(choice, items);
        
        try {
            service.checkUserChoice(wrongChoiceOne, items);
            fail("Expected NumberFormatException");
        } catch (NumberFormatException e) {
        }
        
        try {
            service.checkUserChoice(wrongChoiceTwo, items);
            fail("Expected InvalidUserChoiceException");
        } catch (InvalidUserChoiceException e) {
        }
    }

    /**
     * Test of checkIfAllChangeIsZero method, of class VendingMachineService.
     * 
     * This method is already being tested above.
     */

    /**
     * Test of acquireAllItemsWithInventory method, of class VendingMachineService.
     */
    @Test
    public void testAcquireAllItemsWithInventory() throws Exception {
        List<Item> items = service.acquireAllItemsWithInventory();
        
        for (Item item : items) {
            if (item.getQuantity() == 0) {
                fail("Expected non-zero quantity.");
            }
        }
    }

    /**
     * Test of getUserMoney method, of class VendingMachineService.
     * 
     * No need to test this... It's used on other methods, and if it works
     * on other methods then it works.
     */
    
}
