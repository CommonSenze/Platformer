package me.commonsenze.Platformer.Util;

import java.awt.Rectangle;

import me.commonsenze.Platformer.Objects.Walls;
import me.commonsenze.Platformer.Util.Enums.Role;

public abstract class GameObject {

	private Role role;
	private int x, y, upY, velocity;
	private Rectangle character;
	private boolean jumping;
	
	public GameObject(Role role, Rectangle character) {
		this.role = role;
		this.character = character;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getUpY() {
		return upY;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setUpY(int upY) {
		this.upY = upY;
	}

	public int getVelocity() {
		return velocity;
	}

	public void setVelocity(int velocity) {
		this.velocity = velocity;
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

	public abstract void gravity(Walls walls);
}
