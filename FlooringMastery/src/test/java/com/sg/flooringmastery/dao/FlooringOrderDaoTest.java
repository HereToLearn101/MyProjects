/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Order;
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author tedis
 */
public class FlooringOrderDaoTest {

    private FlooringOrderDao dao = new FlooringOrderProdDaoImpl();

    public FlooringOrderDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        // No need need for this because the map starts out empty.
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addOrder and getAllOrdersDateSpecified method, of class
     * FlooringOrderDao.
     */
    @Test
    public void testAddOrderAndGetAllOrdersDateSpecified() {
        String date = "09232017";
        String date2 = "09242017";

        Order order = new Order(1);
        order.setCustomerName("Ted");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setTax(new BigDecimal("61.88"));
        order.setTotal(new BigDecimal("1051.88"));

        dao.addOrder(date, order);
        
        Order orderOne = dao.getOrderDateSpecified(date, order.getOrderNumber());
        
        assertEquals(order, orderOne);

        Order order2 = new Order(2);
        order2.setCustomerName("Tasha");
        order2.setState("OH");
        order2.setTaxRate(new BigDecimal("6.25"));
        order2.setProductType("Exotic Halosium");
        order2.setArea(new BigDecimal("197.00"));
        order2.setCostPerSquareFoot(new BigDecimal("81.00"));
        order2.setLaborCostPerSquareFoot(new BigDecimal("7.00"));
        order2.setMaterialCost(new BigDecimal("15957.00"));
        order2.setLaborCost(new BigDecimal("1379.00"));
        order2.setTax(new BigDecimal("1083.50"));
        order2.setTotal(new BigDecimal("18419.50"));

        dao.addOrder(date, order2);
        
        Order orderTwo = dao.getOrderDateSpecified(date, order2.getOrderNumber());
        
        assertEquals(order2, orderTwo);

        Order order3 = new Order(3);
        order.setCustomerName("Ted");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setTax(new BigDecimal("61.88"));
        order.setTotal(new BigDecimal("1051.88"));

        dao.addOrder(date2, order3);

        List<Order> listOfOrders = dao.getAllOrdersDateSpecified(date);
        List<Order> listOfOrders2 = dao.getAllOrdersDateSpecified(date2);

        assertEquals(2, listOfOrders.size());
        assertEquals(1, listOfOrders2.size());
    }

    /**
     * Test of removeOrderDateSpecified method, of class FlooringOrderDao.
     */
    @Test
    public void testRemoveOrderDateSpecified() {
        String date = "09232017";

        Order order = new Order(1);
        order.setCustomerName("Ted");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setTax(new BigDecimal("61.88"));
        order.setTotal(new BigDecimal("1051.88"));

        dao.addOrder(date, order);

        Order order2 = new Order(2);
        order2.setCustomerName("Tasha");
        order2.setState("OH");
        order2.setTaxRate(new BigDecimal("6.25"));
        order2.setProductType("Exotic Halosium");
        order2.setArea(new BigDecimal("197.00"));
        order2.setCostPerSquareFoot(new BigDecimal("81.00"));
        order2.setLaborCostPerSquareFoot(new BigDecimal("7.00"));
        order2.setMaterialCost(new BigDecimal("15957.00"));
        order2.setLaborCost(new BigDecimal("1379.00"));
        order2.setTax(new BigDecimal("1083.50"));
        order2.setTotal(new BigDecimal("18419.50"));

        dao.addOrder(date, order2);

        dao.removeOrderDateSpecified(date, 1);
        List<Order> listOfOrders = dao.getAllOrdersDateSpecified(date);

        assertEquals(1, listOfOrders.size());
    }

    /**
     * Test of removeDate method, of class FlooringOrderDao.
     */
    @Test
    public void testRemoveDate() {
        String date = "09232017";

        Order order = new Order(1);
        order.setCustomerName("Ted");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setTax(new BigDecimal("61.88"));
        order.setTotal(new BigDecimal("1051.88"));

        dao.addOrder(date, order);
        dao.removeDate(date);

        Set<String> dates = dao.getAllDates();

        assertFalse(dates.contains(dao));
    }

