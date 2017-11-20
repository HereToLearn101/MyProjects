/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary;

import com.sg.dvdlibrary.ui.*;
import com.sg.dvdlibrary.dao.*;
import com.sg.dvdlibrary.controller.DvdLibraryController;

/**
 *
 * @author tedis
 */
public class App {

    public static void main(String[] args) {
        UserIO io = new UserIOConsoleImpl();
        DvdLibraryView myView = new DvdLibraryView(io);
        DvdLibraryDao myDao = new DvdLibraryDaoFileImpl();
        DvdLibraryController controller = new DvdLibraryController(myDao, myView);
        controller.run();
    }

}
