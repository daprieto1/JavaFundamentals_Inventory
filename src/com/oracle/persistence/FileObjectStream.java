package com.oracle.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileObjectStream implements IFilePersistence{

	private String fileName;
	
	public FileObjectStream(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public void save(Object obj) throws IOException {
		try {
			FileOutputStream fileOutput = new FileOutputStream(this.fileName);
			ObjectOutputStream output = new ObjectOutputStream(fileOutput);
			
			output.writeObject(obj);
			
			output.close();
			fileOutput.close();
		}catch (FileNotFoundException e) {
			System.out.println("File not found");	
			throw e;
		}catch (IOException e) {
			System.out.println("Error initializing data stream");
			throw e;
		}
		
	}
	
	public void save(ArrayList<Object> array) throws IOException {
		try {
			FileOutputStream fileOutput = new FileOutputStream(this.fileName);
			ObjectOutputStream output = new ObjectOutputStream(fileOutput);
			
			for(Object o : array)
				output.writeObject(o);
			
			output.close();
			fileOutput.close();
		}catch (FileNotFoundException e) {
			System.out.println("File not found");	
			throw e;
		}catch (IOException e) {
			System.out.println("Error initializing data stream");
			throw e;
		}
	}

	@Override
	public Object get() throws IOException, ClassNotFoundException {
		try {
			FileInputStream fileInput = new FileInputStream(this.fileName);
			ObjectInputStream input = new ObjectInputStream(fileInput);
			
			return input.readObject();	
		}catch (FileNotFoundException e) {
			System.out.println("File not found");	
			throw e;
		}catch (IOException e) {
			System.out.println("Error initializing data stream");
			throw e;
		}	catch(ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
	}
	

}
