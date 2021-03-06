/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author tedis
 */
public class Item {
    private String itemName;
    private BigDecimal itemCost;
    private int quantity;
    
    public Item(String itemName) {
        this.itemName = itemName;
    }
    
    public String getItemName() {
        return itemName;
    }
    
    public BigDecimal getItemCost() {
        return itemCost;
    }
    
    public void setItemCost(BigDecimal itemCost) {
        this.itemCost = itemCost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.itemName);
        hash = 79 * hash + Objects.hashCode(this.itemCost);
        hash = 79 * hash + this.quantity;
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
        final Item other = (Item) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemCost, other.itemCost)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return " |Item: " + itemName + " |Cost: " + itemCost;
    }
}
