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
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tedis
 */
public class VendingMachineServiceImpl implements VendingMachineService {

    private VendingMachineDao dao;
    private BigDecimal userMoney = new BigDecimal("0.00");

    public VendingMachineServiceImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public void createItem(Item item) throws VendingMachinePersistenceException,
            VendingMachineDataValidationException, VendingMachineDuplicateException {
        if (dao.getItem(item.getItemName()) != null) {
            throw new VendingMachineDuplicateException("Item "
                    + item.getItemName() + " already exists.");
        }

        validateItemData(item);

        dao.addItem(item.getItemName(), item);
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException {
        return dao.getAllItems();
    }

//    @Override
//    public Item getItem(String itemName) throws VendingMachinePersistenceException {
//        return dao.getItem(itemName);
//    }
    
    @Override
    public void updateItemInventory(String itemName, Item item) throws VendingMachinePersistenceException {
        item.setQuantity(item.getQuantity() - 1);
        dao.editItem(itemName, item);
    }

    // Pass to the Change class constructor
    @Override
    public Change processChange(BigDecimal change) {
//        BigDecimal bgChangeDue = new BigDecimal(changeDue);
//        bgChangeDue.multiply(new BigDecimal(100));
//        return bgChangeDue.multiply(new BigDecimal(100)).intValue();

        BigDecimal toPennies = change.multiply(new BigDecimal(100));

        BigDecimal setScaleOfToPennies = toPennies.setScale(0);
        Change changeBackToUser = new Change(setScaleOfToPennies);
        
        return changeBackToUser;
    }

    // Process item: update user's money, validate funds, and subtract item cost
    // if validateFunds method has been passed.
    @Override
    public void processPayment(BigDecimal moneyInput, Item item) throws InsufficientFundsException,
            VendingMachinePersistenceException, NoItemInventoryException, WrongMoneyInputException {
        validateMoneyInput(moneyInput);
        //this.userMoney = moneyInput;
        this.userMoney = this.userMoney.add(moneyInput);

        validateFunds(item);

        this.userMoney = this.userMoney.subtract(item.getItemCost());
        //return this.userMoney;
    }

    // Setting userMoney to 0.
    @Override
    public void finishProcessPayment() {
        this.userMoney = new BigDecimal("0.00");
    }
    
    @Override
    public void checkItemInventory(Item item) throws NoItemInventoryException {
        validateItemInventory(item);
    }
    
    @Override
    public void checkUserChoice(String userChoice, List<Item> itemsWithInventory) throws
            InvalidUserChoiceException, NumberFormatException{
        validateUserChoice(userChoice, itemsWithInventory);
    }
    
    @Override
    public boolean checkIfAllChangeIsZero(Change change) {
        if ((change.getQuarters() == 0) && (change.getDimes() == 0) && 
                (change.getNickels() == 0) && (change.getPennies() == 0)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Item> acquireAllItemsWithInventory() throws VendingMachinePersistenceException {
        return dao.getItemsWithInventory();
    }
    
    @Override
    public BigDecimal getUserMoney() {
        return this.userMoney;
    }

    // Created this validation just incase if I wanted to add an option of 'Add Item'
    // to the Vending Machine application
    private void validateItemData(Item item) throws VendingMachineDataValidationException {
        if (item.getItemName() == null
                || item.getItemName().trim().length() == 0
                || item.getItemCost().equals(new BigDecimal(0))
                || item.getQuantity().equals(0)) {

            throw new VendingMachineDataValidationException("All fields"
                    + " are required! \n"
                    + "Make sure... \n"
                    + "Item name is not null/empty \n"
                    + "Item cost is not null/0 \n"
                    + "Number of Items is not null/0");
        }
    }

    private void validateFunds(Item item) throws InsufficientFundsException {
        if (item.getItemCost().compareTo(userMoney) == 1) {
            throw new InsufficientFundsException("NOT ENOUGH! "
                    + item.getItemName() + " costs $" + item.getItemCost() + ". "
                    + "You input a total of $" + userMoney + ". \nPlease add $"
                    + (item.getItemCost().subtract(userMoney)) + " or more!");
        }
    }

    // Created this just incase....
    private void validateItemInventory(Item item) throws NoItemInventoryException {
        if (item.getQuantity() == 0) {
            throw new NoItemInventoryException("No more in stock! Choose a different item.");
        }
    }

    private void validateMoneyInput(BigDecimal moneyInput) throws WrongMoneyInputException {
        BigDecimal setScaleMoneyInput = moneyInput.setScale(2, RoundingMode.UP);

        if (moneyInput.compareTo(setScaleMoneyInput) != 0) {
            throw new WrongMoneyInputException("Please give me a input in this "
                    + "format: x.xx | x | .xx");
        }
    }
    
    private void validateUserChoice(String userChoice, List<Item> itemsWithInventory) throws
            InvalidUserChoiceException, NumberFormatException{
        //int userChoiceInInt = Integer.parseInt(userChoice);
        if (Integer.parseInt(userChoice) < 0 || (Integer.parseInt(userChoice) > (itemsWithInventory.size())) ) {
            throw new InvalidUserChoiceException("No choice exists! Please give me a number"
                    + " associated with the item, or type 'exit' if you want"
                    + "to leave.");
        } 
//        else {
//            throw new NumberFormatException("Give me a number input from the choices listed above!");
//        }
    }
}
