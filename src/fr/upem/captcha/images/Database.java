/**
 * @author Cécile Rousset
 * @author Maximilien Pluchard
 */
package fr.upem.captcha.images;

import java.util.List;
/**
 * Database
 * A database is an entity that can contain sub categories
 */
public interface Database {
	public List<Images> getCategories();
	public boolean hasCategories();
}
