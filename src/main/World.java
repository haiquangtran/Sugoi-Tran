package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import objects.Enemy;
import objects.Danger;
import objects.Entity;
import objects.GameObject;
import objects.PickUpObject;
import objects.Player;
import objects.Player.Type;
import objects.River;
import objects.Tile;
import tools.ImageLibrary;
import tools.Vector2D;
import gui.Camera;

public class World extends Thread{
	private List<Tile> map;
	private List<Entity> entities;
	private List<PickUpObject> pickups;
	private List<GameObject> everything;

	private Player player;

	private static final long SECOND = 1000;
	private static final long UPDATE_INTERVAL = SECOND/60l;

	public int mapWidth=0, mapHeight=0;

	public Object key = new Object();



	public World(List<Entity> entities, List<Tile> map, List<PickUpObject> pickUps, Player player){
		this.map = map;
		this.entities = entities;
		this.pickups = pickUps;
		this.player = player;
		everything = new ArrayList<>();
		everything.addAll(map);
		everything.addAll(entities);
		everything.addAll(pickUps);

		for(Tile t : map){
			mapWidth = Math.max(mapWidth, t.getX());
			mapHeight = Math.max(mapHeight, t.getY());
		}
		mapWidth += Constants.TILE_WIDTH;
		mapHeight += Constants.TILE_HEIGHT;
	}

	public void draw(Graphics g, Dimension d, Camera cam){
		//Draw background
		drawBackground(g, d, cam);
		//Draw Player
		if (player != null){
			player.draw(g,cam);
		}
		//Draw the world
		for (Tile items: map){
			items.draw(g,cam);
		}
		//Draw the characters
		for (Entity entity: entities){
			entity.draw(g,cam);
		}
		//Draw Pick Ups
		for (PickUpObject items: pickups){
			items.draw(g,cam);
		}
	}

	private void drawBackground(Graphics g, Dimension d , Camera cam){
		g.fillRect(0,0,d.width,d.height);
		BufferedImage bgimg = ImageLibrary.get("backgroundRear.png");
		g.drawImage(bgimg, (int)d.getWidth()/2-bgimg.getWidth()/2, (int)d.getHeight()/2-bgimg.getHeight()/2, null);
		BufferedImage fgimg = ImageLibrary.get("backgroundForeground.png");
		g.drawImage(fgimg, (int)d.getWidth()/2-bgimg.getWidth()/2-cam.getX()/2, (int)d.getHeight()/2-bgimg.getHeight()/2-cam.getY()/2, null);
	}

	public void run(){
		long previousUpdate = 0;
		while (true){


			long timeElapsed = System.currentTimeMillis() - previousUpdate;
			if (timeElapsed > UPDATE_INTERVAL){

				synchronized (key) {

					player.step(everything);

					for(Entity e : this.entities){
						if(e instanceof Enemy){
							e.step(everything);
						}
						previousUpdate = System.currentTimeMillis();
					}

				}
			}
			else{
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}


	}

	private List<PickUpObject> getPickUpCollisions(GameObject object){
		List<PickUpObject> collisions = new ArrayList<PickUpObject>();
		for (PickUpObject pickup : pickups){
			if (overlappingBoundingBox(object,pickup)) collisions.add(pickup);
		}
		return collisions;
	}

	private List<Entity> getEntityCollisions(GameObject object){
		List<Entity> collisions = new ArrayList<Entity>();
		for (Entity entity : entities){
			if (overlappingBoundingBox(object,entity)) collisions.add(entity);
		}
		return collisions;
	}

	private List<Tile> getTileCollisions(GameObject object){
		List<Tile> collisions = new ArrayList<Tile>();
		for (Tile tile : map){
			if (overlappingBoundingBox(object,tile)) collisions.add(tile);
		}
		return collisions;
	}

	/**
	 * Returns true if the two given objects have overlapping bounding boxes.
	 * @return boolean
	 */
	public static boolean overlappingBoundingBox(GameObject obj1, GameObject obj2){
		Rectangle r1 = obj1.boundingBox();
		Rectangle r2 = obj2.boundingBox();
		return r1.intersects(r2);
	}
}
