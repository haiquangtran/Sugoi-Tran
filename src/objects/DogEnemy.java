package objects;

import java.awt.Image;
import java.awt.image.BufferedImage;

import tools.Animation;
import tools.DeathAnimation;
import tools.ImageLibrary;
import tools.SoundLibrary;

public class DogEnemy extends Enemy {
	//Dog
	private static Animation dogAnimLeftWalking;
	private static Animation dogAnimLeftStatic;
	private static Animation dogAnimRightStatic;
	private static Animation dogAnimRightWalking;
	private static DeathAnimation deathAnim;
	private static final long ANIMATION_DELAY = 100;

	public DogEnemy(int x, int y) {
		super(x, y);
		this.animation = dogAnimRightStatic;
		//this.animation.start();
		this.STEP_SIZE = 10;
	}
	static {
	//Dog
	dogAnimLeftWalking = new Animation();
	dogAnimLeftWalking.addFrame(ImageLibrary.get("LdogWalk1Sprite.png"), ANIMATION_DELAY);
	dogAnimLeftWalking.addFrame(ImageLibrary.get("LdogWalk2Sprite.png"), ANIMATION_DELAY);
	dogAnimLeftWalking.addFrame(ImageLibrary.get("LdogStaticSprite.png"), ANIMATION_DELAY);
	dogAnimLeftWalking.start();

	dogAnimRightWalking = new Animation();
	dogAnimRightWalking.addFrame(ImageLibrary.get("RdogWalk1Sprite.png"), ANIMATION_DELAY);
	dogAnimRightWalking.addFrame(ImageLibrary.get("RdogWalk2Sprite.png"), ANIMATION_DELAY);
	dogAnimRightWalking.addFrame(ImageLibrary.get("RdogStaticSprite.png"), ANIMATION_DELAY);
	dogAnimRightWalking.start();

	dogAnimLeftStatic = new Animation();
	dogAnimLeftStatic.addFrame(ImageLibrary.get("LdogStaticSprite.png"), ANIMATION_DELAY);

	dogAnimRightStatic = new Animation();
	dogAnimRightStatic.addFrame(ImageLibrary.get("RdogStaticSprite.png"),ANIMATION_DELAY);

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
			if (this.animation == dogAnimRightWalking) this.animation = dogAnimRightStatic;
			else if (this.animation == dogAnimLeftWalking) this.animation = dogAnimLeftStatic;
		}
		else if (this.movement.x() > 0){
			this.animation = dogAnimRightWalking;
		}
		else if (this.movement.x() < 0){
			this.animation = dogAnimLeftWalking;
		}
	}

	@Override
	public void kill(){
		this.animation = deathAnim;
		if(!dead){
			dead = true;
			SoundLibrary.playSound("dogdead.wav");
			deathAnim.start();
		}
		dead = true;
	}
}
