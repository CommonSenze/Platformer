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


public class Laura extends GameObject implements Renderable {


	public Laura() {
		super(Role.LAURA, new Rectangle(70, 15), new Color(200, 150, 150)); // Laura's length == 50, height == 20
		setX(Main.WIDTH/3); // Spawn Laura on the left third
		setY(Main.HEIGHT/2); // Spawn Laura above the floor
		rebuild();
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		rebuild();
		g.setColor(getColor());
		g.fillRect(getCharacter().x, getCharacter().y, getCharacter().width, getCharacter().height);
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

	// James' move speed up by 10 on the y-axis (jumps down)
	public void jump() {
		setVertical(6);
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
					setX(prevX);
				}
			}
		}

		// Realigns getCharacter()'s x and y to GameObject's x and y.
		rebuild();
	}

}
