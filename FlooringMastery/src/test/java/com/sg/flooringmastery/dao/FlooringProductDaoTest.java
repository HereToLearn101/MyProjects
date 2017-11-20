/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooringmastery.dao;

import com.sg.flooringmastery.dto.Product;
import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author tedis
 */
public class FlooringProductDaoTest {

    private FlooringProductDao productDao = new FlooringProductDaoImpl();

    public FlooringProductDaoTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        List<Product> products = productDao.getAllProducts();
        for (Product product : products) {
            productDao.removeProduct(product.getProductType());
        }
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addProduct method, of class FlooringProductDao.
     */
    @Test
    public void testAddProduct() throws Exception {
        Product product = new Product("Spider Web");
        product.setCostPerSquareFoot(new BigDecimal("200.00"));
        product.setLaborCostPerSquareFoot(new BigDecimal("10.00"));

        productDao.addProduct(product);

        Product addedProduct = productDao.getProduct(product.getProductType());

        assertEquals(product, addedProduct);

    }

    /**
     * Test of removeProduct method, of class FlooringProductDao.
     */
    @Test
    public void testRemoveProduct() throws Exception {
        Product product = new Product("Spider Web");
        product.setCostPerSquareFoot(new BigDecimal("200.00"));
        product.setLaborCostPerSquareFoot(new BigDecimal("10.00"));

        productDao.addProduct(product);
        productDao.removeProduct(product.getProductType());

        List<Product> products = productDao.getAllProducts();

        assertEquals(0, products.size());

    }

    /**
     * Test of getProduct method, of class FlooringProductDao.
     */
    @Test
    public void testGetProduct() throws Exception {
        Product product = new Product("Spider Web");
        product.setCostPerSquareFoot(new BigDecimal("200.00"));
        product.setLaborCostPerSquareFoot(new BigDecimal("10.00"));

        productDao.addProduct(product);
        Product currentProduct = productDao.getProduct(product.getProductType());

        assertEquals(product, currentProduct);
    }

    /**
     * Test of getAllProducts method, of class FlooringProductDao.
     */
    @Test
    public void testGetAllProducts() throws Exception {
        Product product = new Product("Spider Web");
        product.setCostPerSquareFoot(new BigDecimal("200.00"));
        product.setLaborCostPerSquareFoot(new BigDecimal("10.00"));

        productDao.addProduct(product);

        Product product2 = new Product("Speed Force");
        product2.setCostPerSquareFoot(new BigDecimal("100.00"));
        product2.setLaborCostPerSquareFoot(new BigDecimal("20.00"));

        productDao.addProduct(product2);

        List<Product> products = productDao.getAllProducts();
        
        assertEquals(2, products.size());
    }

    /**
     * Test of editProduct method, of class FlooringProductDao.
     */
    @Test
    public void testEditProduct() throws Exception {
        Product product = new Product("Spider Web");
        product.setCostPerSquareFoot(new BigDecimal("200.00"));
        product.setLaborCostPerSquareFoot(new BigDecimal("10.00"));

        productDao.addProduct(product);
        
        Product editedOrder = productDao.getProduct(product.getProductType());
        editedOrder.setCostPerSquareFoot(new BigDecimal("1000.00"));
        editedOrder.setLaborCostPerSquareFoot(new BigDecimal("50.00"));
        
        productDao.editProduct(product.getProductType(), editedOrder);
        
        Product checkOrder = productDao.getProduct(editedOrder.getProductType());
        
        assertEquals(editedOrder, checkOrder);
    }
}
