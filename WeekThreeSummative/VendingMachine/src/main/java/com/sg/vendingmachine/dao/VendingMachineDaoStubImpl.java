/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tedis
 */
public class VendingMachineDaoStubImpl implements VendingMachineDao {
    
    private Item item;
    private List<Item> listOfItems = new ArrayList<>();
    
    public VendingMachineDaoStubImpl () {
        this.item = new Item("KitKat");
        item.setItemCost(new BigDecimal("1.00"));
        item.setQuantity(10);
        
        this.listOfItems.add(item);
    }
    @Override
    public Item addItem(String itemName, Item item) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item getItem(String itemName) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item removeItem(String itemName) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editItem(String itemName, Item item) throws VendingMachinePersistenceException {
        listOfItems.remove(itemName);
        listOfItems.add(item);
    }

    @Override
    public List<Item> getItemsWithInventory() throws VendingMachinePersistenceException {
        return listOfItems;
    }
    
}
