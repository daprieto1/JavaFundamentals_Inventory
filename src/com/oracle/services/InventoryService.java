package com.oracle.services;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import com.oracle.models.Inventory;
import com.oracle.models.Product;
import com.oracle.persistence.DAOProduct;
import com.oracle.persistence.FileObjectStream;

public class InventoryService implements IInventoryService {

	private DAOProduct daoProduct;
	private Inventory inventory;

	public static final String INVENTORY_FILE = "Inventory.orcl";

	public InventoryService() throws SQLException {
		this.daoProduct = new DAOProduct();
		this.inventory = new Inventory();
		this.inventory.setProducts(this.daoProduct.findAll());
	}

	@Override
	public Inventory getInventory() {
		return this.inventory;
	}

	@Override
	public void saveProductToInventory(Product product) throws SQLException {
		this.inventory.addToInventory(product);
		daoProduct.insert(product);
	}

	@Override
	public void cleanInventory() throws SQLException {
		for(Product p : this.inventory.getProducts())
			daoProduct.delete(p.getItemId());
	}

}
