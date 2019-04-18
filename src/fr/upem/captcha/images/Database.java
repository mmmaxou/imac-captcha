package fr.upem.captcha.images;

import java.util.List;

public interface Database {
	public List<ImageCategory> getCategories();
	public boolean hasCategories();
}
