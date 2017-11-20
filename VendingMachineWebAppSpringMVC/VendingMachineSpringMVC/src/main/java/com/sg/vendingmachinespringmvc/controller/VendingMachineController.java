/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author tedis
 */
@Controller
public class VendingMachineController {

    VendingMachineDao dao;
    Change change = new Change();

    @Inject
    public VendingMachineController(VendingMachineDao dao) {
        this.dao = dao;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String displayVendingPage(HttpServletRequest request, Model model) {
        List<Item> itemList = dao.getAllItems();

        model.addAttribute("itemList", itemList);

        return "vending";
    }

    @RequestMapping(value = "/processItem", method = RequestMethod.GET)
    public String processItem(HttpServletRequest request, Model model) {

        List<Item> itemList = dao.getAllItems();

        // validates that user has to pick an item and input money first, or in
        // other words both the input tag with name="money" and input tag with
        // name="item" are not empty
        if (!(request.getParameter("money").equals("")) && !(request.getParameter("item").equals(""))) {
            BigDecimal money = new BigDecimal(request.getParameter("money"));
            int itemId = Integer.parseInt(request.getParameter("item"));

            Item item = dao.getItemById(itemId);

            // validates if the item price selected is greater than the amount
            // of money user inputs
            if (money.compareTo(item.getPrice()) == -1) {
                BigDecimal depositRemaining = item.getPrice().subtract(money);

                model.addAttribute("money", money);
                model.addAttribute("itemId", itemId);
                model.addAttribute("message", "Please deposit: $" + depositRemaining);
                model.addAttribute("itemList", itemList);

                return "vending";
                
                // else if statement below validates whether item's quantity
                // that is selected has a quantity = 0. If true, code inside is
                // executed
            } else if (item.getQuantity() == 0) {
                model.addAttribute("message", "SOLD OUT!!!");
                model.addAttribute("money", money);
                model.addAttribute("itemId", itemId);
                model.addAttribute("itemList", itemList);

                return "vending";
            } else {
                // else if it passes the conditional statement above, then all
                // information that is required are supplied to to process Item
                item.setQuantity(item.getQuantity() - 1);
                BigDecimal remain = money.subtract(item.getPrice());
                remain = remain.multiply(new BigDecimal("100"));
                createChange(remain);
                Change changeFromRemain = getChange();

                String changeToDisplay = "";
                if (changeFromRemain.getQuarters() != 0) {
                    changeToDisplay += Integer.toString(changeFromRemain.getQuarters()) + "Quarters ";
                }

                if (changeFromRemain.getDimes() != 0) {
                    changeToDisplay += Integer.toString(changeFromRemain.getDimes()) + "Dimes ";
                }

                if (changeFromRemain.getNickels() != 0) {
                    changeToDisplay += Integer.toString(changeFromRemain.getNickels()) + "Nickels ";
                }

                if (changeFromRemain.getPennies() != 0) {
                    changeToDisplay += Integer.toString(changeFromRemain.getPennies()) + "Pennies ";
                }

                model.addAttribute("itemList", itemList);
                model.addAttribute("change", changeToDisplay);
                model.addAttribute("message", "Thank You!");
                
                return "vending";
            }
        }
        
        return "redirect:/";
    }

    // method to create the change
    private void createChange(BigDecimal remain) {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;

        if ((remain.compareTo(new BigDecimal("25")) == 1) || (remain.compareTo(new BigDecimal("25")) == 0)) {
            quarters = remain.divide(new BigDecimal("25"), 0, RoundingMode.DOWN).intValue();
            remain = remain.remainder(new BigDecimal("25"));
        }

        if ((remain.compareTo(new BigDecimal("10")) == 1) || (remain.compareTo(new BigDecimal("10")) == 0)) {
            dimes = remain.divide(new BigDecimal("10"), 0, RoundingMode.DOWN).intValue();
            remain = remain.remainder(new BigDecimal("10"));
        }

        if ((remain.compareTo(new BigDecimal("5")) == 1) || (remain.compareTo(new BigDecimal("5")) == 0)) {
            nickels = remain.divide(new BigDecimal("5"), 0, RoundingMode.DOWN).intValue();
            remain = remain.remainder(new BigDecimal("5"));
        }

        pennies = remain.intValue();

        change.setQuarters(quarters);
        change.setDimes(dimes);
        change.setNickels(nickels);
        change.setPennies(pennies);
    }

    // method to grab the instance of Change object that holds the change info
    private Change getChange() {
        return change;
    }
}
