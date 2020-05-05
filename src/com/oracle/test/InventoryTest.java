package com.oracle.test;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.oracle.models.Inventory;
import com.oracle.models.InventoryException;
import com.oracle.models.Product;

public class InventoryTest {

	private Inventory inventory;

	public static final int ADD_PRODUCTS = 3;

	@BeforeEach
	public void init() {
		this.inventory = new Inventory();
		
		int numItems = ADD_PRODUCTS;
		while (numItems > 0) {
			inventory.addToInventory(ProductTest.getTestProduct());
			numItems--;
		}
	}

	@Test
	public void testAddToInventory() {
		assertEquals(inventory.getNumItems(), ADD_PRODUCTS);
	}

	@Test
	public void testFindProduct() throws InventoryException {
		Product originalProduct = ProductTest.getTestProduct();
		inventory.addToInventory(originalProduct);

		Product foundProduct = inventory.findProduct(originalProduct.getItemId());

		assertEquals(originalProduct, foundProduct);
	}

	@Test
	public void testFindProductWhenNotExist() {
		Exception e = assertThrows(InventoryException.class, () -> inventory.findProduct(UUID.randomUUID()));

		assertEquals(e.getMessage(), InventoryException.NOT_FOUND_PRODUCT);
	}
}
