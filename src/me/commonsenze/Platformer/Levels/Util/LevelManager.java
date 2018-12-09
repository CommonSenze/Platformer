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

	private static ArrayList<Silhouette> finishedSlots = new ArrayList<>();

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
	}

	@Override
	public void tick() {
		ArrayList<Silhouette> silhouettes = new ArrayList<>(getFinishSlots());
		for (Silhouette silhouette : silhouettes) {
			silhouette.tick();
		}
		Main.LEVEL.getLevel().tick();
	}

	@Override
	public void render(Graphics g) {
		ArrayList<Silhouette> silhouettes = new ArrayList<>(getFinishSlots());
		for (Silhouette silhouette : silhouettes) {
			silhouette.render(g);
		}
		Main.LEVEL.getLevel().render(g);
	}

	public void addSilhouette(Silhouette silhouette) {
		finishedSlots.add(silhouette);
	}

	public void removeSilhouette(Silhouette silhouette) {
		finishedSlots.remove(silhouette);
	}

	public static ArrayList<Silhouette> getFinishSlots() {
		return finishedSlots;
	}
}
