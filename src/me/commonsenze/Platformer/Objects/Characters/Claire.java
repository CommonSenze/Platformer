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

public class Claire extends GameObject implements Renderable {

	// Constructor for Claire the character
	public Claire() {
		// This super constructor sets the Role of the character to individualize the class, set the rectangle of the character
		// so we can render his body and hit box, and set the color of what we want the rectangle to be.
		super(Role.CLAIRE, new Rectangle(60, 60), Color.CYAN);
		
		// While in the constructor, we set the X and Y of the character as the starting positions
		// Keep in mind, positive X is from left to right, positive Y is from TOP TO BOTTOM
		setX(Main.WIDTH/2+70);
		setY(100);
		
		// After we set the location, we rebuild the character
		rebuild();
	}

	@Override
	public void gravity() {
		// If James is not in the wall, jump is true and moves him to the floor by .5
		setVertical(getVertical()-0.5F);
		setOnFloor(false);

		float prevY = getY();

		// Moves James by (Y - upY)
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
				} else if (prevY+getHeight() > hitBox.getIntY()) {
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
		g.fillRect(getCharacter().x, getCharacter().y, getCharacter().width, getCharacter().height);
	}

	// Claire' move speed up by 10 on the y-axis (jumps down)
	public void jump() {
		setVertical(5);
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
			// Renders Claire' x-axis movement to the JFramea
			setX(getX()+getVelocity());
			Main.CAMERA.setSpeed(0);
		}
		
		rebuild();

		for (HitBox hitBox : Handler.getHitBoxes()) {
			if (hitBox == this)continue;
			// If Claire is in the wall, he will move out until he isn't in the wall anymore
			if (hitBox.insideBlock(getCharacter())) {
				if (prevX > hitBox.getX()) {
					setX(hitBox.getX()+hitBox.getWidth());
				} else if (prevX+getWidth() <= hitBox.getX()) {
					setX(prevX);
				}
			}
		}

		// Realigns getCharacter()'s x and y to GameObject's x and y.
		rebuild();
	}
}
