/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;

/**
 *
 * @author tedis
 */
public class DvdLibraryView {

    private UserIO io;
    
    public DvdLibraryView(UserIO io) {
        this.io = io;
    }

    public int printMenuAndGetSelection() {
        io.print("Main Menu");
        io.print("1. Add a DVD");
        io.print("2. Remove a DVD");
        io.print("3. Edit a DVD");
        io.print("4. List of DVDs");
        io.print("5. View DVD");
        io.print("6. Search DVD");
        io.print("7. Quit");

        return io.readInt("Please select from the above choices.", 1, 7);
    }
    
    public Dvd getNewDvdInfo() {
        String dvdTitle = io.readString("Enter a Title");
        String releaseDate = io.readString("Enter a Release Date");
        String mpaaRating = io.readString("Enter MPAA Rating");
        String directorName = io.readString("Enter Director's Name");
        String studio = io.readString("Enter a Studio");
        String userRating = io.readString("Your rating for the movie: ");
        Dvd dvdInfo = new Dvd(dvdTitle);
        dvdInfo.setReleaseDate(releaseDate);
        dvdInfo.setMPAARating(mpaaRating);
        dvdInfo.setDirectorName(directorName);
        dvdInfo.setStudio(studio);
        dvdInfo.setUserRating(userRating);
        
        return dvdInfo;
    }
    
    public void displayCreateDvdBanner() {
        io.print("=== Create DVD ===");
    }
    
    public void displayCreateSuccessBanner() {
        io.readString("DVD successfully created. Please hit enter to continue");
    }
    
    public void displayDvdList(List<Dvd> dvdList) {
        for (Dvd currentDvd : dvdList) {
            io.print(currentDvd.getTitle() + ": " +
                    currentDvd.getReleaseDate() + " " + 
                    currentDvd.getMPAARating());
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayDisplayAllBanner() {
        io.print("=== Display All DVDs ===");
    }
    
    public void displayDisplayDvdBanner() {
        io.print("=== Display DVD ===");
    }
    
    public String getDvdTitleChoice() {
        return io.readString("Please enter the DVD Title.");
    }
    
    public void displayDvd(Dvd dvd) {
        if (dvd != null) {
            io.print(dvd.getTitle());
            io.print(dvd.getReleaseDate());
            io.print(dvd.getMPAARating());
            io.print(dvd.getDirectorName());
            io.print(dvd.getStudio());
            io.print(dvd.getUserRating());
        } else {
            io.print("No such DVD.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayRemoveDvdBanner() {
        io.print("=== Remove DVD ===");
    }
    
    public void displayRemoveSuccessBanner() {
        io.readString("DVD successfully removed. Please hit enter to continue.");
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }
    
    public void displayUnknownCommandBanner() { 
        io.print("Unknown Command!!! Try again...");
        io.print("");
    }
    
    public void displayEditDvdBanner() {
        io.print("=== Editing DVD ===");
    }
    
    public int menuEditDvd() {
        io.print("Choose what to edit: ");
        io.print("1. Title");
        io.print("2. Release Date");
        io.print("3. MPAA Rating");
        io.print("4. Director's Name");
        io.print("5. Studio");
        io.print("6. User Rating or Review");
        io.print("7. Quit");
        
        return io.readInt("Please select from the above choice.", 1, 7);
    }
    
    public Dvd editTitle(Dvd dvd) {
        Dvd newTitledDvd = new Dvd(io.readString("Edit Title now: "));
        newTitledDvd.setReleaseDate(dvd.getReleaseDate());
        newTitledDvd.setMPAARating(dvd.getMPAARating());
        newTitledDvd.setDirectorName(dvd.getDirectorName());
        newTitledDvd.setStudio(dvd.getStudio());
        newTitledDvd.setUserRating(dvd.getUserRating());
        return newTitledDvd;
    }
    
    public void editReleaseDate(Dvd dvd) {
        dvd.setReleaseDate(io.readString("Edit release date now: "));
        io.readString("Please enter to continue.");
    }
    
    public void editMPAARating(Dvd dvd) {
        dvd.setMPAARating(io.readString("Edit MPAA rating now: "));
        io.readString("Please enter to continue.");
    }
    
    public void editDirectName(Dvd dvd) {
        dvd.setDirectorName(io.readString("Edit Director's name now: "));
        io.readString("Please enter to continue.");
    }
    
    public void editStudio(Dvd dvd) {
        dvd.setStudio(io.readString("Edit studio now: "));
        io.readString("Please enter to continue.");
    }
    
    public void editUserRating(Dvd dvd) {
        dvd.setUserRating(io.readString("Edit user rating or review now: "));
        io.readString("Please enter to continue.");
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public void displaySearchBanner() {
        io.print("=== Search Title ===");
    }
    
    
    public void displaySearchSuccessBanner() {
        io.readString("Search success! Please enter to continue.");
    }
    
    public String getKeyWord() {
        return io.readString("Please enter a key word: ");
    }
}
