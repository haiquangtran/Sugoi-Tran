package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tools.ImageLibrary;


/**
 * Game menu
 * @author Jason
 *
 */
public class Menu extends JPanel{

	private JLabel title;
	private JButton playButton;
	private Image img;
	
	//screen dimensions
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	public static final int screenWidth = (int) tk.getScreenSize().getWidth();
	public static final int screenHeight = (int) tk.getScreenSize().getHeight();

	
	public Menu() {
		title = new JLabel();
		playButton = new JButton();		

		setLayout(null);
		
		img = ImageLibrary.get("sugoi_tran.png");
		Image resize = img.getScaledInstance(600, 100, Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(resize);
		title.setIcon(icon);
		
		playButton.setIcon(new ImageIcon(ImageLibrary.get("play button.png")));
		
		//setting the size of te title label
		title.setPreferredSize(new Dimension(600,100));
		Dimension size = title.getPreferredSize();	
		
		//set the boundaries of the button and title
		title.setBounds(screenWidth/2-(size.width/2), 100, size.width, size.height);
		size  = playButton.getPreferredSize();
		
		playButton.setBounds(screenWidth/2-(size.width/2), screenHeight/2, size.width, size.height);
		
		
		this.add(title);
		this.add(playButton);
		this.setVisible(true);
	}
	
	public JButton getPlayButton(){
		return playButton;
	}
	
	
}
