/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 *
 * @author tedis
 */
public interface FlooringOrderDao {
    Order addOrder(String date, Order order);
    
    Order removeOrderDateSpecified(String date, int order);
    
    void removeDate(String date);
    
    Order getOrderDateSpecified(String date, int orderNumber);
    
    List<Order> getAllOrdersDateSpecified(String date);
    
    Collection<List<Order>> getAllOrders();

    Set<String> getAllDates();
    
    void editOrder(String oldDate, String newDate, int orderNumber, Order order) throws FlooringPersistenceException;
    
    boolean loadOrdersAndMode() throws FlooringPersistenceException, ModeException;
    
    void writeCurrentWork() throws FlooringPersistenceException;
}