package me.commonsenze.Platformer.Levels.Util;

import java.awt.Graphics;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Levels.LevelOne;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Objects.HitBox;
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
		level = new LevelOne(handler);
		for (Block block : level.getBlocks())
			Handler.addHitBox(block);
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
	}

	@Override
	public void render(Graphics g) {
		level.render(g);
	}
}
