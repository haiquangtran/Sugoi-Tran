package objects;

import main.Constants;
import tools.Animation;
import tools.Animation;
import tools.ImageLibrary;


public class Energy extends PickUpObject {

	protected Energy(Animation animation, int x, int y, int amount) {
		super(animation, x, y, amount);
		// TODO Auto-generated constructor stub
	}

	public static Energy newEnergy(int x, int y, int amount){
		Animation anim = new Animation();
		anim.addFrame(ImageLibrary.get("energy1Sprite.png"), 1000);
		anim.addFrame(ImageLibrary.get("energy2Sprite.png"), 1000);
		anim.start();
		return new Energy(anim,x,y, amount);
	}

	@Override
	public void onCollision(Player player) {
		if(canPickUp()){
			pickup();
			player.energy += amount;
			player.points += 5;
			player.energy = Math.min(player.energy, Constants.MAX_ENERGY);
		}
	}



}
