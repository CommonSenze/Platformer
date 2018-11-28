package me.commonsenze.Platformer.Levels.Util;

import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Levels.DevLevel;
import me.commonsenze.Platformer.Levels.Levels.LevelOne;
import me.commonsenze.Platformer.Levels.Levels.LevelTwo;

public enum Levels {

	ONE(new LevelOne()),
	TWO(new LevelTwo()),
	DEV(new DevLevel());
	
	private Level level;
	
	private Levels(Level level) {
		this.level = level;
	}
	
	public Level getLevel() {
		return level;
	}
	
	public static Levels nextLevel() {
		for (int i = 0; i < Levels.values().length; i++) {
			if (Levels.values()[i] == Main.LEVEL)return Levels.values()[i+1];
		}
		return null;
	}
	
	public static Levels prevLevel() {
		for (int i = 0; i < Levels.values().length; i++) {
			if (Levels.values()[i] == Main.LEVEL)return Levels.values()[i-1];
		}
		return null;
	}
}
