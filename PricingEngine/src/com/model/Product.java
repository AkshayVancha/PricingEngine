package com.model;

import java.util.List;

public class Product {

	private String productName;
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}

	public String getSupply() {
		return supply;
	}

	public void setSupply(String supply) {
		this.supply = supply;
	}

	public List<CompetitorPrice> getCompetitorPriceList() {
		return competitorPriceList;
	}

	public void setCompetitorPriceList(List<CompetitorPrice> competitorPriceList) {
		this.competitorPriceList = competitorPriceList;
	}

	private String demand;
	
	private String supply;
	
	private List<CompetitorPrice> competitorPriceList;
}
