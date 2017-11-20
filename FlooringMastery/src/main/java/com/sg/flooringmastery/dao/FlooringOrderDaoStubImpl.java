/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author tedis
 */
public class FlooringOrderDaoStubImpl implements FlooringOrderDao {
    
    private Order createdOrder;
    private Map<String, List<Order>> orders = new HashMap<>();
    
    public FlooringOrderDaoStubImpl() {
        String date = "09232017";
        
        this.createdOrder = new Order(1);
        createdOrder.setCustomerName("Jojo");
        createdOrder.setState("OH");
        createdOrder.setTaxRate(new BigDecimal("6.25"));
        createdOrder.setProductType("Wood");
        createdOrder.setArea(new BigDecimal("100.00"));
        createdOrder.setCostPerSquareFoot(new BigDecimal("5.15"));
        createdOrder.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        createdOrder.setMaterialCost(new BigDecimal("515.00"));
        createdOrder.setLaborCost(new BigDecimal("475.00"));
        createdOrder.setTax(new BigDecimal("61.88"));
        createdOrder.setTotal(new BigDecimal("1051.88"));
        
        List<Order> listOfOrders = new ArrayList<>();
        listOfOrders.add(createdOrder);
        
        this.orders.put(date, listOfOrders);
    }

    @Override
    public Order addOrder(String date, Order order) {
        List<Order> ordersOfDate = orders.get(date);
        
        for (int i = 0; i < ordersOfDate.size(); i++) {
            Order currentOrder = ordersOfDate.get(i);
            if (currentOrder.getOrderNumber() == order.getOrderNumber()) {
               return ordersOfDate.get(i);
            }
        }
        return null;
    }

    @Override
    public Order getOrderDateSpecified(String date, int orderNumber) {
        List<Order> ordersOfDate = orders.get(date);
        
        for (int i = 0; i < ordersOfDate.size(); i++) {
            Order order = ordersOfDate.get(i);
            if (order.getOrderNumber() == orderNumber) {
               return ordersOfDate.get(i);
            }
        }
        return null;
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
        for (int i = 0; i < ordersOfDate.size(); i++) {
            Order order = ordersOfDate.get(i);
            if (order.getOrderNumber() == orderNumber) {
                return ordersOfDate.get(i);
            }
        }
        return null;
    }

    @Override
    public boolean loadOrdersAndMode() throws FlooringPersistenceException, ModeException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeCurrentWork() throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
