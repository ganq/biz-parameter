package com.mysoft.b2b.bizsupport.vo;

public enum BrowseObjType {
	
	SUPPLIER("0"),BIDDING("1");
	
	private String name;
	
	private BrowseObjType(String name){
		this.name = name;
	}
	
	public String getValue(){
		return this.name;
	}
}
