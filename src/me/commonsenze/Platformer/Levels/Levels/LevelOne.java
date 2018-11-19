package me.commonsenze.Platformer.Levels.Levels;

import java.awt.Graphics;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Util.Obstacles;

public class LevelOne extends Level {

	public LevelOne() {
		int x = floor();
		// ceiling
		ceiling();

		// Left wall
		add(new Block(-2, 0, 102, Main.HEIGHT));

		// Right wall
		add(new Block(x, 0, 100, Main.HEIGHT));
	}

	private int floor() {
		int x = 0;
		// X = 0 to 1000
		add(new Block(x, Main.HEIGHT-100, Main.WIDTH/2, 100));
		x += Main.WIDTH/2;
		return x;
	}

	private void ceiling() {
		// X = 0 to 1000
		add(new Block(0, 100, Main.WIDTH/2, 100));
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
