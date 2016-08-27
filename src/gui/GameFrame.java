package gui;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;

import main.World;
import objects.Entity;
import objects.PickUpObject;
import objects.Player;
import objects.Tile;

/**
 * Main class for game, starts the game and contains the main GUI for displaying
 * the game
 *
 * @author Preet Nijjar
 *
 */
public class GameFrame extends JFrame {

	// screen dimensions
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	public static final int screenWidth = (int) tk.getScreenSize().getWidth();
	public static final int screenHeight = (int) tk.getScreenSize().getHeight();

	public GameCanvas gameCanvas;
	public Menu menu;

	public GameFrame(List<Entity> entities, List<Tile> tiles,
			List<PickUpObject> pickups, Player player) {
		super();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Set JFrame size to screen width and height
		this.setTitle("Sugoi Tran");
		this.setSize(tk.getScreenSize());
		this.setResizable(true);

		World world = new World(entities, tiles, pickups, player);
		gameCanvas = new GameCanvas(this.getWidth(), this.getHeight(), this,
				world, player);
		gameCanvas.setBackground(Color.WHITE);
		this.add(gameCanvas);
		gameCanvas.setFocusable(true);
		this.setVisible(true);
		world.start();
	}

}
