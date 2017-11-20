/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tedis
 */
public class VendingMachineDaoTest {
    
    private VendingMachineDao vMDao = new VendingMachineDaoFileImpl();
    
    public VendingMachineDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception {
        List<Item> items = vMDao.getAllItems();
        for (Item item : items) {
            vMDao.removeItem(item.getItemName());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addItem method, of class VendingMachineDao.
     */
    @Test
    public void testAddGetItem() throws Exception {
        Item item = new Item("Twix");
        item.setItemCost(new BigDecimal("1.00"));
        item.setQuantity(10);
        
        vMDao.addItem(item.getItemName(), item);
        
        Item fromDao = vMDao.getItem(item.getItemName());
        
        assertEquals(item, fromDao);
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItems() throws Exception {
        Item itemOne = new Item("KitKat");
        itemOne.setItemCost(new BigDecimal("1.00"));
        itemOne.setQuantity(10);
        
        vMDao.addItem(itemOne.getItemName(), itemOne);
        
        Item itemTwo = new Item("Twix");
        itemTwo.setItemCost(new BigDecimal("2.00"));
        itemTwo.setQuantity(20);
        
        vMDao.addItem(itemTwo.getItemName(), itemTwo);
        
        assertEquals(2, vMDao.getAllItems().size());
    }

    /**
     * Test of getItem method, of class VendingMachineDao.
     * 
     * No need to do this if testAddGetItem method works. The method .getItem is
     * already being used there.
     */
    

    /**
     * Test of removeItem method, of class VendingMachineDao.
     */
    @Test
    public void testRemoveItem() throws Exception {
        Item itemOne = new Item("KitKat");
        itemOne.setItemCost(new BigDecimal("1.00"));
        itemOne.setQuantity(10);
        
        vMDao.addItem(itemOne.getItemName(), itemOne);
        
        Item itemTwo = new Item("Twix");
        itemTwo.setItemCost(new BigDecimal("2.00"));
        itemTwo.setQuantity(20);
        
        vMDao.addItem(itemTwo.getItemName(), itemTwo);
               
        //Item itemShouldNotExist = vMDao.getItem("KitKat");
        vMDao.removeItem("KitKat");
        assertEquals(1, vMDao.getAllItems().size());
        assertNull(vMDao.getItem("KitKat"));
        
        vMDao.removeItem("Twix");
        assertEquals(0, vMDao.getAllItems().size());
        assertNull(vMDao.getItem("Twix"));
    }

    /**
     * Test of editItem method, of class VendingMachineDao.
     */
    @Test
    public void testEditItem() throws Exception {
        Item itemOne = new Item("KitKat");
        itemOne.setItemCost(new BigDecimal("1.00"));
        itemOne.setQuantity(10);
        
        vMDao.addItem(itemOne.getItemName(), itemOne);
        
        Item itemTwo = new Item("KitKat");
        itemTwo.setItemCost(new BigDecimal("2.00"));
        itemTwo.setQuantity(20);
        
        vMDao.editItem(itemOne.getItemName(), itemTwo);
        
        Item itemToCheck = vMDao.getItem("KitKat");
        
        assertEquals(itemTwo, itemToCheck);
    }

    /**
     * Test of getItemsWithInventory method, of class VendingMachineDao.
     */
    @Test
    public void testGetItemsWithInventory() throws Exception {
        Item itemOne = new Item("KitKat");
        itemOne.setItemCost(new BigDecimal("1.00"));
        itemOne.setQuantity(10);
        
        vMDao.addItem(itemOne.getItemName(), itemOne);
        
        Item itemTwo = new Item("Twix");
        itemTwo.setItemCost(new BigDecimal("1.00"));
        itemTwo.setQuantity(20);
        
        vMDao.addItem(itemTwo.getItemName(), itemTwo);
        
        Item itemThree = new Item("Snickers");
        itemThree.setItemCost(new BigDecimal("1.00"));
        itemThree.setQuantity(10);
        
        vMDao.addItem(itemThree.getItemName(), itemThree);
        
        Item itemFour = new Item("Skittles");
        itemFour.setItemCost(new BigDecimal("1.00"));
        itemFour.setQuantity(0);
        
        vMDao.addItem(itemFour.getItemName(), itemFour);
        
        List<Item> listOfItemsWithInv = vMDao.getItemsWithInventory();
        // created a new list object to hold a string of item names (below, I used
        // it to sort the names alphabetically).
        List<String> listOfItemsByName = new ArrayList<>();
        
        for (Item item : listOfItemsWithInv) {
            listOfItemsByName.add(item.getItemName());
        }
        
        // To a sort a list alphabetically
        Collections.sort(listOfItemsByName);
        
        assertEquals(3, listOfItemsWithInv.size());
        
        assertEquals(itemOne.getItemName(), listOfItemsByName.get(0));
        assertEquals(itemThree.getItemName(), listOfItemsByName.get(1));
        assertEquals(itemTwo.getItemName(), listOfItemsByName.get(2));
        
        assertEquals(false, listOfItemsByName.contains("Skittles"));
    }
    
}
