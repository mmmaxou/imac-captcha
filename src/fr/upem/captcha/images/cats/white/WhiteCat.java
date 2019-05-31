/**
 * Author
 * Cécile Rousset
 * Maximilien Pluchard
 */
package fr.upem.captcha.images.cats.white;

import java.util.List;

import fr.upem.captcha.images.Images;

/**
 * 
 * @class WhiteCat
 * Contains the white cat folder
 *
 */

public class WhiteCat extends Images {
	
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
	protected List<Images> _categories() {
		return null;
	}
	
}
