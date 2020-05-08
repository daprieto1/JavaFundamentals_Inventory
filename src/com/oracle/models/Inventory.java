package com.oracle.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Inventory implements Serializable{
	
	private List<Product> products;

	public Inventory() {		
		this.products = new ArrayList<>();
	}	
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Product p : this.products)
			sb.append(p.toString());
		return sb.toString();
	}

}
