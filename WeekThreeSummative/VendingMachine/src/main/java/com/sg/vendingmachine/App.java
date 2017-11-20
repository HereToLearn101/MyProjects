/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;

import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.service.VendingMachineServiceImpl;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOConsoleImpl;
import com.sg.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author tedis
 */
public class App {
    public static void main(String[] args) {
        // Constructor Dependency Injection
        UserIO myIO = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIO);
        VendingMachineDao myDao = new VendingMachineDaoFileImpl();
        VendingMachineService myService = new VendingMachineServiceImpl(myDao);
        VendingMachineController controller = new VendingMachineController(myService, myView);
        controller.run();
    }
}
