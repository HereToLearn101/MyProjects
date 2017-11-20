/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.service.exceptions.VendingMachineDuplicateException;
import com.sg.vendingmachine.service.exceptions.VendingMachineDataValidationException;
import com.sg.vendingmachine.service.exceptions.NoItemInventoryException;
import com.sg.vendingmachine.service.exceptions.InvalidUserChoiceException;
import com.sg.vendingmachine.service.exceptions.WrongMoneyInputException;
import com.sg.vendingmachine.service.exceptions.InsufficientFundsException;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author tedis
 */
public interface VendingMachineService {
    
    void createItem(Item item) throws VendingMachinePersistenceException,
            VendingMachineDataValidationException,
            VendingMachineDuplicateException;
    
    List<Item> getAllItems() throws VendingMachinePersistenceException;
    
    //Item getItem(String itemName) throws VendingMachinePersistenceException;
    
    void updateItemInventory(String itemName, Item item) throws VendingMachinePersistenceException;
    
    void processPayment(BigDecimal moneyInput, Item item)
            throws InsufficientFundsException, VendingMachinePersistenceException,
            NoItemInventoryException, WrongMoneyInputException;
    
    void finishProcessPayment();
    
    Change processChange(BigDecimal changeInPennies);
    
    void checkItemInventory(Item item) throws NoItemInventoryException;
    
    void checkUserChoice(String userChoice, List<Item> itemsWithInventory) throws
            InvalidUserChoiceException, NumberFormatException;
    
    boolean checkIfAllChangeIsZero(Change change);
    
    List<Item> acquireAllItemsWithInventory() throws VendingMachinePersistenceException;
    
    BigDecimal getUserMoney();
}
