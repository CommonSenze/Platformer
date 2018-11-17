package me.commonsenze.Platformer.Objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Util.Renderable;

public class Door extends HitBox implements Renderable {

	private boolean unlocked, openWidth;
	
	public Door(int x, int y, int width, int height) {
		this(new Rectangle(x,y,width,height));
	}
	
	public Door(Rectangle character) {
		super(character);
		this.openWidth = getCharacter().width > getCharacter().height;
	}
	
	@Override
	public void tick() {
		if (isUnlocked()) {
			open();
		}
		rebuild();
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(getCharacter().x, getCharacter().y, getCharacter().width, getCharacter().height);
	}
	
	private void open() {
		if (openWidth&&getCharacter().width != 0)
		getCharacter().setBounds(getCharacter().x, getCharacter().y, getCharacter().width-1, getCharacter().height);
		else if (getCharacter().height!= 0)getCharacter().setBounds(getCharacter().x, getCharacter().y, getCharacter().width, getCharacter().height-1);
	}
	
	public void unlock() {
		this.unlocked = true;
	}
	
	public boolean isUnlocked() {
		return unlocked;
	}

	@Override
	public void gravity() {}

	@Override
	public void walk() {}
}
