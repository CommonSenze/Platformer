package me.commonsenze.Platformer.Objects.Characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Objects.HitBox;
import me.commonsenze.Platformer.Util.GameData;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Renderable;
import me.commonsenze.Platformer.Util.Enums.Role;

public class Thomas extends GameObject implements Renderable {

	// Constructor for James the character
	public Thomas() {
		super(Role.THOMAS, new Rectangle(20, 30), Color.RED);
		setX(Main.WIDTH/2);
		setY(120);
		rebuild();
	}

	@Override
	public void gravity() {
		// If James is not in the wall, jump is true and moves him to the floor by .5
		setVertical(getVertical()-0.5F);
		setOnFloor(false);

		float prevY = getY();

		// Moves James by (Y - Vertical)
		setY(getY()-getVertical());

		// Realigns getCharacter()'s x and y to GameObject's x and y.
		rebuild();

		for (HitBox hitBox : Handler.getHitBoxes()) {
			if (hitBox == this)continue;
			// If James is in the wall, he will move out until he isn't in the wall anymore
			if (hitBox.insideBlock(getCharacter())) {
				if (prevY+getHeight() <= hitBox.getY()) {
					setVertical(-1);
					setY(hitBox.getY()-getHeight());
					setOnFloor(true);
					setJumping(false);
				} else if (prevY > hitBox.getIntY()) {
					setVertical(-1);
					setY(hitBox.getIntY()+hitBox.getHeight());
				}
			}
		}

		// Realigns getCharacter()'s x and y to GameObject's x and y.
		rebuild();
	}

	@Override
	public void tick() {

	}

	@Override
	public void render(Graphics g) {
		// Claire' color set to green and creates his rectangle
		g.setColor(getColor());
		g.fillRect(getIntX(), getIntY(), getCharacter().width, getCharacter().height);
	}

	// James' move speed up by 10 on the y-axis (jumps down)
	public void jump() {
		setVertical(10);
	}

	@Override
	public void walk() {
		if (getRole() != GameData.getSelectedCharacter())return;
		
		float prevX = getX();
		
		// Extended level movement
		if (getX()+getWidth() >= 900&&getVelocity() >0) {
			Main.CAMERA.setSpeed(5);
		} else if (getX() < 100&&getVelocity()<0) {
			Main.CAMERA.setSpeed(-5);
		} else {
			// Renders James' x-axis movement to the JFrame
			setX(getX()+getVelocity());
			Main.CAMERA.setSpeed(0);
		}
		
		rebuild();

		for (HitBox hitBox : Handler.getHitBoxes()) {
			if (hitBox == this)continue;
			// If James is in the wall, he will move out until he isn't in the wall anymore
			if (hitBox.insideBlock(getCharacter())) {
				if (prevX > hitBox.getX()) {
					setX(hitBox.getX()+hitBox.getWidth());
				} else if (prevX+getWidth() <= hitBox.getX()) {
					setX(hitBox.getX()-getWidth());
				}
			}
		}

		// Realigns getCharacter()'s x and y to GameObject's x and y.
		rebuild();
	}
}
