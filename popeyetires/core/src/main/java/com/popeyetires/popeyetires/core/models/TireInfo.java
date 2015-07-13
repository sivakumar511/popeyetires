package com.popeyetires.popeyetires.core.models;

public class TireInfo {
	private String title;
	private String description;
	private String treadDepth;
	private String warranty;
	private String price;
	private String tireImage;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getTreadDepth() {
		return treadDepth;
	}
	
	public void setTreadDepth(String treadDepth) {
		this.treadDepth = treadDepth;
	}
	
	public String getWarranty() {
		return warranty;
	}
	
	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}

	public String getTireImage() {
		return tireImage;
	}

	public void setTireImage(String tireImage) {
		this.tireImage = tireImage;
	}
	
	public String toString() {
		return "title=[" + title + "]description=[" + description + "]treadDepth=[" + treadDepth + "]warranty=[" + warranty + "]price=[" + price + "]";
	}
}
