package objects;

import java.awt.Image;
import java.awt.image.BufferedImage;

import tools.Animation;
import tools.ImageLibrary;


public class River extends Tile{

	protected River(Animation anim, int x, int y) {
		super(x,y);
		this.animation = anim;
	}

	public static River newRiver(int x, int y){
		Animation anim = new Animation();
		anim.addFrame(ImageLibrary.get("water1Sprite.png"), 1000);
		anim.addFrame(ImageLibrary.get("water2Sprite.png"), 1000);
		anim.start();
		return new River(anim,x,y);
	}

}
