package me.commonsenze.Platformer.Levels;

import java.awt.Graphics;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Util.Obstacles;

public class DevLevel extends Level {
	
	public DevLevel() {
		// floor
		add(new Block(0, Main.HEIGHT-100, Main.WIDTH+2000, 100));
		// ceiling
		add(new Block(0, 0, Main.WIDTH+2000, 70));
		// Left wall
		add(new Block(-2, 0, 102, Main.HEIGHT));
		
		add(new Block(400, 150, 20, 20));
		
		add(new Block(Main.WIDTH+20, Main.HEIGHT-150, 200, 200));
	}

	@Override
	public void tick() {
		for (Obstacles obs : getObstacles()) {
			obs.tick();
		}
	}

	@Override
	public void render(Graphics g) {
		for (Obstacles obs : getObstacles()) {
			if (obs instanceof Block)obs.render(g);
		}
	}
}
