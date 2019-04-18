package fr.upem.captcha.images.cats.grey;

import java.util.List;

import fr.upem.captcha.images.ImageCategory;

public class GreyCat extends ImageCategory {
	
	public GreyCat() {
		super();
	}

	@Override
	public String name() {
		return "Un Chat gris";
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
