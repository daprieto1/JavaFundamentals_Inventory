package com.oracle.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.oracle.models.Product;
import com.oracle.persistence.DAOProduct;

public class DAOProductTest {

	private static DAOProduct daoProduct;

	private static ArrayList<Product> createdProducts;

	@BeforeAll
	public static void initBeforeAll() {
		daoProduct = new DAOProduct();
		createdProducts = new ArrayList<>();
	}

	@Test
	public void testFindAll() throws SQLException {
		insertProduct();

		List<Product> products = daoProduct.findAll();

		assertNotNull("list of products is null", products);
		assertTrue("The num of products should be greater than 0", products.size() > 0);
	}

	@Test
	public void testInsert() throws SQLException {
		Product product = ProductTest.getTestProduct();
		boolean result = daoProduct.insert(product);

		assertTrue("the product was not inserted successfuly", result);

		createdProducts.add(product);
		Product productFromDb = daoProduct.findById(product.getItemId());

		assertNotNull("the product from db is null", productFromDb);
		assertTrue("the product from db is not the same that the original product", product.equals(productFromDb));
	}

	@Test
	public void testFindById() throws SQLException {
		UUID id = insertProduct();

		Product product = daoProduct.findById(id);

		assertNotNull(product);
		assertTrue("The product exists in the created product list",
				createdProducts.stream().anyMatch(p -> p.equals(product)));
	}
	
	@Test
	public void testUpdate() throws SQLException {
		UUID id = insertProduct();
		Product productBefore = createdProducts.stream().filter(p -> p.getItemId().equals(id)).findFirst().orElse(null);

		assertNotNull("productBefore is null", productBefore);

		productBefore.setName("Change Test");
		productBefore.setActive(false);
		productBefore.setQtyInStock(ProductTest.QTY_TEST * 2);
		productBefore.setPrice(ProductTest.PRICE_TEST * 2);
		
		boolean result = daoProduct.update(productBefore);
		
		assertTrue("the update was not successful", result);
		
		Product productAfter = daoProduct.findById(id);
		
		assertNotNull(productAfter);
		assertEquals(productBefore.getItemId(), productAfter.getItemId());
		assertEquals(productBefore.getName(), productAfter.getName());
		assertEquals(productBefore.getPrice(), productAfter.getPrice());
		assertEquals(productBefore.getQtyInStock(), productAfter.getQtyInStock());
		assertEquals(productBefore.isActive(), productAfter.isActive());
	}

	@Test
	public void testDelete() throws SQLException {
		UUID id = insertProduct();

		boolean result = daoProduct.delete(id);

		assertTrue("the product was not deleted", result);

		Product product = daoProduct.findById(id);

		assertNull("the product was founded into the db", product);
	}

	/**
	 * Insert a product for testing.
	 * 
	 * @return
	 * @throws SQLException
	 */
	private UUID insertProduct() throws SQLException {
		Product product = ProductTest.getTestProduct();
		boolean result = daoProduct.insert(product);

		assertTrue("in methos insert product fail", result);

		createdProducts.add(product);
		return product.getItemId();
	}

	@AfterAll
	public static void clean() throws SQLException {
		for (Product p : createdProducts) {
			if (daoProduct.findById(p.getItemId()) != null) {
				assertTrue("fail when try to delete product in clean method", daoProduct.delete(p.getItemId()));
				assertNull("fail because find a deleted product in clean method", daoProduct.findById(p.getItemId()));
			}
		}
	}
}
