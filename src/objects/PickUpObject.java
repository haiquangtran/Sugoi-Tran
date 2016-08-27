package objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Constants;
import tools.Animation;
import tools.SoundLibrary;
import gui.Camera;

public abstract class PickUpObject extends GameObject{
	protected int amount;
	private boolean canPickUp = true;


	public PickUpObject(Animation animation, int x, int y, int amount){
		super(x,y);
		this.animation = animation;
		this.amount = amount;
	}

	@Override
	public void draw(Graphics g, Camera cam) {
		if (canPickUp) super.draw(g, cam);
	}

	public abstract void onCollision(Player player);

	public boolean canPickUp(){
		return canPickUp;
	}

	public void pickup(){
		SoundLibrary.playSound("coin.wav");
		canPickUp = false;
	}


	/**
	 * The amount of points if character picks it up
	 * @return
	 */
	public int getAmount(){
		return amount;
	}

}