    /**
     * Test of getOrderDateSpecified method, of class FlooringOrderDao.
     */
    @Test
    public void testGetOrderDateSpecified() {
        String date = "09232017";

        Order order = new Order(1);
        order.setCustomerName("Ted");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setTax(new BigDecimal("61.88"));
        order.setTotal(new BigDecimal("1051.88"));

        dao.addOrder(date, order);

        Order currentOrder = dao.getOrderDateSpecified(date, 1);

        assertEquals(order.getOrderNumber(), currentOrder.getOrderNumber());
    }

    /**
     * Test of getAllOrders method, of class FlooringOrderDao.
     */
    @Test
    public void testGetAllOrders() {
        String date = "09232017";
        String date2 = "09242017";

        Order order = new Order(1);
        order.setCustomerName("Ted");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setTax(new BigDecimal("61.88"));
        order.setTotal(new BigDecimal("1051.88"));

        dao.addOrder(date, order);

        Order order2 = new Order(2);
        order2.setCustomerName("Tasha");
        order2.setState("OH");
        order2.setTaxRate(new BigDecimal("6.25"));
        order2.setProductType("Exotic Halosium");
        order2.setArea(new BigDecimal("197.00"));
        order2.setCostPerSquareFoot(new BigDecimal("81.00"));
        order2.setLaborCostPerSquareFoot(new BigDecimal("7.00"));
        order2.setMaterialCost(new BigDecimal("15957.00"));
        order2.setLaborCost(new BigDecimal("1379.00"));
        order2.setTax(new BigDecimal("1083.50"));
        order2.setTotal(new BigDecimal("18419.50"));

        dao.addOrder(date, order2);

        Order order3 = new Order(3);
        order.setCustomerName("Ted");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setTax(new BigDecimal("61.88"));
        order.setTotal(new BigDecimal("1051.88"));

        dao.addOrder(date2, order3);

        Collection<List<Order>> collection = dao.getAllOrders();
        List<Order> numberOfOrderInList = new ArrayList<>();

        for (List<Order> orders : collection) {
            for (Order orderInList : orders) {
                numberOfOrderInList.add(orderInList);
            }
        }

        assertEquals(3, numberOfOrderInList.size());
    }

    /**
     * Test of getAllDates method, of class FlooringOrderDao.
     */
    @Test
    public void testGetAllDates() {
        String date = "09232017";
        String date2 = "09242017";

        Order order = new Order(1);
        order.setCustomerName("Ted");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setTax(new BigDecimal("61.88"));
        order.setTotal(new BigDecimal("1051.88"));

        dao.addOrder(date, order);

        Order order2 = new Order(2);
        order2.setCustomerName("Tasha");
        order2.setState("OH");
        order2.setTaxRate(new BigDecimal("6.25"));
        order2.setProductType("Exotic Halosium");
        order2.setArea(new BigDecimal("197.00"));
        order2.setCostPerSquareFoot(new BigDecimal("81.00"));
        order2.setLaborCostPerSquareFoot(new BigDecimal("7.00"));
        order2.setMaterialCost(new BigDecimal("15957.00"));
        order2.setLaborCost(new BigDecimal("1379.00"));
        order2.setTax(new BigDecimal("1083.50"));
        order2.setTotal(new BigDecimal("18419.50"));

        dao.addOrder(date, order2);

        Order order3 = new Order(3);
        order.setCustomerName("Ted");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setTax(new BigDecimal("61.88"));
        order.setTotal(new BigDecimal("1051.88"));

        dao.addOrder(date2, order3);

        Set<String> dates = dao.getAllDates();
        assertEquals(2, dates.size());
    }

    /**
     * Test of editOrder method, of class FlooringOrderDao.
     */
    @Test
    public void testEditOrder() throws Exception {
        String date = "09232017";
        String date2 = "09242017";

        Order order = new Order(1);
        order.setCustomerName("Ted");
        order.setState("OH");
        order.setTaxRate(new BigDecimal("6.25"));
        order.setProductType("Wood");
        order.setArea(new BigDecimal("100.00"));
        order.setCostPerSquareFoot(new BigDecimal("5.15"));
        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        order.setMaterialCost(new BigDecimal("515.00"));
        order.setLaborCost(new BigDecimal("475.00"));
        order.setTax(new BigDecimal("61.88"));
        order.setTotal(new BigDecimal("1051.88"));

        dao.addOrder(date, order);

        Order editOrder = dao.getOrderDateSpecified(date, 1);
        editOrder.setCustomerName("Jojo");

        dao.editOrder(date, date2, editOrder.getOrderNumber(), editOrder);

        List<Order> ordersForDate = dao.getAllOrdersDateSpecified(date);
        List<Order> ordersForDate2 = dao.getAllOrdersDateSpecified(date2);

        assertFalse(ordersForDate.contains(order));
        assertTrue(ordersForDate2.contains(order));
        assertEquals(editOrder, dao.getOrderDateSpecified(date2, 1));
    }

