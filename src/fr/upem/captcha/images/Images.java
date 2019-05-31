/**
 * @author Cécile Rousset
 * @author Maximilien Pluchard
 */
package fr.upem.captcha.images;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * abstract Images
 * A class that implements Database that contains images to display for the captcha
 * Contains all the logic to retrieve images in a folder
 */

public abstract class Images implements Database {

	// ########## Attributes ##########

	/**
	 * Contains the files to be displayed
	 */
	private ArrayList<BufferedImage> bufferedImageList;
	/**
	 * Contains the sub categories
	 */
	private List<Images> categories;

	// ########## Methods ##########	

	/**
	 * Constructor
	 */
	protected Images() {
		this.bufferedImageList = new ArrayList<BufferedImage>();
		this.addAllFiles();
		this.categories = this._categories();
	}

	/**
	 * Add all images in the System Directory to the fileList
	 */
	private void addAllFiles() {
		// JAR DEBUG
		for (String filename : getFileNamelist()) {
			
			try {
				BufferedImage img = ImageIO.read(getClass().getResourceAsStream(filename));
				System.out.println("Image : " + img.hashCode());
				bufferedImageList.add(img);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Image not found :" + filename);
			}
			/*
			URL res = getClass().getResource(filename);
			if (res != null) {
				System.out.println("RES : " + res);
				File file = new File(res.getPath());
				System.out.println("FILE : " + file);
				if (file != null) {
					fileList.add(file);
				}
			} else {
				System.err.println("Error retrieving file : " + filename + " | Res : " + res);
			}
			*/
		}

		// OLD
		/*
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
		*/
	}	

	/**
	 * @return Return a list with all the photos in store
	 */
	public List<BufferedImage> getPhotos() {
		if (this.hasCategories()) {
			List<BufferedImage> allFiles = new ArrayList<BufferedImage>();
			// System.out.println("Sub categories : ");
			// System.out.println(this.categories);
			for(Images imageCategory : this.categories) {
				for(BufferedImage f : imageCategory.getPhotos()) {
					// System.out.println("File : " + f);
					allFiles.add(f);
				}				
			}
			for(BufferedImage f : this.bufferedImageList) {
				allFiles.add(f);
			}
			return Collections.unmodifiableList(allFiles);
		}
		else
		{
			return Collections.unmodifiableList(this.bufferedImageList);		
		}
	};

	/**
	 * @param amount The amount of images to get
	 * @return Return a list of random photos of the size given by @param amount
	 */
	public List<BufferedImage> getRandomPhotosFile(int amount) {
		List<BufferedImage> files = this.getPhotos();
		ArrayList<BufferedImage> allFiles = new ArrayList<BufferedImage>(files);
		Collections.shuffle(allFiles);
		int sub = Math.min(amount, allFiles.size());
		return Collections.unmodifiableList(allFiles.subList(0, sub));
	};

	/**
	 * @return Return a random image from this category
	 */
	public BufferedImage getRandomPhotoFile() {
		List<BufferedImage> files = this.getPhotos();
		ArrayList<BufferedImage> allFiles = new ArrayList<BufferedImage>(files);
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
		Images other = (Images) obj;
		if (name() == null) {
			if (other.name() != null)
				return false;
		} else if (!name().equals(other.name()))
			return false;
		return true;
	}

	/**
	 * @param imageCategory The category to compare to
	 * @return Return true if the two categories are strictly the same
	 */
	public boolean sameCategory(Images imageCategory) {
		return this.getClass().equals( imageCategory.getClass());
	}

	/**
	 * @param imageCategory The category to compare to
	 * @return Return true if the given category is a descendant of this category
	 */
	public boolean descendantCategory(Images imageCategory) {
		return !sameCategory(imageCategory) && this.getClass().isAssignableFrom( imageCategory.getClass() );
	}

	/**
	 * @param imageCategory The category to compare to
	 * @return Return true if the two category are the same, or the given category
	 * is a descendant of this category
	 */
	public boolean compatibleCategory(Images imageCategory) {
		return this.getClass().isAssignableFrom( imageCategory.getClass() );
	}

	/**
	 * @return The name of the category
	 */
	abstract public String name();

	/**
	 * Return a copy of the sub category
	 * @return The sub categories
	 */
	public List<Images> getCategories() {
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
	 * @return The list of sub categories
	 */
	abstract protected List<Images> _categories();
	
	/**
	 * @return The listed images
	 */
	abstract protected List<String> getFileNamelist();

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name();
	}


}
