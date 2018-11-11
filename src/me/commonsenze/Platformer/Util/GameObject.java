package me.commonsenze.Platformer.Util;

import java.awt.Color;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Objects.HitBox;
import me.commonsenze.Platformer.Util.Enums.Role;

public abstract class GameObject extends HitBox {

	private Role role;
	private Color color;
	private float vertical, velocity = 0;
	private boolean jumping, onFloor;

	public GameObject(Role role, Rectangle character, Color color) {
		super(character);
		this.color = color;
		this.role = role;
	}

	public Role getRole() {
		return role;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public boolean onFloor() {
		return onFloor;
	}

	public void setOnFloor(boolean onFloor) {
		this.onFloor = onFloor;
	}
	
	public float getVertical() {
		return vertical;
	}
	
	public void setVertical(float vertical) {
		this.vertical = vertical;
	}

	public float getVelocity() {
		return velocity;
	}
	
	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	public abstract void jump();
}
