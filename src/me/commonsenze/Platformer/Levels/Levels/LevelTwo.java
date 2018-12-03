package me.commonsenze.Platformer.Levels.Levels;

import java.awt.Graphics;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Objects.Water;
import me.commonsenze.Platformer.Util.Obstacle;

public class LevelTwo extends Level {
	
	public LevelTwo() {
		floor();
		// ceiling
		ceiling();

		// Left wall
		add(new Block(-2, 0, 102, Main.HEIGHT, this));

		// Right wall
		add(new Block(getWidth(), 0, 100, Main.HEIGHT, this));
	}

	private void floor() {
		// X = 0 to 1000
		addFloor(new Block(getWidth(), Main.HEIGHT-100, Main.WIDTH, 100, this));
		// X = 1000 to 1200
		addFloor(new Block(getWidth(), Main.HEIGHT-150, 200, 150, this));
		// X = 1200 to 1400
		addFloor(new Block(getWidth(), Main.HEIGHT-300, 200, 300, this));
		// Water
		add(new Water(getWidth(), Main.HEIGHT-270, 250, 220, this));
		// X = 1400 to 1650
		addFloor(new Block(getWidth(), Main.HEIGHT-50, 250, 50, this));
		// X = 1650 to 1750
		addFloor(new Block(getWidth(), Main.HEIGHT-300, 100, 300, this));
		// X = 1750 to 1850
		addFloor(new Block(getWidth(), Main.HEIGHT-100, 500, 100, this));
	}

	private void ceiling() {
		// X = 0 to 700
		add(new Block(0, 0, Main.WIDTH-300, 70, this));
		// X = 600 to 1300
		add(new Block(Main.WIDTH-400, -50, Main.WIDTH-300, 70, this));
		// X = 400 to 420
		add(new Block(400, 150, 20, 20, this));
		// X = 1250 to 1300
		add(new Block(Main.WIDTH+250, -200, 50, 200, this));
		// X = 1300 to 1800
		add(new Block(1300, -200, 500, 50, this));
		// X = 1800 to 1750
		add(new Block(1800, -200, 50, 200, this));
		// X = 1800 to 2300
		add(new Block(1800, 0, 500, 50, this));
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
		return getWidth()-100;
	}

	@Override
	public int getFinishedYPlace() {
		return getHieght();
	}
}
