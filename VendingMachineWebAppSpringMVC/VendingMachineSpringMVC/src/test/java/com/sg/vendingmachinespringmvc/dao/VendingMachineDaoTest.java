/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author tedis
 */
public class VendingMachineDaoTest {
    
    VendingMachineDao dao;
    
    public VendingMachineDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("vendingMachineDao", VendingMachineDao.class);
        
        List<Item> items = dao.getAllItems();
        for (Item item : items) {
            dao.removeItem(item.getItemId());
        }
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addItem method and getItemById method, of class VendingMachineDao.
     */
    @Test
    public void testAddItemAndGetItemById() {
        Item item = new Item();
        item.setItemId(1);
        item.setItemName("Twix");
        item.setPrice(new BigDecimal("1.00"));
        item.setQuantity(10);
        
        dao.addItem(item);
        
        Item currentItem = dao.getItemById(item.getItemId());
        
        assertEquals(item, currentItem);
    }

    /**
     * Test of removeItem method, of class VendingMachineDao.
     */
    @Test
    public void testRemoveItem() {
        Item item1 = new Item();
        item1.setItemId(1);
        item1.setItemName("Twix");
        item1.setPrice(new BigDecimal("1.00"));
        item1.setQuantity(10);
        
        dao.addItem(item1);
        
        Item item2 = new Item();
        item2.setItemId(2);
        item2.setItemName("KitKat");
        item2.setPrice(new BigDecimal("1.00"));
        item2.setQuantity(10);
        
        dao.addItem(item2);
        
        dao.removeItem(item1.getItemId());
        
        assertEquals(1, dao.getAllItems().size());
    }

    /**
     * Test of updateItem method, of class VendingMachineDao.
     */
    @Test
    public void testUpdateItem() {
        Item item1 = new Item();
        item1.setItemId(1);
        item1.setItemName("Twix");
        item1.setPrice(new BigDecimal("1.00"));
        item1.setQuantity(10);
        
        dao.addItem(item1);
        
        Item itemToUpdate = dao.getItemById(item1.getItemId());
        itemToUpdate.setItemName("KitKat");
        
        dao.updateItem(itemToUpdate);
        
        Item itemToCheck = dao.getItemById(itemToUpdate.getItemId());
        
        assertEquals(itemToUpdate, itemToCheck);
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllItems() {
        Item item1 = new Item();
        item1.setItemId(1);
        item1.setItemName("Twix");
        item1.setPrice(new BigDecimal("1.00"));
        item1.setQuantity(10);
        
        dao.addItem(item1);
        
        Item item2 = new Item();
        item2.setItemId(2);
        item2.setItemName("KitKat");
        item2.setPrice(new BigDecimal("1.00"));
        item2.setQuantity(10);
        
        dao.addItem(item2);
        
        List<Item> items = dao.getAllItems();
        
        assertEquals(2, items.size());
    }

    /**
     * Test of getItemById method, of class VendingMachineDao.
     * 
     * Already tested above
     */
    
}
