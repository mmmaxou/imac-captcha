package fr.upem.captcha.images;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;

public abstract class ImageCategory implements Database {
	private ArrayList<File> fileList;
	private List<ImageCategory> categories;

	/**
	 * Constructor
	 */
	protected ImageCategory() {
		this.fileList = new ArrayList<File>();
		this.addAllFiles();
		this.categories = this._categories();
	}

	/**
	 * Return a File that represent the current System Directory
	 * @return
	 */
	private File getCurrentClassDir() {
		String className = this.getClass().getSimpleName() + ".class";
		URL url = this.getClass().getResource(className);
		String uri = url.getFile().replace("/" + className, "");
		return new File(uri);
	}

	/**
	 * Add all images in the System Directory to {@value} fileList
	 */
	private void addAllFiles() {
		File curDir = getCurrentClassDir();
		final String[] EXTENSIONS = new String[]{ "jpeg", "png", "jpg" };
		final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
			@Override
			public boolean accept(final File dir, final String name) {
				for (final String ext : EXTENSIONS) {
					if (name.endsWith("." + ext)) {
						return (true);
					}
				}
				return (false);
			}
		};
		for (final File f : curDir.listFiles(IMAGE_FILTER)) {
			if (f != null) {
				fileList.add(f);
			}
		}
	}	

	/**
	 * Return a list with all the photos in store
	 * @return
	 */
	public List<File> getPhotos() {
		if (this.hasCategories()) {
			List<File> allFiles = new ArrayList<File>();
			System.out.println("Sub categories : ");
			System.out.println(this.categories);
			for(ImageCategory imageCategory : this.categories) {
				for(File f : imageCategory.getPhotos()) {
					System.out.println("File : " + f);
					allFiles.add(f);
				}				
			}
			for(File f : this.fileList) {
				allFiles.add(f);
			}
			return Collections.unmodifiableList(this.fileList);
		}
		else
		{
			return Collections.unmodifiableList(this.fileList);		
		}
	};

	/**
	 * Return a list of random photos of the size given by @param amount 
	 * @param amount
	 * @return
	 */
	public List<File> getRandomPhotosFile(int amount) {
		List<File> allFiles = this.getPhotos();
		Collections.shuffle(allFiles);
		int sub = Math.min(amount, allFiles.size());
		return Collections.unmodifiableList(allFiles.subList(0, sub));
	};

	/**
	 * Return a random image from this category
	 * @return
	 */
	public File getRandomPhotoFile() {
		List<File> allFiles = this.getPhotos();
		Collections.shuffle(allFiles);
		return allFiles.get(0);
	};

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name() == null) ? 0 : name().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ImageCategory other = (ImageCategory) obj;
		if (name() == null) {
			if (other.name() != null)
				return false;
		} else if (!name().equals(other.name()))
			return false;
		return true;
	}

	/**
	 * Return true if the two categories are strictly the same
	 * @param imageCategory
	 * @return
	 */
	public boolean sameCategory(ImageCategory imageCategory) {
		return this.getClass().equals( imageCategory.getClass());
	}

	/**
	 * Return true if the given category is a descendant of this category
	 * @param imageCategory
	 * @return
	 */
	public boolean descendantCategory(ImageCategory imageCategory) {
		return !sameCategory(imageCategory) && this.getClass().isAssignableFrom( imageCategory.getClass() );
	}

	/**
	 * Return true if the two category are the same, or the given category
	 * is a descendant of this category
	 * @param imageCategory
	 * @return
	 */
	public boolean compatibleCategory(ImageCategory imageCategory) {
		return this.getClass().isAssignableFrom( imageCategory.getClass() );
	}
	
	/**
	 * @return the name
	 */
	abstract public String name();
	
	/**
	 * Return a copy of the sub category
	 * @return
	 */
	public List<ImageCategory> getCategories() {
		if (this.hasCategories()) {
			return Collections.unmodifiableList(this.categories);
		} else {
			return null;
		}
	};

	/**
	 * Say if the category has sub categories
	 */
	abstract public boolean hasCategories();

	/**
	 * Create the categories
	 * @return
	 */
	abstract protected List<ImageCategory> _categories();
}
