/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tedis
 */
public class FlooringProductDaoStubImpl implements FlooringProductDao {
    
    private Product product;
    private List<Product> products = new ArrayList<>();
    
    public FlooringProductDaoStubImpl () {
        this.product = new Product("Spider Web");
        product.setCostPerSquareFoot(new BigDecimal("200.00"));
        product.setLaborCostPerSquareFoot(new BigDecimal("10.00"));
        
        products.add(product);
    }

    @Override
    public Product addProduct(Product product) throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product removeProduct(String productType) throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Product getProduct(String productType) throws FlooringPersistenceException {
        if (productType.equals(product.getProductType())) {
            return product;
        }
        return null;
    }

    @Override
    public List<Product> getAllProducts() throws FlooringPersistenceException {
        return products;
    }

    @Override
    public void editProduct(String oldProductType, Product updatedProduct) throws FlooringPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
