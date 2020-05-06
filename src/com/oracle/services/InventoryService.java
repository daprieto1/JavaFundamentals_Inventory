package com.oracle.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.oracle.models.Inventory;
import com.oracle.models.Product;
import com.oracle.persistence.FileObjectStream;

public class InventoryService implements IInventoryService {

	private FileObjectStream stream;
	private Inventory inventory;

	public static final String INVENTORY_FILE = "Inventory.orcl";

	public InventoryService() throws ClassNotFoundException, IOException {
		this.stream = new FileObjectStream(INVENTORY_FILE);
		try {
			this.inventory = (Inventory) this.stream.get();
		} catch (FileNotFoundException e) {
			this.inventory = new Inventory();
		}
	}

	@Override
	public Inventory getInventory() {
		return this.inventory;
	}

	@Override
	public void saveProductToInventory(Product p) throws IOException {
		this.inventory.addToInventory(p);
		this.stream.save(this.inventory);
	}

}
