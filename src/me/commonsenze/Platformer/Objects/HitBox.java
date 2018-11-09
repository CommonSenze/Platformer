package me.commonsenze.Platformer.Objects;

import java.awt.Rectangle;
import java.util.ArrayList;

public abstract class HitBox {

	private float x, y;
	private Rectangle character;
	
	public HitBox(Rectangle character) {
		this.character = character;
		this.y = character.y;
		this.x = character.x;
	}
	
	public void setX(float x) {
		this.x = x;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public int getHeight() {
		return character.height;
	}
	
	public int getWidth() {
		return character.width;
	}

	public Rectangle getCharacter() {
		return character;
	}
	
	public boolean insideBlock(Rectangle rectangle) {
		return getCharacter().intersects(rectangle);
	}
	
	public void rebuild() {
		getCharacter().setLocation((int)getX(), (int)getY());
	}
	
	public abstract void gravity(ArrayList<Block> blocks);
	
	public abstract void walk(ArrayList<Block> blocks);
}
