package com.oracle.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.oracle.models.DVD;
import com.oracle.models.Product;

public class DVDTest {

	private DVD dvd;
	private Product product;
	
	public static final double STOCK_FACTOR = 1.05; 

	@BeforeEach
	public void init() {
		product = ProductTest.getTestProduct();
		dvd = new DVD(product, 234, 34, "test studio");
	}

	@Test
	public void testGetInventoryValue() {
		double value = dvd.getInventoryValue();

		assertEquals(value, STOCK_FACTOR * product.getInventoryValue());
	}
}
