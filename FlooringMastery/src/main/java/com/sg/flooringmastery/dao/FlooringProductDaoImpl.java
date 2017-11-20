/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author tedis
 */
public class FlooringProductDaoImpl implements FlooringProductDao {
    
    private Map<String, Product> products = new HashMap<>();
    private static final String PRODUCTS_FILE = "Products.txt";
    private static final String DELIMITER = ",";

    @Override
    public Product addProduct(Product product) throws FlooringPersistenceException {
        loadAllProducts();
        Product newProduct = products.put(product.getProductType(), product);
        writeProducts();
        return newProduct;
    }

    @Override
    public Product removeProduct(String productType) throws FlooringPersistenceException {
        loadAllProducts();
        Product removedProduct = products.remove(productType);
        writeProducts();
        return removedProduct;
    }

    @Override
    public Product getProduct(String productType) throws FlooringPersistenceException {
        loadAllProducts();
        return products.get(productType);
    }

    @Override
    public List<Product> getAllProducts() throws FlooringPersistenceException {
        loadAllProducts();
        return new ArrayList<Product>(products.values());
    }

    @Override
    public void editProduct(String oldProductType, Product updatedProduct) throws FlooringPersistenceException {
        removeProduct(oldProductType);
        addProduct(updatedProduct);
    }
    
    private void loadAllProducts() throws FlooringPersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(PRODUCTS_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException(
                    "-_- Could not load product data into memory.", e);
        }
        String currentLine;
        String[] currentTokens;
       
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Product currentProduct = new Product(currentTokens[0]);
            currentProduct.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
            currentProduct.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));

            products.put(currentProduct.getProductType(), currentProduct);
        }
        // close scanner
        scanner.close();
    }
    
    private void writeProducts() throws FlooringPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(PRODUCTS_FILE));
        } catch (IOException e) {
            throw new FlooringPersistenceException(
                    "Could not save product data.", e);
        }

        // Write out the Item objects to the items file.
        List<Product> productsList = this.getAllProducts();
        for (Product currentProduct : productsList) {
            // write the Item object to the file
            out.println(currentProduct.getProductType() + DELIMITER
                    + currentProduct.getCostPerSquareFoot()+ DELIMITER
                    + currentProduct.getLaborCostPerSquareFoot());
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }
}