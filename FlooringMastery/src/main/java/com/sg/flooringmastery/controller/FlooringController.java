/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.controller;

import com.sg.flooringmastery.dao.FlooringPersistenceException;
import com.sg.flooringmastery.dao.ModeException;
import com.sg.flooringmastery.dto.Order;
import com.sg.flooringmastery.dto.Product;
import com.sg.flooringmastery.dto.Tax;
import com.sg.flooringmastery.service.FlooringService;
import com.sg.flooringmastery.service.exceptions.CustomerNameInputException;
import com.sg.flooringmastery.service.exceptions.ExitException;
import com.sg.flooringmastery.service.exceptions.OrderNumberInputException;
import com.sg.flooringmastery.service.exceptions.WrongCommitInputException;
import com.sg.flooringmastery.service.exceptions.WrongDateInputException;
import com.sg.flooringmastery.ui.FlooringView;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author tedis
 *
 */
public class FlooringController {

    private FlooringService service;
    private FlooringView view;
    private boolean productionMode;

    public FlooringController(FlooringService service, FlooringView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepPlaying = true;
        int choice = 0;

        try {
            productionMode = service.loadOrdersAndMode();

            while (keepPlaying) {
                choice = view.displayMenu(productionMode);

                try {
                    switch (choice) {
                        case 1:
                            displayOrders();
                            break;
                        case 2:
                            addOrder();
                            break;
                        case 3:
                            editOrder();
                            break;
                        case 4:
                            removeOrder();
                            break;
                        case 5:
                            saveCurrentWork();
                            break;
                        case 6:
                            keepPlaying = false;
                            break;
                        default:
                            view.displayErrorMsg("Please give me a number associated with the option!");
                    }
                } catch (FlooringPersistenceException e) {
                    view.displayErrorMsg(e.getMessage());
                }
            }
        } catch (FlooringPersistenceException ePersistence) {
            view.displayErrorMsg(ePersistence.getMessage());
        } catch (ModeException eMode) {
            view.displayErrorMsg(eMode.getMessage());
        }
    }

    private void displayOrders() {
        boolean error = true;

        view.displayDisplayBanner();
        String dateOrExit;
        try {
            do {
                dateOrExit = view.displayAskDateAndExitMsg();
                service.checkingForExitInput(dateOrExit);
                try {
                    service.checkingDateInput(dateOrExit);
                    error = false;
                } catch (WrongDateInputException eWrongInput) {
                    view.displayErrorMsg(eWrongInput.getMessage());
                    error = true;
                } catch (FlooringPersistenceException ePer) {
                    view.displayErrorMsg(ePer.getMessage());
                    error = true;
                }
            } while (error);

            List<Order> listOfOrders = service.acquireAllOrdersDateSpecified(dateOrExit);
            view.displayAllOrders(listOfOrders);
        } catch (ExitException exit) {
            view.displayErrorMsg(exit.getMessage());
        }
    }

