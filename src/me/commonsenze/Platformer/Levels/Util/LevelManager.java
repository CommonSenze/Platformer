package me.commonsenze.Platformer.Levels.Util;

import java.awt.Graphics;
import java.util.ArrayList;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Util.Obstacles;
import me.commonsenze.Platformer.Util.Renderable;

public class LevelManager implements Renderable {

	private Handler handler;
	
	public LevelManager(Handler handler) {
		this.handler = handler;
		start();
		handler.addRenderable(this);
	}
	
	public void start() {
		Main.LEVEL = Levels.ONE;
		for (Obstacles obs : Main.LEVEL.getLevel().getObstacles())
			if (obs instanceof Block) {
				Handler.addHitBox((Block)obs);
			}
	}
	
	public static ArrayList<Obstacles> getObstacles(){
		return Main.LEVEL.getLevel().getObstacles();
	}
	
	public Levels getLevel() {
		return Main.LEVEL;
	}
	
	public void changeLevel(Levels currentLevel) {
		handler.removeRenderable(Main.LEVEL.getLevel());
		Main.LEVEL = currentLevel;
		handler.addRenderable(currentLevel.getLevel());
	}

	@Override
	public void tick() {
		Main.LEVEL.getLevel().tick();
	}

	@Override
	public void render(Graphics g) {
		Main.LEVEL.getLevel().render(g);
	}
}
