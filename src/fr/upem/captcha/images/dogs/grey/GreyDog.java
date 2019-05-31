/**
 * @author Cécile Rousset
 * @author Maximilien Pluchard
 */
package fr.upem.captcha.images.dogs.grey;

import java.util.List;

import fr.upem.captcha.images.Images;

/**
 * GreyDog
 * Contains the grey dog folder
 */
public class GreyDog extends Images {
	
	public GreyDog() {
		super();
	}

	@Override
	public String name() {
		return "Un chien gris";
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