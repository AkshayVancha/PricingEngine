package com.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import com.model.CompetitorPrice;
import com.model.Product;

public class PricingEngine {
 public static void main(String[] args) {
	
	 String line;
     Map<String,Product> productMap=new HashMap<String,Product>();
     boolean isProductListActive = true;
     
     System.out.println("Please enter Products supply,demand  and List of Competitor prices and then enter 'process'.\n");
     
     Scanner input = new Scanner(System.in);

    try{
     while((line = input.nextLine()) != null && !line.equalsIgnoreCase("process")){
         
         if(line.length() == 0){
             isProductListActive = false;
             continue;
         }
         if(isProductListActive){
             String[] arr=line.split(" ");
             if(arr.length>1){
            	 Product product=new Product();
            	 product.setProductName(arr[0]);
            	 product.setSupply(arr[1]);
            	 product.setDemand(arr[2]);
            	 productMap.put(arr[0],product);
             }
            
         }else{
              
        	 String[] arr=line.split(" ");
             if(arr.length>1){
            	 CompetitorPrice cp=new CompetitorPrice();
            	 cp.setProductName(arr[0]);
            	 cp.setCompetitorName(arr[1]);
            	 cp.setPrice(Double.valueOf(arr[2]));
            	 if(productMap.get(arr[0]).getCompetitorPriceList() !=null){
            		 productMap.get(arr[0]).getCompetitorPriceList().add(cp);
            	 }else{
            		 List<CompetitorPrice> list=new ArrayList<CompetitorPrice>();
            		 list.add(cp);
            		 productMap.get(arr[0]).setCompetitorPriceList(list);
            	 }
            	
             }
         }
     }
     
     input.close();
    
     processPricingEngine(productMap);
    }catch(Exception ex){
    	ex.printStackTrace();
    	System.out.println("Failed to parse input");
    }
}
 
	 public static void processPricingEngine(Map<String,Product> productMap){
		 
		 
		 productMap.forEach((k,v)->{
			 
			 List<CompetitorPrice> cpList=v.getCompetitorPriceList();
			 
			 Map<Double,Integer> tm=new TreeMap<Double,Integer>();
			 
			 for(CompetitorPrice cp:cpList){
				 if(tm.containsKey(cp.getPrice())){
					 tm.put(cp.getPrice(), tm.get(cp.getPrice())+1);
				 }else{
					 tm.put(cp.getPrice(),1);
				 }
			 }
			 double price=0;
			 int count=0;
			 for (Entry<Double,Integer> entry : tm.entrySet()) {
				 if(count ==0 || entry.getValue() > count){
					 price = entry.getKey();
					 count = entry.getValue();
				 }
			 }
			 
			 if(v.getSupply().equals("L") && v.getDemand().equals("L")){
				 price=price+price*0.1;
			 }else if(v.getSupply().equals("L") && v.getDemand().equals("H")){
				 price=price+price*0.05;
			 }else if(v.getSupply().equals("H") && v.getDemand().equals("L")){
				 price=price-price*0.05;
			 }
			 
			 System.out.println(k+" "+price);
		    	
	     });
	 }

}
