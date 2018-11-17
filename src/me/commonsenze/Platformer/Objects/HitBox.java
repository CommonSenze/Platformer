package me.commonsenze.Platformer.Objects;

import java.awt.Rectangle;

public abstract class HitBox {

	private float x, y, gravity;
	private Rectangle character;
	
	public HitBox(Rectangle character) {
		this.character = character;
		this.y = character.y;
		this.x = character.x;
		this.gravity = 0.5F;
	}
	
	/**
	 * This method sets the x of the object, independent of the character that is rendered.
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * This method returns the float x of the object, separate of the characters x.
	 */
	public float getX() {
		return x;
	}
	
	/**
	 * This method returns the float y of the object, separate of the characters y.
	 */
	public float getY() {
		return y;
	}
	
	/**
	 * This method returns the integer x of the object, separate of the characters x.
	 */
	public int getIntX() {
		return (int) x;
	}

	/**
	 * This method returns the integer y of the object, separate of the characters y.
	 */
	public int getIntY() {
		return (int) y;
	}

	/**
	 * This method sets the y of the object, independent of the character that is rendered.
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/**
	 * This method returns the gravity value of the object.
	 */
	public float getGravity() {
		return gravity;
	}

	/**
	 * This method sets the gravity of the object.
	 */
	public void setGravity(float gravity) {
		this.gravity = gravity;
	}
	
	/**
	 * This method returns the character's height
	 */
	public int getHeight() {
		return character.height;
	}
	
	/**
	 * This method returns the character's width
	 */
	public int getWidth() {
		return character.width;
	}

	/**
	 * This method returns the rectangle of the character that is rendered
	 */
	public Rectangle getCharacter() {
		return character;
	}
	
	/**
	 * This method checks to see if the rectangle of this character
	 * is inside the rectangle of another object.
	 */
	public boolean insideBlock(Rectangle rectangle) {
		return getCharacter().intersects(rectangle);
	}
	
	/**
	 * This method is used to realign the rectangles X and Y with the actual X and Y of the class.
	 * We have both X and Y's separate so we can do a check to see if the next location of the X and Y of the class
	 * Falls into a hitbox shown in the gravity and walk method. If the X or Y is inside another hitbox,
	 * we pull the character back and realign the character after.
	 */
	public void rebuild() {
		getCharacter().setLocation(getIntX(), getIntY());
	}
	
	/**
	 * Every tick, this method is activated to act upon the object as gravity. Checking if the character isn't inside any hitboxes.
	 * If it is, it will redo the rebuild of the object to prevent the object from looking like it's inside a block.
	 */
	public abstract void gravity();
	
	/**
	 * This method is called every tick, like gravity, and has the same implementations like gravity, except it is for the
	 * x plane and for moving left and right with the keybinds, unlike gravity affecting the y plane and being for jumping.
	 */
	public abstract void walk();
}
