/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.dto;

/**
 *
 * @author tedis
 */
public class Dvd {
    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String directorName;
    private String studio;
    private String userRating;
    
    public Dvd (String title) {
        this.title = title;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
    
    public String getReleaseDate() {
        return this.releaseDate;
    }
    
    public void setMPAARating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }
    
    public String getMPAARating() {
        return this.mpaaRating;
    }
    
    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }
    
    public String getDirectorName() {
        return this.directorName;
    }
    
    public void setStudio(String studio) {
        this.studio = studio;
    }
    
    public String getStudio() {
        return this.studio;
    }
    
    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }
    
    public String getUserRating() {
        return this.userRating;
    }
}
