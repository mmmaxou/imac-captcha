/**
 * @author Cécile Rousset
 * @author Maximilien Pluchard
 */
package fr.upem.captcha.images.dogs.white;

import java.util.ArrayList;
import java.util.List;

import fr.upem.captcha.images.Images;

/**
 * WhiteDog
 * Contains the white dog folder
 *
 */

public class WhiteDog extends Images {
	
	public WhiteDog() {
		super();
	}

	@Override
	public String name() {
		return "Un chien blanc";
	}

	@Override
	public boolean hasCategories() {
		return false;
	}

	@Override
	protected List<Images> _categories() {
		return null;
	}

	@Override
	protected List<String> getFileNamelist() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("dog-006.jpg");
		s.add("dog-007.jpg");
		s.add("dog-008.jpg");
		s.add("dog-009.jpg");
		return s;
	}
	
}
