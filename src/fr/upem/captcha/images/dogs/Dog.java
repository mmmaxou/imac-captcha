package fr.upem.captcha.images.dogs;

import java.util.List;

import fr.upem.captcha.images.Database;
import fr.upem.captcha.images.ImageCategory;

public class Dog extends ImageCategory implements Database {

	public Dog() {
		super();
	}

	@Override
	public boolean hasCategories() {
		return false;
	}

	@Override
	public List<ImageCategory> _categories() {
		return null;
	}
	
	@Override
	public String name() {
		return "Un Chien";
	}

}