    private void addOrder() throws FlooringPersistenceException {
        boolean error = true;

        Order newOrder;
        List<Tax> listOfTaxes = service.acquireAllTaxes();
        List<Product> listOfProducts = service.acquireAllProducts();

        int newOrderNumber = service.createNewOrderNumber();

        do {
            // newOrder references to the new Order object
            newOrder = view.startNewOrderNumAndName(newOrderNumber);
            try {
                service.checkingCustomerNameInput(newOrder.getCustomerName());
                error = false;
            } catch (CustomerNameInputException e) {
                view.displayErrorMsg(e.getMessage());
                error = true;
            }
        } while (error);

        do {
            try {
                view.displayStateAndTaxRatesBanner();
                view.displayStateTaxRates(listOfTaxes);
                Tax stateAndTax = service.acquireTax(view.chooseStateTaxRate());
                //newOrder = view.getNewOrderStateAndTax(newOrder, stateAndTax);
                view.getNewOrderStateAndTax(newOrder, stateAndTax);
                error = false;
            } catch (NullPointerException e) {
                view.displayErrorMsg("Please choose one of the state abbreviations display above!");
                error = true;
            }
        } while (error);

        do {
            try {
                view.displayProductsBanner();
                view.displayProducts(listOfProducts);
                Product product = service.acquireProduct(view.chooseProduct());
                view.getNewOrderProductAndArea(newOrder, product);
                error = false;
            } catch (NullPointerException e) {
                view.displayErrorMsg("Please choose one of the products listed above!");
                error = true;
            }
        } while (error);

        BigDecimal materialCost = service.calculateMaterialCost(newOrder);
        BigDecimal laborCost = service.calculateLaborCost(newOrder);
        BigDecimal totalTax = service.calculateTax(newOrder, materialCost, laborCost);
        BigDecimal totalCost = service.calculateTotal(materialCost, laborCost, totalTax);

        view.finishOrderSetup(newOrder, materialCost, laborCost, totalTax, totalCost);

        view.displayOrderInfo(newOrder);

        String dateForAddOrder;
        do {
            String commitChoice = view.displayCommitMsg();
            try {
                if (service.continueCommit(commitChoice)) {
                    do {
                        dateForAddOrder = view.displayAskDateMsgToPutOrder();
                        try {
                            service.checkingDateInputFormat(dateForAddOrder);
                            error = false;
                        } catch (WrongDateInputException eWrongDate) {
                            view.displayErrorMsg(eWrongDate.getMessage());
                            error = true;
                        }
                    } while (error);
                    service.createOrder(dateForAddOrder, newOrder);
                }
                error = false;
            } catch (WrongCommitInputException e) {
                view.displayErrorMsg(e.getMessage());
                error = true;
            }
        } while (error);
    }

    private void editOrder() throws FlooringPersistenceException {
        boolean error = true;
        view.displayFunctionsAndEditBanner();

        String dateOrExit;
        try {
            do {
                dateOrExit = view.displayAskDateAndExitMsg();
                service.checkingForExitInput(dateOrExit);
                try {
                    service.checkingDateInput(dateOrExit);
                    error = false;
                } catch (WrongDateInputException eWrongInput) {
                    view.displayErrorMsg(eWrongInput.getMessage());
                    error = true;
                } catch (FlooringPersistenceException ePer) {
                    view.displayErrorMsg(ePer.getMessage());
                    error = true;
                }
            } while (error);

            List<Order> orders = service.acquireAllOrdersDateSpecified(dateOrExit);
            String orderNum;

            do {
                orderNum = view.displayAskOrderNumber();
                service.checkingForExitInput(orderNum);
                try {
                    service.checkingOrderNumberInput(orderNum);
                    service.checkingOrderInDate(orders, Integer.parseInt(orderNum), dateOrExit);
                    error = false;
                } catch (OrderNumberInputException eOrderNumber) {
                    view.displayErrorMsg(eOrderNumber.getMessage());
                    error = true;
                } catch (FlooringPersistenceException e) {
                    view.displayErrorMsg(e.getMessage());
                    view.displayOrderNumbersAvailableToChoose(orders, dateOrExit);
                    error = true;
                }
            } while (error);

            String editInput;
            Order order = service.acquireOrder(dateOrExit, Integer.parseInt(orderNum));
            do {
                editInput = view.askEditOrderName(order);
                try {
                    service.checkingCustomerNameInputInEdit(editInput);
                    if (service.changeData(editInput)) {
                        order = view.editOrderName(order, editInput);
                    }
                    error = false;
                } catch (CustomerNameInputException e) {
                    view.displayErrorMsg(e.getMessage());
                    error = true;
                }
            } while (error);

            List<Tax> taxes = service.acquireAllTaxes();
            do {
                view.displayStateAndTaxRatesBanner();
                view.displayStateTaxRates(taxes);
                view.displayCurrentOrderState(order);
                editInput = view.chooseStateTaxRate();
                try {
                    if (service.changeData(editInput)) {
                        Tax tax = service.acquireTax(editInput);
                        order = view.editOrderState(order, tax);
                    }
                    error = false;
                } catch (NullPointerException e) {
                    view.displayErrorMsg("Please choose one of the state abbreviations display above!");
                    error = true;
                }
            } while (error);

            List<Product> products = service.acquireAllProducts();
            do {
                view.displayProductsBanner();
                view.displayProducts(products);
                view.displayCurrentProduct(order);
                editInput = view.chooseProduct();
                try {
                    if (service.changeData(editInput)) {
                        Product product = service.acquireProduct(editInput);
                        order = view.editOrderProduct(order, product);
                    }
                    error = false;
                } catch (NullPointerException e) {
                    view.displayErrorMsg("Please choose one of the products listed above!");
                    error = true;
                }
            } while (error);

            do {
                editInput = view.askEditOrderArea(order);
                try {
                    if (service.changeData(editInput)) {
                        order = view.editOrderArea(order, editInput);
                    }
                    error = false;
                } catch (NumberFormatException e) {
                    view.displayErrorMsg("Please give me a real number greater than 0!");
                    error = true;
                }
            } while (error);

            BigDecimal materialCost = service.calculateMaterialCost(order);
            BigDecimal laborCost = service.calculateLaborCost(order);
            BigDecimal totalTax = service.calculateTax(order, materialCost, laborCost);
            BigDecimal totalCost = service.calculateTotal(materialCost, laborCost, totalTax);

            order = view.finishOrderSetup(order, materialCost, laborCost, totalTax, totalCost);

            view.displayOrderInfo(order);

            String dateForEditedOrder;
            do {
                String commitChoice = view.displayCommitMsg();
                try {
                    if (service.continueCommit(commitChoice)) {
                        do {
                            dateForEditedOrder = view.displayAskDateMsgToPutOrder();
                            try {
                                service.checkingDateInputFormat(dateForEditedOrder);
                                error = false;
                            } catch (WrongDateInputException eWrongDate) {
                                view.displayErrorMsg(eWrongDate.getMessage());
                                error = true;
                            }
                        } while (error);
                        service.changeOrder(dateOrExit, dateForEditedOrder, Integer.parseInt(orderNum), order);
                    }
                    error = false;
                } catch (WrongCommitInputException e) {
                    view.displayErrorMsg(e.getMessage());
                    error = true;
                }
            } while (error);
        } catch (ExitException eExit) {
            view.displayErrorMsg(eExit.getMessage());
        }
    }

