package tp2.viewer;
import java.awt.Graphics2D;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import tp2.shapes.Drawable;

public class GraphicViewer extends JFrame {
	private static final long serialVersionUID = 1L;
	private final int width = 800;
	private final int height = 600;
	private Graphics2D onscreen;

	
	public GraphicViewer() {
		setVisible(true);
		setSize(width, height);
		setTitle("Afficheur de dessin");

		BufferedImage onscreenImage  = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		ImageIcon icon = new ImageIcon(onscreenImage);
		JLabel draw = new JLabel(icon);

		getContentPane().add(draw);
		onscreen  = onscreenImage.createGraphics();
		
		// Closing any view will quit :
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	
	public void draw(java.util.List<Drawable> shapes) {
		for (Drawable shape : shapes) {
			shape.draw(onscreen);
		}
		repaint(100);
	}

}



