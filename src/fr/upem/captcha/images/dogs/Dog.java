package fr.upem.captcha.images.dogs;

import java.util.ArrayList;
import java.util.List;

import fr.upem.captcha.images.Database;
import fr.upem.captcha.images.ImageCategory;
import fr.upem.captcha.images.dogs.black.BlackDog;
import fr.upem.captcha.images.dogs.white.WhiteDog;

public class Dog extends ImageCategory implements Database {

	public Dog() {
		super();
	}

	@Override
	public boolean hasCategories() {
		return true;
	}

	@Override
	public List<ImageCategory> _categories() {
		ArrayList<ImageCategory> a = new ArrayList<ImageCategory>();
		a.add(new BlackDog());
		a.add(new WhiteDog());
		return a;
	}
	
	@Override
	public String name() {
		return "Un chien";
	}

}
