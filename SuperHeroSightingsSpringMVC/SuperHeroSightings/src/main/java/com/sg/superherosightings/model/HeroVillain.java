/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.superherosightings.model;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author tedis
 */
public class HeroVillain {
    
    private int id;
    private String hVName;
    private String hVDescription;
    private boolean isVillain;
    private List<Power> powers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String gethVName() {
        return hVName;
    }

    public void sethVName(String hVName) {
        this.hVName = hVName;
    }

    public String gethVDescription() {
        return hVDescription;
    }

    public void sethVDescription(String hVDescription) {
        this.hVDescription = hVDescription;
    }

    public boolean isIsVillain() {
        return isVillain;
    }

    public void setIsVillain(boolean isVillain) {
        this.isVillain = isVillain;
    }

    public List<Power> getPowers() {
        return powers;
    }

    public void setPowers(List<Power> powers) {
        this.powers = powers;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.hVName);
        hash = 17 * hash + Objects.hashCode(this.hVDescription);
        hash = 17 * hash + (this.isVillain ? 1 : 0);
        hash = 17 * hash + Objects.hashCode(this.powers);
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
        final HeroVillain other = (HeroVillain) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.isVillain != other.isVillain) {
            return false;
        }
        if (!Objects.equals(this.hVName, other.hVName)) {
            return false;
        }
        if (!Objects.equals(this.hVDescription, other.hVDescription)) {
            return false;
        }
        if (!Objects.equals(this.powers, other.powers)) {
            return false;
        }
        return true;
    }
    
    
}
