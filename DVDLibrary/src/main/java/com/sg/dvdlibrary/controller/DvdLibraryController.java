/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.controller;

import com.sg.dvdlibrary.ui.DvdLibraryView;
import com.sg.dvdlibrary.dto.Dvd;
import com.sg.dvdlibrary.dao.*;
import java.util.List;

/**
 *
 * @author tedis
 */
public class DvdLibraryController {
    
    private DvdLibraryDao dao;
    private DvdLibraryView view;
    
    public DvdLibraryController(DvdLibraryDao dao, DvdLibraryView view) {
        this.dao = dao;
        this.view = view;
    }
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
                
                menuSelection = getMenuSelection();
                
                switch (menuSelection) {
                    case 1:
                        createDvd();
                        break;
                    case 2:
                        removeDvd();
                        break;
                    case 3:
                        editDvd();
                        break;
                    case 4:
                        listDvds();
                        break;
                    case 5:
                        viewDvd();
                        break;
                    case 6:
                        searchTitle();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
                
            }
            exitMessage();
        } catch (DvdLibraryDaoException e) {
            view.displayErrorMessage(e.getMessage());
        }
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void createDvd() throws DvdLibraryDaoException {
        view.displayCreateDvdBanner();
        Dvd newDvd = view.getNewDvdInfo();
        dao.addDvd(newDvd.getTitle(), newDvd);
        view.displayCreateSuccessBanner();
    }
    
    private void listDvds() throws DvdLibraryDaoException {
        view.displayDisplayAllBanner();
        List<Dvd> dvdList = dao.getAllDvds();
        
        view.displayDvdList(dvdList);
    }
    
    private void viewDvd() throws DvdLibraryDaoException {
        view.displayDisplayDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvd(dvdTitle);
        view.displayDvd(dvd);
    }
    
    private void removeDvd() throws DvdLibraryDaoException {
        view.displayRemoveDvdBanner();
        String dvdTitle = view.getDvdTitleChoice();
        dao.removeDvd(dvdTitle);
        view.displayRemoveSuccessBanner();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
    
    private void editDvd() throws DvdLibraryDaoException {
        
        boolean keepGoing = true;
        int menuEditChoice = 0;
        String title = view.getDvdTitleChoice();
        Dvd dvd = dao.getDvd(title);
        
        if (dvd == null) {
            keepGoing = false;
        } else {
            view.displayDvd(dvd);
            view.displayEditDvdBanner();
        }
                
        while (keepGoing) {
            
            menuEditChoice = view.menuEditDvd();
            
            switch (menuEditChoice) {
                case 1:
                    Dvd titleEditedDvd = view.editTitle(dao.removeDvd(title));
                    dao.editDvd(titleEditedDvd.getTitle(), titleEditedDvd);
                    break;
                case 2:
                    view.editReleaseDate(dvd);
                    dao.editDvd(title, dvd);
                    break;
                case 3:
                    view.editMPAARating(dvd);
                    dao.editDvd(title, dvd);
                    break;
                case 4:
                    view.editDirectName(dvd);
                    dao.editDvd(title, dvd);
                    break;
                case 5:
                    view.editStudio(dvd);
                    dao.editDvd(title, dvd);
                    break;
                case 6:
                    view.editUserRating(dvd);
                    dao.editDvd(title, dvd);
                    break;
                case 7:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }
        }
    }
    
    private void searchTitle() throws DvdLibraryDaoException{
        view.displaySearchBanner();
        String keyWord = view.getKeyWord();
        List<Dvd> matchingDvds = dao.getMatchingDvds(keyWord);
        view.displayDvdList(matchingDvds);
    }
}
