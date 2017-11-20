/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.ui;

import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author tedis
 */
public class FlooringView {

    private UserIO io;

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public int displayMenu(boolean mode) {
        if (mode) {
            io.print("********************************");
            io.print("********FLOORING PROGRAM********");
            io.print("*********PRODUCTION MODE********");
            io.print("********************************");
            io.print("1. Display Orders \n"
                    + "2. Add an Order \n"
                    + "3. Edit an Order \n"
                    + "4. Remove an Order \n"
                    + "5. Save Current Work \n"
                    + "6. Quit");
        } else {
            io.print("********************************");
            io.print("********FLOORING PROGRAM********");
            io.print("*********TRAINING MODE**********");
            io.print("********************************");
            io.print("1. Display Orders \n"
                    + "2. Add an Order \n"
                    + "3. Edit an Order \n"
                    + "4. Remove an Order \n"
                    + "5. Save Current Work \n"
                    + "6. Quit");
        }

        return io.readInt("Your choice? (1 - 6) -> ");
    }

    public void displayAllOrders(List<Order> listOfOrders) {
        for (Order order : listOfOrders) {
            io.print("Order " + order.getOrderNumber() + ": " + order.getCustomerName()
                    + ", " + order.getState() + ", " + order.getTaxRate() + ", "
                    + order.getProductType() + ", " + order.getArea() + ", "
                    + order.getCostPerSquareFoot() + ", " + order.getLaborCostPerSquareFoot()
                    + ", " + order.getMaterialCost() + ", " + order.getLaborCost() + ", "
                    + order.getTax() + ", " + order.getTotal());
        }
        io.readString("Press enter to continue...");
    }

    public void displayOrderNumbersAvailableToChoose(List<Order> listOfOrders, String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMddyyyy");
        LocalDate localDate = LocalDate.parse(date, formatter);
        io.print("*** current orders on " + localDate + " ***");
        for (Order order : listOfOrders) {
            io.print(Integer.toString(order.getOrderNumber()));
        }
    }

    public void displayOrderInfo(Order order) {
        io.print("Order Number: " + order.getOrderNumber() + "\n"
                + "Customer Name: " + order.getCustomerName() + "\n"
                + "State: " + order.getState() + "\n"
                + "Tax Rate: " + order.getTaxRate() + "\n"
                + "Product: " + order.getProductType() + "\n"
                + "Area: " + order.getArea() + "\n"
                + "Cost Per Square Foot: " + order.getCostPerSquareFoot() + "\n"
                + "Labor Cost Per Square Foot: " + order.getLaborCostPerSquareFoot() + "\n"
                + "Material Cost: " + order.getMaterialCost() + "\n"
                + "Labor Cost: " + order.getLaborCost() + "\n"
                + "Total Tax: " + order.getTax() + "\n"
                + "Total Cost: " + order.getTotal());

        io.readString("Press enter to continue...");
    }

    public String displayAskDateAndExitMsg() {
        return io.readString("Please give me a date (MMddyyyy) or 'exit': ");
    }

    public String displayAskDateMsgToPutOrder() {
        return io.readString("What date you want your order to be processed? (MMddyyyy): ");
    }

    public String displayAskOrderNumber() {
        return io.readString("Please give me an Order Number (Integer value) or 'exit': ");
    }

    //********************************** FOR THE ADD OPTION **************************************
    public void displayAddBanner() {
        io.print("");
        io.print("*** ADD OPTION ***");
    }

    public Order startNewOrderNumAndName(int newOrderNumber) {
        Order newOrder = new Order(newOrderNumber);
        newOrder.setCustomerName(io.readString("Customer Name: "));

        return newOrder;
    }

    public Order getNewOrderStateAndTax(Order newOrder, Tax stateAndTax) {
        newOrder.setState(stateAndTax.getState());
        newOrder.setTaxRate(stateAndTax.getTaxRate());

        return newOrder;
    }

    public Order getNewOrderProductAndArea(Order newOrder, Product product) {
        newOrder.setProductType(product.getProductType());
        newOrder.setArea(new BigDecimal(io.readString("Area: ")).setScale(2, RoundingMode.HALF_UP));
        newOrder.setCostPerSquareFoot(product.getCostPerSquareFoot());
        newOrder.setLaborCostPerSquareFoot(product.getLaborCostPerSquareFoot());

        return newOrder;
    }

