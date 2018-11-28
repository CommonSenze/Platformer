package me.commonsenze.Platformer.Levels.Util;

import java.awt.Rectangle;
import java.util.ArrayList;

import me.commonsenze.Platformer.Util.Obstacle;
import me.commonsenze.Platformer.Util.Renderable;

public abstract class Level implements Renderable {

	private ArrayList<Rectangle> finishedSlots = new ArrayList<>();
	protected ArrayList<Obstacle> obstacles = new ArrayList<>();
	
	public void add(Obstacle block) {
		obstacles.add(block);
	}
	
	public ArrayList<Obstacle> getObstacles(){
		return obstacles;
	}
	
	public void addRectangle(Rectangle rectangle) {
		finishedSlots.add(rectangle);
	}
	
	public void removeRectangle(Rectangle rectangle) {
		finishedSlots.remove(rectangle);
	}
	
	public ArrayList<Rectangle> getFinishSlots() {
		return finishedSlots;
	}
	
	public abstract void loadFinishedSlots();
}
