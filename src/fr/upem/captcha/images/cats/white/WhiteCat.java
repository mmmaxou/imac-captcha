package fr.upem.captcha.images.cats.white;

import java.util.List;

import fr.upem.captcha.images.ImageCategory;

public class WhiteCat extends ImageCategory {
	
	public WhiteCat() {
		super();
	}

	@Override
	public String name() {
		return "Un chat blanc";
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
