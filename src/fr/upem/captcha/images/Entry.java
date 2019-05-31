/**
 * @author Cécile Rousset
 * @author Maximilien Pluchard
 */
package fr.upem.captcha.images;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import fr.upem.captcha.images.cats.Cat;
import fr.upem.captcha.images.dogs.Dog;
import fr.upem.captcha.images.miscellaneous.Miscellaneous;

/**
 * Entry
 * Entry point of the images database
 * Contains no image
 * Contains all sub categories
 *
 */

public class Entry implements Database {
	public List<Images> categories = new ArrayList<Images>();
	
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
	public List<Images> getCategories() {
		return Collections.unmodifiableList(this.categories);
	}
	
}
