package com.oracle.models;

/**
 * The expetions of the inventory
 * @author diegoprietotorres
 *
 */
public class InventoryException extends Exception {

	/**
	 * If a product not exists
	 */
	public static final String NOT_FOUND_PRODUCT = "The product not exists";

	/**
	 * Is a product has negative stock
	 */
	public static final String BAD_STOCK = "The stock not support negative values";
	
	public InventoryException(String message) {
		super(message);
	}
}
