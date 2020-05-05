package com.oracle.test;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import com.oracle.models.DVD;
import com.oracle.models.InventoryException;
import com.oracle.models.Product;

public class ProductTest {

	private Product product;

	public static final int QTY_TEST = 5;
	public static final double PRICE_TEST = 10.0;
	
	public static Product getTestProduct() {
		return new Product("test", ProductTest.QTY_TEST, ProductTest.PRICE_TEST);
	}

	@BeforeEach
	public void init() {
		this.product = ProductTest.getTestProduct();
	}

	@Test
	public void testGetInventoryValue() {
		assertEquals(this.product.getInventoryValue(), QTY_TEST * PRICE_TEST);
	}

	@Test
	public void testAddToStock() {
		int newStock = 5;
		this.product.addStock(newStock);

		assertEquals(this.product.getQtyInStock(), QTY_TEST + newStock);
	}

	@Test
	public void testDeductToStock() {

		try {
			int deductStock = 3;
			this.product.deductStock(deductStock);
			assertEquals(this.product.getQtyInStock(), QTY_TEST - deductStock);

		} catch (InventoryException e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}

	}

	@Test
	public void testDeductToStockShouldFail() {
		Exception e = assertThrows(InventoryException.class, () -> this.product.deductStock(10));

		assertTrue(e.getMessage().contains(InventoryException.BAD_STOCK));
		assertEquals(this.product.getQtyInStock(), QTY_TEST);
	}
}
