package com.tony.mushrommstreet.bean;

import java.io.Serializable;
import java.util.List;

public class Collocation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2897613391245799722L;

	private String bigImageUrl;
	private String avatar;
	private String name;
	private String loveCount;
	private String smallImage;

	public String getBigImageUrl() {
		return bigImageUrl;
	}

	public void setBigImageUrl(String bigImageUrl) {
		this.bigImageUrl = bigImageUrl;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoveCount() {
		return loveCount;
	}

	public void setLoveCount(String loveCount) {
		this.loveCount = loveCount;
	}

	public String getSmallImage() {
		return smallImage;
	}

	public void setSmallImages(String smallImage) {
		this.smallImage = smallImage;
	}
}
