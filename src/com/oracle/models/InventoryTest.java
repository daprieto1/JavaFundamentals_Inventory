package com.oracle.models;

public class InventoryTest {

	public static void main(String[] args) {
		
		Product p1 = new Product();
		Product p2 = new Product(1, "Deditos", 75, 5600.35);
		
		System.out.println(p1);
		System.out.println(p2);

	}

}
