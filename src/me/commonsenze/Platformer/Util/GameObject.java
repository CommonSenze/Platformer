package me.commonsenze.Platformer.Util;

import java.awt.Rectangle;

import me.commonsenze.Platformer.Objects.Walls;
import me.commonsenze.Platformer.Util.Enums.Role;

public abstract class GameObject {

	private Role role;
	private float x, y, upY, velocity = 0;
	private Rectangle character;
	private boolean jumping, onFloor;

	public GameObject(Role role, Rectangle character) {
		this.role = role;
		this.character = character;
	}

	public Role getRole() {
		return role;
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

	public float getUpY() {
		return upY;
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setUpY(float upY) {
		this.upY = upY;
	}

	public float getVelocity() {
		return velocity;
	}
	
	public void setVelocity(float velocity) {
		this.velocity = velocity;
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

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public boolean onFloor() {
		return onFloor;
	}

	public void setOnFloor(boolean onFloor) {
		this.onFloor = onFloor;
	}

	public void rebuild() {
		getCharacter().setLocation((int)x, (int)y);
	}

	public abstract void gravity(Walls walls);
	public abstract void jump();
}
