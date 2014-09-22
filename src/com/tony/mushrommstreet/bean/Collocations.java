package com.tony.mushrommstreet.bean;

import java.io.Serializable;
import java.util.List;

public class Collocations implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4433194908708000068L;
	
	private List<Collocation> collocations;
	private String bigImage;
	public List<Collocation> getCollocations() {
		return collocations;
	}
	public void setCollocations(List<Collocation> collocations) {
		this.collocations = collocations;
	}
	public String getBigImage() {
		return bigImage;
	}
	public void setBigImage(String bigImage) {
		this.bigImage = bigImage;
	}
	
	
	
}
