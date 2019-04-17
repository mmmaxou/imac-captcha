package fr.upem.captcha.images;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import fr.upem.captcha.images.cats.Cat;
import fr.upem.captcha.images.dogs.Dog;
import fr.upem.captcha.images.miscellaneous.Miscellaneous;

public class Entry implements Database {
	public List<Category> categories = new ArrayList<Category>();
	
	public Entry() {
		categories.add(new Category("Chat", new Cat()));
		categories.add(new Category("Chien", new Dog()));
		categories.add(new Category("Etrangeté", new Miscellaneous()));		
	}
	
	@Override
	public boolean hasCategories() {
		return true;
	}

	@Override
	public List<Category> categories() {
		return Collections.unmodifiableList(this.categories);
	}
	
}
