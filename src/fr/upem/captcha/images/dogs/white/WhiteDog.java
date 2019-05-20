package fr.upem.captcha.images.dogs.white;

import java.util.List;

import fr.upem.captcha.images.ImageCategory;

public class WhiteDog extends ImageCategory {
	
	public WhiteDog() {
		super();
	}

	@Override
	public String name() {
		return "Un chien blanc";
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
