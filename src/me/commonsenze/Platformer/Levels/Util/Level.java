package me.commonsenze.Platformer.Levels.Util;

import java.util.ArrayList;

import me.commonsenze.Platformer.Util.Obstacle;
import me.commonsenze.Platformer.Util.Renderable;

public abstract class Level implements Renderable {

	protected ArrayList<Obstacle> obstacles = new ArrayList<>();
	
	public void add(Obstacle block) {
		obstacles.add(block);
	}
	
	public ArrayList<Obstacle> getObstacles(){
		return obstacles;
	}
}
