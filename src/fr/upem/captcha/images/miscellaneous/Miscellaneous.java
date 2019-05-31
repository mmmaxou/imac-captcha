/**
 * @author Cécile Rousset
 * @author Maximilien Pluchard
 */

package fr.upem.captcha.images.miscellaneous;

import java.util.ArrayList;
import java.util.List;

import fr.upem.captcha.images.Images;

/**
 * Miscellaneous
 * Contains the miscellaneous folder
 * Can't be picked as the right image
 *
 */
public class Miscellaneous extends Images {
	public Miscellaneous() {
		super();
	}

	@Override
	public boolean hasCategories() {
		return false;
	}
	
	@Override
	public String name() {
		return "Une créature";
	}

	@Override
	protected List<Images> _categories() {
		return null;
	}
	
	@Override
	protected List<String> getFileNamelist() {
		ArrayList<String> s = new ArrayList<String>();
		s.add("bunny-001.jpg");
		s.add("bunny-002.jpg");
		s.add("otter-001.jpg");
		s.add("phoque-001.jpg");
		s.add("phoque-002.jpg");
		s.add("squirrel-001.jpg");
		s.add("squirrel-002.jpg");
		s.add("drake-001.jpg");
		s.add("hypogriffe-001.jpg");
		s.add("troll-001.png");
		s.add("unicorn-001.jpg");
		s.add("yokai-001.jpg");
		return s;
	}
}
