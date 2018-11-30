package me.commonsenze.Platformer.Levels;

import java.awt.Graphics;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Util.Obstacle;

public class DevLevel extends Level {
	
	public DevLevel() {
		// floor
		addFloor(new Block(0, Main.HEIGHT-100, Main.WIDTH+2000, 100, this));
		// ceiling
		add(new Block(0, 0, Main.WIDTH+2000, 70, this));
		// Left wall
		add(new Block(-2, 0, 102, Main.HEIGHT, this));
		
		add(new Block(400, 150, 20, 20, this));
		
		add(new Block(Main.WIDTH+20, Main.HEIGHT-150, 200, 200, this));
	}

	@Override
	public void tick() {
		for (Obstacle obs : getObstacles()) {
			obs.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		for (Obstacle obs : getObstacles()) {
			if (obs instanceof Block)obs.render(g);
		}
	}

	@Override
	public int getFinishedXPlace() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFinishedYPlace() {
		// TODO Auto-generated method stub
		return 0;
	}
}
