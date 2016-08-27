package objects;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import tools.Animation;
import tools.ImageLibrary;
import main.Constants;

public class CheckPoint extends Tile {

	protected CheckPoint(Animation anim, int x, int y) {
		super(x, y);
		this.animation = anim;
	}

	public static CheckPoint newWall(int x, int y){
			Animation anim = new Animation();
			anim.addFrame(ImageLibrary.get("wallSprite.png"), 0);
			return new CheckPoint(anim,x,y);
	}

	public static CheckPoint newCheckpoint(int x, int y){
		Animation anim = new Animation();
		anim.addFrame(ImageLibrary.get("invisibleSprite.png"), 0);
		return new CheckPoint(anim,x,y);
	}

}
