package objects;

import main.Constants;
import tools.Animation;
import tools.Animation;
import tools.ImageLibrary;
import tools.SoundLibrary;


public class Points extends PickUpObject {

	public Points(Animation image, int x, int y, int amount) {
		super(image, x, y, amount);
	}

	@Override
	public void onCollision(Player player) {
		if(canPickUp()){
			pickup();
			player.points += amount;
		}
	}

	public static Points newPoints(int x, int y, int amount){
		Animation anim = new Animation();
		anim.addFrame(ImageLibrary.get("pointBubble1Sprite.png"), 250);
		anim.addFrame(ImageLibrary.get("pointBubble2Sprite.png"), 250);
		anim.addFrame(ImageLibrary.get("pointBubble3Sprite.png"), 250);
		anim.addFrame(ImageLibrary.get("pointBubble4Sprite.png"), 250);
		anim.start();
		return new Points(anim,x,y, amount);
	}

}
