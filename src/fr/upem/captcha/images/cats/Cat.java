package fr.upem.captcha.images.cats;

import java.util.ArrayList;
import java.util.List;

import fr.upem.captcha.images.Database;
import fr.upem.captcha.images.ImageCategory;
import fr.upem.captcha.images.cats.grey.GreyCat;
import fr.upem.captcha.images.cats.white.WhiteCat;

public class Cat extends ImageCategory implements Database  {	
	public Cat() {
		super();
	}

	@Override
	public String name() {
		return "Un chat";
	}

	@Override
	public boolean hasCategories() {
		return true;
	}

	@Override
	protected List<ImageCategory> _categories() {
		ArrayList<ImageCategory> a = new ArrayList<ImageCategory>();
		a.add(new GreyCat());
		a.add(new WhiteCat());
		return a;
	}
}
