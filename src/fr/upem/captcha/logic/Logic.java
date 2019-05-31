/**
 * Author
 * Cécile Rousset
 * Maximilien Pluchard
 */
package fr.upem.captcha.logic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fr.upem.captcha.images.Entry;
import fr.upem.captcha.images.Images;
import fr.upem.captcha.images.dogs.Dog;
import fr.upem.captcha.images.miscellaneous.Miscellaneous;
import fr.upem.captcha.ui.Ui;

/**
 * 
 * Logic
 * Handle the basic logic of the program
 * Start the creation of the frame, pick images and display them.
 * After the user selected images, verify and process the choice
 *
 */

public class Logic {

	// ########## Attributes ##########
	/**
	 * Contains a list of string of images selected by the user
	 */
	private List<String> selectedImages = new ArrayList<String>();
	
	/**
	 * Contains the list of valid images 
	 */
	private List<String> validImages = new ArrayList<String>();
	
	/**
	 * Contains the list of categories that were already used
	 */
	private List<Images> used = new ArrayList<Images>();
	
	/**
	 * Main Entry point of the database
	 */
	private Entry db = new Entry();
	
	/**
	 * Current difficulty
	 */
	private int difficulty = 0;
	
	/**
	 * Store the amount randomly chosen.
	 * We will then get that amount of random images
	 * Start from 1 to 4
	 */
	private int baseAmount = 0;
	
	/**
	 * Static value to store the amount of JFrames to display
	 */
	final int IMAGE_POOL_SIZE = 9;	
	
	/**
	 * Store the main category chosen
	 */
	private Images category = null;
	
	/**
	 * Store the number of valid images used
	 */
	private int validUsed = 0;

	// ########## Methods ##########
	
	/**
	 * Main constructor
	 * Simply lunch start
	 */
	public Logic() {
		start();
	}

	/**
	 * Choose a random category to be used as the main category
	 * Pick random images and send them to a JFrame
	 */
	private void start() {
		chooseRandomCategory();
		
		System.out.println("Main Category for the captcha : " + this.category.name());
		System.out.println("Start Difficulty : " + this.difficulty);
		
		List<JLabel> images = pickRandomImages();
		Ui.setFrame(images, this.category.name(), verify());
	}

	/**
	 * Utility function to choose a random amount of images
	 * if the choice were already made ( ie: it's the 2nd try ) simply increment the amount of valid used and return it
	 * @return The amount randomly chosen
	 */
	private int amountToRetrieve() {
		if ( validUsed != 0) {
			return validUsed + 1;
		} else {
			final int min = 1;
			final int max = 4;
			return (int)( Math.random()*( max - min + 1 ) ) + min;
			
		}
	}

	/**
	 * Choose a random category
	 * If the choice were already made ( ie: it's the 2nd try ) does nothing
	 * List all the categories from the Entry Class and take one randomly that is not Miscellaneous
	 */
	private void chooseRandomCategory() {
		if (this.category != null) {
			return;
		}
		int cpt = 0;
		boolean done = false;
		while (cpt < 100 && done == false) {
			cpt++;
			int l = db.getCategories().size();
			int r = ThreadLocalRandom.current().nextInt(l);
			Images c = db.getCategories().get(r);
			if (c instanceof Miscellaneous == false || c instanceof Dog) {
				this.category = c;
				done = true;
			}
		}
	}

