package com.ibport.isr;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.ibport.model.NutraProduct;


public class NutraWeb {
	public static String URL_ROOT = "http://www.nutrabio.com/category/herbal/";
	public static String DIR_DATA = "data";
	
	public static void main(String[] args) throws Exception {
		
		
		// check directory
		File dirData = new File(DIR_DATA);
		if(!dirData.isDirectory()){
			if(!dirData.mkdir()){
				throw new Exception("No directory found for saving data!"); 
			}
		}
		

		String listURL = URL_ROOT ;
		System.out.println("parsing.. " + listURL);
		String pattern = "\\w+";
	      // Create a Pattern object
	      Pattern r = Pattern.compile(pattern);
	
		try {
			
			Document docRoot = Jsoup.connect(listURL).userAgent("Mozilla").timeout(5000).get();
//			System.out.println(docRoot);
			for(Element ele : docRoot.select(".product-item .padding")){
//				System.out.println(ele);
				NutraProduct product = new NutraProduct();
				Element name = ele.select(".product-name a").first();
				if(name.text().contains(" - ")){
					String[] productName = name.text().split(" - ");
					
					product.setProductName(productName[0]);
					product.setProductDescription(productName[1]);
				}else{
					product.setProductName(name.text());
				}
				
				
				
				Element code = ele.getElementsByClass("product-code").first();
				product.setProductCode(code.text().replace("Item: ", ""));
				
			    
			      
				Element price = ele.getElementsByClass("product-price").first();
				product.setProductPrice(price.text());
				
				System.out.println(product);
				Thread.sleep(100);
			}
		} catch (IOException e) { System.err.println("Error: " + e.getMessage()); }
		
	}
	

}
