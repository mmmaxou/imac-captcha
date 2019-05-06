package fr.upem.captcha.images.cats.black;

import java.util.List;
import fr.upem.captcha.images.ImageCategory;

public class BlackCat extends ImageCategory {
	
	public BlackCat() {
		super();
	}

	@Override
	public String name() {
		return "Un Chat noir";
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

