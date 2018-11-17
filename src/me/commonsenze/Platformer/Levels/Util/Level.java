package me.commonsenze.Platformer.Levels.Util;

import java.util.ArrayList;

import me.commonsenze.Platformer.Util.Obstacles;
import me.commonsenze.Platformer.Util.Renderable;

public abstract class Level implements Renderable {

	protected ArrayList<Obstacles> obstacles = new ArrayList<>();
	
	public void add(Obstacles block) {
		obstacles.add(block);
	}
	
	public ArrayList<Obstacles> getObstacles(){
		return obstacles;
	}
}
