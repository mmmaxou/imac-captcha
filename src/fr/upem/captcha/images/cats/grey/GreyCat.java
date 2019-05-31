/**
 * Author
 * Cécile Rousset
 * Maximilien Pluchard
 */
package fr.upem.captcha.images.cats.grey;

import java.util.List;

import fr.upem.captcha.images.Images;

/**
 * 
 * @class GreyCat
 * Contains the grey cat folder
 *
 */

public class GreyCat extends Images {
	
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
	protected List<Images> _categories() {
		return null;
	}
}
