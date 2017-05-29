package com.ibport.model;

public class ShiseidoProduct extends Product{
	private String productBrand;
	private String size;
	
	public String getProductBrand() {
		return productBrand;
	}

	public void setProductBrand(String productBrand) {
		this.productBrand = productBrand;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public String toString() {
		return productName + "\t" + productCode + "\t"
				+ productPrice + "\t"  + productBrand + "\t" + size;
	}

}
