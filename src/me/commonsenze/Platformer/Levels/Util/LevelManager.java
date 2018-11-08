package me.commonsenze.Platformer.Levels.Util;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Levels.LevelOne;

public class LevelManager {

	private Level level;
	private Handler handler;
	
	public LevelManager(Handler handler) {
		this.handler = handler;
		start();
	}
	
	public void start() {
		level = new LevelOne();
		handler.addRenderable(level);
	}
	
	public Level getLevel() {
		return level;
	}
	
	public void changeLevel(Level level) {
		handler.removeRenderable(this.level);
		this.level = level;
		handler.addRenderable(level);
	}
}
