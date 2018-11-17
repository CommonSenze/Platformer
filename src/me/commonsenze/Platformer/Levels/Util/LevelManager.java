package me.commonsenze.Platformer.Levels.Util;

import java.awt.Graphics;
import java.util.ArrayList;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Util.Obstacles;
import me.commonsenze.Platformer.Util.Renderable;

public class LevelManager implements Renderable {

	private static Levels currentLevel;
	private Handler handler;
	
	public LevelManager(Handler handler) {
		this.handler = handler;
		start();
		handler.addRenderable(this);
	}
	
	public void start() {
		currentLevel = Levels.ONE;
		for (Obstacles obs : currentLevel.getLevel().getObstacles())
			if (obs instanceof Block) {
				Handler.addHitBox((Block)obs);
			}
	}
	
	public static ArrayList<Obstacles> getObstacles(){
		return currentLevel.getLevel().getObstacles();
	}
	
	public Levels getLevel() {
		return currentLevel;
	}
	
	public void changeLevel(Levels currentLevel) {
		handler.removeRenderable(LevelManager.currentLevel.getLevel());
		LevelManager.currentLevel = currentLevel;
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
