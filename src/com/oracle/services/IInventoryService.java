package com.oracle.services;

import java.sql.SQLException;

import com.oracle.models.Inventory;
import com.oracle.models.Product;

public interface IInventoryService {

	public Inventory getInventory();

	public void saveProductToInventory(Product p) throws SQLException;
	
	public void cleanInventory() throws SQLException;
	

}
