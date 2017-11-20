/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author tedis
 */
public class Sighting {
    
    private int id;
    private LocalDate date;
    private Location location;
    private List<HeroVillain> hVList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public List<HeroVillain> gethVList() {
        return hVList;
    }

    public void sethVList(List<HeroVillain> hVList) {
        this.hVList = hVList;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.date);
        hash = 61 * hash + Objects.hashCode(this.location);
        hash = 61 * hash + Objects.hashCode(this.hVList);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Sighting other = (Sighting) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        if (!Objects.equals(this.location, other.location)) {
            return false;
        }
        if (!Objects.equals(this.hVList, other.hVList)) {
            return false;
        }
        return true;
    }
    
    
}
