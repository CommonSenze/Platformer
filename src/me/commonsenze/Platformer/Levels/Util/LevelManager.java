package me.commonsenze.Platformer.Levels.Util;

import java.awt.Graphics;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Levels.LevelOne;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Renderable;

public class LevelManager implements Renderable {

	private Level level;
	private Handler handler;
	
	public LevelManager(Handler handler) {
		this.handler = handler;
		start();
		handler.addRenderable(this);
	}
	
	public void start() {
		level = new LevelOne();
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void changeLevel(Level level) {
		handler.removeRenderable(this.level);
		this.level = level;
		handler.addRenderable(level);
	}

	@Override
	public void tick() {
		level.tick();
		for (GameObject object : handler.getObjects()) {
			object.gravity(level.getWalls());
		}
	}

	@Override
	public void render(Graphics g) {
		level.render(g);
	}
}
