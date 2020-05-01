package com.oracle.models;

public class DVD extends Product {

	private int duration;
	private int ageRange;
	private String filmStudio;

	public DVD(String name, int qtyInStock, double price, int duration, int ageRange, String filmStudio) {
		super(name, qtyInStock, price);
		this.duration = duration;
		this.ageRange = ageRange;
		this.filmStudio = filmStudio;			
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getAgeRange() {
		return ageRange;
	}

	public void setAgeRange(int ageRange) {
		this.ageRange = ageRange;
	}

	public String getFilmStudio() {
		return filmStudio;
	}

	public void setFilmStudio(String filmStudio) {
		this.filmStudio = filmStudio;
	}
	
	@Override
	public double getInventoryValue() {
		return 1.05 * (super.getPrice() * super.getQtyInStock());
	}
	
	@Override
	public String toString() {
		return super.toString()
				+ "\nDuration 				: " + this.duration + " min"
				+ "\nAge range 				: " + this.ageRange + " years"
				+ "\nStudio 				: " + this.filmStudio;
	}

}
