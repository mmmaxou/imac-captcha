package fr.upem.captcha.ui;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class CaptchaFrame extends JFrame {
	
	private List<JLabel> images;
	private String type;
	private Runnable validation;
	private JFrame frame;
	
	public CaptchaFrame(List<JLabel> images, String type, Runnable validation) throws HeadlessException {
		super();
		this.images = images;
		this.type = type;
		this.validation = validation;
		create();
	}

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
	
	private void populateFrameWithImages() {
		for (JLabel image : this.images) {
			frame.add(image);
		}
	}

	private void addValidationButton() {
		JButton okButton = createOkButton();
		frame.add(okButton);		
	}
	
	private void addImageTypeIndicator() {
		frame.add(new JTextArea("Veuillez sélectionner les images qui contiennent : " + type));
	}

	private JButton createOkButton(){
		return new JButton(new AbstractAction("Vérifier") { //ajouter l'action du boutons
			private static final long serialVersionUID = 1L;
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(validation);
			}
		});
	}

	private GridLayout createLayout(){
		return new GridLayout(4,3);
	}	
	
	public void dispose() {
		frame.dispose();
	}
}
