package me.commonsenze.Platformer.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Levels.Util.Level;
import me.commonsenze.Platformer.Util.Obstacle;

public class Block extends HitBox implements Obstacle {

	private Level level;
	
	public Block(int x, int y, int width, int height, Level level) {
		this(new Rectangle(x,y,width,height), level);
	}
	
	public Block(Rectangle character, Level level) {
		super(character);
		this.level = level;
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

	@Override
	public void gravity() {}

	@Override
	public void walk() {}
	
	@Override
	public Rectangle getObsticale() {
		return getCharacter();
	}
}
