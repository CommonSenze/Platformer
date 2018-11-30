package me.commonsenze.Platformer.Levels.Util;

import java.awt.Graphics;
import java.util.ArrayList;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Objects.Block;
import me.commonsenze.Platformer.Objects.Silhouette;
import me.commonsenze.Platformer.Util.Obstacle;
import me.commonsenze.Platformer.Util.Renderable;
import me.commonsenze.Platformer.Util.Enums.Role;

public class LevelManager implements Renderable {

	private static ArrayList<Silhouette> finishedSlots = new ArrayList<>();
	
	public LevelManager() {
		start();
		Handler.addRenderable(this);
	}
	
	public void start() {
		Main.LEVEL = Levels.ONE;
		for (Obstacle obs : Main.LEVEL.getLevel().getObstacles())
			if (obs instanceof Block) {
				Handler.addHitBox((Block)obs);
			}
		reloadFinishedSlots(Main.LEVEL.getLevel().getFinishedXPlace(), Main.LEVEL.getLevel().getFinishedYPlace());
	}
	
	public static ArrayList<Obstacle> getObstacles(){
		return Main.LEVEL.getLevel().getObstacles();
	}
	
	public Levels getLevel() {
		return Main.LEVEL;
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
		reloadFinishedSlots(Main.LEVEL.getLevel().getFinishedXPlace(), Main.LEVEL.getLevel().getFinishedYPlace());
	}

	@Override
	public void tick() {
		for (Silhouette silhouette : getFinishSlots()) {
			silhouette.changeX(Main.CAMERA.getXSpeed());
			silhouette.changeY(Main.CAMERA.getYSpeed());
			silhouette.tick();
		}
		Main.LEVEL.getLevel().tick();
	}

	@Override
	public void render(Graphics g) {
		for (Silhouette silhouette : getFinishSlots()) {
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
	
	public ArrayList<Silhouette> getFinishSlots() {
		return finishedSlots;
	}
	
	public static void reloadFinishedSlots(int x, int y) {
		finishedSlots.clear();
		for (Role role : Role.values()) {
			if (role.isUnlocked(Main.LEVEL.getPointValue())) {
				finishedSlots.add(new Silhouette(x, y-role.getGameObject().getHeight(),role));
				x+=role.getGameObject().getWidth()+10;
			}
		}
	}
}
