package fr.upem.captcha.images.miscellaneous;

import java.util.List;

import fr.upem.captcha.images.Category;
import fr.upem.captcha.images.Database;
import fr.upem.captcha.images.Images;

public class Miscellaneous extends Images implements Database {
	public Miscellaneous() {
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
