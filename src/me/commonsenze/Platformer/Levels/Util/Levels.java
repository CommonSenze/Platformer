package me.commonsenze.Platformer.Levels.Util;

import me.commonsenze.Platformer.Levels.DevLevel;
import me.commonsenze.Platformer.Levels.Levels.LevelOne;

public enum Levels {

	DEV(new DevLevel()),
	ONE(new LevelOne());
	
	private Level level;
	
	private Levels(Level level) {
		this.level = level;
	}
	
	public Level getLevel() {
		return level;
	}
}
