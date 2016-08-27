package tools;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import objects.CatEnemy;
import objects.Danger;
import objects.Entity;
import objects.GameObject;
import objects.Player;
import objects.River;

public class Utilities {

	private Utilities(){}

	/**
	 * The 'relative distance' between two vectors is c^2 = a^2 + b^2
	 * This is pythagoras, except we're not square-rooting to save on speed.
	 *  We use this when we only want to know if one thing is further away than another.
	 */
	public static float relativeDistance(Vector2D u, Vector2D v){
		return (u.x()-v.x())*(u.x()-v.x()) + (u.y()-v.y())*(u.y()-v.y());
	}

	public static boolean intersecting(Vector2D vector, Rectangle rect){
		return rect.contains(vector.x(),vector.y());
	}

	public static float distance(Vector2D u, Vector2D v){
		return (float) Math.sqrt( (u.x()-v.x())*(u.x()-v.x()) + (u.y()-v.y())*(u.y()-v.y()) );
	}

	/**
	 * Return true if the given vector is intersecting any of the bounding boxes of
	 * the GameObjects in the given list.
	 * @param vector: position to check
	 * @param list: list of GameObjects to check against
	 * @return: true if vector is touching anything, false otherwise
	 */
	public static boolean colliding(Vector2D vector, List<? extends GameObject> list){
		for (GameObject thing : list){
			if (Utilities.intersecting(vector, thing.boundingBox()) && !(thing instanceof Danger)) return true;
		}
		return false;
	}

	/**
	 * Return true if the given rectangle is intersecting any of the bounding boxes of
	 * the GameObjects in the given list.
	 * @param retangle: position to check
	 * @param list: list of GameObjects to check against
	 * @return: true if rectangle is touching anything, false otherwise
	 *
	 *
	 *
	 *
	 * DEPRECATED
	 * BEING USED IN AI
	 * ONLY USE THERE
	 *
	 */
	public static boolean colliding(Rectangle rect, List<? extends GameObject> list){
		for (GameObject thing : list){
			//Touching object check - Fall through water
			if (rect.intersects(thing.boundingBox()) && !(thing instanceof River) && !(thing instanceof Danger)){				return true;
			}
		}
		return false;
	}


	/**
	 * Return the list of all GameObjects within a certain distance of the given position.
	 * @param position: position to check
	 * @param list: list of GameObjects to check
	 * @return list of neighbours
	 */
	public static <E extends GameObject> List<E> getNearby(Vector2D position, List<E> list){
		final int MAX_DISTANCE = 50;
		List<E> neighbours = new ArrayList<>();
		for (E thing : list){
			Vector2D other = thing.getPosition();
			if (Utilities.distance(position, other) < MAX_DISTANCE){
				neighbours.add(thing);
			}
		}
		return neighbours;
	}

	/**
	 * Given a point on an image, return true if the pixel at this point is transparent.
	 */
	public static boolean isPixelTransparent(int x, int y, BufferedImage bi){
		int pixel = bi.getRGB(x,y);
		return pixel>>24 == 0x00;
	}

}
