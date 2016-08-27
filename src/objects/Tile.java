package objects;

import gui.Camera;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import tools.Animation;
import main.Constants;

public abstract class Tile extends GameObject{

	protected Tile(int x, int y){
		super(x,y);
	}

}
