package main;

import java.awt.Checkbox;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

import objects.CatEnemy;
import objects.CheckPoint;
import objects.DogEnemy;
import objects.Energy;
import objects.Entity;
import objects.Lever;
import objects.PickUpObject;
import objects.Player;
import objects.Points;
import objects.River;
import objects.Spikes;
import objects.Tile;
import objects.Wall;

/**
 * Parses game levels and returns Lists of the things in it. Should not be instantiated. Use its public static methods.
 * @author craigaaro
 */
public class Parser {

	private Parser(){}


	private static final char CLOUDS = 'C';
	private static final char BACKGROUND = '*';
	private static final char TILE = 'I';
	private static final char WALL = 'W';
	private static final char RIVER = 'V';
	private static final char ENERGY = 'E';
	private static final char SPIKE_UP = 'D';
	private static final char SPIKE_LEFT = 'R';
	private static final char SPIKE_DOWN = 'U';
	private static final char SPIKE_RIGHT = 'L';
	private static final char LEVER = 'H';
	private static final char PLAYER = 'P';
	private static final char POINTS = 'N';
	private static final char CATENEMY = 'A';
	private static final char DOGENEMY = 'B';
	private static final char CHECKPOINT = 'T';


	private static int col = 0;
	private static int row = 0;

	/**
	 * Takes a file name, reads the level described in that file, and then returns an array of data:
	 *  - array[0] is a List of entities
	 *  - array[1] is a List of tiles.
	 *  - array[2] is a list of pick ups
	 *  - array[3] is the player
	 * @param filename: name of file containing the level.
	 * @return a 2-element array with a List of entities and a List of tiles.
	 * @throws IOException: if there is an error in parsing
	 */
	public static Object[] parse(String filename) throws IOException{

		// File loading
		File file = new File(filename);
		Scanner scan = null;
		try{
			scan = new Scanner(file);
		}
		catch(IOException e){
			throw new IOException("Failed reading file: " + filename);
		}

		// Initialise variables
		List<Entity> entities = new ArrayList<>();
		List<Tile> tiles = new ArrayList<>();
		List<PickUpObject> pickups = new ArrayList<>();
		Player player = null;

		row = 0;
		col = 0;

		try{
		// Parsing
		while (scan.hasNextLine()){

			char[] line = scan.nextLine().toCharArray();
			for (col = 0; col < line.length; col++){
				char c = line[col];
				switch(c){
				case CLOUDS:
					//System.out.println("CLOUD LOADING NOT YET IMPLEMENTED");
					break;
				case BACKGROUND:
					break;
				case TILE:
					Wall floor = Wall.newFloor(col,row);
					tiles.add(floor);
					break;
				case WALL:
					Wall wall = Wall.newWall(col,row);
					tiles.add(wall);
					break;
				case SPIKE_UP:
					Spikes upSpikes = Spikes.newUpSpikes(col, row);
					tiles.add(upSpikes);
					break;
				case SPIKE_DOWN:
					Spikes downSpikes = Spikes.newDownSpikes(col, row);
					tiles.add(downSpikes);
					break;
				case SPIKE_RIGHT:
					Spikes rightSpikes = Spikes.newRightSpikes(col, row);
					tiles.add(rightSpikes);
					break;
				case SPIKE_LEFT:
					Spikes leftSpikes = Spikes.newLeftSpikes(col, row);
					tiles.add(leftSpikes);
					break;
				case RIVER:
					River river = River.newRiver(col, row);
					tiles.add(river);
					break;
				case ENERGY:
					Energy energy = Energy.newEnergy(col, row, 10);
					pickups.add(energy);
					break;
				case PLAYER:
					player = new Player(col,row);
					break;
				case LEVER:
					Lever lever = Lever.newLever(col,row);
					tiles.add(lever);
					break;
				case POINTS:
					Points points = Points.newPoints(col, row, 10);
					pickups.add(points);
					break;
				case CATENEMY:
						CatEnemy catEnemy = new CatEnemy(col, row);
						entities.add(catEnemy);
						break;
				case DOGENEMY:
						DogEnemy dogEnemy = new DogEnemy(col, row);
						entities.add(dogEnemy);
						break;
				case CHECKPOINT:
						CheckPoint checkPoint = CheckPoint.newCheckpoint(col, row);
						tiles.add(checkPoint);
						break;
				default:
					throw new IOException("Invalid character " + c + " found when parsing " + getPosition());
				}

				// error checking
				if (entities.size() > 0 && entities.get(entities.size()-1) == null){
					String str = "parser added null to entities. char was " + c + " at " + getPosition();
					throw new IOException(str);
				}
				else if (tiles.size() > 0 && tiles.get(tiles.size()-1) == null){
					String str = "parser added null to tiles. char was " + c + " at " + getPosition();
					throw new IOException(str);
				}
				else if (pickups.size() > 0 && pickups.get(pickups.size()-1) == null){
					String str = "parser added null to pickups. char was " + c + " at " + getPosition();
					throw new IOException(str);
				}

			}

			row++;
		}
		System.out.println("done loading");
		}
		catch(ArrayIndexOutOfBoundsException e){
			throw new ArrayIndexOutOfBoundsException("Out of bounds while parsing at " + getPosition());
		}

		//finished
		if (player == null){
			throw new IOException("No player found!");
		}

		return new Object[]{ entities, tiles, pickups, player };

	}

	/**
	 * Return a formatted string describing the point given.
	 * @param col
	 * @param row
	 * @return
	 */
	private static String getPosition(){
		return "("+col+","+row+")";
	}

}
