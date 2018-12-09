package me.commonsenze.Platformer.Levels.Levels;

import java.awt.Graphics;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Objects.Silhouette;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Obstacle;
import me.commonsenze.Platformer.Util.Enums.Role;

public class LevelOne extends Level {
	
	public LevelOne() {
		floor();
		// ceiling
		ceiling();

		// Left wall
		add(new Block(-2, 0, 102, Main.HEIGHT, this));

		// Right wall
		add(new Block(getWidth(), 0, 100, Main.HEIGHT, this));
		
		spawnSilhouettes();
	}

	public void relocateCharacters() {
		GameObject thomas = Role.THOMAS.getGameObject();
		thomas.setX(100);
		thomas.setY(150);
		thomas.setGameX(thomas.getX());
		thomas.setGameY(thomas.getY());
	}

	private void floor() {
		// X = 0 to 1000
		addFloor(new Block(getWidth(), Main.HEIGHT-100, Main.WIDTH, 100, this));
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
	}

	@Override
	public void spawnSilhouettes() {
		add(new Silhouette(getWidth()-200, getHieght(), Role.THOMAS));
	}
}
