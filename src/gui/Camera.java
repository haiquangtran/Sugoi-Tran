package gui;

import java.awt.Dimension;

import objects.Player;
import main.Constants;
import main.World;
import tools.Vector2D;

public class Camera {

	private Vector2D cameraPos = new Vector2D(0, 0);

	public void updateCameraPos(Player p, World w, Dimension screenSize){
		int screenCenterX = screenSize.width/2;
		int screenCenterY = screenSize.height/2;
		int playerCenterX = p.getX()+(Constants.PLAYER_WIDTH/2);
		int playerCenterY = p.getY()+(Constants.PLAYER_HEIGHT/2);

		if(playerCenterX - screenCenterX < 0){
			cameraPos.setX(0);
		}else if(playerCenterX + screenCenterX > w.mapWidth){
			cameraPos.setX(w.mapWidth-screenSize.width);
		}else{
			cameraPos.setX(playerCenterX-screenCenterX);
		}

		if(playerCenterY - screenCenterY < 0){
			cameraPos.setY(0);
		}else if(playerCenterY + screenCenterY > w.mapHeight){
			cameraPos.setY(w.mapHeight-screenSize.height);
		}else{
			cameraPos.setY(playerCenterY-screenCenterY);
		}
	}

	public int getX(){
		return (int)cameraPos.x();
	}

	public int getY(){
		return (int)cameraPos.y();
	}

}
