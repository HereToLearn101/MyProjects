/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringOrderDao;
import com.sg.flooringmastery.dao.FlooringOrderDaoStubImpl;
import com.sg.flooringmastery.dao.FlooringPersistenceException;
import com.sg.flooringmastery.dao.FlooringProductDao;
import com.sg.flooringmastery.dao.FlooringProductDaoStubImpl;
import com.sg.flooringmastery.dao.FlooringTaxDao;
import com.sg.flooringmastery.dao.FlooringTaxDaoStubImpl;
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
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author tedis
 */
public class FlooringServiceTest {

    private FlooringService service;

    public FlooringServiceTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContextUnitTesting.xml");
        service = ctx.getBean("service", FlooringService.class);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of acquireAllOrdersDateSpecified method, of class FlooringService.
     */
    @Test
    public void testAcquireAllOrdersDateSpecified() {
        String date = "09232017";

        List<Order> listOfOrdersOfDate = service.acquireAllOrdersDateSpecified(date);

        assertEquals(1, listOfOrdersOfDate.size());
    }

    /**
     * Test of loadOrdersAndMode method, of class FlooringService.
     */
    @Test
    public void testLoadOrdersAndMode() throws Exception {
        // This method is suppose to load in all the files with orders, but I'm 
        // using a stub so no need to test this.
    }

    /**
     * Test of acquireOrder method, of class FlooringService.
     */
    @Test
    public void testAcquireOrder() {
        String date = "09232017";

        service.acquireOrder(date, 1);
    }

    /**
     * Test of createOrder method, of class FlooringService.
     */
    @Test
    public void testCreateOrderAndNewOrderNumber() throws Exception {
        String date = "09232017";

        int newOrderNumber = service.createNewOrderNumber();

        assertEquals(2, newOrderNumber);

        Order newOrder = new Order(newOrderNumber);
        newOrder.setCustomerName("Cielo");
        newOrder.setState("OH");
        newOrder.setTaxRate(new BigDecimal("6.25"));
        newOrder.setProductType("Wood");
        newOrder.setArea(new BigDecimal("100.00"));
        newOrder.setCostPerSquareFoot(new BigDecimal("5.15"));
        newOrder.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        newOrder.setMaterialCost(new BigDecimal("515.00"));
        newOrder.setLaborCost(new BigDecimal("475.00"));
        newOrder.setTax(new BigDecimal("61.88"));
        newOrder.setTotal(new BigDecimal("1051.88"));

        service.createOrder(date, newOrder);
    }

    /**
     * Test of createNewOrderNumber method, of class FlooringService.
     *
     * This method is tested above with createOrder method.
     */
    /**
     * Test of removeOrder method, of class FlooringService.
     */
    @Test
    public void testRemoveOrder() {
        String date = "09232017";

        service.removeOrder(date, 1);
    }

    /**
     * Test of acquireTax method, of class FlooringService.
     */
    @Test
    public void testAcquireTax() throws Exception {
        service.acquireTax("OH");
    }

    /**
     * Test of acquireProduct method, of class FlooringService.
     */
    @Test
    public void testAcquireProduct() throws Exception {
        service.acquireProduct("Spider Web");
    }

    /**
     * Test of changeOrder method, of class FlooringService.
     */
    @Test
    public void testChangeOrder() throws Exception {
        String date = "09232017";

        Order editedOrder = service.acquireOrder(date, 1);
        editedOrder.setCustomerName("Ted");

        service.changeOrder(date, date, editedOrder.getOrderNumber(), editedOrder);

        Order currentOrder = service.acquireOrder(date, 1);

        assertEquals(editedOrder, currentOrder);
    }

    /**
     * Test of acquireAllTaxes method, of class FlooringService.
     */
    @Test
    public void testAcquireAllTaxes() throws Exception {
        List<Tax> listOfTaxes = service.acquireAllTaxes();

        assertEquals(1, listOfTaxes.size());
    }

    /**
     * Test of acquireAllProducts method, of class FlooringService.
     */
    @Test
    public void testAcquireAllProducts() throws Exception {
        List<Product> listOfProducts = service.acquireAllProducts();

        assertEquals(1, listOfProducts.size());
    }

    /**
     * Test of save method, of class FlooringService.
     */
    @Test
    public void testSave() throws Exception {
        // This method touches files. I'm using stubs so there's
        // no need to test this.
    }

    /**
     * Test of calculateMaterialCost method, of class FlooringService.
     */
    @Test
    public void testCalculateMaterialCost() {
        String date = "09232017";

        Order order = service.acquireOrder(date, 1);

        BigDecimal result = service.calculateMaterialCost(order);

        assertEquals((new BigDecimal("515.00")), result);
    }

    /**
     * Test of calculateLaborCost method, of class FlooringService.
     */
    @Test
    public void testCalculateLaborCost() {
        String date = "09232017";

        Order order = service.acquireOrder(date, 1);

        BigDecimal result = service.calculateLaborCost(order);

        assertEquals(new BigDecimal("475.00"), result);
    }