    /**
     * Test of loadOrdersAndMode method, of class FlooringOrderDao.
     */
    @Test
    public void testLoadOrdersAndMode() throws Exception {
        boolean productionMode = dao.loadOrdersAndMode();
        Set<String> dates = dao.getAllDates();
        if (productionMode) {
            assertTrue(dates.size() > 0);
            assertTrue(productionMode);
        } else {
            assertFalse(productionMode);
        }

    }

    /**
     * Test of writeCurrentWork method, of class FlooringOrderDao.
     * Uncomment method below if just running tests. This test method
     * will come in conflict if running with the whole application in
     * production mode.
     */
//    @Test
//    public void testWriteCurrentWork() throws Exception {
//        // In production mode
//        dao.loadOrdersAndMode();
//        // Testing with date 09232017 with size = 3 orders after
//        // running loadOrdersAndMode method   
//        String date = "09232017";
//        String delimiter = ",";
//
//        List<Order> ordersForDate = dao.getAllOrdersDateSpecified(date);
//        int sizeOfOrderForDate = ordersForDate.size();
//
//        Order order = new Order(1);
//        order.setCustomerName("Ted");
//        order.setState("OH");
//        order.setTaxRate(new BigDecimal("6.25"));
//        order.setProductType("Wood");
//        order.setArea(new BigDecimal("100.00"));
//        order.setCostPerSquareFoot(new BigDecimal("5.15"));
//        order.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
//        order.setMaterialCost(new BigDecimal("515.00"));
//        order.setLaborCost(new BigDecimal("475.00"));
//        order.setTax(new BigDecimal("61.88"));
//        order.setTotal(new BigDecimal("1051.88"));
//
//        dao.addOrder(date, order);
//
//        dao.writeCurrentWork();
//
//        Scanner scanner;
//        Map<String, List<Order>> orders = new HashMap<>();
//        String file = "Orders_09232017.txt";
//
//        scanner = new Scanner(new BufferedReader(new FileReader(file)));
//
//        String currentLine;
//        String[] currentTokens;
//
//        List<Order> listOfOrders = new ArrayList<>();
//        while (scanner.hasNextLine()) {
//            currentLine = scanner.nextLine();
//
//            currentTokens = currentLine.split(delimiter);
//
//            Order currentOrder = new Order(Integer.parseInt(currentTokens[0]));
//            currentOrder.setCustomerName(currentTokens[1]);
//            currentOrder.setState(currentTokens[2]);
//            currentOrder.setTaxRate(new BigDecimal(currentTokens[3]));
//            currentOrder.setProductType(currentTokens[4]);
//            currentOrder.setArea(new BigDecimal(currentTokens[5]));
//            currentOrder.setCostPerSquareFoot(new BigDecimal(currentTokens[6]));
//            currentOrder.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[7]));
//            currentOrder.setMaterialCost(new BigDecimal(currentTokens[8]));
//            currentOrder.setLaborCost(new BigDecimal(currentTokens[9]));
//            currentOrder.setTax(new BigDecimal(currentTokens[10]));
//            currentOrder.setTotal(new BigDecimal(currentTokens[11]));
//
//            listOfOrders.add(currentOrder);
//        }
//        orders.put(date, listOfOrders);
//        // close scanner
//        scanner.close();
//
//        Collection<List<Order>> collection = orders.values();
//        List<Order> list = new ArrayList<>();
//        for (List<Order> ordersInCollection : collection) {
//            list = ordersInCollection;
//        }
//        assertTrue(list.size() > sizeOfOrderForDate);
//    }

}
