package com.tony.mushrommstreet.bean;

import java.io.Serializable;

public class Cat implements Serializable{

	/**
	 * @author TonyWang
	 * @time 2014. 9.11
	 * 
	 */
	private static final long serialVersionUID = 5429684225780308207L;
	
	private String title;
	private String logoIcon;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLogoIcon() {
		return logoIcon;
	}
	public void setLogoIcon(String logoIcon) {
		this.logoIcon = logoIcon;
	}
	
	

}
