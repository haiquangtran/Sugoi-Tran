package objects;

import gui.Camera;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.swing.text.Position;

import tools.Animation;
import tools.Vector2D;
import main.Constants;

/**
 * Top-level class for everything that can be drawn on the screen.
 * Everything has an animation and a position.
 * The position is set using GameObject's constructor. Subclasses should call super(x,y)
 * Each subclass is responsible for setting its own animation.
 * @author craigaaro
 *
 */
public abstract class GameObject {

	protected Animation animation;
	protected Vector2D position;

	public GameObject(int x, int y){
		position = new Vector2D(x*Constants.TILE_WIDTH,y*Constants.TILE_HEIGHT);
	}

	public void draw(Graphics g, Camera cam) {
		g.drawImage(animation.getImage() , getX() - cam.getX(), getY() - cam.getY(), Constants.TILE_WIDTH, Constants.TILE_HEIGHT, null);

		/* draws bounding boxes
		g.setColor(Color.RED);
		g.drawRect(getX()-cam.getX(),getY()-cam.getY(),Constants.TILE_WIDTH,Constants.TILE_HEIGHT);
		*/
	}

	public int getX(){
		return (int)(position.x());
	}

	public int getY(){
		return (int)(position.y());
	}

	public Vector2D getPosition(){
		return new Vector2D(getX(),getY());
	}

	public Rectangle boundingBox(){
		BufferedImage bi = animation.getImage();
		return new Rectangle(getX(),getY(),bi.getWidth(),bi.getHeight());
	}

}
