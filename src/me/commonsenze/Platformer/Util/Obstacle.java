package me.commonsenze.Platformer.Util;

import java.awt.Rectangle;

import me.commonsenze.Platformer.Levels.Util.Level;

public interface Obstacle extends Renderable {

	Level getLevel();
	
	Rectangle getObsticale();
}
