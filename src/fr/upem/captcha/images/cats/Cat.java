/**
 * @author Cécile Rousset
 * @author Maximilien Pluchard
 */
package fr.upem.captcha.images.cats;

import java.util.ArrayList;
import java.util.List;

import fr.upem.captcha.images.Database;
import fr.upem.captcha.images.Images;
import fr.upem.captcha.images.cats.black.BlackCat;
import fr.upem.captcha.images.cats.grey.GreyCat;
import fr.upem.captcha.images.cats.white.WhiteCat;

/**
 * 
 * Cat
 * Contains the cat folder
 *
 */
public class Cat extends Images implements Database  {	
	public Cat() {
		super();
	}

	@Override
	public String name() {
		return "Un chat";
	}

	@Override
	public boolean hasCategories() {
		return true;
	}

	@Override
	protected List<Images> _categories() {
		ArrayList<Images> a = new ArrayList<Images>();
		a.add(new BlackCat());
		a.add(new GreyCat());
		a.add(new WhiteCat());
		return a;
	}

	@Override
	protected List<String> getFileNamelist() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("cat-001.jpg");
		s.add("cat-002.jpg");
		s.add("cat-003.jpg");
		s.add("cat-005.jpg");
		return s;
	}
}
