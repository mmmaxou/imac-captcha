/**
 * Author
 * Cécile Rousset
 * Maximilien Pluchard
 */
package fr.upem.captcha.ui;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * 
 * @class UI
 * Handle the UI that isn't the JFrame ( dialogs )
 *
 */

public class Ui {

	// ########## Attributes ##########
	
	/**
 	 * Main Frame
	 */	
	private static CaptchaFrame frame;

	// ########## Methods ##########
	
	/**
	 * Init the main frame
	 * @param images
	 * @param type
	 * @param validation
	 */
	public static void setFrame(List<JLabel> images, String type, Runnable validation) {
		if (frame != null) {
			frame.dispose();
			frame = null;
		}
		frame = new CaptchaFrame(images, type , validation);
	}
	
	/**
	 * Display the message for success
	 * Free ressources
	 */
	public static void showSuccessDialog() {
		JOptionPane.showMessageDialog(null, "Vous avez réussi ! Bravo !");
		frame.dispose();
		frame = null;
	}
	
	/**
	 * Display message for error
	 */
	public static void showErrorDialog() {
		JOptionPane.showMessageDialog(null, "Oh non, c'est raté :'(");
	}

}
