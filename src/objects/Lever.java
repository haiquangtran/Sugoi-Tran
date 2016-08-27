package objects;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import tools.Animation;
import tools.ImageLibrary;

public class Lever extends Tile{

	protected Lever(Animation anim, int x, int y) {
		super(x,y);
		this.animation = anim;
	}

	public static Lever newLever(int x, int y){
		Animation anim = new Animation();
		anim.addFrame(ImageLibrary.get("lever_red_left.png"), 0);
		return new Lever(anim,x,y);
	}

}
