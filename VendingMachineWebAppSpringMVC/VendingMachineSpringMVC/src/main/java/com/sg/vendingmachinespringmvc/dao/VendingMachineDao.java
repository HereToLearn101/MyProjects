/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author tedis
 */
public interface VendingMachineDao {
    
    public Item addItem(Item item);
    
    public void removeItem(int itemId);
    
    public void updateItem(Item item);
    
    public List<Item> getAllItems();
    
    public Item getItemById(int itemId);
    
//    public void createChange(BigDecimal remain);
//    
//    public Change getChange();
}