    private void removeOrder() throws FlooringPersistenceException {
        boolean error = true;

        view.displayRemoveBanner();

        String dateOrExit;
        try {
            do {
                dateOrExit = view.displayAskDateAndExitMsg();
                service.checkingForExitInput(dateOrExit);
                try {
                    service.checkingDateInput(dateOrExit);
                    error = false;
                } catch (WrongDateInputException eWrongInput) {
                    view.displayErrorMsg(eWrongInput.getMessage());
                    error = true;
                } catch (FlooringPersistenceException ePer) {
                    view.displayErrorMsg(ePer.getMessage());
                    error = true;
                }
            } while (error);

            List<Order> orders = service.acquireAllOrdersDateSpecified(dateOrExit);
            String orderNum;

            do {
                orderNum = view.displayAskOrderNumber();
                service.checkingForExitInput(orderNum);
                try {
                    service.checkingOrderNumberInput(orderNum);
                    service.checkingOrderInDate(orders, Integer.parseInt(orderNum), dateOrExit);
                    error = false;
                } catch (OrderNumberInputException eOrderNumber) {
                    view.displayErrorMsg(eOrderNumber.getMessage());
                    error = true;
                } catch (FlooringPersistenceException ePersistence) {
                    view.displayErrorMsg(ePersistence.getMessage());
                    view.displayOrderNumbersAvailableToChoose(orders, dateOrExit);
                    error = true;
                }
            } while (error);

            do {
                String commitChoice = view.displayCommitMsg();
                try {
                    if (service.continueCommit(commitChoice)) {
                        service.removeOrder(dateOrExit, Integer.parseInt(orderNum));
                    }
                    error = false;
                } catch (WrongCommitInputException e) {
                    view.displayErrorMsg(e.getMessage());
                    error = true;
                }
            } while (error);
        } catch (ExitException eExit) {
            view.displayErrorMsg(eExit.getMessage());
        }
    }

    private void saveCurrentWork() throws FlooringPersistenceException {
        if (productionMode) {
            service.save();
        }
        view.displaySaveSuccessBanner();
    }
}
