/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.service;

import com.sg.flooringmastery.dao.FlooringPersistenceException;
import com.sg.flooringmastery.dto.Order;
import java.util.List;
import com.sg.flooringmastery.dao.FlooringOrderDao;
import com.sg.flooringmastery.dao.FlooringProductDao;
import com.sg.flooringmastery.dao.FlooringTaxDao;
import com.sg.flooringmastery.dao.ModeException;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.service.exceptions.CustomerNameInputException;
import com.sg.flooringmastery.service.exceptions.ExitException;
import com.sg.flooringmastery.service.exceptions.OrderNumberInputException;
import com.sg.flooringmastery.service.exceptions.WrongCommitInputException;
import com.sg.flooringmastery.service.exceptions.WrongDateInputException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Set;
import org.apache.commons.lang3.text.WordUtils;

/**
 *
 * @author tedis
 */
public class FlooringServiceImpl implements FlooringService {

    private FlooringOrderDao orderDao;
    private FlooringTaxDao taxDao;
    private FlooringProductDao productDao;

    public FlooringServiceImpl(FlooringOrderDao orderDao, FlooringTaxDao taxDao, FlooringProductDao productDao) {
        this.orderDao = orderDao;
        this.taxDao = taxDao;
        this.productDao = productDao;
    }

    @Override
    public List<Order> acquireAllOrdersDateSpecified(String date) {
        return orderDao.getAllOrdersDateSpecified(date);
    }

    @Override
    public boolean loadOrdersAndMode() throws FlooringPersistenceException, ModeException {
        return orderDao.loadOrdersAndMode();
    }

    @Override
    public Order acquireOrder(String date, int orderNumber) {
        return orderDao.getOrderDateSpecified(date, orderNumber);
    }

    @Override
    public void createOrder(String date, Order order) throws FlooringPersistenceException {
        orderDao.addOrder(date, order);
    }

    @Override
    public int createNewOrderNumber() throws FlooringPersistenceException {
        int max = 0;

        for (List<Order> orders : orderDao.getAllOrders()) {
            for (Order order : orders) {
                if (order.getOrderNumber() > max) {
                    max = order.getOrderNumber();
                }
            }
        }
        return (max + 1);
    }

    @Override
    public Order removeOrder(String date, int orderNumber) {
        return orderDao.removeOrderDateSpecified(date, orderNumber);
    }

    @Override
    public Tax acquireTax(String chosenState) throws FlooringPersistenceException {
        return taxDao.getTax(chosenState.toUpperCase());
    }

    @Override
    public Product acquireProduct(String chosenProduct) throws FlooringPersistenceException {
        String capFirstCharEachWord = WordUtils.capitalize(chosenProduct);

        return productDao.getProduct(capFirstCharEachWord);
    }

    @Override
    public void changeOrder(String oldDate, String newDate, int orderNumber, Order order) throws FlooringPersistenceException {
        orderDao.editOrder(oldDate, newDate, orderNumber, order);
    }

    @Override
    public List<Tax> acquireAllTaxes() throws FlooringPersistenceException {
        return taxDao.getAllTaxes();
    }

    @Override
    public List<Product> acquireAllProducts() throws FlooringPersistenceException {
        return productDao.getAllProducts();
    }

    @Override
    public void save() throws FlooringPersistenceException {
        orderDao.writeCurrentWork();
    }

    @Override
    public BigDecimal calculateMaterialCost(Order newOrder) {
        return newOrder.getArea().multiply(newOrder.getCostPerSquareFoot()).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateLaborCost(Order newOrder) {
        return newOrder.getArea().multiply(newOrder.getLaborCostPerSquareFoot()).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateTax(Order newOrder, BigDecimal materialCost, BigDecimal laborCost) {
        return (newOrder.getTaxRate().divide(new BigDecimal("100.00"))).multiply((materialCost.add(laborCost))).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal calculateTotal(BigDecimal materialCost, BigDecimal laborCost, BigDecimal totalTax) {
        return (materialCost.add(laborCost)).add(totalTax).setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public boolean continueCommit(String commitChoice) throws WrongCommitInputException {
        commitChoice = commitChoice.toLowerCase();
        validateCommit(commitChoice);
        if (commitChoice.equals("y") || commitChoice.equals("yes")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean changeData(String editInput) {
        return validateChangeData(editInput);
    }

    @Override
    public void checkingCustomerNameInput(String name) throws CustomerNameInputException {
        validateCustomerName(name);
    }

    @Override
    public void checkingCustomerNameInputInEdit(String name) throws CustomerNameInputException {
        validateCustomerNameInEdit(name);
    }

    @Override
    public void checkingOrderInDate(List<Order> listOfOrders, int orderNumber, String date) throws FlooringPersistenceException {
        validateOrderInDate(listOfOrders, orderNumber, date);
    }

    @Override
    public void checkingDateInput(String date) throws WrongDateInputException,
            FlooringPersistenceException {
        Set<String> dates = orderDao.getAllDates();
        validateDateFormat(date);
        validateDateExists(dates, date);
    }

    @Override
    public void checkingDateInputFormat(String date) throws WrongDateInputException {
        validateDateFormat(date);
    }

    @Override
    public void checkingForExitInput(String input) throws ExitException {
        validateInputForExit(input);
    }

    @Override
    public void checkingOrderNumberInput(String orderNumber) throws OrderNumberInputException {
        validateOrderNumberInput(orderNumber);
    }

    private void validateDateFormat(String date) throws WrongDateInputException {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
            LocalDate.parse(date, formatter);
        } catch (DateTimeParseException e) {
            throw new WrongDateInputException("Invalid date input! Please try again...");
        }
    }

    private void validateDateExists(Set<String> dates, String date) throws FlooringPersistenceException {
        if (!dates.contains(date)) {
            throw new FlooringPersistenceException("No orders exist on that date! Please choose another...");
        }
    }

    private void validateCustomerName(String name) throws CustomerNameInputException {
        if (name.equals("") || name.contains(",")) {
            throw new CustomerNameInputException("Invalid name input! No blanks and commas!");
        }
    }

    private void validateOrderNumberInput(String orderNumber) throws OrderNumberInputException {
        try {
            Integer.parseInt(orderNumber);
        } catch (NumberFormatException e) {
            throw new OrderNumberInputException("Invalid order number input! Try again...");
        }
    }

    private void validateCustomerNameInEdit(String name) throws CustomerNameInputException {
        if (name.contains(",")) {
            throw new CustomerNameInputException("Invalid name input! No commas!");
        }
    }

    private void validateCommit(String commit) throws WrongCommitInputException {
        if (!(commit.equals("y") || commit.equals("yes") || commit.equals("n")
                || commit.equals("no"))) {
            throw new WrongCommitInputException("Please give me a right input!");
        }
    }

    private boolean validateChangeData(String editInput) {
        if (editInput.equals("")) {
            return false;
        }
        return true;
    }

    private void validateOrderInDate(List<Order> listOfOrders, int orderNumber, String date) throws FlooringPersistenceException {
        boolean foundOrder = false;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate formattedDate = LocalDate.parse(date, formatter);
        for (Order order : listOfOrders) {
            if (order.getOrderNumber() == orderNumber) {
                foundOrder = true;
            }
        }

        if (!foundOrder) {
            throw new FlooringPersistenceException("Order number " + orderNumber + " does not exist on " + formattedDate + "...");
        }
    }

    private void validateInputForExit(String input) throws ExitException {
        if (input.toLowerCase().equals("exit")) {
            throw new ExitException("Exiting and going back to the menu! Press enter to continue...");
        }
    }
}
