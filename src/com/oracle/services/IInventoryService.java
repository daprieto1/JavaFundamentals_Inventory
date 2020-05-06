package com.oracle.services;

import java.io.IOException;

import com.oracle.models.Inventory;
import com.oracle.models.Product;

public interface IInventoryService {

	public Inventory getInventory();

	public void saveProductToInventory(Product p) throws IOException;
	
	

}
