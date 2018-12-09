package me.commonsenze.Platformer.Levels.Util;

import java.util.ArrayList;

import me.commonsenze.Platformer.Util.Obstacle;
import me.commonsenze.Platformer.Util.Renderable;

public abstract class Level implements Renderable {

	protected ArrayList<Obstacle> obstacles = new ArrayList<>();
	private int width = 0, finalHieght = 0;
	
	public void add(Obstacle block) {
		obstacles.add(block);
	}
	
	public void addFloor(Obstacle block) {
		obstacles.add(block);
		width += block.getObsticale().width;
		finalHieght += block.getObsticale().y;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHieght() {
		return finalHieght;
	}
	
	public void setHieght(int hieght) {
		this.finalHieght = hieght;
	}
	
	public ArrayList<Obstacle> getObstacles(){
		return obstacles;
	}
	
	public abstract void spawnSilhouettes();
}
