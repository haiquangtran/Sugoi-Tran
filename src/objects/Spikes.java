package objects;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.Constants;
import tools.Animation;
import tools.ImageLibrary;

public class Spikes extends Tile implements Danger{

	enum Direction{bottom,top,left,right}

	private Direction spikeDir;

	protected Spikes(Animation anim, int x, int y, Direction dir) {
		super(x, y);
		this.spikeDir = dir;
		this.animation = anim;
	}

	public static Spikes newUpSpikes(int x, int y){
		Animation anim = new Animation();
		anim.addFrame(ImageLibrary.get("spikeTopSprite.png"), 0);
		return new Spikes(anim,x,y,Spikes.Direction.top);
	}

	public static Spikes newDownSpikes(int x, int y){
		Animation anim = new Animation();
		anim.addFrame(ImageLibrary.get("spikeBottomSprite.png"), 0);
		return new Spikes(anim,x,y,Spikes.Direction.bottom);
	}

	public static Spikes newRightSpikes(int x, int y){
		Animation anim = new Animation();
		anim.addFrame(ImageLibrary.get("spikeRightSprite.png"), 0);
		return new Spikes(anim,x,y,Spikes.Direction.right);
	}

	public static Spikes newLeftSpikes(int x, int y){
		Animation anim = new Animation();
		anim.addFrame(ImageLibrary.get("spikeLeftSprite.png"), 0);
		return new Spikes(anim,x,y,Spikes.Direction.left);
	}

	public Rectangle boundingBox(){
		BufferedImage bi = animation.getImage();
		Rectangle rect = new Rectangle();
		switch(spikeDir){
		case top:
			rect.x = (int) position.x();
			rect.y = (int) position.y();
			rect.width = bi.getWidth();
			rect.height = 10;
			break;
		case bottom:
			rect.x = (int) position.x();
			rect.y = (int) (position.y()+bi.getHeight()-10);
			rect.width = bi.getWidth();
			rect.height = 10;
			break;
		case left:
			rect.x = (int) position.x();
			rect.y = (int) position.y();
			rect.width = 10;
			rect.height = bi.getHeight();
			break;
		case right:
			rect.x = (int) (position.x()+bi.getWidth()-10);
			rect.y = (int) position.y();
			rect.width = 10;
			rect.height = bi.getHeight();
			break;
		default:
			rect.x = (int) position.x();
			rect.y = (int) position.y();
			rect.width = bi.getWidth();
			rect.height = bi.getHeight();
			break;
		}
		return rect;
	}

}
