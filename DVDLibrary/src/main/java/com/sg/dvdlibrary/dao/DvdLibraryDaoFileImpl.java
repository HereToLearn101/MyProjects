/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dao;

import com.sg.dvdlibrary.dto.Dvd;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author tedis
 */
public class DvdLibraryDaoFileImpl implements DvdLibraryDao {

    private Map<String, Dvd> dvds = new HashMap<>();
    public static final String DVD_FILE = "dvd.txt";
    public static final String DELIMITER = "::";

    @Override
    public Dvd addDvd(String dvdTitle, Dvd dvd) throws DvdLibraryDaoException{
        loadDvdLibrary();
        Dvd newDvd = dvds.put(dvdTitle, dvd);
        writeDvdLibrary();
        return newDvd;
    }

    @Override
    public List<Dvd> getAllDvds() throws DvdLibraryDaoException{
        loadDvdLibrary();
        return new ArrayList<Dvd>(dvds.values());
    }

    @Override
    public Dvd getDvd(String dvdTitle) throws DvdLibraryDaoException{
        // Remember that the value of the key is the dvd object that holds all
        // the dvd information.
        loadDvdLibrary();
        return dvds.get(dvdTitle);
    }

    @Override
    public Dvd removeDvd(String dvdTitle) throws DvdLibraryDaoException{
        loadDvdLibrary();
        Dvd removedDvd = dvds.remove(dvdTitle);
        writeDvdLibrary();
        return removedDvd;
    }
    
    // for editing Dvds, specifically for rename the title of dvds, maybe you should
    // try to remove them first. Try this first... if not solved with this, then
    // remove for all changing dvd fields, then put back to map once all dvd info
    // has changed.
    @Override
    public void editDvd(String titleToEdit, Dvd dvd) throws DvdLibraryDaoException{
        addDvd(titleToEdit, dvd);
    }
    
    @Override
    public List<Dvd> getMatchingDvds(String thisKeyWord) throws DvdLibraryDaoException{
        List<Dvd> allDvds = getAllDvds();
        List<Dvd> matchingDvds = new ArrayList<>();
        
        for (Dvd dvd : allDvds) {
            if(dvd.getTitle().contains(thisKeyWord)){
                matchingDvds.add(dvd);
            }
        }
        
        return matchingDvds;
    }

    private void loadDvdLibrary() throws DvdLibraryDaoException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(DVD_FILE)));
        } catch (FileNotFoundException e) {
            throw new DvdLibraryDaoException(
                    "-_- Could not load dvd library data into memory.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;
        // currentTokens holds each of the parts of the currentLine after it has
        // been split on our DELIMITER
        String[] currentTokens;
        // Go through DVD_FILE line by line, decoding each line into a 
        // Dvd object.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // break up the line into tokens
            currentTokens = currentLine.split(DELIMITER);
            // Create a new Dvd object and put it into the map of DVDs
            // NOTE FOR APPRENTICES: We are going to use the dvd title
            // which is currentTokens[0] as the map key for our dvd object.
            // We also have to pass the dvd title into the Dvd constructor
            Dvd currentDvd = new Dvd(currentTokens[0]);
            // Set the remaining vlaues on currentDvd manually
            currentDvd.setReleaseDate(currentTokens[1]);
            currentDvd.setMPAARating(currentTokens[2]);
            currentDvd.setDirectorName(currentTokens[3]);
            currentDvd.setStudio(currentTokens[4]);
            currentDvd.setUserRating(currentTokens[5]);

            // Put currentDvd into the map using Title as the key
            dvds.put(currentDvd.getTitle(), currentDvd);
        }
        // close scanner
        scanner.close();
    }

    /**
     * Writes all dvds in the library out to a DVD_FILE.
     *
     * @throws DvdLibraryDaoException if an error occurs writing to the file
     */
    private void writeDvdLibrary() throws DvdLibraryDaoException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(DVD_FILE));
        } catch (IOException e) {
            throw new DvdLibraryDaoException(
                    "Could not save dvd data.", e);
        }

        // Write out the Student objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the dvd map,
        // get the Collection of Dvds and iterate over them but we've
        // already created a method that gets a List of Dvds so
        // we'll reuse it.
        List<Dvd> dvdList = this.getAllDvds();
        for (Dvd currentDvd : dvdList) {
            // write the Dvd object to the file
            out.println(currentDvd.getTitle() + DELIMITER
                    + currentDvd.getReleaseDate() + DELIMITER
                    + currentDvd.getMPAARating() + DELIMITER
                    + currentDvd.getDirectorName() + DELIMITER
                    + currentDvd.getStudio() + DELIMITER
                    + currentDvd.getUserRating());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}
