package me.commonsenze.Platformer.Objects.Characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Objects.HitBox;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Renderable;
import me.commonsenze.Platformer.Util.Enums.Role;


public class Sarah extends GameObject implements Renderable {


	public Sarah() {
		super(Role.SARAH, new Rectangle(15, 20), new Color(128, 0, 128)); // Sarah's length == 50, height == 20
		setX(Main.WIDTH/3-50); // Spawn Sarah on the left third
		setY(Main.HEIGHT/2); // Spawn Sarah above the floor
		rebuild();
	}

	@Override
	public void tick() {
		gravity();
		walk();
	}

	@Override
	public void render(Graphics g) {
		// Claire' color set to green and creates his rectangle
		g.setColor(getColor());
		g.fillRect(getIntX(), getIntY(), getCharacter().width, getCharacter().height);
	}
	@Override
	public void gravity() {
		// If James is not in the wall, jump is true and moves him to the floor by .5
		setVertical(getVertical()-0.5F);
		setOnFloor(false);

		int prevY = getIntY();

		// Moves James by (Y - Vertical)
		setY(getY()-getVertical());

		// Realigns getCharacter()'s x and y to GameObject's x and y.
		rebuild();

		for (HitBox hitBox : Handler.getHitBoxes()) {
			if (hitBox == this)continue;
			// If James is in the wall, he will move out until he isn't in the wall anymore
			if (hitBox.insideBlock(getCharacter())) {
				if (prevY+getHeight() <= hitBox.getIntY()) {
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
	
	public int getFeet() {
		return getIntY()+getHeight();
	}

	// James' move speed up by 10 on the y-axis (jumps down)
	public void jump() {
		setVertical(6);
	}

}
