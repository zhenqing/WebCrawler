package com.ibport.model;

public class SleekhairProduct extends Product {

	private String size;
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return productName + "\t" + productCode + "\t"
		+ productPrice + "\t" + size;
	}

}
