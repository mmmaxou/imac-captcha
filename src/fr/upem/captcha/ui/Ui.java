package fr.upem.captcha.ui;

import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Ui {

	private static CaptchaFrame frame;
	
	public static void setFrame(List<JLabel> images, String type, Runnable validation) {
		if (frame != null) {
			frame.dispose();
			frame = null;
		}
		frame = new CaptchaFrame(images, type , validation);
	}
	
	public static void showSuccessDialog() {
		JOptionPane.showMessageDialog(null, "Vous avez réussi ! Bravo !");
		frame.dispose();
		frame = null;
	}

	public static void showErrorDialog() {
		JOptionPane.showMessageDialog(null, "Oh non, c'est raté :'(");
	}

}
