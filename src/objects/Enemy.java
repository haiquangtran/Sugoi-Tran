package objects;


import java.awt.Rectangle;
import java.util.List;

import main.Constants;
import tools.Utilities;
import tools.Vector2D;



public abstract class Enemy extends Entity implements Danger{
	protected  int STEP_SIZE;
	protected boolean dead = false;
	public Enemy(int x, int y) {
		super(x, y);
		this.movement = new Vector2D(5,0);

		// TODO Auto-generated constructor stub
	}

	public abstract void kill();

}
