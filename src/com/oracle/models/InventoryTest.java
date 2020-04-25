package com.oracle.models;

import java.util.ArrayList;
import java.util.Scanner;

public class InventoryTest {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		ArrayList<Product> products = new ArrayList<Product>();
		
		int idTemp;
		String nameTemp;
		double priceTemp;
		int qtyTemp;
		int numProducts = 1;
		String userOption;
		
		do {
			
			System.out.println("Info of the product " + numProducts);
			
			System.out.print("Please enter name : ");
			nameTemp = in.next();
			System.out.print("Please enter price : ");
			priceTemp = in.nextDouble();
			System.out.print("Please enter quantity : ");
			qtyTemp = in.nextInt();
					
			numProducts++;
			products.add(new Product(nameTemp, qtyTemp,priceTemp));
			System.out.println("Do you want to add more products?");
			userOption = in.next();
			
		}while(userOption.toUpperCase().equals("S") || userOption.toUpperCase().equals("SI"));
		
		
		in.close();
		
		products.add(new Product("Deditos", 75, 5600.35));
		products.add(new Product("Bocadillo", 324, 6344.35));
		products.add(new Product("Aromatica", 634, 3468.35));
		products.add(new Product("Chocolate", 24, 2346.35));
		
		products.get(1).setActive(false);
		
		for(Product p : products)
			System.out.println(p);

	}

}
