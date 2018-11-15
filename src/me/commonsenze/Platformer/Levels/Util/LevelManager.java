package me.commonsenze.Platformer.Levels.Util;

import java.awt.Graphics;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Util.Renderable;

public class LevelManager implements Renderable {

	private Levels currentLevel;
	private Handler handler;
	
	public LevelManager(Handler handler) {
		this.handler = handler;
		start();
		handler.addRenderable(this);
	}
	
	public void start() {
		currentLevel = Levels.ONE;
		for (Block block : currentLevel.getLevel().getBlocks())
			Handler.addHitBox(block);
	}
	
	public Levels getLevel() {
		return currentLevel;
	}
	
	public void changeLevel(Levels currentLevel) {
		handler.removeRenderable(this.currentLevel.getLevel());
		this.currentLevel = currentLevel;
		handler.addRenderable(currentLevel.getLevel());
	}

	@Override
	public void tick() {
		currentLevel.getLevel().tick();
	}

	@Override
	public void render(Graphics g) {
		currentLevel.getLevel().render(g);
	}
}
