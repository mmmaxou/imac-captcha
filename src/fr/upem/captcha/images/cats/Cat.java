package fr.upem.captcha.images.cats;

import java.util.List;

import fr.upem.captcha.images.Category;
import fr.upem.captcha.images.Database;
import fr.upem.captcha.images.Images;

public class Cat extends Images implements Database  {

	public Cat() {
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
