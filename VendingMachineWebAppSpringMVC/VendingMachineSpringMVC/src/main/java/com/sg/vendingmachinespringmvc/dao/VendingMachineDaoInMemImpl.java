/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author tedis
 */
public class VendingMachineDaoInMemImpl implements VendingMachineDao {
    
    private Map<Integer, Item> items = new HashMap<>();
    private Change change = new Change();
    private static int itemIdCounter = 1;
    
    public VendingMachineDaoInMemImpl() {
        Item item1 = new Item();
        item1.setItemId(1);
        item1.setItemName("Snickers");
        item1.setPrice(new BigDecimal("1.50"));
        item1.setQuantity(10);
        
        items.put(item1.getItemId(), item1);
        
        Item item2 = new Item();
        item2.setItemId(2);
        item2.setItemName("M&M's");
        item2.setPrice(new BigDecimal("1.25"));
        item2.setQuantity(8);
        
        items.put(item2.getItemId(), item2);
        
        Item item3 = new Item();
        item3.setItemId(3);
        item3.setItemName("Almond Joy");
        item3.setPrice(new BigDecimal("1.25"));
        item3.setQuantity(11);
        
        items.put(item3.getItemId(), item3);
        
        Item item4 = new Item();
        item4.setItemId(4);
        item4.setItemName("Milky Way");
        item4.setPrice(new BigDecimal("1.65"));
        item4.setQuantity(3);
        
        items.put(item4.getItemId(), item4);
        
        Item item5 = new Item();
        item5.setItemId(5);
        item5.setItemName("Payday");
        item5.setPrice(new BigDecimal("1.75"));
        item5.setQuantity(2);
        
        items.put(item5.getItemId(), item5);
        
        Item item6 = new Item();
        item6.setItemId(6);
        item6.setItemName("Reese's");
        item6.setPrice(new BigDecimal("1.50"));
        item6.setQuantity(5);
        
        items.put(item6.getItemId(), item6);
        
        Item item7 = new Item();
        item7.setItemId(7);
        item7.setItemName("Pringles");
        item7.setPrice(new BigDecimal("2.35"));
        item7.setQuantity(4);
        
        items.put(item7.getItemId(), item7);
        
        Item item8 = new Item();
        item8.setItemId(8);
        item8.setItemName("Cheezits");
        item8.setPrice(new BigDecimal("2.00"));
        item8.setQuantity(6);
        
        items.put(item8.getItemId(), item8);
        
        Item item9 = new Item();
        item9.setItemId(9);
        item9.setItemName("Doritos");
        item9.setPrice(new BigDecimal("1.95"));
        item9.setQuantity(7);
        
        items.put(item9.getItemId(), item9);
    }

    @Override
    public Item addItem(Item item) {
        item.setItemId(itemIdCounter);
        items.put(item.getItemId(), item);
        itemIdCounter++;
        return item;
    }

    @Override
    public void removeItem(int itemId) {
        items.remove(itemId);
    }

    @Override
    public void updateItem(Item item) {
        items.put(item.getItemId(), item);
    }

    @Override
    public List<Item> getAllItems() {
        Collection<Item> cItems = items.values();
        return new ArrayList(cItems);
    }

    @Override
    public Item getItemById(int itemId) {
        return items.get(itemId);
    }
}
