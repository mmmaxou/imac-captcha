package fr.upem.captcha.images;

import fr.upem.captcha.images.Images;

public class Category {
	private String name;
	private Images classInstance;
	
	public Category(String name, Images className) {
		this.name = name;
		this.classInstance = className;
	}
	public String name() {
		return name;
	}
	public Images classInstance() {
		return classInstance;
	}
}
