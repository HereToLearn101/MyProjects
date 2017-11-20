/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

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
public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public void displayItemChoicesMsg() {
        io.print("Item Choices: ");
    }

    // Created this method just incase if I want to add an 'add option' to the
    // menu...
    public Item getNewItem() {
        Item newItems = new Item(io.readString("What new item would you like to add? "));
        newItems.setItemCost(io.readBigDecimal("Set cost for item: "));
        newItems.setQuantity(io.readInt("Stock number: "));

        return newItems;
    }

    // Display items choices dynamically
    public String displayItems(List<Item> listOfItemsWithInventory) {

        for (int i = 0; i < listOfItemsWithInventory.size(); i++) {
            Item item = listOfItemsWithInventory.get(i);
            io.print("'" + (i + 1) + "'" + ": " + item.getItemName() + " $" + item.getItemCost());
        }

        io.print("'Exit': To Leave");

        return io.readString("Please choose an option listed above -> ");
    }

    public void displayChosenItem(Item theItem) {
        io.print("You have chosen " + theItem.getItemName() + ".");
        io.print("");
    }
    

    // Ask user money input
    public String displayAskMoneyInputAndExitOption() {
        
        return io.readString("Please put some money into the machine or 'exit' "
                + "to leave: ");
    }

    public void displayItemInventory(Item item) {
        io.readString("There are only " + item.getQuantity() + " of " + item.getItemName() + " left. Press enter to continue...");
    }

    public void displayChange(Change change, boolean trueOrFalseZeroChange) {
        io.print("");       
        if (trueOrFalseZeroChange) {
            io.print("------------------------------- THANK YOU -------------------------------");
            io.print("");
            io.print("$0.00 change back.");
            io.print("");
        } else {
            io.print("------------------------ THANK YOU, YOUR CHANGE -------------------------");
            io.print("Quarter(s): " + change.getQuarters());
            io.print("Dime(s): " + change.getDimes());
            io.print("Nickel(s): " + change.getNickels());
            io.print("Pennies: " + change.getPennies());
        }
    }

    public void displaySuccessBanner() {
        io.print("Purchase was a success.");
    }

    public void displayWelcomeMsg() {
        io.print(" __      __            _ _               __  __            _     _            \n"
                + " \\ \\    / /           | (_)             |  \\/  |          | |   (_)           \n"
                + "  \\ \\  / /__ _ __   __| |_ _ __   __ _  | \\  / | __ _  ___| |__  _ _ __   ___ \n"
                + "   \\ \\/ / _ \\ '_ \\ / _` | | '_ \\ / _` | | |\\/| |/ _` |/ __| '_ \\| | '_ \\ / _ \\\n"
                + "    \\  /  __/ | | | (_| | | | | | (_| | | |  | | (_| | (__| | | | | | | |  __/\n"
                + "     \\/ \\___|_| |_|\\__,_|_|_| |_|\\__, | |_|  |_|\\__,_|\\___|_| |_|_|_| |_|\\___|\n"
                + "                                  __/ |                                       \n"
                + "                                 |___/                                        ");
    }
    
    public void displayMsg(String msg) {
        io.print(msg);
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("---------------------------------- ERROR --------------------------------- \n" + errorMsg);
        io.readString("Press enter to continue...");
    }

    public void displayExitMsg() {
        io.print("   _____  ____   ____  _____  ______     ________ _ \n" +
"  / ____|/ __ \\ / __ \\|  __ \\|  _ \\ \\   / /  ____| |\n" +
" | |  __| |  | | |  | | |  | | |_) \\ \\_/ /| |__  | |\n" +
" | | |_ | |  | | |  | | |  | |  _ < \\   / |  __| | |\n" +
" | |__| | |__| | |__| | |__| | |_) | | |  | |____|_|\n" +
"  \\_____|\\____/ \\____/|_____/|____/  |_|  |______(_)\n" +
"                                                    \n" +
"                                                    ");
    }
}
