package objects;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import tools.Animation;
import tools.ImageLibrary;
import main.Constants;

public class Wall extends Tile {

	protected Wall(Animation anim, int x, int y) {
		super(x, y);
		this.animation = anim;
	}

	public static Wall newWall(int x, int y){
			Animation anim = new Animation();
			anim.addFrame(ImageLibrary.get("wallSprite.png"), 0);
			return new Wall(anim,x,y);
	}

	public static Wall newFloor(int x, int y){
		Animation anim = new Animation();
		anim.addFrame(ImageLibrary.get("floorSprite.png"), 0);
		return new Wall(anim,x,y);
	}

}
