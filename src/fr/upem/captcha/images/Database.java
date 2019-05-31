/**
 * Author
 * Cécile Rousset
 * Maximilien Pluchard
 */
package fr.upem.captcha.images;

import java.util.List;
/**
 * @interface Database
 * A database is an entity that can contain sub categories
 */
public interface Database {
	public List<Images> getCategories();
	public boolean hasCategories();
}
