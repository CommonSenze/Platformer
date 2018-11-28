package me.commonsenze.Platformer.Levels.Levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Util.Obstacle;

public class LevelOne extends Level {

	public LevelOne() {
		int x = floor();
		// ceiling
		ceiling();

		// Left wall
		add(new Block(-2, 0, 102, Main.HEIGHT, this));

		// Right wall
		add(new Block(x, 0, 100, Main.HEIGHT, this));
		
		loadFinishedSlots();
	}

	private int floor() {
		int x = 0;
		// X = 0 to 1000
		add(new Block(x, Main.HEIGHT-100, Main.WIDTH, 100, this));
		x += Main.WIDTH;
		return x;
	}

	private void ceiling() {
		// X = 0 to 1000
		add(new Block(0, 0, Main.WIDTH, 100, this));
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
		for (Rectangle rectangle : getFinishSlots()) {
			g.setColor(Color.WHITE);
			g.fillRect(rectangle.x-5, rectangle.y-5, rectangle.width+10, rectangle.height+10);
			g.setColor(Color.BLACK);
			g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		}
	}

	@Override
	public void loadFinishedSlots() {
		addRectangle(new Rectangle(Main.WIDTH-200, Main.HEIGHT-200, 10,20));
	}
}
