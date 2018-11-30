package me.commonsenze.Platformer.Levels.Util;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.DevLevel;
import me.commonsenze.Platformer.Levels.Levels.LevelOne;
import me.commonsenze.Platformer.Levels.Levels.LevelTwo;

public enum Levels {

	ONE(new LevelOne(), 1),
	TWO(new LevelTwo(), 2),
	DEV(new DevLevel(), 0);
	
	private Level level;
	private int pointValue;
	
	private Levels(Level level, int pointValue) {
		this.level = level;
		this.pointValue = pointValue;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public int getPointValue() {
		return pointValue;
	}
	
	public static void nextLevel() {
		for (int i = 0; i < Levels.values().length; i++) {
			if (Levels.values()[i] == Main.LEVEL) {
				int k = i+1;
				if (k == Levels.values().length)k = 0;
				LevelManager.setLevel(Levels.values()[k]);
				break;
			}
		}
	}
	
	public static void prevLevel() {
		for (int i = 0; i < Levels.values().length; i++) {
			if (Levels.values()[i] == Main.LEVEL){
				int k = i-1;
				if (k == -1)k = Levels.values().length-1;
				LevelManager.setLevel(Levels.values()[k]);
				break;
			}
		}
	}
	
	public static Levels parseLevel(Level level) {
		for (Levels levels : Levels.values()) {
			if (levels.getLevel().equals(level))return levels;
		}
		return null;
	}
}
