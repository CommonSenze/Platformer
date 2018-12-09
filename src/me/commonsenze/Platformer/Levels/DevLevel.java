package me.commonsenze.Platformer.Levels;

import java.awt.Graphics;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Obstacle;
import me.commonsenze.Platformer.Util.Enums.Role;

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
	
	public void relocateCharacters() {
		GameObject thomas = Role.THOMAS.getGameObject();
		thomas.setX(110);
		thomas.setY(150);
		thomas.setGameX(thomas.getX());
		thomas.setGameY(thomas.getY());
		GameObject chris = Role.CHRIS.getGameObject();
		chris.setX(140);
		chris.setY(150);
		chris.setGameX(chris.getX());
		chris.setGameY(chris.getY());
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
		// TODO Auto-generated method stub
		
	}
}
