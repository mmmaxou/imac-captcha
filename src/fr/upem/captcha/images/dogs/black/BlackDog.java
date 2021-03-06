/**
 * @author Cécile Rousset
 * @author Maximilien Pluchard
 */
package fr.upem.captcha.images.dogs.black;

import java.util.ArrayList;
import java.util.List;

import fr.upem.captcha.images.Images;

/**
 * BlackDog
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

	@Override
	protected List<String> getFileNamelist() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("dog-010.jpg");
		s.add("dog-011.jpg");
		s.add("dog-012.jpg");
		s.add("dog-013.jpg");
		return s;
	}

}
