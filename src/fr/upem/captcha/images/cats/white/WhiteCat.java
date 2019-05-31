/**
 * @author Cécile Rousset
 * @author Maximilien Pluchard
 */
package fr.upem.captcha.images.cats.white;

import java.util.ArrayList;
import java.util.List;

import fr.upem.captcha.images.Images;

/**
 * 
 * WhiteCat
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

	@Override
	protected List<String> getFileNamelist() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("cat-000.jpg");
		s.add("cat-010.jpg");
		s.add("cat-011.jpg");
		s.add("cat-012.jpg");
		return s;
	}
	
}
