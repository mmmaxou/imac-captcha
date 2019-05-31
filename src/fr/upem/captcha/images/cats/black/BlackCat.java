/**
 * @author Cécile Rousset
 * @author Maximilien Pluchard
 */
package fr.upem.captcha.images.cats.black;

import java.util.List;
import fr.upem.captcha.images.Images;

/**
 * 
 * BlackCat
 * Contains the black cat folder
 *
 */

public class BlackCat extends Images {
	
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
	protected List<Images> _categories() {
		return null;
	}
}

