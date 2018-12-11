package me.commonsenze.Platformer.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Util.Obstacle;

public class Block extends HitBox implements Obstacle {

	private Level level;
	private boolean ceiling;
	
	public Block(int x, int y, int width, int height, Level level, boolean ceiling) {
		this(new Rectangle(x,y,width,height), level, ceiling);
	}
	
	public Block(Rectangle character, Level level, boolean ceiling) {
		super(character);
		this.level = level;
		this.ceiling = ceiling;
	}
	
	@Override
	public void tick() {
		rebuild();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(getCharacter().x, getCharacter().y, getCharacter().width, getCharacter().height);
	}
	
	public Level getLevel() {
		return level;
	}
	
	public boolean isCeiling() {
		return ceiling;
	}

	@Override
	public void gravity() {}

	@Override
	public void walk() {}
	
	@Override
	public Rectangle getObsticale() {
		return getCharacter();
	}
}
