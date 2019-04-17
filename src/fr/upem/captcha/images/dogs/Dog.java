package fr.upem.captcha.images.dogs;

import java.util.List;

import fr.upem.captcha.images.Category;
import fr.upem.captcha.images.Database;
import fr.upem.captcha.images.Images;

public class Dog extends Images implements Database {

	public Dog() {
		super();
	}

	@Override
	public boolean hasCategories() {
		return false;
	}

	@Override
	public List<Category> categories() {
		return null;
	}

}
