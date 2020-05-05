package com.oracle.models;

import java.util.ArrayList;
import java.util.UUID;

public class Inventory {
	
	private ArrayList<Product> products;

	public Inventory() {		
		this.products = new ArrayList<>();
	}
	
	public void addToInventory(Product product) {
		this.products.add(product);
	}
	
	public int getNumItems() {
		return this.products.size();
	}
	
	public Product findProduct(UUID id) throws InventoryException{
		Product product = this.products.stream().filter(p -> p.getItemId().equals(id)).findFirst().orElse(null);
		if(product == null)
			throw new InventoryException(InventoryException.NOT_FOUND_PRODUCT);
		return product;
	}

}
