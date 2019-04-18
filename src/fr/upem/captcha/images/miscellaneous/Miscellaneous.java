package fr.upem.captcha.images.miscellaneous;

import java.util.List;

import fr.upem.captcha.images.ImageCategory;

public class Miscellaneous extends ImageCategory {
	public Miscellaneous() {
		super();
	}

	@Override
	public boolean hasCategories() {
		return false;
	}
	
	@Override
	public String name() {
		return "Une créature fantastique";
	}

	@Override
	protected List<ImageCategory> _categories() {
		return null;
	}
}
