package com.oracle.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.io.IOException;

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
	public void init() throws ClassNotFoundException, IOException {		
		this.inventoryService = new InventoryService();
	}
	
	@Test
	public void testAddProductAndGetInventory() throws IOException {
		Inventory inventory = this.inventoryService.getInventory();
		
		assertEquals(inventory.getNumItems(), 0);
		
		Product p = ProductTest.getTestProduct();
		this.inventoryService.saveProductToInventory(p);
		
		Product p2 = ProductTest.getTestProduct();
		this.inventoryService.saveProductToInventory(p2);
		
		assertEquals(inventory.getNumItems(), 2);
		
		inventory = this.inventoryService.getInventory();
		assertEquals(inventory.getNumItems(), 2);
	}
	
	@AfterEach
	public void finalize() {
		File file = new File(inventoryService.INVENTORY_FILE);
		file.delete();
	}
}
