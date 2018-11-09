package me.commonsenze.Platformer.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import me.commonsenze.Platformer.Util.Renderable;

public class Block extends HitBox implements Renderable {
	
	public Block(int x, int y, int width, int height) {
		this(new Rectangle(x,y,width,height));
	}
	
	public Block(Rectangle character) {
		super(character);
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

	@Override
	public void gravity(ArrayList<Block> blocks) {}

	@Override
	public void walk(ArrayList<Block> blocks) {}
}
