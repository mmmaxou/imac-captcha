/**
 * @author Cécile Rousset
 * @author Maximilien Pluchard
 */
package fr.upem.captcha.images.dogs;

import java.util.ArrayList;
import java.util.List;

import fr.upem.captcha.images.Database;
import fr.upem.captcha.images.Images;
import fr.upem.captcha.images.dogs.black.BlackDog;
import fr.upem.captcha.images.dogs.grey.GreyDog;
import fr.upem.captcha.images.dogs.white.WhiteDog;

/**
 * Dog
 * Contains the dog folder
 *
 */

public class Dog extends Images implements Database {

	public Dog() {
		super();
	}

	@Override
	public boolean hasCategories() {
		return true;
	}

	@Override
	public List<Images> _categories() {
		ArrayList<Images> a = new ArrayList<Images>();
		a.add(new BlackDog());
		a.add(new WhiteDog());
		a.add(new GreyDog());
		return a;
	}
	
	@Override
	public String name() {
		return "Un chien";
	}

}
