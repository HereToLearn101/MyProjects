/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author tedis
 */
public class FlooringTaxDaoImpl implements FlooringTaxDao {
    private Map<String, Tax> taxes = new HashMap<>();
    private static final String TAXES_FILE = "Taxes.txt";
    private static final String DELIMITER = ",";

    @Override
    public Tax addTax(Tax tax) throws FlooringPersistenceException {
        loadAllTaxes();
        Tax newTax = taxes.put(tax.getState(), tax);
        writeTaxes();
        return newTax;
    }

    @Override
    public Tax removeTax(String state) throws FlooringPersistenceException {
        loadAllTaxes();
        Tax removedTax = taxes.remove(state);
        writeTaxes();
        return removedTax;
    }

    @Override
    public Tax getTax(String state) throws FlooringPersistenceException {
        loadAllTaxes();
        return taxes.get(state);
    }

    @Override
    public List<Tax> getAllTaxes() throws FlooringPersistenceException {
        loadAllTaxes();
        return new ArrayList<Tax>(taxes.values());
    }

    @Override
    public void editTax(String oldTax, Tax updatedTax) throws FlooringPersistenceException {
        removeTax(oldTax);
        addTax(updatedTax);
    }

    private void loadAllTaxes() throws FlooringPersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(TAXES_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException(
                    "-_- Could not load tax data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentTokens holds each of the parts of the currentLine after it has
        // been split on our DELIMITER
        String[] currentTokens;
        // Go through TAXES_FILE line by line, decoding each line into a 
        // Tax object.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create a new Tax object and put it into the map of Taxes
            Tax currentTax = new Tax(currentTokens[0]);
            // Set the remaining vlaues on currentTax manually
            currentTax.setTaxRate(new BigDecimal(currentTokens[1]));

            // Put currentTax into the map using order number as the key
            taxes.put(currentTax.getState(), currentTax);
        }
        // close scanner
        scanner.close();
    }
    
    private void writeTaxes() throws FlooringPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(TAXES_FILE));
        } catch (IOException e) {
            throw new FlooringPersistenceException(
                    "Could not save tax data.", e);
        }

        // Write out the Item objects to the items file.
        List<Tax> taxesList = this.getAllTaxes();
        for (Tax currentTax : taxesList) {
            // write the Item object to the file
            out.println(currentTax.getState() + DELIMITER
                    + currentTax.getTaxRate());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
