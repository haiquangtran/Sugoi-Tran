package main;

import java.io.File;

import tools.Vector2D;

public class Constants {

	private Constants(){}

	public static final int TILE_WIDTH = 32;
	public static final int TILE_HEIGHT = 32;
	public static final String ASSETS = "assets"+ File.separatorChar;
	public static final String LEVEL_FILENAME = ASSETS + "map_Test.txt";
	public static final int STARTING_ENERGY = 0;
	public static final int MAX_ENERGY = 100;
	public static final int STARTING_POINTS = 0;
	public static final int PLAYER_WIDTH = 32;
	public static final int PLAYER_HEIGHT = 32;

	public static final Vector2D GRAVITY_VECTOR = new Vector2D(0,0.5f);
	public static final Vector2D TERMINAL_VELOCITY = new Vector2D(0,15);

}
