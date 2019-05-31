/**
 * Author
 * Cécile Rousset
 * Maximilien Pluchard
 */
package fr.upem.captcha.images.dogs.black;

import java.util.List;

import fr.upem.captcha.images.Images;

/**
 * @class BlackDog
 * Contains the black dog folder
 *
 */

public class BlackDog extends Images {
	
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
	protected List<Images> _categories() {
		return null;
	}

}
