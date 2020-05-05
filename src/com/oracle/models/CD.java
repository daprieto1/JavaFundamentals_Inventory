package com.oracle.models;

public class CD extends Product {

	private String artist;
	private int numOfSongs;
	private String discOwner;		
	
	public CD(Product p, String artist, int numOfSongs, String discOwner) {	
		super(p.getName(),p.getQtyInStock(), p.getPrice());
		this.artist = artist;
		this.numOfSongs = numOfSongs;
		this.discOwner = discOwner;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getNumOfSongs() {
		return numOfSongs;
	}

	public void setNumOfSongs(int numOfSongs) {
		this.numOfSongs = numOfSongs;
	}

	public String getDiscOwner() {
		return discOwner;
	}

	public void setDiscOwner(String discOwner) {
		this.discOwner = discOwner;
	}

	@Override
	public String toString() {
		return super.toString() 
				+ "\nArtist : " + this.artist 
				+ "\nNumber of Songs : " + this.numOfSongs 
				+ "\nOwner : " + this.discOwner;
	}

}