    /**
     * Test of calculateTax method, of class FlooringService.
     */
    @Test
    public void testCalculateTax() {
        String date = "09232017";

        Order order = service.acquireOrder(date, 1);

        BigDecimal materialCost = service.calculateMaterialCost(order);
        BigDecimal laborCost = service.calculateLaborCost(order);
        BigDecimal totalTax = service.calculateTax(order, materialCost, laborCost);

        assertEquals(new BigDecimal("61.88"), totalTax);
    }

    /**
     * Test of calculateTotal method, of class FlooringService.
     */
    @Test
    public void testCalculateTotal() {
        String date = "09232017";

        Order order = service.acquireOrder(date, 1);

        BigDecimal materialCost = service.calculateMaterialCost(order);
        BigDecimal laborCost = service.calculateLaborCost(order);
        BigDecimal totalTax = service.calculateTax(order, materialCost, laborCost);
        BigDecimal totalCost = service.calculateTotal(materialCost, laborCost, totalTax);

        assertEquals(new BigDecimal("1051.88"), totalCost);
    }

    /**
     * Test of continueCommit method, of class FlooringService.
     */
    @Test
    public void testContinueCommit() throws Exception {
        String yes = "y";
        String no = "n";

        boolean commit = service.continueCommit(yes);
        boolean noCommit = service.continueCommit(no);

        assertTrue(commit);
        assertFalse(noCommit);
    }

    @Test
    public void testContinueCommitWrongCommitInputException() throws Exception {
        String yes = "yea";

        try {
            service.continueCommit(yes);
            fail("Expected WrongCommitInputException");
        } catch (WrongCommitInputException e) {

        }
    }

    /**
     * Test of changeData method, of class FlooringService.
     */
    @Test
    public void testChangeData() {
        String changeData = "new data";
        String noChange = "";

        boolean dataChanged = service.changeData(changeData);
        boolean dataChanged2 = service.changeData(noChange);

        assertTrue(dataChanged);
        assertFalse(dataChanged2);
    }

    /**
     * Test of checkingCustomerNameInput method, of class FlooringService.
     */
    @Test
    public void testCheckingCustomerNameInput() throws Exception {
        String blank = "";
        String comma = ",";

        try {
            service.checkingCustomerNameInput(blank);
            fail("expected CustomerNameInputException");
        } catch (CustomerNameInputException e) {

        }

        try {
            service.checkingCustomerNameInput(comma);
            fail("expected CustomerNameInputException");
        } catch (CustomerNameInputException e) {

        }
    }

    /**
     * Test of checkingCustomerNameInputInEdit method, of class FlooringService.
     */
    @Test
    public void testCheckingCustomerNameInputInEdit() throws Exception {
        String comma = ",";
        String blank = "";

        try {
            service.checkingCustomerNameInputInEdit(comma);
            fail("expected CustomerNameInputException");
        } catch (CustomerNameInputException e) {

        }

        try {
            service.checkingCustomerNameInputInEdit(blank);
        } catch (CustomerNameInputException e) {
            fail("expected no CustomerNameInputException");
        }
    }

    /**
     * Test of checkingOrderInDate method, of class FlooringService.
     */
    @Test
    public void testCheckingOrderInDate() throws Exception {
        String date = "09232017";

        List<Order> listOfOrders = service.acquireAllOrdersDateSpecified(date);

        try {
            service.checkingOrderInDate(listOfOrders, 2, date);
            fail("expected FlooringPersistenceException");
        } catch (FlooringPersistenceException e) {

        }
    }

    /**
     * Test of checkingDateInput method, of class FlooringService.
     */
    @Test
    public void testCheckingDateInput() throws Exception {
        String invalidDate = "09322017";
        String date = "09242017";

        try {
            service.checkingDateInput(invalidDate);
            fail("expected WrongDateInputException");
        } catch (WrongDateInputException e) {

        }

        try {
            service.checkingDateInput(date);
            fail("expected FlooringPersistenceException");
        } catch (FlooringPersistenceException e) {

        }
    }

    /**
     * Test of checkingDateInputFormat method, of class FlooringService.
     */
    @Test
    public void testCheckingDateInputFormat() throws Exception {
        String invalidDate = "09322017";

        try {
            service.checkingDateInput(invalidDate);
            fail("expected WrongDateInputException");
        } catch (WrongDateInputException e) {

        }
    }

    /**
     * Test of checkingForExitInput method, of class FlooringService.
     */
    @Test
    public void testCheckingForExitInput() throws Exception {
        String exit = "exit";

        try {
            service.checkingForExitInput(exit);
            fail("expected ExitException");
        } catch (ExitException e) {

        }
    }

    /**
     * Test of checkingOrderNumberInput method, of class FlooringService.
     */
    @Test
    public void testCheckingOrderNumberInput() throws Exception {
        String orderNumber = "3f";

        try {
            service.checkingOrderNumberInput(orderNumber);
            fail("expected OrderNumberInputException");
        } catch (OrderNumberInputException e) {

        }
    }

}