    public Order finishOrderSetup(Order newOrder, BigDecimal materialCost, BigDecimal laborCost, BigDecimal totalTax, BigDecimal totalCost) {
        newOrder.setMaterialCost(materialCost);// materialCost
        newOrder.setLaborCost(laborCost);
        newOrder.setTax(totalTax);
        newOrder.setTotal(totalCost);

        io.print("");
        io.print("*** YOUR ORDER ***");
        return newOrder;
    }
//***********************************************************************************************
//***********************************************************************************************

//******************************* FOR THE EDIT OPTION *******************************************
    public void displayFunctionsAndEditBanner() {
        io.print("");
        io.print("*** WELCOME TO THE EDIT OPTION ***");
        io.print("To edit: simply input a new information ");
        io.print("                  OR                     ");
        io.print("Press enter to keep order data");
        io.print("");
    }

    public String askEditOrderName(Order existingOrder) {
        io.print("Order's current customer name: " + existingOrder.getCustomerName());
        return io.readString("Change Customer Name? ->  ");
    }

    public Order editOrderName(Order existingOrder, String newName) {
        existingOrder.setCustomerName(newName);

        return existingOrder;
    }

    public void displayCurrentOrderState(Order existingOrder) {
        io.print("");
        io.print("Order's current State: " + existingOrder.getState());
    }

    public Order editOrderState(Order existingOrder, Tax newStateAndTax) {
        existingOrder.setState(newStateAndTax.getState());
        existingOrder.setTaxRate(newStateAndTax.getTaxRate());

        return existingOrder;
    }

    public void displayCurrentProduct(Order existingOrder) {
        io.print("");
        io.print("Order's current Product: " + existingOrder.getProductType());
    }

    public Order editOrderProduct(Order existingOrder, Product newProduct) {
        existingOrder.setProductType(newProduct.getProductType());
        existingOrder.setCostPerSquareFoot(newProduct.getCostPerSquareFoot());
        existingOrder.setLaborCostPerSquareFoot(newProduct.getLaborCostPerSquareFoot());

        return existingOrder;
    }

    public String askEditOrderArea(Order existingOrder) {
        io.print("");
        io.print("Order's current Area: " + existingOrder.getArea());
        return io.readString("Change Area? -> ");
    }

    public Order editOrderArea(Order existingOrder, String newArea) {
        existingOrder.setArea(new BigDecimal(newArea));

        return existingOrder;
    }

//***********************************************************************************************
//***********************************************************************************************
    public void displayRemoveBanner() {
        io.print("");
        io.print("*** REMOVE OPTION ***");
    }

    public String displayCommitMsg() {
        return io.readString("Commit? (Y/N)");
    }

    public String displayAskSaveMsg() {
        return io.readString("Do you want to save? (Y/N)");
    }

    public void displayErrorMsg(String errorMsg) {
        io.print("");
        io.readString(errorMsg + "\nPress enter to continue...");
    }

    public void displayStateTaxRates(List<Tax> listOfTaxes) {
        for (Tax tax : listOfTaxes) {
            io.print(tax.getState() + " : " + tax.getTaxRate());
        }
        io.print("*************************************************************");
    }

    public String chooseStateTaxRate() {
        return io.readString("Please choose a State Abbreviation: ");
    }

    public void displayProducts(List<Product> listOfProducts) {
        for (Product product : listOfProducts) {
            io.print(product.getProductType() + " | " + product.getCostPerSquareFoot()
                    + " | " + product.getLaborCostPerSquareFoot());
        }
        io.print("*************************************************************");
    }

    public String chooseProduct() {
        return io.readString("Please choose a product: ");
    }

    public void displayDisplayBanner() {
        io.print("");
        io.print("*** DISPLAY OPTION ***");
    }

    public void displayStateAndTaxRatesBanner() {
        io.print("*** STATE AND TAX RATES ***");
    }

    public void displayProductsBanner() {
        io.print("*** PRODUCTS ***");
    }

    public void displaySuccessCreateOrderBanner() {
        io.readString("Your order was a SUCCESS! Press enter to continue...");
    }

    public void displaySuccessEditOrderBanner() {
        io.readString("Order was successfully edited! Press enter to continue...");
    }

    public void displaySuccessRemoveOrderBanner() {
        io.readString("Order was successfully removed! Press enter to continue...");
    }

    public void displaySaveSuccessBanner() {
        io.readString("Current work was successfully saved! Press enter to continue...");
    }
}
