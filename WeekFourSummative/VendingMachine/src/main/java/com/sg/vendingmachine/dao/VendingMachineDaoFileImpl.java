/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dto.Item;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;

/**
 *
 * @author tedis
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<String, Item> items = new HashMap<>();
    private static final String ITEMS_FILE = "items.txt";
    private static final String DELIMITER = "::";

    @Override
    public Item addItem(String itemName, Item item) throws VendingMachinePersistenceException{
        loadItemsLibrary();
        Item newItem = items.put(itemName, item);
        writeItemsLibrary();
        return newItem;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachinePersistenceException{
        loadItemsLibrary();
        return new ArrayList<Item>(items.values());
    }

    @Override
    public Item getItem(String itemName) throws VendingMachinePersistenceException{
        // Remember that the value of the key is the Item object that holds all
        // the item information.
        loadItemsLibrary();
        return items.get(itemName);
    }

    @Override
    public Item removeItem(String itemName) throws VendingMachinePersistenceException{
        loadItemsLibrary();
        Item removedItem = items.remove(itemName);
        writeItemsLibrary();
        return removedItem;
    }
    
    @Override
    public void editItem(String oldItemName, Item item) throws VendingMachinePersistenceException{
        removeItem(oldItemName);
        addItem(item.getItemName(), item);
    }
    
    // This method creates a list of non-zero quantity items
    @Override
    public List<Item> getItemsWithInventory() throws VendingMachinePersistenceException {
        List<Item> items = getAllItems();
        List<Item> itemsWithInventory = new ArrayList<>();

        for (Item item : items) {
            if (item.getQuantity() != 0) {
                itemsWithInventory.add(item);
            }
        }

        return itemsWithInventory;
    }

    private void loadItemsLibrary() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(ITEMS_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load dvd library data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentTokens holds each of the parts of the currentLine after it has
        // been split on our DELIMITER
        String[] currentTokens;
        // Go through ITEMS_FILE line by line, decoding each line into a 
        // Item object.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create a new Item object and put it into the map of Items
            Item currentItem = new Item(currentTokens[0]);
            // Set the remaining vlaues on currentItem manually
            currentItem.setItemCost(new BigDecimal(currentTokens[1]));
            currentItem.setQuantity(Integer.parseInt(currentTokens[2]));

            // Put currentItem into the map using item name as the key
            items.put(currentItem.getItemName(), currentItem);
        }
        // close scanner
        scanner.close();
    }

    /**
     * Writes all items out to a ITEMS_FILE.
     *
     * @throws VendingMachinePersistenceException if an error occurs writing to the file
     */
    private void writeItemsLibrary() throws VendingMachinePersistenceException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(ITEMS_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save dvd data.", e);
        }

        // Write out the Item objects to the items file.
        List<Item> itemsList = this.getAllItems();
        for (Item currentItem : itemsList) {
            // write the Item object to the file
            out.println(currentItem.getItemName() + DELIMITER
                    + currentItem.getItemCost() + DELIMITER
                    + currentItem.getQuantity());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
