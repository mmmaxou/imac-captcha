/**
 * Author
 * Cécile Rousset
 * Maximilien Pluchard
 */
package fr.upem.captcha.ui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

/**
 * @class CaptchaFrame
 * Custom class that extends JFrame to create our own JFrame for the captcha
 *
 */

public class CaptchaFrame extends JFrame {

	// ########## Attributes ##########
	
	/**
	 * Contains all JLabels to be displayed
	 */
	private List<JLabel> images;
	/**
	 * Contains the type of category to choose
	 */
	private String type;
	/**
	 * A Runnable that handle validation callback 
	 */
	private Runnable validation;
	/**
	 * Main JFrame
	 */
	private JFrame frame;

	// ########## Methods ##########
	
	/**
	 * Main constructor
	 * @param images
	 * @param type
	 * @param validation
	 * @throws HeadlessException
	 */
	public CaptchaFrame(List<JLabel> images, String type, Runnable validation) throws HeadlessException {
		super();
		this.images = images;
		this.type = type;
		this.validation = validation;
		create();
	}

	/**
	 * Init and return the main JFrame
	 * @return
	 */
	private JFrame create() {
		frame = new JFrame("NOTRE SUPERBE CAPTCHA");// Création de la fenêtre principale
		GridLayout layout = createLayout();  // Création d'un layout de type Grille avec 4 lignes et 3 colonnes
		frame.setLayout(layout);  // affection du layout dans la fenêtre.
		frame.setSize(1024, 768); // définition de la taille
		frame.setResizable(false);  // On définit la fenêtre comme non redimentionnable
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Lorsque l'on ferme la fenêtre on quitte le programme.
		populateFrameWithImages();
		addImageTypeIndicator();
		addValidationButton();
		frame.setVisible(true);
		return frame;
	}

	/**
	 * Insert all images into the JFrame
	 */
	private void populateFrameWithImages() {
		for (JLabel image : this.images) {
			frame.add(image);
		}
	}

	/**
	 * Insert Validation button
	 */
	private void addValidationButton() {
		JButton okButton = createOkButton();
		frame.add(okButton);		
	}

	/**
	 * Insert the text to be displayed
	 */
	private void addImageTypeIndicator() {
		JTextArea textArea = new JTextArea("Cliquez sur les images qui contiennent " + type);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		Font font = new Font("Arial", Font.BOLD, 18);
		textArea.setFont(font);
		frame.add(textArea);
	}

	/**
	 * Insert the Validate button
	 * @return
	 */
	private JButton createOkButton(){
		return new JButton(new AbstractAction("Valider") { //ajouter l'action du boutons
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(validation);
			}
		});
	}

	/**
	 * Create grid
	 * @return
	 */
	private GridLayout createLayout(){
		return new GridLayout(4,3);
	}	
	
	/**
	 * Free ressources
	 */
	public void dispose() {
		frame.dispose();
	}
}
