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
		int numProducts;
		
		/*System.out.println("How many products do you want to enter?");
		numProducts = in.nextInt();
		
		for(int i = 0; i < numProducts;i++) {
			System.out.println("Info of the product " + (i+1) + " of " + numProducts);
			System.out.print("Please enter Id : ");
			idTemp = in.nextInt();
			System.out.print("Please enter name : ");
			nameTemp = in.next();
			System.out.print("Please enter price : ");
			priceTemp = in.nextDouble();
			System.out.print("Please enter quantity : ");
			qtyTemp = in.nextInt();
					
			products.add(new Product(idTemp, nameTemp, qtyTemp,priceTemp));
		}*/
		
		in.close();
		
		products.add(new Product(1, "Deditos", 75, 5600.35));
		products.add(new Product(2, "Bocadillo", 324, 6344.35));
		products.add(new Product(3, "Aromatica", 634, 3468.35));
		products.add(new Product(4, "Chocolate", 24, 2346.35));
		
		products.get(1).setActive(false);
		
		for(Product p : products)
			System.out.println(p);

	}

}
