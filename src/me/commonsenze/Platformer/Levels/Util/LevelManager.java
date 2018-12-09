package me.commonsenze.Platformer.Levels.Util;

import java.awt.Graphics;
import java.util.ArrayList;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Objects.Silhouette;
import me.commonsenze.Platformer.Util.Obstacle;
import me.commonsenze.Platformer.Util.Renderable;

public class LevelManager implements Renderable {

	public LevelManager() {
		start();
		Handler.addRenderable(this);
	}

	public void start() {
		for (Obstacle obs : Main.LEVEL.getLevel().getObstacles())
			if (obs instanceof Block) {
				Handler.addHitBox((Block)obs);
			}
	}

	public static ArrayList<Obstacle> getObstacles(){
		return Main.LEVEL.getLevel().getObstacles();
	}
	
	public static Obstacle getObstacleAt(int x, int y) {
		for (Obstacle obs : Main.LEVEL.getLevel().getObstacles())
			if (obs.getObsticale().contains(x,y))return obs;
		return null;
	}

	public static void setLevel(Levels currentLevel) {
		for (Obstacle obs : Main.LEVEL.getLevel().getObstacles())
			if (obs instanceof Block) {
				Handler.removeHitBox((Block)obs);
			}
		Main.LEVEL = currentLevel;
		for (Obstacle obs : Main.LEVEL.getLevel().getObstacles())
			if (obs instanceof Block) {
				Handler.addHitBox((Block)obs);
			}
		Main.LEVEL.getLevel().relocateCharacters();
	}

	@Override
	public void tick() {
		ArrayList<Silhouette> silhouettes = new ArrayList<>(Main.LEVEL.getLevel().getSilhouettes());
		for (Silhouette silhouette : silhouettes) {
			silhouette.tick();
		}
		Main.LEVEL.getLevel().tick();
	}

	@Override
	public void render(Graphics g) {
		ArrayList<Silhouette> silhouettes = new ArrayList<>(Main.LEVEL.getLevel().getSilhouettes());
		for (Silhouette silhouette : silhouettes) {
			silhouette.render(g);
		}
		Main.LEVEL.getLevel().render(g);
	}
}
