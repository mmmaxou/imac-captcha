package fr.upem.captcha.images.dogs.black;

import java.util.List;

import fr.upem.captcha.images.ImageCategory;

public class BlackDog extends ImageCategory {
	
	public BlackDog() {
		super();
	}

	@Override
	public String name() {
		return "Un chien noir";
	}

	@Override
	public boolean hasCategories() {
		return false;
	}

	@Override
	protected List<ImageCategory> _categories() {
		return null;
	}

}
