/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringPersistenceException;
import com.sg.flooringmastery.dao.ModeException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.service.exceptions.CustomerNameInputException;
import com.sg.flooringmastery.service.exceptions.ExitException;
import com.sg.flooringmastery.service.exceptions.OrderNumberInputException;
import com.sg.flooringmastery.service.exceptions.WrongCommitInputException;
import com.sg.flooringmastery.service.exceptions.WrongDateInputException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author tedis
 */
public interface FlooringService {
    List<Order> acquireAllOrdersDateSpecified(String date);
    
    boolean loadOrdersAndMode() throws FlooringPersistenceException, ModeException;
    
    Order acquireOrder(String date, int orderNumber);
    
    void createOrder(String date, Order order) throws FlooringPersistenceException;
    
    int createNewOrderNumber() throws FlooringPersistenceException;
    
    Order removeOrder(String date, int orderNumber);
    
    Tax acquireTax(String chosenState) throws FlooringPersistenceException;
    
    Product acquireProduct(String chosenProduct) throws FlooringPersistenceException;
    
    void changeOrder(String oldDate, String newDate, int orderNumber, Order order) throws FlooringPersistenceException;
    
    List<Tax> acquireAllTaxes() throws FlooringPersistenceException;
    
    List<Product> acquireAllProducts() throws FlooringPersistenceException;
    
    void save() throws FlooringPersistenceException;
    
    BigDecimal calculateMaterialCost(Order newOrder);
    
    BigDecimal calculateLaborCost(Order newOrder);
    
    BigDecimal calculateTax(Order newOrder, BigDecimal materialCost, BigDecimal laborCost);
    
    BigDecimal calculateTotal(BigDecimal materialCost, BigDecimal laborCost, BigDecimal totalTax);
    
    boolean continueCommit(String commitChoice) throws WrongCommitInputException;
    
    boolean changeData(String editInput);
    
    void checkingCustomerNameInput(String name) throws CustomerNameInputException;
    
    void checkingCustomerNameInputInEdit(String name) throws CustomerNameInputException;
    
    void checkingOrderInDate(List<Order> orders, int orderNumber, String date) throws FlooringPersistenceException;
    
    void checkingDateInput(String date) throws WrongDateInputException, FlooringPersistenceException;
    
    void checkingDateInputFormat(String date) throws WrongDateInputException;
    
    void checkingForExitInput(String input) throws ExitException;
    
    void checkingOrderNumberInput(String orderNumber) throws OrderNumberInputException;
}