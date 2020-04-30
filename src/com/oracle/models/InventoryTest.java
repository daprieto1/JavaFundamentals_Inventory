package com.oracle.models;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.UUID;

public class InventoryTest {

	private static ArrayList<Product> products = new ArrayList<Product>();

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);

		int numProducts = 1;
		String userOption;

		/*
		 * System.out.print("Enter the number of products you would like to add : ");
		 * int maxProducts = in.nextInt();
		 * 
		 * do { try { System.out.println("Info of the product " + numProducts);
		 * addToInventory(in); numProducts++; maxProducts--; } catch (Exception e) {
		 * System.out.println("Incorrect value entered."); in.nextLine(); }
		 * 
		 * } while (maxProducts > 0);
		 */

		products.add(new Product("Deditos", 75, 5600.35));
		products.add(new Product("Bocadillo", 324, 6344.35));
		products.add(new Product("Aromatica", 634, 3468.35));
		products.add(new Product("Chocolate", 24, 2346.35));

		int menuOption = -1;
		do {
			menuOption = getMenuOption(in);
			executeAction(in, menuOption);
		} while (menuOption != 0);

		in.close();

	}

	/**
	 * Display all products.
	 * 
	 * @param products
	 */
	static void displayInventory() {
		System.out.println("Display from Array List");
		for (Product p : products)
			System.out.println(p);
	}

	static void displayInventory(String name) {
		System.out.println("Display using Array and String");
		products.stream().filter(p -> p.getName().equals(name)).forEach(p -> System.out.println(p));
	}

	/**
	 * Add a new product to the inventory.
	 * 
	 * @param products - Database of products
	 * @param in       - Scanner
	 */
	static void addToInventory(Scanner in) {
		String nameTemp;
		double priceTemp;
		int qtyTemp;

		System.out.print("Please enter name : ");
		nameTemp = in.next();
		System.out.print("Please enter price : ");
		priceTemp = in.nextDouble();
		System.out.print("Please enter quantity : ");
		qtyTemp = in.nextInt();

		products.add(new Product(nameTemp, qtyTemp, priceTemp));
	}

	static Product getProduct(UUID id) throws InventoryException {
		Product product = products.stream().filter(p -> p.getItemId().equals(id)).findFirst().orElse(null);

		if (product == null)
			throw new InventoryException(InventoryException.NOT_FOUND_PRODUCT);

		return product;
	}

	/**
	 * Get num of items in the inventory.
	 * 
	 * @return
	 */
	static int getNumProducts() {
		return products.size();
	}

	static int getMenuOption(Scanner in) {
		int menuOption = -1;

		do {
			try {
				System.out.println("\n\n 1. View Inventory " + "\n 2. Add Stock" + "\n 3. Deduct Stock"
						+ "\n 4. Discontinue Product" + "\n 0. Exit");
				System.out.print("Please enter a menu option : ");
				menuOption = in.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Incorrect data type");
				in.nextLine();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.out.println(e.getStackTrace());
				in.nextLine();
			}

		} while (menuOption < 0 || menuOption > 4);

		in.nextLine();
		return menuOption;
	}

	/**
	 * Execute the particular action
	 * 
	 * @param menuOption
	 */
	static void executeAction(Scanner in, int menuOption) {
		Product product = null;
		String idString = "";

		switch (menuOption) {
		case 1:
			displayInventory();
			break;
		case 2:
			do {
				try {
					System.out.print("Insert the product id : ");
					idString = in.nextLine();
					UUID id = UUID.fromString(idString);
					System.out.print("Insert the additional stock : ");
					int quantity = in.nextInt();
					in.nextLine();
					product = getProduct(id);
					product.addStock(quantity);
				} catch (InventoryException e) {
					System.out.println(e.getMessage() + " with id = " + idString);
				}
			} while (product == null);
			break;
		case 3:
			do {
				try {
					System.out.print("Insert the product id : ");
					idString = in.nextLine();
					UUID id = UUID.fromString(idString);
					product = getProduct(id);
					try {
						System.out.print("Insert the deduct stock : ");
						int quantity = in.nextInt();
						in.nextLine();
						product.deductStock(quantity);
					} catch (InventoryException e) {
						System.out.println(e.getMessage() + " final stock = " + (product.getQtyInStock()));
						product = null;
					}
				} catch (InventoryException e) {
					System.out.println(e.getMessage() + " with id = " + idString);
				}
			} while (product == null);
			break;
		case 4:
			do {
				try {
					System.out.print("Insert the product id : ");
					idString = in.nextLine();
					UUID id = UUID.fromString(idString);
					product = getProduct(id);
					product.setActive(false);
				} catch (InventoryException e) {
					System.out.println(e.getMessage() + " with id = " + idString);
				}
			} while (product == null);

			break;
		}
	}

}
