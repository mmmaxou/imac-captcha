package fr.upem.captcha.images;

import java.util.List;

public interface Database {
	public boolean hasCategories();
	public List<Category> categories();
}
