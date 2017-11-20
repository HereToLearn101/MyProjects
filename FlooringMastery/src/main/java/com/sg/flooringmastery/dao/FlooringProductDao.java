/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.util.List;

/**
 *
 * @author tedis
 */
public interface FlooringProductDao {
    Product addProduct(Product product) throws FlooringPersistenceException;
    
    Product removeProduct(String productType) throws FlooringPersistenceException;
    
    Product getProduct(String productType) throws FlooringPersistenceException;
    
    List<Product> getAllProducts() throws FlooringPersistenceException;
    
    void editProduct(String oldProductType, Product updatedProduct) throws FlooringPersistenceException;
}