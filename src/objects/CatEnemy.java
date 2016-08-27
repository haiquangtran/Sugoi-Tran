package objects;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ObjectInputStream.GetField;

import tools.Animation;
import tools.DeathAnimation;
import tools.ImageLibrary;
import tools.SoundLibrary;

public class CatEnemy extends Enemy {
	//Cat
	private static Animation catAnimLeftWalking;
	private static Animation catAnimLeftStatic;
	private static Animation catAnimRightStatic;
	private static Animation catAnimRightWalking;
	private static DeathAnimation deathAnim;
	private static final long ANIMATION_DELAY = 100;

	public CatEnemy(int x, int y) {
		super(x, y);
		this.animation = catAnimRightStatic;
		this.STEP_SIZE = 10;

	}

	static {
		//Cat
		catAnimLeftWalking = new Animation();
		catAnimLeftWalking.addFrame(ImageLibrary.get("LcatWalk1Sprite.png"), ANIMATION_DELAY );
		catAnimLeftWalking.addFrame(ImageLibrary.get("LcatWalk2Sprite.png"), ANIMATION_DELAY);
		catAnimLeftWalking.addFrame(ImageLibrary.get("LcatStaticSprite.png"), ANIMATION_DELAY);
		catAnimLeftWalking.start();

		catAnimRightWalking = new Animation();
		catAnimRightWalking.addFrame(ImageLibrary.get("RcatWalk1Sprite.png"), ANIMATION_DELAY);
		catAnimRightWalking.addFrame(ImageLibrary.get("RcatWalk2Sprite.png"), ANIMATION_DELAY);
		catAnimRightWalking.addFrame(ImageLibrary.get("RcatStaticSprite.png"), ANIMATION_DELAY);
		catAnimRightWalking.start();

		catAnimLeftStatic = new Animation();
		catAnimLeftStatic.addFrame(ImageLibrary.get("LcatStaticSprite.png"), ANIMATION_DELAY);

		catAnimRightStatic = new Animation();
		catAnimRightStatic.addFrame(ImageLibrary.get("RcatStaticSprite.png"),ANIMATION_DELAY);

		deathAnim = new DeathAnimation();
		deathAnim.addFrame(ImageLibrary.get("death1Sprite.png"), 100);
		deathAnim.addFrame(ImageLibrary.get("death2Sprite.png"), 100);
		deathAnim.addFrame(ImageLibrary.get("death3Sprite.png"), 100);
		deathAnim.addFrame(ImageLibrary.get("death4Sprite.png"), 100);
		deathAnim.addFrame(ImageLibrary.get("death5Sprite.png"), 100);
	}



	@Override
	public void updateAnimation() {

		if (dead){
			this.movement = movement.ZERO_VECTOR;
		}

		if (this.movement.x() == 0){
			if (this.animation == catAnimRightWalking) this.animation = catAnimRightStatic;
			else if (this.animation == catAnimLeftWalking) this.animation = catAnimLeftStatic;
		}
		else if (this.movement.x() > 0){
			this.animation = catAnimRightWalking;
		}
		else if (this.movement.x() < 0){
			this.animation = catAnimLeftWalking;
		}
		// TODO Auto-generated method stub

	}

	public void kill(){
		this.animation = deathAnim;
		if(!dead){
			dead = true;
			SoundLibrary.playSound("catdead.wav");
			deathAnim.start();
		}
		dead = true;
	}
}
