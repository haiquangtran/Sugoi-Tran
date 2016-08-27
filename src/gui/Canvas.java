package gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;


/**
 * Drawing canvas extending JPanel
 * @author Preet Nijjar
 *
 */
public class Canvas extends JPanel {
	/**
	 * Creates a new Drawing Canvas with the given width and height
	 * @param width Width of the Canvas
	 * @param height Height of the Canvas
	 */
	public Canvas(int width, int height){
		super();
		this.setPreferredSize(new Dimension(width, height));
		this.setSize(new Dimension(width, height));
	}

}
