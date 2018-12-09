package me.commonsenze.Platformer.Objects.Characters;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import me.commonsenze.Platformer.Handler;
import me.commonsenze.Platformer.Main;
import me.commonsenze.Platformer.Objects.HitBox;
import me.commonsenze.Platformer.Util.GameObject;
import me.commonsenze.Platformer.Util.Renderable;
import me.commonsenze.Platformer.Util.Enums.Classifier;

public class Claire extends GameObject implements Renderable {

	// Constructor for Claire the character
	public Claire() {
		// This super constructor sets the Role of the character to individualize the class, set the rectangle of the character
		// so we can render his body and hit box, and set the color of what we want the rectangle to be.
		super(Classifier.CLAIRE, new Rectangle(60, 60), Color.CYAN);
		
		// While in the constructor, we set the X and Y of the character as the starting positions
		// Keep in mind, positive X is from left to right, positive Y is from TOP TO BOTTOM
		setX(Main.WIDTH/2+70);
		setY(100);
		
		this.setGameX(getX()%Main.WIDTH);
		this.setGameY(getY()%Main.HEIGHT);
		
		jump = 5;
		
		// After we set the location, we rebuild the character
		rebuild();
	}

	@Override
	public void gravity() {
		// If James is not in the wall, jump is true and moves him to the floor by .5
		setVertical(getVertical()-getGravity());
		setOnFloor(false);

		int prevY = getIntY();

		// Moves James by (Y - Vertical)
		setY(getY()-getVertical());

		// Realigns getCharacter()'s x and y to GameObject's x and y.
		rebuild();
		
		for (HitBox hitBox : Handler.getHitBoxes()) {
			if (hitBox == this)continue;
			if (!withInXBounds(hitBox))continue;
			// If James is in the wall, he will move out until he isn't in the wall anymore
			if (hitBox.insideBlock(getCharacter())) {
				if (prevY >= hitBox.getIntY()) {
					setVertical(0);
					setY(hitBox.getIntY()+hitBox.getHeight());
				} else if (prevY+getHeight() <= hitBox.getIntY()) {
					setVertical(0);
					setY(hitBox.getIntY()-getHeight());
					setOnFloor(true);
					setJumping(false);
				} else {
					setY(prevY);
				}
			}
		}

		// Realigns getCharacter()'s x and y to GameObject's x and y.
		rebuild();
	}

	@Override
	public void tick() {
		if (Main.FINISHED)return;
		gravity();
		walk();
	}

	@Override
	public void render(Graphics g) {
		// Claire' color set to green and creates his rectangle
		if (isFinished())g.setColor(Color.WHITE);
		else g.setColor(getColor());
		g.fillRect(getIntX(), getIntY(), getCharacter().width, getCharacter().height);
	}
	
	public int getFeet() {
		return getIntY()+getHeight();
	}

	// Claire' move speed up by 10 on the y-axis (jumps down)
	public void jump() {
		setVertical(jump);
	}
}
