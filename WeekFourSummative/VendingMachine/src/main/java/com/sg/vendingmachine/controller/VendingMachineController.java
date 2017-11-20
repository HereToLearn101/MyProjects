/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.controller;

import com.sg.vendingmachine.dao.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Change;
import com.sg.vendingmachine.dto.Item;
import com.sg.vendingmachine.service.exceptions.InsufficientFundsException;
import com.sg.vendingmachine.service.exceptions.InvalidUserChoiceException;
import com.sg.vendingmachine.service.exceptions.NoItemInventoryException;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.service.exceptions.WrongMoneyInputException;
import com.sg.vendingmachine.ui.VendingMachineView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author tedis
 */
public class VendingMachineController {

    private VendingMachineService service;
    private VendingMachineView view;

    public VendingMachineController(VendingMachineService service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        boolean hasErrors = true;

        view.displayWelcomeMsg();
        while (keepGoing) {

            try {
                String itemChoice = displayItemChoices();
                if (!leaveVM(itemChoice)) {
                    Item item = processItemChoice(itemChoice);

                    do {
                        try {
                            buyItem(item);
                            hasErrors = false;
                        } catch (InsufficientFundsException | WrongMoneyInputException e) {
                            view.displayErrorMessage(e.getMessage());
                            hasErrors = true;
                        } catch (NumberFormatException e) {
                            view.displayErrorMessage("ERROR: Please give me a currency"
                                    + " value on this format: x.xx | x | .xx");
                            hasErrors = true;
                        }
                    } while (hasErrors);

                } else {
                    keepGoing = false;
                    view.displayExitMsg();
                }

            } catch (VendingMachinePersistenceException | NoItemInventoryException 
                    | InvalidUserChoiceException e) {
                view.displayErrorMessage(e.getMessage());
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                view.displayErrorMessage("ERROR: Please give me a number associated"
                        + " with the item listed above, or type 'exit' if you want"
                        + " to leave.");
            }
        }

    }

    private void buyItem(Item item) throws VendingMachinePersistenceException, InsufficientFundsException,
            NoItemInventoryException, WrongMoneyInputException, NumberFormatException {
        String moneyOrExitInput = view.displayAskMoneyInputAndExitOption();
        
        if (!leaveVM(moneyOrExitInput)) {
            // .askMoneyInput() returns String and supplied to BigDecimal
            BigDecimal userMoneyOrExitOption = new BigDecimal(moneyOrExitInput);
            service.processPayment(userMoneyOrExitOption, item);

            view.displaySuccessBanner();
        }
        
        // .processChange class formats change to a whole number, not decimal form
        // and return a Change type instance to be supplied to .displayChange
        // method.
        Change changeBackToUser = service.processChange(service.getUserMoney());
        
        // .checkIfAllChangeIsZero checks the information in the Change class
        // if Quarters, Dimes, Nickels, and Pennies are equal to zero.
        // True if all info is zero, false if it's not.
        Boolean trueOrFalseZeroChange = service.checkIfAllChangeIsZero(changeBackToUser);
        view.displayChange(changeBackToUser, trueOrFalseZeroChange);
        // .finishProcessPayment() method makes sure that user money is set back
        // to zero after .displayChange method above is executed.
        service.finishProcessPayment();
        
        if (!leaveVM(moneyOrExitInput)) {
        // .updateItemInventory method subtracts item quantity
        service.updateItemInventory(item.getItemName(), item);
        }
        view.displayItemInventory(item);        
    }

    private String displayItemChoices() throws VendingMachinePersistenceException,
            NoItemInventoryException {
        List<Item> items = service.acquireAllItemsWithInventory();
        view.displayItemChoicesMsg();

        // When I use Maven Apache 3.5.0, the print display in .displayItems
        // method does not show first... I had to go back and use the bundled
        // Maven version
        return view.displayItems(items);
    }

    private Item processItemChoice(String choice) throws VendingMachinePersistenceException,
            NoItemInventoryException, InvalidUserChoiceException, ArrayIndexOutOfBoundsException {
        List<Item> itemsWithInventory = service.acquireAllItemsWithInventory();

        // Throws an InvalidUserChoiceException
        service.checkUserChoice(choice, itemsWithInventory);

        view.displayChosenItem(theItemChoice(choice, itemsWithInventory));

        // Set this method call just incase for NoItemInventoryException...
        service.checkItemInventory(theItemChoice(choice, itemsWithInventory));

        return theItemChoice(choice, itemsWithInventory);
    }

    // This method checkes to see if the value supplied in the parameter is
    // equal to "exit"
    private boolean leaveVM(String choice) {
        if (choice.toLowerCase().equals("exit")) {
            return true;
        }
        return false;
    }

    // Method to get the item, based on choice, using a list with non-zero
    // inventory value items
    private Item theItemChoice(String choice, List<Item> itemsWithInventory) throws
            ArrayIndexOutOfBoundsException {
        return itemsWithInventory.get(Integer.parseInt(choice) - 1);
    }
}
