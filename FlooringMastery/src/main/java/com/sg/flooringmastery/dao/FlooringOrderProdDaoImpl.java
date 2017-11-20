/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author tedis
 */
public class FlooringOrderProdDaoImpl implements FlooringOrderDao {

    private Map<String, List<Order>> orders = new HashMap<>();
    private static final String MODE_FILE = "mode.txt";
    private static final String DELIMITER = ",";

    @Override
    public Order addOrder(String date, Order order) {
        List<Order> listOfOrders;
        if (orders.containsKey(date)) {
            // this gets a reference the list object of the date key?
            listOfOrders = orders.get(date);
            // automatically adds to the list that it references to?
            // no need to 'orders.put(date, listOfOrders)'?
            listOfOrders.add(order);
        } else {
            listOfOrders = new ArrayList<>();
            listOfOrders.add(order);
            orders.put(date, listOfOrders);
        }
        return order;
    }

    @Override
    public Order getOrderDateSpecified(String date, int orderNumber) {
        List<Order> ordersOfDate = orders.get(date);
        int positionOfOrder = 0;
        for (int i = 0; i < ordersOfDate.size(); i++) {
            Order order = ordersOfDate.get(i);
            if (order.getOrderNumber() == orderNumber) {
                positionOfOrder = i;
            }
        }
        return ordersOfDate.get(positionOfOrder);
    }

    @Override
    public List<Order> getAllOrdersDateSpecified(String date) {
        return new ArrayList<Order>(orders.get(date));
    }
    
    @Override
    public Collection<List<Order>> getAllOrders() {
        return orders.values();
    }

    @Override
    public Set<String> getAllDates() {
        return orders.keySet();
    }

    @Override
    public void editOrder(String oldDate, String newDate, int orderNumber, Order order) throws FlooringPersistenceException {
        removeOrderDateSpecified(oldDate, orderNumber);
        addOrder(newDate, order);
    }
    
    @Override
    public void removeDate(String date) {
        orders.remove(date);
    }
    
    @Override
    public Order removeOrderDateSpecified(String date, int orderNumber) {
        List<Order> ordersOfDate = orders.get(date);
        int positionOfOrder = 0;
        for (int i = 0; i < ordersOfDate.size(); i++) {
            Order order = ordersOfDate.get(i);
            if (order.getOrderNumber() == orderNumber) {
                positionOfOrder = i;
            }
        }
        return ordersOfDate.remove(positionOfOrder);
    }

    @Override
    public boolean loadOrdersAndMode() throws FlooringPersistenceException, ModeException {
        loadAllOrders();
        return loadMode();
    }

    @Override
    public void writeCurrentWork() throws FlooringPersistenceException {
        for (String date : orders.keySet()) {
            writeOrder(date);
        }
    }

    private void writeOrder(String date) throws FlooringPersistenceException {
        PrintWriter out;

        String fileName = "Orders_" + date + ".txt";

        try {
            out = new PrintWriter(new FileWriter(fileName));
        } catch (IOException e) {
            throw new FlooringPersistenceException(
                    "Could not save order data.", e);
        }

        // Write out the Order objects to the items file.
        List<Order> ordersList = new ArrayList<>(orders.get(date));
        for (Order currentOrder : ordersList) {
            // write the Order object to the file
            out.println(currentOrder.getOrderNumber() + DELIMITER
                    + currentOrder.getCustomerName() + DELIMITER
                    + currentOrder.getState() + DELIMITER
                    + currentOrder.getTaxRate() + DELIMITER
                    + currentOrder.getProductType() + DELIMITER
                    + currentOrder.getArea() + DELIMITER
                    + currentOrder.getCostPerSquareFoot() + DELIMITER
                    + currentOrder.getLaborCostPerSquareFoot() + DELIMITER
                    + currentOrder.getMaterialCost() + DELIMITER
                    + currentOrder.getLaborCost() + DELIMITER
                    + currentOrder.getTax() + DELIMITER
                    + currentOrder.getTotal());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }

    private void loadAllOrders() throws FlooringPersistenceException {
        Scanner scanner;
        String date;

        File directory = new File("C:\\Users\\tedis\\Documents\\SoftwareGuild\\FlooringMasteryChanges\\FlooringMasteryWithBigDecimal");
        File[] listOfFiles = directory.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.getName().contains("Orders_")) {
                    // grabs the date part of the whole file name
                    date = file.getName().substring(7, 15);
                    try {
                        // Create Scanner for reading the file
                        scanner = new Scanner(new BufferedReader(new FileReader(file.getName())));
                    } catch (FileNotFoundException e) {
                        throw new FlooringPersistenceException(
                                "-_- Could not load order data into memory.", e);
                    }
                    // currentLine holds the most recent line read from the file
                    String currentLine;
                    // currentTokens holds each of the parts of the currentLine after it has
                    // been split on our DELIMITER
                    String[] currentTokens;
                    // Go through ORDERS_FILE line by line, decoding each line into a 
                    // Order object.
                    // Process while we have more lines in the file
                    List<Order> listOfOrders = new ArrayList<>();
                    while (scanner.hasNextLine()) {
                        // get the next line in the file
                        currentLine = scanner.nextLine();
                        // break up the line into tokens
                        currentTokens = currentLine.split(DELIMITER);
                        // Create a new Order object and put it into the map of Orders
                        Order currentOrder = new Order(Integer.parseInt(currentTokens[0]));
                        // Set the remaining vlaues on currentOrder manually
                        currentOrder.setCustomerName(currentTokens[1]);
                        currentOrder.setState(currentTokens[2]);
                        currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
                        currentOrder.setProductType(currentTokens[4]);
                        currentOrder.setArea(new BigDecimal(currentTokens[5]));
                        currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
                        currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
                        currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
                        currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
                        currentOrder.setTax(new BigDecimal(currentTokens[10]));
                        currentOrder.setTotal(new BigDecimal(currentTokens[11]));

                        // Put currentOrder into the map using order number as the key
                        listOfOrders.add(currentOrder);
                    }
                    orders.put(date, listOfOrders);
                    // close scanner
                    scanner.close();
                }
            }
        }
    }
    
    private boolean loadMode() throws FlooringPersistenceException, ModeException {
        Scanner scanner;
        String mode;
        
        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(MODE_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException(
                    "-_- Could not load dvd library data into memory.", e);
        }
        
        mode = scanner.nextLine();
        
        if (mode.toLowerCase().equals("production")) {
            return true;
        } else if (mode.toLowerCase().equals("training")) {
            return false;
        } else {
            throw new ModeException("Mode is unrecognizable! Please change it "
                    + "to either 'Production' or 'Training' mode...");
        }
    }
}