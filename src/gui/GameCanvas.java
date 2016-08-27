package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

import main.Constants;
import main.Parser;
import main.World;
import objects.Entity;
import objects.PickUpObject;
import objects.Player;
import objects.Tile;
import tools.ImageLibrary;


/**
 * The display for the game
 * @author Preet Nijjar
 *
 */
public class GameCanvas extends Canvas {

	private long lastJump = 0;
	private GameFrame parent;
	private World world;
	private Player player;
	private long lastRedraw = 0;
	private Camera cam = new Camera();
	private HUD hud = new HUD();
	private Listener l;

	/**
	 * Constructs a new GameCanvas with the given width and height
	 * @param width The Width of the canvas
	 * @param height The Height of the canvas

	 */
	public GameCanvas(int width, int height, GameFrame parent, World world, Player player) {
		super(parent.getWidth(), parent.getHeight());
		this.parent = parent;
		parent.setBackground(Color.GRAY);
		this.world = world;
		this.player = player;
		l = new Listener();
		this.addKeyListener(l);
	}

	public void reset(){
		Object[] data;
		try {
			data = Parser.parse(Constants.LEVEL_FILENAME);
			world = new World((List<Entity>)data[0], (List<Tile>)data[1], (List<PickUpObject>)data[2], (Player)data[3]);
			player = (Player)data[3];
			lastJump = 0;
			lastRedraw = 0;
			cam = new Camera();
			hud = new HUD();
			world.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Draws everything.
	 */

	@Override
	public void paint(Graphics g){
		synchronized (world.key) {
			Dimension d = new Dimension(this.getWidth(),this.getHeight());
			cam.updateCameraPos(player, world, d);
			world.draw(g,d,cam);
			hud.draw(g, player, d.getWidth(), d.getHeight());
		}
		this.repaint();
	}

	private class Listener implements KeyListener{

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyPressed(KeyEvent e) {

			int code = e.getKeyCode();

		    if (code == KeyEvent.VK_RIGHT || code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A || code == KeyEvent.VK_D){

					player.move(code);

			}
			else if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
		    	if (System.currentTimeMillis() - lastJump > 200){
		    		player.jump();
		    		lastJump = System.currentTimeMillis();
		    	}
			}
			else if (code == KeyEvent.VK_SPACE){
				if(player.dead){
					reset();
				}else{
					player.transform();
				}
			}
			else if (code == KeyEvent.VK_ESCAPE){
				player.die();
			}

		}

		@Override
		public void keyReleased(KeyEvent e){
			int code = e.getKeyCode();
			if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_A || code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
				player.stop(code);
			}
		}

	}


}
