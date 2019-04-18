package fr.upem.captcha.images;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import fr.upem.captcha.images.cats.Cat;
import fr.upem.captcha.images.dogs.Dog;
import fr.upem.captcha.images.miscellaneous.Miscellaneous;

public class Entry implements Database {
	public List<ImageCategory> categories = new ArrayList<ImageCategory>();
	
	public Entry() {
		categories.add(new Cat());
		categories.add(new Dog());
		categories.add(new Miscellaneous());
	}
	
	@Override
	public boolean hasCategories() {
		return true;
	}

	@Override
	public List<ImageCategory> getCategories() {
		return Collections.unmodifiableList(this.categories);
	}
	
}
