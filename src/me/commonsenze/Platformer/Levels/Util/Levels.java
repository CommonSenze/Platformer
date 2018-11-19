package me.commonsenze.Platformer.Levels.Util;

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
}
