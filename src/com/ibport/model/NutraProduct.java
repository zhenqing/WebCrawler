package com.ibport.model;
public class NutraProduct extends Product{


	private String productDescription;
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	@Override
	public String toString() {
		return productName + "\t" + productCode + "\t"
				+ productPrice + "\t" + (productDescription==null?"":productDescription);
	}

}