	/**
	 * Does 2 things :
	 * - Pick a small amount of images from the main category choosen
	 * - Pick images to fill the JFrame up to ${IMAGE_POOL_SIZE} images
	 * To pick random images, it first choose a random category that is not the main, then pick random images, up to the amount to be retrieved ( ${} ) 
	 * @return List of random images picked
	 */
	private List<JLabel> pickRandomImages() {
		int amount = 0;
		List<BufferedImage> files;
		boolean harderCategory = false;
				
		if (this.difficulty == 0) {
			// If it's the first try
			// Choose a random baseAmount
			amount = amountToRetrieve();
			this.baseAmount = amount;
		} else {
			// If it's not the first try
			// Either take a harder category or take more images from that category
			harderCategory = Math.random() > 0.5;
			if (harderCategory) {
				amount = this.baseAmount;
				
				// Change the category to a harder one
				if (this.category.hasCategories()) {
			        Random rand = new Random();
			        List<Images> categories = this.category.getCategories();
			        Images category = categories.get(rand.nextInt(categories.size())); 
					this.category = category;
				}
				
			} else {
				
				// Take more images from that category
				amount = this.baseAmount + this.difficulty;
				amount = amount <= 4 ? amount : 4;
			}			
		}

		/// Get the valid images		
		files = this.category.getRandomPhotosFile(amount);	
		List<JLabel> jLabels = new ArrayList<JLabel>();
		validUsed = files.size();
		
		for (BufferedImage file : files) {
			JLabel jlabel = createLabelImage(file);
			jLabels.add(jlabel);
			this.validImages.add(String.valueOf(file.hashCode()));
			System.out.println("[Valid]" + file.hashCode());
		}
		
		

		/// Fill the images
		int cpt = 0;
		while (cpt < 100 && jLabels.size() < IMAGE_POOL_SIZE) {
			cpt++;
			int l = db.getCategories().size();
			int r = ThreadLocalRandom.current().nextInt(l);
			Images c = db.getCategories().get(r);
			
			System.out.println("Random category : " + c.name());
			
			// Is it the same category than ours ?
			// Is it already used ?
			if (c.compatibleCategory(this.category) == false &&
				this.used.contains(c) == false) {
				
				System.out.println("Okay, lets use this category : " + c.name());
				this.used.add(c);
				files = c.getPhotos();
				for (BufferedImage file : files) {
					if (jLabels.size() < IMAGE_POOL_SIZE) {
						JLabel jlabel = createLabelImage(file);
						jLabels.add(jlabel);
						System.out.println("[Filler]" + file.hashCode());
					}
				}
			}
		}
		
		// Return all images
		Collections.shuffle(jLabels);
		return jLabels;
	}

	/**
	 * Utility function to create a JLabel from a file
	 * 
	 * @param img A buffered Image to display
	 * @return The JLabel ready to be displayed
	 */
	private JLabel createLabelImage(final BufferedImage img){
		Image sImage = img.getScaledInstance(1024/3,768/4, Image.SCALE_SMOOTH); //redimentionner l'image

		final JLabel label = new JLabel(new ImageIcon(sImage)); // créer le composant pour ajouter l'image dans la fenêtre

		label.addMouseListener(new MouseListener() { //Ajouter le listener d'évenement de souris
			private boolean isSelected = false;

			@Override
			public void mousePressed(MouseEvent arg0) { //ce qui nous intéresse c'est lorsqu'on clique sur une image, il y a donc des choses à faire ici
				EventQueue.invokeLater(new Runnable() { 

					@Override
					public void run() {
						if(!isSelected){
							label.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
							isSelected = true;
							selectedImages.add(String.valueOf(img.hashCode()));
						}
						else {
							label.setBorder(BorderFactory.createEmptyBorder());
							isSelected = false;
							selectedImages.remove(String.valueOf(img.hashCode()));
						}

					}
				});
			}

			@Override
			public void mouseClicked(MouseEvent arg0) {}
			@Override
			public void mouseEntered(MouseEvent arg0) {}
			@Override
			public void mouseExited(MouseEvent arg0) {}
			@Override
			public void mouseReleased(MouseEvent arg0) {}
		});

		return label;
	}

	/**
	 * Runnable that return the verification callback
	 * Either display the success dialog or the error dialog and reset
	 * @return The runnable
	 */
	private Runnable verify() {
		return new Runnable() { // faire des choses dans l'interface donc appeler cela dans la queue des évènements
			@Override
			public void run() { // c'est un runnable
				if (isSelectionValid()) {
					Ui.showSuccessDialog();
				} else {
					Ui.showErrorDialog();
					reset();
				}
			}
		};
	}

	/**
	 * Does the verification
	 * Sort the selected images and the valid images
	 * Then compare each entry of the array to check if they contain the same images
	 * @return the result of the comparison ( true or false )
	 */
	private boolean isSelectionValid() {
		System.out.println(this.selectedImages);
		System.out.println(this.validImages);

		if (this.selectedImages == this.validImages)
			return true;
		if (this.selectedImages == null || this.validImages == null)
			return false;
		if (this.selectedImages.size() != this.validImages.size())
			return false;
		int n = this.selectedImages.size();

		Collections.sort(this.selectedImages);
		Collections.sort(this.validImages);

		for (int i=0; i<n; i++) {
			int comparaison = this.selectedImages.get(i).compareTo(this.validImages.get(i));
			if (comparaison != 0) {
				System.out.println("Comparaison failed at " + i);
				return false;
			}
		}
		return true;
	}

	private void reset() {
		selectedImages = new ArrayList<String>();
		validImages = new ArrayList<String>();
		used = new ArrayList<Images>();
		this.difficulty++;
		this.start();
	}
}
