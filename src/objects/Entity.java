package objects;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import main.Constants;
import tools.SoundLibrary;
import tools.Utilities;
import tools.Vector2D;

/**
 * Entity represents some moving thing on the screen. Will contain things like
 * collision detection, drawing etc.
 *
 * @author Mary
 */
public abstract class Entity extends GameObject {
	protected boolean grounded = true;
	protected Vector2D movement = new Vector2D(0, 0);

	public Entity(int x, int y) {
		super(x, y);
	}

	/**
	 * Return a copy of this Entity's movement vector.
	 *
	 * @return a Vector2D
	 */
	public Vector2D getMovementVector(Vector2D movement) {
		return new Vector2D(movement.x(), movement.y());
	}

	public abstract void updateAnimation();

	/**
	 * Apply the movement vector to the position vector.
	 *
	 * @param tiles
	 *            : list of all tiles in the map.
	 */
	public void step(List<GameObject> everything) {
		// get nearby tiles

		List<GameObject> nearby = Utilities.getNearby(position, everything);
		if (this instanceof Enemy) updateAnimation();

		// apply gravity
		applyGravity(nearby);

		// move
		if (movement.isZeroVector())
			return;
		Vector2D start = getPosition();
		Vector2D goal = position.add(movement);
		float distBetween = Utilities.distance(position, goal);
		float distTravelled = 0;
		Vector2D pos = getPosition();
		Vector2D unit = movement.unitVector();
		// repeatedly add the unit vector onto the position until you either
		// move down the entire length of the movement vector or you collide
		// wit something

		// repeatedly add unit vector onto the position
		// at each step, check if You've travelled the length of the movement vector
		// or if you have collided with anything
		moving: while (true){

			if (distTravelled+1 >= distBetween) break moving; //travelled too far
			Rectangle playerBounding = new Rectangle((int)pos.add(unit).x(),(int)pos.add(unit).y(),Constants.PLAYER_WIDTH,Constants.PLAYER_HEIGHT);

			for (GameObject thing : nearby){
				if (playerBounding.intersects(thing.boundingBox())){

					if (thing instanceof Tile){
						if (thing instanceof River){
							if (this instanceof Player){
								Player p = (Player)this;
								if (p.isCat()) p.die();
							}
						}
						else if (thing instanceof Wall){
							break moving;
						}
						else if (thing instanceof CheckPoint){
							if(this instanceof Enemy){
								this.movement = movement.horizontalFlip();
								break moving;
							}
						}
						else if (thing instanceof Danger){
							if (this instanceof Player){
								Player p = (Player)this;
								p.die();
							}
						}
					}
					else if (thing instanceof Entity){

						// player-cat collision
						if (this instanceof Player && thing instanceof CatEnemy || this instanceof CatEnemy && thing instanceof Player){
							Player player = (this instanceof Player) ? (Player)this : (Player)thing;
							CatEnemy enemy = (player == this) ? (CatEnemy)thing : (CatEnemy)this;
							if (player.isCat()) player.die();
							else enemy.kill();
						}

						// player-dog collision
						else if (this instanceof Player && thing instanceof DogEnemy || this instanceof DogEnemy && thing instanceof Player){
							Player player = (this instanceof Player) ? (Player)this : (Player)thing;
							DogEnemy enemy = (player == this) ? (DogEnemy)thing : (DogEnemy)this;
							player.die();
						}


					}
					else if (thing instanceof PickUpObject){

						if (this instanceof Player){
							PickUpObject pickup = (PickUpObject)thing;
							Player player = (Player)this;
							pickup.onCollision(player);
							everything.remove(pickup);
						}
					}

				}

			}
			pos = pos.add(unit);
			distTravelled++;

		}

		// update actual position
		position = pos;

		// if you didn't move vertically, then you must have either
		// hit the ground or you're at the maximum of the jumping parabola
		// if you hit the ground, set velocity_y <-- 0
		if ((int) position.y() == (int) start.y()) {
			if (grounded) {
				movement.setY(0);
			}
		}

	}

	public void applyGravity(List<GameObject> nearby) {
		boolean[] surroundings = checkSurroundings(nearby);
		// array[0] = north, surroundings[1] = south, surroundings[2] = east, surroundings[3] = west
		boolean north = surroundings[0];
		boolean south = surroundings[1];
		boolean east = surroundings[2];
		boolean west = surroundings[3];


		grounded = south;
		if (!grounded && !movement.atTerminalVelocity()) {
			movement = movement.add(Constants.GRAVITY_VECTOR);
		}

		if (!grounded){

			//System.out.println( "movement.x() > 0 " + (movement.x() > 0) + " ||| east " + east + " ||| movement.y() <= 0 " + (movement.y() <= 0));
			//System.out.println(movement);
			if (movement.x() > 0 && east){
				movement.setY(4);
				movement.setX(0);
			}
			else if (movement.x() < 0 && west){
				movement.setY(4);
				movement.setX(0);
			}
			else if ( (movement.y() < 0)&&(north)){
				movement.setY(0);
			}
		}

	}

	/**
	 * Check if you are touching a tile in your surroundings. Return a 4-sized
	 * array: north, south, east, west.
	 */
	private boolean[] checkSurroundings(List<GameObject> nearby) {
		Rectangle above = new Rectangle(getX(), getY() - 1, Constants.PLAYER_WIDTH, 1);
		Rectangle left = new Rectangle(getX() - 1, getY(), 1, Constants.PLAYER_HEIGHT);
		Rectangle right = new Rectangle(getX() + Constants.PLAYER_WIDTH + 1, getY(), 1, Constants.PLAYER_HEIGHT);
		Rectangle below = new Rectangle(getX(), getY() + Constants.PLAYER_HEIGHT + 1, Constants.PLAYER_WIDTH, 1);
		boolean north, south, east, west;
		north = south = east = west = false;
		for (GameObject obj : nearby) {

			if (!(obj instanceof Tile)) continue;
			Tile tile = (Tile)obj;

			if (tile instanceof Wall){
				Rectangle bounding = tile.boundingBox();
				if (below.intersects(bounding)) {
					if (!(tile instanceof River)) south = true;
				} else if (above.intersects(bounding)) {
					if (!(tile instanceof River)) north = true;
				} else if (right.intersects(bounding)) {
					if (!(tile instanceof River)) east = true;
				} else if (left.intersects(bounding)) {
					if (!(tile instanceof River)) west = true;
				}
			}
		}
		return new boolean[] { north, south, east, west };
	}

	/**
	 * Return true if the point (x,y) is a non-transparent pixel on this
	 * entity's image.
	 *
	 * @return boolean
	 */
	public boolean on(int x, int y) {
		return Utilities.isPixelTransparent(x, y, animation.getImage());
	}
}
