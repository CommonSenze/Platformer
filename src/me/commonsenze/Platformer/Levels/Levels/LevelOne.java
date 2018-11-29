package me.commonsenze.Platformer.Levels.Levels;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Util.Obstacle;
import me.commonsenze.Platformer.Util.Enums.Role;

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
		for (Rectangle rectangle : getFinishSlots()) {
			rectangle.setBounds(rectangle.x-Main.CAMERA.getXSpeed(), rectangle.y-Main.CAMERA.getYSpeed(), rectangle.width, rectangle.height);
		}
	}

	@Override
	public void render(Graphics g) {
		for (Rectangle rectangle : getFinishSlots()) {
			g.setColor(Color.WHITE);
			g.fillRect(rectangle.x-4, rectangle.y-4, rectangle.width+8, rectangle.height+8);
			g.setColor(Color.BLACK);
			g.fillRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
		}
		for (Obstacle obs : getObstacles()) {
			if (obs instanceof Block)obs.render(g);
		}
	}

	@Override
	public void loadFinishedSlots() {
		addRectangle(new Rectangle(Main.WIDTH-200, Main.HEIGHT-104-Role.THOMAS.getGameObject().getHeight(), Role.THOMAS.getGameObject().getWidth()
				,Role.THOMAS.getGameObject().getHeight()));
	}
}
