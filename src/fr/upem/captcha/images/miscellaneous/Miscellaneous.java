/**
 * Author
 * Cécile Rousset
 * Maximilien Pluchard
 */
package fr.upem.captcha.images.miscellaneous;

import java.util.List;

import fr.upem.captcha.images.Images;

/**
 * @class Miscellaneous
 * Contains the miscellaneous folder
 * Can't be picked as the right image
 *
 */
public class Miscellaneous extends Images {
	public Miscellaneous() {
		super();
	}

	@Override
	public boolean hasCategories() {
		return false;
	}
	
	@Override
	public String name() {
		return "Une créature";
	}

	@Override
	protected List<Images> _categories() {
		return null;
	}
}
