package com.ibport.isr;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.ibport.model.ShiseidoProduct;
import com.ibport.model.SleekhairProduct;

public class SleekhairWeb {
	public static String URL_ROOT = "http://www.sleekhair.com/haircare.html#/?_=1&page=";
	public static String DIR_DATA = "data";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String listURL = "" ;
		for(int page=1;page<160;page++){
			try {
				listURL = URL_ROOT + page;
				System.out.println("parsing.. " + listURL);
				Document docRoot = Jsoup.connect(listURL).userAgent("Mozilla").timeout(5000).get();
				System.out.println(docRoot);
				for(Element ele : docRoot.select(".cat-box-row")){
//					System.out.println(ele);
					SleekhairProduct product = new SleekhairProduct();
					
				    String detailUrl = ele.select(".qv a").first().attr("url");
				    try{
				    	Document detailPage = Jsoup.connect(detailUrl).userAgent("Mozilla").timeout(5000).get();
				    	Element name = detailPage.select(".itemtitle-pro").first();
				    	product.setProductName(name.text());
				    	
				    	Element code = detailPage.select("span[itemprop='productID']").first();
				    	product.setProductCode(code.text());
				    	
				    	Element price = detailPage.select(".out-of-stock-pt1 strong").first();
				    	product.setProductPrice(price.text());
				    	
				    	Element size = detailPage.select(".more-pro-price p").first();
				    	product.setProductPrice(size.text());
				    	
				    	
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
