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
	
	public String getDescription() {
		return description;
	}
	
	public String getTreadDepth() {
		return treadDepth;
	}
	
	public String getWarranty() {
		return warranty;
	}
	
	public String getPrice() {
		return price;
	}

	public String getTireImage() {
		return tireImage;
	}

	public void setTireImage(String tireImage) {
		this.tireImage = tireImage;
	}	
}
