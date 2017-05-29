package com.ibport.isr;

import java.io.IOException;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.ibport.model.Product;
import com.ibport.model.ShiseidoProduct;

public class ShiseidoWeb {
	
	public static String URL_ROOT = "http://www.shiseido.com/men/men,en_US,sc.html&forceProducthits&start=";
	public static String DIR_DATA = "data";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String listURL = "" ;
		System.out.println("parsing.. " + listURL);
		for(int page=0;page<2;page++){
			try {
				listURL = URL_ROOT + page*12 +"&sz=12";
				Document docRoot = Jsoup.connect(listURL).userAgent("Mozilla").timeout(5000).get();
//				System.out.println(docRoot);
				for(Element ele : docRoot.select(".productlisting")){
//					System.out.println(ele);
					ShiseidoProduct product = new ShiseidoProduct();
					
					Element brand = ele.select(".productbrand").first();
					if(brand !=null){
						product.setProductBrand(brand.text());
					}
					Element producttileheader = ele.select(".producttileheader").first();
					if(producttileheader !=null){
						product.setProductBrand(producttileheader.text());
					}
					
					
					Element name = ele.select(".namepriceContainer a").first();
					product.setProductName(name.text());
					
				    String detailUrl = ele.select(".productdetailbutton a").first().attr("href");
				    try{
				    	Document detailPage = Jsoup.connect(detailUrl).userAgent("Mozilla").timeout(5000).get();
				    	Element labels = detailPage.select(".productVariationLabel").first();
				    	Element labels1 = detailPage.select(".sizeVariations").first();
				    	if(labels != null){
				    		
					    	Element size = labels.select(".variationSize").first();
					    	product.setSize(size.text());
					    	Element price = labels.select(".variationPrice").first();
					    	product.setProductPrice(price.text());
					    	Element code = labels.select(".variationItemID").first();
					    	product.setProductCode(code.text());
				    	}else if(labels1 != null){
				    		Element size = labels1.select(".svSize").last();
					    	product.setSize(size.text());
					    	Element price = labels1.select(".svPrice").last();
					    	product.setProductPrice(price.text());
					    	Element code = labels1.select(".svItemID").last();
					    	product.setProductCode(code.text());
				    	}
				    	
				    }catch(IOException e){
				    	System.err.println("Error: " + e.getMessage());
				    }
				    System.out.println(product);
				    Thread.sleep(100);
				}
			} catch (Exception e) { System.err.println("Error: " + e.getMessage()); } 
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
	
		

}
