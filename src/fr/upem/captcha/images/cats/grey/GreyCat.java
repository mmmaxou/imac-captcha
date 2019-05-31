/**
 * @author Cécile Rousset
 * @author Maximilien Pluchard
 */
package fr.upem.captcha.images.cats.grey;

import java.util.ArrayList;
import java.util.List;

import fr.upem.captcha.images.Images;

/**
 * 
 * GreyCat
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

	@Override
	protected List<String> getFileNamelist() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("cat-006.jpg");
		s.add("cat-007.jpg");
		s.add("cat-008.jpg");
		s.add("cat-009.jpg");
		return s;
	}
}
