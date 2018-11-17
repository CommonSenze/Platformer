package me.commonsenze.Platformer.Util;

import java.awt.Color;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Objects.HitBox;
import me.commonsenze.Platformer.Util.Enums.Role;

public abstract class GameObject extends HitBox {

	private Role role;
	private Color color;
	private float vertical, velocity = 0;
	private boolean jumping, onFloor;
	protected int jump;

	public GameObject(Role role, Rectangle character, Color color) {
		super(character);
		this.color = color;
		this.role = role;
	}

	public String getName() {
		return role.name().substring(0, 1) + role.name().substring(1, role.name().length()).toLowerCase();
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
	
	public void setJump(int jump) {
		this.jump = jump;
	}
	
	public int getJump() {
		return jump;
	}

	@Override
	public void walk() {
		int prevX = getIntX();

		// Extended level movement
		if (getX()+getWidth() >= 900&&getVelocity() >0&&getRole() == GameData.getSelectedCharacter()) {
			Main.CAMERA.setXSpeed(5);
		} else if (getX() < 100&&getVelocity()<0&&getRole() == GameData.getSelectedCharacter()) {
			Main.CAMERA.setXSpeed(-5);
		} else {
			// Renders James' x-axis movement to the JFrame
			setX(getX()+getVelocity());
			if(getRole() == GameData.getSelectedCharacter())Main.CAMERA.setXSpeed(0);
		}

		rebuild();

		boolean collide = false;

		for (HitBox hitBox : Handler.getHitBoxes()) {
			if (hitBox == this)continue;
			// If James is in the wall, he will move out until he isn't in the wall anymore
			if (hitBox.insideBlock(getCharacter())) {
				if (Main.CAMERA.getXSpeed() != 0) {
					if (prevX+5 >= hitBox.getIntX()+hitBox.getWidth()) {
						setX(hitBox.getX()+hitBox.getWidth());
						Main.CAMERA.setXSpeed(0);
						collide = true;
					} else if (prevX+getWidth()-5 <= hitBox.getIntX()) {
						setX(hitBox.getX()-getWidth());
						Main.CAMERA.setXSpeed(0);
						collide = true;
					}
				} else {
					if (prevX >= hitBox.getIntX()+hitBox.getWidth()) {
						setX(hitBox.getX()+hitBox.getWidth());
						collide = true;
					} else if (prevX+getWidth() <= hitBox.getIntX()) {
						setX(hitBox.getX()-getWidth());
						collide = true;
					}
				}
			}
		}

		if (getRole() == GameData.getSelectedCharacter()) {

			for (HitBox hitBox : Handler.getHitBoxes()) {
				if (!(hitBox instanceof GameObject)||hitBox == this)continue;
				if (prevX <= hitBox.getX()+hitBox.getWidth()&&prevX+getWidth() >= hitBox.getX()) {
					GameObject object = (GameObject) hitBox;
					if (object.getFeet() == getIntY()&&onFloor())
						if (!collide)object.setVelocity(getVelocity());
				}
			}
		}

		// Realigns getCharacter()'s x and y to GameObject's x and y.
		rebuild();
	}

	public abstract int getFeet();

	public abstract void jump();
}
