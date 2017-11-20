/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.util.List;

/**
 *
 * @author tedis
 */
public interface VendingMachineDao {
    
    Item addItem(String itemName, Item item) throws VendingMachinePersistenceException;
    
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    Item getItem(String itemName) throws VendingMachinePersistenceException;
    
    Item removeItem(String itemName) throws VendingMachinePersistenceException;
    
    void editItem(String itemName, Item item) throws VendingMachinePersistenceException;
    
    List<Item> getItemsWithInventory() throws VendingMachinePersistenceException;
}
