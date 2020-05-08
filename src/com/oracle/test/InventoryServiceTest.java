package com.oracle.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.oracle.models.Inventory;
import com.oracle.models.Product;
import com.oracle.services.InventoryService;

public class InventoryServiceTest {

	private InventoryService inventoryService;

	@BeforeEach
	public void init() throws SQLException {
		this.inventoryService = new InventoryService();
	}

	@Test
	public void testAddProductAndGetInventory() throws SQLException {
		Inventory inventory = this.inventoryService.getInventory();

		Product p = ProductTest.getTestProduct();
		this.inventoryService.saveProductToInventory(p);

		Product p2 = ProductTest.getTestProduct();
		this.inventoryService.saveProductToInventory(p2);

		assertTrue(inventory.getNumItems() > 2);

	}

	@AfterEach
	public void finalize() throws SQLException {
		// File file = new File(inventoryService.INVENTORY_FILE);
		// file.delete();
		inventoryService.cleanInventory();
	}
}
