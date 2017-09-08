package com.ibport.isr;


import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.ibport.model.WalmartProduct;


public class WalmartWeb {

    public static String URL_ROOT1 = "http://www.walmart.com/browse/video-games/playstation-3-accessories/2636_413799_1225016?page=";
    public static String URL_ROOT2 = "&cat_id=2636_413799_1225016";
    public static String DIR_DATA = "data";

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        String listURL = "";
        for (int page = 2; page < 15; page++) {
            try {
                listURL = URL_ROOT1 + page + URL_ROOT2;
                Document docRoot = Jsoup.connect(listURL).userAgent("Mozilla").timeout(5000).get();
                //$("#searchProductResult ul")
                for (Element ele : docRoot.select(".tile-grid-unit-wrapper")) {
//					System.out.println(ele);
                    WalmartProduct product = new WalmartProduct();

                    String detailUrl = ele.select(".js-product-image").first().attr("href");
                    try {
                        Document detailPage = Jsoup.connect("http://www.walmart.com" + detailUrl).userAgent("Mozilla").timeout(5000).get();
                        Element name = detailPage.select(".product-name").first();
                        product.setProductName(name.text());

                        Element code = detailPage.select(".product-subhead-walmartnumber").first();
                        if (code != null) {
                            product.setProductCode(code.text());
                        }

                        Element price = detailPage.select(".js-price-display").first();
                        product.setProductPrice(price.text());


                    } catch (IOException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    System.out.println(product);
                    Thread.sleep(1000);
                }
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


}
