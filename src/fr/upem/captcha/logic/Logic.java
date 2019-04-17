package fr.upem.captcha.logic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import fr.upem.captcha.images.Category;
import fr.upem.captcha.images.Entry;
import fr.upem.captcha.images.Images;
import fr.upem.captcha.images.miscellaneous.Miscellaneous;
import fr.upem.captcha.ui.Ui;

public class Logic {

	private List<String> selectedImages = new ArrayList<String>();
	private List<String> validImages = new ArrayList<String>();
	private List<Category> used = new ArrayList<Category>();
	private Entry db = new Entry(); 
	private int difficulty = 0;
	final int IMAGE_POOL_SIZE = 9;	
	private Category category = null;
	private int validUsed = 0;

	public Logic() {
		start();
	}

	private void start() {
		chooseRandomCategory();
		List<JLabel> images = pickRandomImages();
		Ui.setFrame(images, this.category.name(), verify());
	}

	private int amountToRetrieve() {
		if ( validUsed != 0) {
			return validUsed + 1;
		} else {
			final int min = 1;
			final int max = 4;
			return (int)( Math.random()*( max - min + 1 ) ) + min + difficulty;
		}
	}

	private void chooseRandomCategory() {
		int cpt = 0;
		boolean done = false;
		while (cpt < 100 && done == false) {
			cpt++;
			int l = db.categories().size();
			int r = ThreadLocalRandom.current().nextInt(l);
			Category c = db.categories().get(r);
			if (c.classInstance() instanceof Miscellaneous == false) {
				this.category = c;
				done = true;
			}
		}
	}

	private List<JLabel> pickRandomImages() {
		int amount = amountToRetrieve();
		List<JLabel> jLabels = new ArrayList<JLabel>();

		/// Get the valid images
		Images imagesClass = category.classInstance();
		List<File> files = imagesClass.getRandomPhotosURL(amount);
		validUsed = files.size();
		for (File file : files) {
			JLabel jlabel = createLabelImage(file);
			jLabels.add(jlabel);
			this.validImages.add(file.getName());
			System.out.println("[Valid]" + file);
		}

		/// Fill the images
		int cpt = 0;
		while (cpt < 100 && jLabels.size() < IMAGE_POOL_SIZE) {
			cpt++;
			int l = db.categories().size();
			int r = ThreadLocalRandom.current().nextInt(l);
			Category c = db.categories().get(r);
			if (c.name().compareTo(this.category.name()) != 0 && this.used.contains(c) == false) {
				this.used.add(c);
				imagesClass = c.classInstance();
				files = imagesClass.getPhotos();
				for (File file : files) {
					if (jLabels.size() < IMAGE_POOL_SIZE) {
						JLabel jlabel = createLabelImage(file);
						jLabels.add(jlabel);
						System.out.println("[Filler]" + file);
					}
				}
			}
		}
		Collections.shuffle(jLabels);
		return jLabels;
	}

	private JLabel createLabelImage(final File file){
		BufferedImage img = null;
		try {
			img = ImageIO.read(file); //lire l'image
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (img == null) {
			System.err.println("Image not found :");
			System.err.println(file);
		}
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
							selectedImages.add(file.getName());
						}
						else {
							label.setBorder(BorderFactory.createEmptyBorder());
							isSelected = false;
							selectedImages.remove(file.getName());
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
		used = new ArrayList<Category>();
		category = null;
		this.difficulty++;
		this.start();
	}
}
